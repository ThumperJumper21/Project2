import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlottingFunction {

    /**
     * This consits the main method to generate data and save to a CSV file for
     * plotting
     * the specifc function to calculate the y value is defined in the
     * calculateFunction method
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Define parameters
        double startX = -10.0; // Starting x-value
        double endX = 10.0; // Ending x-value
        double step = 0.1; // Step size

        // Generates the data
        int size = (int) ((endX - startX) / step) + 1;
        double[] xValues = new double[size];
        double[] yValues = new double[size];

        int index = 0;
        for (double x = startX; x <= endX; x += step) {
            xValues[index] = x;
            yValues[index] = calculateFunction(x); // Calls the function to calculate the y
            index++;
        }

        // Exporting to the CSV
        exportToCSV("data.csv", xValues, yValues);

        System.out.println("Data has been generated and saved to data.csv");
    }

    // Example function: Linear function y = 2x + 1
    /**
     * @param x
     * @return
     */
    public static double calculateFunction(double x) {
        double m = 2.0; // The slope
        double b = 1.0; // The intercept
        return m * x + b;
    }

    // Export x and y values to a CSV file for plotting
    /**
     * @param fileName
     * @param xValues
     * @param yValues
     */
    public static void exportToCSV(String fileName, double[] xValues, double[] yValues) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("x,y\n");
            for (int i = 0; i < xValues.length; i++) {
                writer.write(xValues[i] + "," + yValues[i] + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
