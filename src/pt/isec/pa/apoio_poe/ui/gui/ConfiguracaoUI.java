package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeState;

public class ConfiguracaoUI extends BorderPane {
    Apoio_poeManager manager;
    Button btnGA,btnGD,btnGP,btnFechar,btnAvancar,btnSave;
    Label lbTitulo;

    public ConfiguracaoUI(Apoio_poeManager manager) {
        this.manager = manager;

        lbTitulo = new Label("Adicionar Alunos");
        lbTitulo.setFont(new Font("Roboto",30));
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
        btnGA.setOnAction(actionEvent -> {
            manager.gerirAlunos();
        });
        btnGD.setOnAction(actionEvent -> {
            manager.gerirDocentes();
        });
        btnGP.setOnAction(actionEvent -> {
            manager.gerirPropostas();
        });
        btnAvancar.setOnAction(actionEvent -> {
            manager.avancar();
        });
        btnFechar.setOnAction(actionEvent -> {
            manager.fechar();
        });
        btnSave.setOnAction(actionEvent -> {
            manager.save();
        });
    }

    private void update() {
        if (manager.getState() != Apoio_poeState.CONFIGURACAO) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}