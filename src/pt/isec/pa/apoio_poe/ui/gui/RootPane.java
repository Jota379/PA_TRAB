package pt.isec.pa.apoio_poe.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.model.fsm.LoadState;

public class RootPane extends BorderPane{
    Apoio_poeManager manager;
    public RootPane(Apoio_poeManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {
        StackPane stackPane = new StackPane(
                new LoadStateUI(manager),
                new ConfiguracaoUI(manager),
                new gerirAlunosUI(manager),
                new gerirDocentesUI(manager),
                new gerirPropostasUI(manager)
        );
        stackPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,new CornerRadii(1),new Insets(1))));
        this.setCenter(stackPane);
    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> update());
    }

    private void update() {
        switch (manager.getState()){
            case CONFIGURACAO:
            case GERIR_ALUNOS:
            case GERIR_DOCENTES:
            case GERIR_PROPOSTAS:
        }
    }
}
