package com.harvestdev.htmlsaver;

import javax.swing.*;
import java.awt.*;

public class AboutFrame extends JFrame {

	public AboutFrame() {
		setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(new Dimension(400, 150));
		setLocationRelativeTo(null);

		setLayout(new GridLayout());
		JLabel label = new JLabel(Consts.ABOUT);
		add(label);
		setVisible(true);
	}

}
