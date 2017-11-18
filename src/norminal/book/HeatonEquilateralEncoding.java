package norminal.book;

import org.encog.mathutil.Equilateral;
import org.encog.util.Format;

/**
 * This code, from Heaton (2011), p. 25, demonstrates how to use equilateral encoding.
 * @author Ron.Coleman
 */
public class HeatonEquilateralEncoding {

    public static void main(String[] args) {
        Equilateral eq = new Equilateral(3,-1,1);
        
        for(int i=0; i < 3; i++) {
            StringBuilder line = new StringBuilder();
            
            line.append(i);
            
            line.append(": ");
            
            double[] d = eq.encode(i);
            
            for(int j=0; j < d.length; j++) {
                if(j > 0)
                    line.append(", ");
                line.append(Format.formatDouble(d[j],4));
            }
            
            System.out.println(line.toString());
        }
    }
    
}
