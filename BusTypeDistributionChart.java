import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class BusTypeDistributionChart extends JFrame {

  public BusTypeDistributionChart(Connection connection) throws SQLException {
    super("Bus Type Distribution Chart");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(500, 500);

    DefaultPieDataset dataset = createDataset(connection);

    JFreeChart chart = ChartFactory.createPieChart(
            "Bus Type Distribution",
            dataset,
            true,
            true,
            false
    );

    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setSectionPaint("sleeper", java.awt.Color.BLUE);
    plot.setSectionPaint("seater", java.awt.Color.GREEN);

    ChartPanel chartPanel = new ChartPanel(chart);
    setContentPane(chartPanel);
    setVisible(true);
  }

  private DefaultPieDataset createDataset(Connection connection) throws SQLException {
    DefaultPieDataset dataset = new DefaultPieDataset();


      PreparedStatement statement = connection.prepareStatement("SELECT type, COUNT(*) AS count FROM bus_type GROUP BY type");
      ResultSet resultSet = statement.executeQuery();

      while (resultSet.next()) {
        String busType = resultSet.getString("type");
        int count = resultSet.getInt("count");
        dataset.setValue(busType, count);

      }


    return dataset;
  }


}
