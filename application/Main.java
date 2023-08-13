package application;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import mainpackage.Engineers;
import mainpackage.dao.NamedParamJdbcDaoImpl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
	
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Engineers Login Application");

            Label usernameLabel = new Label("Username:");
            TextField usernameField = new TextField();

            Label passwordLabel = new Label("Password:");
            PasswordField passwordField = new PasswordField();

            Button loginButton = new Button("Login");
            Label outputLabel = new Label();

            outputLabel.setText("Enter 'exit' for both username and password if you want to exit.");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 20, 20, 20));

            grid.add(usernameLabel, 0, 0);
            grid.add(usernameField, 1, 0);
            grid.add(passwordLabel, 0, 1);
            grid.add(passwordField, 1, 1);
            grid.add(loginButton, 1, 2);
            grid.add(outputLabel, 0, 3, 2, 1);

            Scene scene = new Scene(grid, 300, 200);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("engineersinfo.xml");
            Engineers engineers = ctx.getBean("engineers", Engineers.class);

            AbstractApplicationContext ctx2 = new ClassPathXmlApplicationContext("namedparamjdbcdaoimpl.xml");
            NamedParamJdbcDaoImpl dao = ctx2.getBean("namedParamJdbcDaoImpl", NamedParamJdbcDaoImpl.class);

            loginButton.setOnAction(event -> {

                String inputname = usernameField.getText();
                String inputpw = passwordField.getText();
                boolean ifNotExit = !inputname.equals("exit") || !inputpw.equals("exit");
                boolean loginFail = engineers.login(inputname, inputpw) && ifNotExit;
                if (loginFail == false && ifNotExit == true) {

                    Button companySettingsButton = new Button("Company Settings");
                    Button loadCustomersButton = new Button("Load Customers");
                    Button backButton = new Button("Back");

                    VBox buttonLayout = new VBox(10);
                    buttonLayout.setAlignment(Pos.CENTER);
                    buttonLayout.getChildren().addAll(companySettingsButton, loadCustomersButton, backButton);
                    Scene buttonScene = new Scene(buttonLayout, 300, 200);

                    primaryStage.setScene(buttonScene);

                    backButton.setOnAction(backEvent -> {
                        // Go back to the original login scene
                        primaryStage.setScene(scene);
                        usernameField.clear();
                        passwordField.clear();
                        outputLabel.setText("Enter 'exit' for both username and password if you want to exit."); // Clear the output message
                    });
                    
                    // Handle button actions
                    companySettingsButton.setOnAction(companySettingsEvent -> {
                        // Create a new scene for company settings
                    	Label settingsLabel = new Label("Company Settings:");
                    	Label resultLabel = new Label("Results will show up here.");

                        Button createTableButton = new Button("Create Customer Table");
                        Button dropTableButton = new Button("Drop Customer Table");
                        Button backButtonToButtonScene1 = new Button("Back");

                        VBox settingsLayout = new VBox(10);
                        settingsLayout.setAlignment(Pos.CENTER);
                        settingsLayout.getChildren().addAll(settingsLabel, resultLabel, createTableButton, dropTableButton, backButtonToButtonScene1);
                        Scene settingsScene = new Scene(settingsLayout, 300, 200);

                        primaryStage.setScene(settingsScene);

                        // Handle "Create Customer Table" button action
                        createTableButton.setOnAction(createTableEvent -> {
                        	dao.createCustomerTable();
                        	resultLabel.setText("Customer table created successfully.");
                        });

                        // Handle "Drop Customer Table" button action
                        dropTableButton.setOnAction(dropTableEvent -> {
                        	dao.dropCustomerTable();
                        	resultLabel.setText("Customer table dropped successfully.");
                        });

                        backButtonToButtonScene1.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    });

                    loadCustomersButton.setOnAction(loadCustomersEvent -> {
                    	
                    	// Original terminal-like interface scene
                        TextArea terminalTextArea = new TextArea();
                        terminalTextArea.setEditable(true);
                        terminalTextArea.setPrefRowCount(10);

                        Button backButtonToButtonScene2 = new Button("Back");
                        Button insertButton = new Button("Insert");
                        Button deleteButton = new Button("Delete");

                        HBox buttonBox = new HBox(10);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.getChildren().addAll(backButtonToButtonScene2, insertButton, deleteButton);

                        VBox terminalLayout = new VBox(10);
                        terminalLayout.setAlignment(Pos.CENTER);
                        terminalLayout.getChildren().addAll(terminalTextArea, buttonBox);
                        Scene terminalScene = new Scene(terminalLayout, 400, 300);
                        
                        insertButton.setDisable(false);
                        deleteButton.setDisable(true);
                        
                        // Handle terminal button actions
                        insertButton.setOnAction(insertEvent -> {
                            dao.fillTableFromCsv("C:\\Users\\berkehan\\Downloads\\CustomerData.csv");
                            List<String> NandS = dao.retrieveNamesAndSurnames();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            insertButton.setDisable(true);
                            deleteButton.setDisable(false);
                        });

                        deleteButton.setOnAction(deleteEvent -> {
                            dao.deleteCustomersUsingCsv("C:\\Users\\berkehan\\Downloads\\CustomerData.csv");
                            List<String> NandS = dao.retrieveNamesAndSurnames();
                            for (String item : NandS) {
                                terminalTextArea.appendText("\n" + item);
                            }
                            terminalTextArea.appendText("\nData from the CSV file has been successfully deleted from the table.");
                            insertButton.setDisable(false);
                            deleteButton.setDisable(true);
                        });

                        backButtonToButtonScene2.setOnAction(backToButtonEvent -> {
                            primaryStage.setScene(buttonScene); // Switch to the "Button" scene
                        });
                    	
                        // Reuse the existing terminal-like interface scene
                        primaryStage.setScene(terminalScene);
                    });

                } else if (loginFail == false && ifNotExit == false) {
                    primaryStage.close();
                } else {
                    outputLabel.setText("Login failed. Please check your credentials.");
                }

            });

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setOnCloseRequest(event -> {
                // Close both Spring application contexts when the application is shutting down
                ctx.close();
                ctx2.close();
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        launch(args);

    }
}