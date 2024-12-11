package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Linear {
    public void run() {
        // Get user inputs
        Scanner scanner = new Scanner(System.in); // Create a Scanner object
        System.out.print("Enter the starting x value: "); // Print a message
        int startX = scanner.nextInt(); // Read user input
        System.out.print("Enter the ending x value: "); // Print a message
        int endX = scanner.nextInt(); // Read user input
        System.out.print("Enter the interval (step): "); // Print a message
        int interval = scanner.nextInt(); // Read user input

        // Data series for the linear function
        XYSeries series = new XYSeries("Linear Function: y = 2x + 1"); // Create a new XYSeries
        DescriptiveStatistics stats = new DescriptiveStatistics(); // Create a new DescriptiveStatistics object

        // StringBuilder to store CSV data
        StringBuilder csvData = new StringBuilder("X,Y\n"); // Create a new StringBuilder

        for (int x = startX; x <= endX; x += interval) {
            double y = 2 * x + 1; // Calculate y value
            series.add(x, y); // Add data points to the series
            stats.addValue(y); // Add y value to DescriptiveStatistics object
            csvData.append(x).append(",").append(y).append("\n"); // Append data to CSV string
        }

        // Save CSV data to working directory
        String csvFileName = "linear_function_points.csv"; // Create a new String
        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.write(csvData.toString()); // Write data to CSV file
            System.out.println("\nCSV file saved in working directory as: " + csvFileName); // Print a message
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage()); // Print a message
        }

        // Create dataset and chart
        XYSeriesCollection dataset = new XYSeriesCollection(series); // Create a new XYSeriesCollection
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Linear Function Plot", // Title
                "X", // X-axis label
                "Y", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Plot orientation
                true, // Show legend
                true, // Show tooltips
                false); // Show URLs

        // Save chart as PNG in working directory
        String chartFileName = "linear_function_plot.png"; // Create a new String
        try {
            ChartUtils.saveChartAsPNG(new File(chartFileName), chart, 800, 600); // Save chart as PNG
            System.out.println("\nGraph saved in working directory as: " + chartFileName); // Print a message
        } catch (IOException e) {
            System.out.println("Error saving chart as image: " + e.getMessage()); // Print a message
        }

        // Display the chart in a Swing window
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Linear Function Plot"); // Create a new JFrame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
            frame.add(new ChartPanel(chart)); // Add chart panel to frame
            frame.pack(); // Pack components in frame
            frame.setVisible(true); // Set frame visibility
        });

        scanner.close(); // Close the scanner
    }
}
