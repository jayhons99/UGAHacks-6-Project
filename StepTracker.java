package cs1302.arcade;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class StepTracker extends Application {

    VBox vbox = new VBox();

    int sunData;
    int monData;
    int tueData;
    int wedData;
    int thuData;
    int friData;
    int satData;

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();

    final BarChart<String,Number> barChart =
        new BarChart<String,Number>(xAxis,yAxis);

    XYChart.Series<String, Number>  series = new XYChart.Series();

    @Override public void start(Stage stage) {
        addUserInput();
        addBarChart();

        Scene scene  = new Scene(vbox,1024,576);
        stage.setTitle("Step Tracker");
        stage.setScene(scene);
        stage.show();
    }

    public void addUserInput() {
        VBox vBoxUI = new VBox();
        HBox hBoxDate = new HBox();
        Label sun = new Label("Sun");
        Label mon = new Label("Mon");
        Label tue = new Label("Tue");
        Label wed = new Label("Wed");
        Label thu = new Label("Thu");
        Label fri = new Label("Fri");
        Label sat = new Label("Sat");
        hBoxDate.setSpacing(125);
        hBoxDate.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);

        HBox hBoxUI = new HBox();
        TextField sun1 = new TextField();
        TextField mon1 = new TextField();
        TextField tue1 = new TextField();
        TextField wed1 = new TextField();
        TextField thu1 = new TextField();
        TextField fri1 = new TextField();
        TextField sat1 = new TextField();
        hBoxUI.setSpacing(10);
        hBoxUI.getChildren().addAll(sun1, mon1, tue1, wed1, thu1, fri1, sat1);

        sun1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = sun1.getText();
                        sunData = Integer.parseInt(str);

                        series.getData().get(0).setYValue(sunData);
                    }
                }
            });

        mon1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = mon1.getText();
                        monData = Integer.parseInt(str);

                        series.getData().get(1).setYValue(monData);
                    }
                }
            });

        tue1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = tue1.getText();
                        tueData = Integer.parseInt(str);

                        series.getData().get(2).setYValue(tueData);
                    }
                }
            });

        wed1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = wed1.getText();
                        wedData = Integer.parseInt(str);

                        series.getData().get(3).setYValue(wedData);
                    }
                }
            });

        thu1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = thu1.getText();
                        thuData = Integer.parseInt(str);

                        series.getData().get(4).setYValue(thuData);
                    }
                }
            });

        fri1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = fri1.getText();
                        friData = Integer.parseInt(str);

                        series.getData().get(5).setYValue(friData);
                    }
                }
            });

        sat1.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent ke) {
                    if (ke.getCode().equals(KeyCode.ENTER)) {
                        String str = sat1.getText();
                        satData = Integer.parseInt(str);

                        series.getData().get(6).setYValue(satData);
                    }
                }
            });


        vBoxUI.getChildren().addAll(hBoxDate, hBoxUI);
        vbox.getChildren().add(vBoxUI);
    } // addUserInput



    public void addBarChart() {
        yAxis.setLabel("Number of Steps");
        barChart.setTitle("Weekly Step Tracker");
        series.setName("My Portfolio");

        series.getData().add(new XYChart.Data("Sun", sunData));
        series.getData().add(new XYChart.Data("Mon", monData));
        series.getData().add(new XYChart.Data("Tue", tueData));
        series.getData().add(new XYChart.Data("Wed", wedData));
        series.getData().add(new XYChart.Data("Thu", thuData));
        series.getData().add(new XYChart.Data("Fri", friData));
        series.getData().add(new XYChart.Data("Sat", satData));

        barChart.getData().add(series);

        vbox.getChildren().add(barChart);
    }
}
