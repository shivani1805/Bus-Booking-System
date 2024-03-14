import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BusBookingChart extends JFrame {

  public BusBookingChart(Connection connection) throws SQLException {
    super("Bus Booking Chart");
    setSize(800, 600);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    CategoryDataset dataset = createDataset(connection);

    JFreeChart chart = ChartFactory.createBarChart(
            "Bus Type Distribution",
            "Bus Type",
            "Total Bookings",
            dataset
    );

    ChartPanel chartPanel = new ChartPanel(chart);
    setContentPane(chartPanel);
    setVisible(true);
  }

  private CategoryDataset createDataset(Connection connection) throws SQLException {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    {


      try (PreparedStatement statement = connection.prepareStatement("CALL bus_booking_chart()");
           ResultSet resultSet = statement.executeQuery()) {
        while (resultSet.next()) {
          String busType = resultSet.getString("bus_type");
          int totalBookings = resultSet.getInt("total_bookings");
          dataset.addValue(totalBookings, "Total Bookings", busType);
        }
      }


      return dataset;
    }
  }
}
