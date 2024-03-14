package com.harvestdev.htmlsaver;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;

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
		textField = new JTextField("https://hardev.info", 35);
		buttonVisit = new JButton("Visit");
		buttonSave = new JButton("Save");

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
