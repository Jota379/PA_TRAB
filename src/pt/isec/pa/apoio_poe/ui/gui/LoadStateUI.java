package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeState;

public class LoadStateUI extends BorderPane {
    Apoio_poeManager manager;
    Button btnAvancar,btnLoad;

    public LoadStateUI(Apoio_poeManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        btnAvancar  = new Button("avancar");
        btnAvancar.setMinWidth(100);
        btnLoad = new Button("Load");
        VBox vBox = new VBox(btnAvancar,btnLoad);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        this.setCenter(vBox);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> { update(); });
        btnAvancar.setOnAction(actionEvent -> {
            manager.avancar();
        });
    }

    private void update() {
        if (manager.getState() != Apoio_poeState.LOAD_STATE) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }
}
