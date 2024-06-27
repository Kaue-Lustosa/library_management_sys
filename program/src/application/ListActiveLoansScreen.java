package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ListActiveLoansScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Listar Empréstimos Ativos");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label userLabel = new Label("Usuário (CPF):");
        grid.add(userLabel, 0, 0);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 0);

        Button listButton = new Button("Listar");
        grid.add(listButton, 1, 1);

        listButton.setOnAction(e -> {
            String user = userTextField.getText();
            // Chame a função para listar os empréstimos ativos do usuário na sua classe Operations
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
