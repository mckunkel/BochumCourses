package eigthclasshomeworktimo;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

public class AppConstants {
	public static final String APPNAME = "Pressure Integration GUI";
	public static final String STATUS_TXT = "";
	public static final String RUN_LABEL_TXT = "Compute pressure at R";
	public static final String R0_LABEL_TXT = "Radius of star in solar radii";
	public static final String R_LABEL_TXT = "Radius for pressure computation";
	public static final String DENSITY_LABEL_TXT = "Homogeneous density of star in solr densities";
	public static final String STEP_LABEL_TXT = "Number of integration steps";
	public static final String METHOD_LABEL_TXT = "Choose integration method";

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Insets insets = new Insets(0, 0, 0, 0);
	public static final int APP_WINDOW_SIZE_WIDTH = (int) screenSize.getWidth() - (int) screenSize.getWidth() / 3;
	public static final int APP_WINDOW_SIZE_HEIGHT = (int) screenSize.getHeight() - (int) screenSize.getHeight() / 3;
}
