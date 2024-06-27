package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckUserStatusScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Verificar Situação de Usuário");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label userLabel = new Label("Usuário (CPF):");
        grid.add(userLabel, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Button checkButton = new Button("Verificar");
        grid.add(checkButton, 1, 1);

        checkButton.setOnAction(e -> {
            String user = userTextField.getText();
            // Chame a função para verificar a situação do usuário na sua classe Operations
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}