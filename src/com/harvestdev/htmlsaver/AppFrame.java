package com.harvestdev.htmlsaver;

import javax.swing.*;
import java.awt.*;

public final class AppFrame extends JFrame {
	private JPanel panelTop;
	private JLabel label;
	private JTextField textField;
	private JButton button;
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
		button = new JButton("Save");

		panelTop.add(label);
		panelTop.add(textField);
		panelTop.add(button);

		try {
			editorPane = new JEditorPane(textField.getText());
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, Consts.ADDRESS_UNAVAILABLE);
		}

		editorPane.setContentType("text/html");
		editorPane.setEditable(false);

		scrollPane = new JScrollPane(editorPane);

		getContentPane().add(panelTop, BorderLayout.NORTH);
		getContentPane().add(scrollPane);

		setSize(600, 400);
		setVisible(true);
	}

}
