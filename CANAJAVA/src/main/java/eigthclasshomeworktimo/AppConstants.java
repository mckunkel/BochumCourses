package eigthclasshomeworktimo;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;

public class AppConstants {
	public static final String APPNAME = "Numerical stellar attribute integration GUI";
	public static final String STATUS_TXT = "";
	public static final String RUN_LABEL_TXT = "Compute parameters";
	public static final String R0_LABEL_TXT = "Radius of star (in solar radii)";
	public static final String R_LABEL_TXT = "<html> Radius for pressure <br/>computation (in solar radii) </html>";
	public static final String DENSITY_LABEL_TXT = "Homogeneous density of star (in g/cm^3)";
	public static final String LUMINOSITY_LABEL_TXT = "Surface luminosity (in solar luminosities)";
	public static final String TEMPERATURE_LABEL_TXT = "Surface Temperature (in K)";
	public static final String EPSILON_LABEL_TXT = "Mean energy density (in solar values at core boundary)";
	public static final String KAPPA_LABEL_TXT = "Mean opacity (in solar core values)";
	public static final String STEP_LABEL_TXT = "Number of integration steps";
	public static final String METHOD_LABEL_TXT = "Integration method";

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Insets insets = new Insets(0, 0, 0, 0);
	public static final int APP_WINDOW_SIZE_WIDTH = (int) screenSize.getWidth();
	public static final int APP_WINDOW_SIZE_HEIGHT = (int) screenSize.getHeight();

	public static final int BORDER_SPACING = 15;
	public static final String INPUT1_TXT = "Set physical parameters";
	public static final String INPUT2_TXT = "Set integration parameters";
}
