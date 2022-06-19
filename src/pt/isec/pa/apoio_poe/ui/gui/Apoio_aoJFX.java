package pt.isec.pa.apoio_poe.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;

public class Apoio_aoJFX extends Application {
    Apoio_poeManager manager;
    @Override
    public void init() throws Exception {
        super.init();
        manager = new Apoio_poeManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        RootPane root = new RootPane(manager);
        Scene scene = new Scene(root,400,700);
        stage.setScene(scene);
        stage.setTitle("Apoio_POE");
        stage.setMinWidth(400);
        stage.setMinHeight(700);
        stage.show();
    }



    public void configureStage(Stage stage){
        RootPane root = new RootPane(manager);
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("ApoioPoe");
        stage.show();
    }

    /*private void configureList(Stage stage){
        ListPane root = new ListPane(drawing);
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Drawing@PA");
        stage.show();
    }*/

}
