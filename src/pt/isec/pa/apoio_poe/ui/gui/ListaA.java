package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;

public class ListaA extends BorderPane {
    Apoio_poeManager manager;
    Label texto;
    Button btnVoltar;
    gerirAlunosUI ga;

    public ListaA(Apoio_poeManager manager) {
        this.manager = manager;
        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        texto = new Label();
        texto.setText(manager.listaAlunos());
        btnVoltar = new Button("Voltar");
        VBox vBox = new VBox(texto,btnVoltar);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnVoltar.setOnAction(actionEvent -> {
            ga = new gerirAlunosUI(manager);
            this.getScene().setRoot(ga);
        });
    }

    private void update() {
    }
}
