package cs1302.arcade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;

public class GameApp extends Application {

    VBox vbox = new VBox();


    @Override public void start(Stage stage) {
        addAreaChart();

        Scene scene  = new Scene(vbox,1280,720);
        stage.setTitle("Step Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public void addAreaChart() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of Steps");

        final AreaChart<String,Number> areaChart =
                new AreaChart<String,Number>(xAxis,yAxis);

        areaChart.setTitle("Weekly Step Tracker");

        XYChart.Series series = new XYChart.Series();
        series.setName("");

        series.getData().add(new XYChart.Data("Sun", 0));
        series.getData().add(new XYChart.Data("Mon", 0));
        series.getData().add(new XYChart.Data("Tue", 0));
        series.getData().add(new XYChart.Data("Wed", 100));
        series.getData().add(new XYChart.Data("Thu", 0));
        series.getData().add(new XYChart.Data("Fri", 0));
        series.getData().add(new XYChart.Data("Sat", 0));

        areaChart.getData().add(series);

        vbox.getChildren().add(areaChart);
    }
}
