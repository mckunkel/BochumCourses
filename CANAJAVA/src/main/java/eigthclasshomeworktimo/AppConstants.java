package eigthclasshomeworktimo;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

public class AppConstants {
	public static final String APPNAME = "Pressure Integration GUI";
	public static final String STATUS_TXT = "";
	public static final String RUN_LABEL_TXT = "Compute pressure";
	public static final String R0_LABEL_TXT = "Radius of star (in solar radii)";
	public static final String R_LABEL_TXT = "Radius for pressure computation (in solar radii)";
	public static final String DENSITY_LABEL_TXT = "Homogeneous density of star (in solar densities)";
	public static final String STEP_LABEL_TXT = "Number of integration steps";
	public static final String METHOD_LABEL_TXT = "Integration method";

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Insets insets = new Insets(0, 0, 0, 0);
	public static final int APP_WINDOW_SIZE_WIDTH = (int) screenSize.getWidth() * 2 / 3;
	public static final int APP_WINDOW_SIZE_HEIGHT = (int) screenSize.getHeight() / 3;

	public static final int BORDER_SPACING = 15;
	public static final String FILE_FORM_SELECT = "Set values";
}
