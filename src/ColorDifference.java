import java.awt.color.ColorSpace;


// this is color difference 
// using method CIE94 from 1976

public class ColorDifference {
	static public double findDifference(int argb1, int argb2) {
		LAB lab1 = ColorConvertor.RGBtoLAB(argb1);
		LAB lab2 = ColorConvertor.RGBtoLAB(argb2);
		return findDifference(lab1, lab2);
	}
	static public double findDifference(int r1, int g1, int b1, int r2, int g2, int b2) {
		LAB lab1 = ColorConvertor.RGBtoLAB(r1, g1, b1);
		LAB lab2 = ColorConvertor.RGBtoLAB(r2, g2, b2);
		return findDifference(lab1, lab2);
	}
	
	static public double findDifference(LAB lab1, LAB lab2) {
		double KL = 1;
		double K1 = 0.045;
		double K2 = 0.015;
		// double KL = 2;
		// double K1 = 0.048;
		// double K2 = 0.014;
		
		double dL = lab1.getL() - lab2.getL();
		double c1 = Math.sqrt((Math.pow(lab1.geta(), 2) + Math.pow(lab1.getb(), 2)));
		double c2 = Math.sqrt((Math.pow(lab2.geta(), 2) + Math.pow(lab2.getb(), 2)));
		double dC = c1 - c2;
		double da = lab1.geta() - lab2.geta();
		double db = lab1.getb() - lab2.getb();
		double dH = Math.sqrt((Math.pow(da, 2)+Math.pow(db, 2) - Math.pow(dC, 2)));
		
		double sec1 = Math.pow((dL / KL), 2);
		double sec2 = Math.pow((dC / (1 + (K1 * c1))), 2);
		double sec3 = Math.pow((dH / (1 + (K2 * c1))), 2);
		
		return Math.sqrt(sec1 + sec2 + sec3);
	}
}
