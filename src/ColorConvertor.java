
// this is Color Convertor
// the formulas here are from www.easyrgb

public class ColorConvertor {
	public double RGBtoLAB(int r, int g, int b) {
		// intermediate variables
		double X;
		double Y;
		double Z;
		
		// normalize RGB value
		double normal_R = r / 255;
		double normal_G = g / 255;
		double normal_B = b / 255;
		
		// convert RGB to XYZ
		if(normal_R > 0.04045) {
			normal_R = Math.pow(((normal_R + 0.055) / 1.055), 2.4);
		}
		else {
			normal_R = normal_R / 12.92;
		}
		if(normal_G > 0.04045) {
			normal_G = Math.pow(((normal_G + 0.055) / 1.055), 2.4);
		}
		else {
			normal_G = normal_R / 12.92;
		}
		if(normal_B > 0.04045) {
			normal_B = Math.pow(((normal_B + 0.055) / 1.055), 2.4);
		}
		else {
			normal_B = normal_R / 12.92;
		}
		normal_R = normal_R * 100;
		normal_G = normal_G * 100;
		normal_B = normal_B * 100;
		
		X = normal_R * 0.4124 + normal_G * 0.3576 + normal_B * 0.1805;
		Y = normal_R * 0.2126 + normal_G * 0.7152 + normal_B * 0.0722;
		Z = normal_R * 0.0193 + normal_G * 0.1192 + normal_B * 0.9505;
		
		return 0.0;
	}
}
