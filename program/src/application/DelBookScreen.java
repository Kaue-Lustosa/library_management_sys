package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import utils.Operations;

public class DelBookScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Remover Livro");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label titleLabel = new Label("Título:");
        grid.add(titleLabel, 0, 0);

        TextField titleTextField = new TextField();
        grid.add(titleTextField, 1, 0);

        Label qntdLabel = new Label("Quantidade:");
        grid.add(qntdLabel, 0, 0);

        TextField qntdTextField = new TextField();
        grid.add(qntdTextField, 1, 0);

        Button searchButton = new Button("Remover");
        grid.add(searchButton, 1, 2);

        Label feedbackLabel = new Label():

        searchButton.setOnAction(e -> {
            String title = titleTextField.getText();
            int qntd = Integer.parseInt(qntdTextField.getText());

            var removido = Operations.removerMaterial(title, qntd);

            if (removido) {
                feedbackLabel.setText("Removido(s) " + qntd + " livro(s) com sucesso!");
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro ao remover");
                alert.setHeaderText(null);
                alert.setContentText("Livro não encontrado ou quantidade indisponível.");
                alert.showAndWait();
            }
        });

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
