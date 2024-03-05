package com.example.assignment1_extra;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainController {
    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TableColumn<manga_data, Integer> rank_table;

    @FXML
    private TableColumn<manga_data, Integer> sales_table;

    @FXML
    private TableColumn<manga_data, String> title_table;

    @FXML
    private TableView<manga_data> tableView;

    private ObservableList<manga_data> mangaList;
    @FXML
    private Button changeButton;

    //Initialize click count for switch
    int clickCount = 0;

    //Click event method for Change button
    @FXML
    private void onChangeButtonClick(ActionEvent event) {

        //Increase click count number
        clickCount++;

        //Display switch(button name : Change)
        if (clickCount % 2 == 0) {
            tableView.setVisible(false);//table invisible
            barChart.setVisible(true);//barChart visible

            // Display barChart data
            initData();

        } else if (clickCount % 2 == 1)  {
            tableView.setVisible(true);//table visible
            barChart.setVisible(false);//barChart invisible

            // Display table data
            initData_table();
        }
    }
    // Start with bar chart visible
    public void initialize() {
        tableView.setVisible(false);//table invisible
        barChart.setVisible(true);//barChart visible
        // Display barChart data
        initData();
    }

    // Method to initialize barChart data
    public void initData() {
        try {
            barChart.getData().clear(); // Clear existing data
            //Instance of Database Connector
            DatabaseConnector connector = new DatabaseConnector();
            // Establish a database connection
            Connection con = connector.connect();
            // Create a statement for executing SQL queries
            Statement stmt = con.createStatement();
            // Execute a SQL query to select
            ResultSet rs = stmt.executeQuery("SELECT * FROM manga_sales");

            // Create a new series for the bar chart
            XYChart.Series<String, Number> series1 = new XYChart.Series<>();
            //Retrieve data for the series by while
            while (rs.next()) {
                // Get the title data
                String title = rs.getString("title");
                // Get the sales data
                int sales = rs.getInt("sales");
                // Add a new data point to the series
                series1.getData().add(new XYChart.Data<>(title, sales));
            }
            // Add series to bar chart
            barChart.getData().add(series1);

            //close
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to initialize TableChart data
    public void initData_table() {
        try {
            //Instance of Database Connector
            DatabaseConnector connector = new DatabaseConnector();
            // Establish a database connection
            Connection con = connector.connect();
            // Create a statement for executing SQL queries
            Statement stmt = con.createStatement();
            // Execute a SQL query to select
            ResultSet rs = stmt.executeQuery("SELECT * FROM manga_sales");

            // Create a new ObservableList to store manga data
            mangaList = FXCollections.observableArrayList();

            //Retrieve data by while
            while (rs.next()) {
                // Get the rank data
                int rank = rs.getInt("rank");
                // Get the title data
                String title = rs.getString("title");
                // Get the sales data
                int sales = rs.getInt("sales");

                //Add ObservableList to table
                mangaList.add(new manga_data(rank, title, sales));
            }

            // Set the cell value factory for the rank_table column
            rank_table.setCellValueFactory(new PropertyValueFactory<>("rank_table"));
            // Set the cell value factory for the title_table column
            title_table.setCellValueFactory(new PropertyValueFactory<>("title_table"));
            // Set the cell value factory for the sales_table column
            sales_table.setCellValueFactory(new PropertyValueFactory<>("sales_table"));

            tableView.setItems(mangaList);
            rs.close();
            stmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
