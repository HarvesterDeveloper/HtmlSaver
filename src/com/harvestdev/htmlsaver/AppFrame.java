package com.harvestdev.htmlsaver;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class AppFrame extends JFrame {
	private JMenuBar menuBar;
	private JPanel panelTop;
	private JLabel label;
	private JTextField textField;
	private JButton buttonVisit;
	private JButton buttonSave;
	private JEditorPane editorPane;
	private JScrollPane scrollPane;

	public AppFrame()
	{
		super("HTML Saver");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		JMenuItem menuitemSave = new JMenuItem("Save");
		menuitemSave.addActionListener(e -> saveHtml());
		menuFile.add(menuitemSave);
		setJMenuBar(menuBar);

		panelTop = new JPanel();
		panelTop.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		label = new JLabel("Address:");
		textField = new JTextField("https://hardev.info", 30);
		buttonVisit = new JButton("Visit");
		buttonVisit.addActionListener(e -> {
			try {
				editorPane.setPage(textField.getText());
			} catch (Exception exc) {
				JOptionPane.showMessageDialog(AppFrame.this, Consts.ADDRESS_UNAVAILABLE);
			}
		});
		buttonSave = new JButton("Save");
		buttonSave.addActionListener(e -> saveHtml());

		panelTop.add(label);
		panelTop.add(textField);
		panelTop.add(buttonVisit);
		panelTop.add(buttonSave);

		try {
			editorPane = new JEditorPane(textField.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, Consts.ADDRESS_UNAVAILABLE);
		}

		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(hyperlinkEvent -> {
			if (hyperlinkEvent.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				try {
					editorPane.setPage(hyperlinkEvent.getURL());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(AppFrame.this, Consts.ADDRESS_UNAVAILABLE);
				}
			}
		});

		scrollPane = new JScrollPane(editorPane);

		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(scrollPane);

		setSize(600, 400);
		setVisible(true);
	}

	private void saveHtml() {
		JFileChooser fileChooser = new JFileChooser();

		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			try {
				BufferedReader in = new BufferedReader(
						new InputStreamReader(editorPane.getPage().openStream(), StandardCharsets.UTF_8));

				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(Files.newOutputStream(
								Paths.get(fileToSave.getAbsolutePath())), StandardCharsets.UTF_8));

				String curLine = in.readLine();
				while (curLine != null) {
					writer.write(curLine);
					curLine = in.readLine();
				}

				in.close();
				writer.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		}
	}

}
