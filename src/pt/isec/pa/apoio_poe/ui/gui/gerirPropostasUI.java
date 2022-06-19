package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeState;

public class gerirPropostasUI extends BorderPane {
    Apoio_poeManager manager;
    Button btnGA,btnGD,btnGP,btnFechar,btnAvancar,btnSave;

    public gerirPropostasUI(Apoio_poeManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnGA = new Button("Gerir Alunos");
        btnGA.setMinWidth(100);
        btnGD = new Button("Gerir Docentes");
        btnGD.setMinWidth(100);
        btnGP = new Button("Gerir Proposta");
        btnGP.setMinWidth(100);
        btnFechar = new Button("Fechar");
        btnFechar.setMinWidth(100);
        btnAvancar  = new Button("avancar");
        btnAvancar.setMinWidth(100);
        btnSave = new Button("save");
        VBox vBox = new VBox(btnGA,btnGD,btnGP,btnFechar,btnAvancar,btnSave);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
    }

    private void update() {
        if (manager.getState() != Apoio_poeState.GERIR_PROPOSTAS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
