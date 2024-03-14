package com.harvestdev.htmlsaver;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;

public final class AppFrame extends JFrame {
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
		buttonSave.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();

			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				try {
					PrintWriter writer = new PrintWriter(fileToSave.getAbsolutePath(), "UTF-8");
					writer.print(editorPane.getText());
					writer.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(this, ex.getMessage());
				}
			}
		});

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

}
