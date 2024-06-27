package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AddBookScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Adicionar Livro");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label titleLabel = new Label("Título:");
        grid.add(titleLabel, 0, 0);

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 0);

        Label authorLabel = new Label("Autor:");
        grid.add(authorLabel, 0, 1);

        TextField authorTextField = new TextField();
        grid.add(authorTextField, 1, 1);

        Button addButton = new Button("Adicionar");
        grid.add(addButton, 1, 2);

        addButton.setOnAction(e -> {
            String title = titleTextField.getText();
            String author = authorTextField.getText();
            // Chamar a função para adicionar o livro na classe Operations
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
