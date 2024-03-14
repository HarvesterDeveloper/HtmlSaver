package com.harvestdev.htmlsaver;

public final class Application {
	private static AppFrame appFrame;
	private static AboutFrame aboutFrame;

	public static void main(String[] arguments) {
		appFrame = new AppFrame();
	}

	public static void openAboutWindow() {
		if (aboutFrame == null || !aboutFrame.isDisplayable()) {
			aboutFrame = new AboutFrame();
		}
	}

}
