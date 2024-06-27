package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Operations;

public class SearchBookScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Pesquisar Livro");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label titleLabel = new Label("Título:");
        grid.add(titleLabel, 0, 0);

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 0);

        Button searchButton = new Button("Pesquisar");
        grid.add(searchButton, 1, 2);

        Label resultLabel = new Label():

        searchButton.setOnAction(e -> {
            String title = titleTextField.getText();

            // Chamar a função de pesquisar
            var result = Operations.pesquisarMaterial(title);
            resultLabel.setText("Quantidade em estoque: " + result);
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
