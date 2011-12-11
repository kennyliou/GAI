import java.math.BigDecimal;


// this is Color Convertor
// the formulas here are from www.easyrgb.com
// this function will return a LAB object

public class ColorConvertor {
	static public LAB RGBtoLAB(int argb) {
		// this is the standard way to convert an int to argb value
		int r = (argb)&0xFF;
		int g = (argb>>8)&0xFF;
		int b = (argb>>16)&0xFF;
		int a = (argb>>24)&0xFF;
		
		return RGBtoLAB(r, g, b);
	}
	static public LAB RGBtoLAB(int r, int g, int b) {
		// intermediate variables
		double X;
		double Y;
		double Z;
		LAB lab;
		
		BigDecimal top;
		BigDecimal bottom;
		
		// normalize RGB value
		top = new BigDecimal(r);
		bottom = new BigDecimal(255);
		double normal_R = top.divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		top = new BigDecimal(g);
		double normal_G = top.divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		top = new BigDecimal(b);
		double normal_B = top.divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		// convert RGB to XYZ
		bottom = new BigDecimal(1.055);
		if(normal_R > 0.04045) {
			normal_R = Math.pow((new BigDecimal(normal_R + 0.055).divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue()), 2.4);
		}
		else {
			normal_R = new BigDecimal(normal_R).divide(new BigDecimal(12.92), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		if(normal_G > 0.04045) {
			normal_G = Math.pow((new BigDecimal(normal_G + 0.055).divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue()), 2.4);
		}
		else {
			normal_G = new BigDecimal(normal_G).divide(new BigDecimal(12.92), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		if(normal_B > 0.04045) {
			normal_B = Math.pow((new BigDecimal(normal_B + 0.055).divide(bottom, 5, BigDecimal.ROUND_HALF_UP).doubleValue()), 2.4);
		}
		else {
			normal_B = new BigDecimal(normal_B).divide(new BigDecimal(12.92), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		normal_R = normal_R * 100;
		normal_G = normal_G * 100;
		normal_B = normal_B * 100;
		
		X = normal_R * 0.4124 + normal_G * 0.3576 + normal_B * 0.1805;
		Y = normal_R * 0.2126 + normal_G * 0.7152 + normal_B * 0.0722;
		Z = normal_R * 0.0193 + normal_G * 0.1192 + normal_B * 0.9505;
		
		// convert XYZ to L*ab
		// standard variables
		BigDecimal ref_X = new BigDecimal(95.047);
		BigDecimal ref_Y = new BigDecimal(100.000);
		BigDecimal ref_Z = new BigDecimal(108.883);
		
		double normal_x = new BigDecimal(X).divide(ref_X, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		double normal_y = new BigDecimal(Y).divide(ref_Y, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		double normal_z = new BigDecimal(Z).divide(ref_Z, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		if(normal_x > 0.008856) {
			normal_x = Math.pow(normal_x, new BigDecimal(1).divide(new BigDecimal(3), 5, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		else {
			normal_x = (7.787 * normal_x) + ( new BigDecimal(16).divide(new BigDecimal(116), 5, BigDecimal.ROUND_HALF_UP).doubleValue() );
		}
		if(normal_y > 0.008856) {
			normal_y = Math.pow(normal_y, new BigDecimal(1).divide(new BigDecimal(3), 5, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		else {
			normal_y = (7.787 * normal_y) + new BigDecimal(16).divide(new BigDecimal(116), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		if(normal_z > 0.008856) {
			normal_z = Math.pow(normal_z, new BigDecimal(1).divide(new BigDecimal(3), 5, BigDecimal.ROUND_HALF_UP).doubleValue());
		}
		else {
			normal_z = (7.787 * normal_z) + new BigDecimal(16).divide(new BigDecimal(116), 5, BigDecimal.ROUND_HALF_UP).doubleValue();
		}		
		
		double cie_L = (116 * normal_y) - 16;
		double cie_a = 500 * (normal_x - normal_y);
		double cie_b = 200 * (normal_y - normal_z);
		
		// this is used to scale the value to 5
		BigDecimal one = new BigDecimal(1);
		lab = new LAB(new BigDecimal(cie_L).divide(one, 5, BigDecimal.ROUND_HALF_UP).doubleValue(), 
					  new BigDecimal(cie_a).divide(one, 5, BigDecimal.ROUND_HALF_UP).doubleValue(),
					  new BigDecimal(cie_b).divide(one, 5, BigDecimal.ROUND_HALF_UP).doubleValue());
		
		return lab;
	}
}
