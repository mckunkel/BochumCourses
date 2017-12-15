package eigthclass.GUI.simple;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

public class Constants {

	private Constants() {

	}

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int APP_WINDOW_SIZE_WIDTH = (int) screenSize.getWidth() - (int) screenSize.getWidth() / 3;
	public static final int APP_WINDOW_SIZE_HEIGHT = (int) screenSize.getHeight() - (int) screenSize.getHeight() / 3;

	public static final String APPNAME = "A Starting GUI";
	public static final String STATUSBAR_TXT = "Ready";
	public static final String RUN_TXT = "Click here to start the process";
	public static final String RUNBUTTON_TXT = "Run";

	public static final int MAXI = 1000;
	public static final int MAXJ = 1000;
	public static final int TEXT_FIELD_LENGTH = 5;
	public static final String JLABEL = "Max Value of J";
	public static final String ILABEL = "Max Value of I";

	public static final Insets insets = new Insets(0, 0, 0, 0);
	public static final int BORDER_SPACING = 15;
	public static final String FILE_FORM_SELECT = "Set i and j";
	public static final String BUTNPANEL_SUBTITLE = "A Button Panel";

	public static final String[] numberStrings = { "1", "2", "3", "4", "5", "6" };
	public static final String CONTENTPANEL_TXT = "ContentPanel";
	public static final String SCROLLPANEL_TXT = "ScrollPanel";
}
