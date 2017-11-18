/*
 Copyright (c) Ron Coleman

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package norminal;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import norminal.util.Helper;
import org.encog.mathutil.Equilateral;
import org.encog.util.Format;

/**
 * This code demonstrates how to use equilateral encoding with the Iris data.
 * Note: the number of outputs is one minus the number of nominal subtypes
 * See Heaton (2011), p.25-27 for more details.
 * @author Ron.Coleman
 */
public class EquilateralEncoding {

    // This is the structure of the Iris csv file by column
    public final static char[] DATA_TYPES = {
            Helper.TYPE_DECIMAL, 
            Helper.TYPE_DECIMAL, 
            Helper.TYPE_DECIMAL,
            Helper.TYPE_DECIMAL,
            Helper.TYPE_NOMINAL
    };

    public static void main(String[] args) {
        try {
            // Load the data
            Helper.loadCsv("iris.csv", DATA_TYPES, true);
            
            // If we get here without an exception, then get title of last column
            String title = Helper.getTitle(4);

            System.out.println(title);

            // To set up the equilateral encoding
            int numberSubtypes = Helper.getSubtypeCount();

            // Get the equilateral encoding for [-1, 1] range.
            Equilateral eq = new Equilateral(numberSubtypes, -1, 1);

            ArrayList<String> subtypes = Helper.getSubtypes();
            
            // Output the ideal values for each nominal as a point in
            // n-dimension hyperspace
            for (int index = 0; index < subtypes.size(); index++) {
                String subtype = subtypes.get(index);
                
                System.out.print(subtype + ": ");

                double[] encoding = eq.encode(index);

                for (int dim = 0; dim < encoding.length; dim++) {
                    if (dim > 0) {
                        System.out.print(", ");
                    }
                    System.out.print(Format.formatDouble(encoding[dim], 4));
                }

                System.out.println();
            }
        } catch (Exception ex) {
            Logger.getLogger(EquilateralEncoding.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
