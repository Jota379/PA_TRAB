package pt.isec.pa.apoio_poe.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Cursos;
import pt.isec.pa.apoio_poe.model.data.Ramos;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeState;
import pt.isec.pa.apoio_poe.ui.gui.utils.NumericTextField;

import java.io.File;

public class gerirAlunosUI extends BorderPane {
    Apoio_poeManager manager;
    Label titulo,texto,lbnumAl,lbnumAlrem,lbNome,lbEmail,lbCurso,lbRamo,lbClass,lbTAdd,lbTRem,lbTEdit,lbnumAlE,lbNomeE,lbEmailE,lbCursoE,lbRamoE,lbClassE;
    TextField tfNome,tfEmail,tfNomeE,tfEmailE;
    NumericTextField ntfnumAl,ntfnumAlrem,ntfClass,ntfnumAlE,ntfClassE;
    Button btnAdd,btnEdit,btnRem,btnLista,btnImp,btnExp,btnVoltar,btnVoltarL,btnSubAdd,btnSave,btnRemR,btnSubEdit,btnEditE;
    RadioButton rbLei,rbLeiPL,rbDA,rbRedes,rbSI,rbLeiE,rbLeiPLE,rbDAE,rbRedesE,rbSIE;
    ToggleGroup tgCurso,tgRamos,tgCursoE,tgRamosE;
    CheckBox cbestagio,cbestagioE;
    HBox Hbramos,Hbcursos,HbcursosE,HbramosE;

    //AdicionaAUI a;
    ListaA a;
    VBox PvBox;

    public gerirAlunosUI(Apoio_poeManager manager) {
        this.manager = manager;
        a = new ListaA(this.manager);

        createViews();
        registerHandlers();
        update();
    }

    private void createViews() {


        titulo = new Label("GERIR ALUNOS");
        titulo.setFont(new Font("Roboto",30));
        btnAdd = new Button("Adiciona Aluno");
        btnAdd.setMinWidth(100);
        btnLista = new Button("Lista Alunos");
        btnLista.setMinWidth(100);
        btnEdit = new Button("Edita Aluno");
        btnEdit.setMinWidth(100);
        btnRem = new Button("Remove Aluno");
        btnRem.setMinWidth(100);
        btnExp = new Button("Export CSV");
        btnRem.setMinWidth(100);
        btnImp = new Button("Import CSV");
        btnRem.setMinWidth(100);
        btnVoltar = new Button("Voltar");
        btnVoltar.setMinWidth(100);
        btnSave = new Button("save");
        preparaLista();
        preparaAdd();
        preparaRem();
        preparaEdit();
        PvBox = new VBox(titulo,btnAdd,btnLista,btnEdit,btnRem,btnImp,btnExp,btnVoltar);
        PvBox.setAlignment(Pos.CENTER);
        PvBox.setSpacing(10);
        this.setCenter(PvBox);
    }

    private void preparaLista() {
        texto = new Label();
        btnVoltarL = new Button("Voltar");
    }

    private void preparaAdd() {

        lbTAdd = new Label("Adicionar Alunos");
        lbTAdd.setFont(new Font("Roboto",30));

        lbnumAl = new Label("Numero de aluno:");
        ntfnumAl = new NumericTextField();

        lbNome = new Label("Nome:");
        tfNome = new TextField();

        lbEmail = new Label("Email:");
        tfEmail = new TextField();

        lbCurso = new Label("Curso:");
        tgCurso = new ToggleGroup();
        rbLei = new RadioButton("Lei");
        rbLeiPL = new RadioButton("Lei-PL");
        rbLei.setToggleGroup(tgCurso);
        rbLeiPL.setToggleGroup(tgCurso);
        Hbcursos = new HBox(rbLei,rbLeiPL);
        Hbcursos.setAlignment(Pos.CENTER);

        lbRamo = new Label("ramo:");
        tgRamos = new ToggleGroup();
        rbDA = new RadioButton("DA");
        rbRedes = new RadioButton("RAS");
        rbSI = new RadioButton("SI");
        rbDA.setToggleGroup(tgRamos);
        rbRedes.setToggleGroup(tgRamos);
        rbSI.setToggleGroup(tgRamos);
        Hbramos = new HBox(rbDA,rbRedes,rbSI);
        Hbramos.setAlignment(Pos.CENTER);

        lbClass = new Label("Classificação");
        ntfClass = new NumericTextField();

        cbestagio = new CheckBox("Estagio");

        btnSubAdd = new Button("Adicionar");
    }

    private void preparaEdit(){
        lbTEdit = new Label("Adicionar Alunos");
        lbTEdit.setFont(new Font("Roboto",30));

        lbnumAlE = new Label("Numero de aluno:");
        ntfnumAlE = new NumericTextField();

        btnEditE = new Button("Edita");

        lbNomeE = new Label("Nome:");
        tfNomeE = new TextField();

        lbEmailE = new Label("Email:");
        tfEmailE = new TextField();

        lbCursoE = new Label("Curso:");
        tgCursoE = new ToggleGroup();
        rbLeiE = new RadioButton("Lei");
        rbLeiPLE = new RadioButton("Lei-PL");
        rbLeiE.setToggleGroup(tgCurso);
        rbLeiPLE.setToggleGroup(tgCurso);
        HbcursosE = new HBox(rbLei,rbLeiPL);
        HbcursosE.setAlignment(Pos.CENTER);

        lbRamoE = new Label("ramo:");
        tgRamosE = new ToggleGroup();
        rbDAE = new RadioButton("DA");
        rbRedesE = new RadioButton("RAS");
        rbSIE = new RadioButton("SI");
        rbDAE.setToggleGroup(tgRamos);
        rbRedesE.setToggleGroup(tgRamos);
        rbSIE.setToggleGroup(tgRamos);
        HbramosE = new HBox(rbDA,rbRedes,rbSI);
        HbramosE.setAlignment(Pos.CENTER);

        lbClassE = new Label("Classificação");
        ntfClassE = new NumericTextField();

        cbestagioE = new CheckBox("Estagio");

        btnSubEdit = new Button("confirmar");
    }

    private void preparaRem(){
        lbTRem = new Label("Remove Aluno");
        lbTRem.setFont(new Font("Roboto",30));
        lbnumAlrem = new Label("Numero de aluno:");
        ntfnumAlrem = new NumericTextField();
        btnRemR = new Button("Remove Aluno");
    }

    private void registerHandlers(){
        manager.addPropertyChangeListener(evt -> { update(); });
        btnVoltar.setOnAction(actionEvent -> {
            manager.voltar();
        });
        btnLista.setOnAction(actionEvent -> {
            texto.setText(manager.listaAlunos());
            VBox vBox = new VBox(texto,btnVoltarL);
            vBox.setAlignment(Pos.CENTER);
            vBox.setSpacing(10);
            this.setCenter(vBox);
        });
        btnVoltarL.setOnAction(actionEvent -> {
            this.setCenter(PvBox);
        });
        btnAdd.setOnAction(actionEvent -> {
            VBox vbAddAluno = new VBox();
            vbAddAluno.setAlignment(Pos.CENTER);
            vbAddAluno.setSpacing(10);
            System.out.println("socorro");
            vbAddAluno.getChildren().addAll(lbTAdd,lbnumAl,ntfnumAl,lbNome,tfNome,lbEmail,tfEmail,lbCurso,Hbcursos,lbRamo,Hbramos,lbClass,ntfClass,cbestagio,btnSubAdd,btnVoltarL);
            System.out.println("Passei");
            this.setCenter(vbAddAluno);
        });
        btnSubAdd.setOnAction(actionEvent -> {
            if(verificaAdd()){
            if(!manager.findAluno(Long.parseLong(ntfnumAl.getText()))){
                Cursos aux = Cursos.LEI;
                Ramos auxR = Ramos.DA;
                if(!rbLei.isDisable())
                    aux = Cursos.LEI;
                else if(!rbLeiPL.isDisable()){
                    aux = Cursos.LEI_PL;
                }
                if(!rbDA.isDisable())
                    auxR =Ramos.DA;
                else if(!rbSI.isDisable()){
                    auxR = Ramos.SI;
                }else if(!rbRedes.isDisable()){
                    auxR = Ramos.RAS;
                }

                manager.addAluno(new Aluno(Long.parseLong(ntfnumAl.getText()),tfNome.getText(),tfEmail.getText(),aux,auxR,(Double.parseDouble(ntfClass.getText())/100),!cbestagio.isDisable()));
                Alert sucesso = new Alert(Alert.AlertType.INFORMATION,"Adicionado com sucesso",ButtonType.OK);
                sucesso.setTitle("Sucesso");
                sucesso.setHeaderText(String.format("Aluno : %s",ntfnumAl.getText()));
                sucesso.show();
                clearAdd();
                this.setCenter(PvBox);
            }else{
                Alert aAluno = new Alert(Alert.AlertType.INFORMATION,"Aluno já existe",ButtonType.OK);
                aAluno.setTitle("Alerta");
                aAluno.setHeaderText("ERRO");
                aAluno.setContentText(String.format("%s já existe",ntfnumAl.getText()));
                aAluno.show();
                clearAdd();
                this.setCenter(PvBox);
            }}else{
                Alert preenche = new Alert(Alert.AlertType.INFORMATION,"Tem de preencher",ButtonType.OK);
                preenche.setTitle("Alerta");
                preenche.setHeaderText("ERRO");
                preenche.setContentText(String.format("Tem de preencher ou selecionar todos os campos"));
                preenche.show();
            }
        });
        btnRem.setOnAction(actionEvent -> {
            VBox vBoxR = new VBox(lbTRem,lbnumAlrem,ntfnumAlrem,btnRemR,btnVoltarL);
            vBoxR.setAlignment(Pos.CENTER);
            vBoxR.setSpacing(10);
            this.setCenter(vBoxR);
        });
        btnRemR.setOnAction(actionEvent -> {
            if(manager.findAluno(Long.parseLong(ntfnumAlrem.getText()))){
                manager.remAluno(Long.parseLong(ntfnumAlrem.getText()));
                Alert sucessoR = new Alert(Alert.AlertType.INFORMATION,"Removido com Sucesso",ButtonType.OK);
                sucessoR.setTitle("Sucesso");
                sucessoR.setHeaderText(String.format("Aluno : %s",ntfnumAl.getText()));
                sucessoR.show();
                ntfnumAlrem.clear();
                this.setCenter(PvBox);
            }else{
                Alert aAlunoR = new Alert(Alert.AlertType.INFORMATION,"Aluno não existe",ButtonType.OK);
                aAlunoR.setTitle("Alerta");
                aAlunoR.setHeaderText("ERRO");
                aAlunoR.setContentText(String.format("%s não existe",ntfnumAl.getText()));
                aAlunoR.show();
                ntfnumAlrem.clear();
                this.setCenter(PvBox);
            }
        });
        btnImp.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("File open...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Alunos (*.csv)","*.csv"),
                    new FileChooser.ExtensionFilter("All files","*")
            );
            File file = fileChooser.showOpenDialog(this.getScene().getWindow());
            if(file != null) {
                manager.addAlunosFromFile(file.getAbsolutePath());
            }
        });
        btnExp.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Exporta Alunos...");
            fileChooser.setInitialDirectory(new File("."));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Alunos (*.csv)","*.csv"),
                    new FileChooser.ExtensionFilter("All files","*")
            );
            File file = fileChooser.showSaveDialog(this.getScene().getWindow());
            if(file != null){
                System.out.println(file.getAbsolutePath());
                try {
                    manager.exportAlunos(file.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }});
        btnEdit.setOnAction(actionEvent -> {
            VBox vbAddAluno = new VBox();
            vbAddAluno.setAlignment(Pos.CENTER);
            vbAddAluno.setSpacing(10);
            vbAddAluno.getChildren().addAll(lbTEdit,lbnumAlE,ntfnumAlE,lbNomeE,tfNomeE,lbEmailE,tfEmailE,lbCursoE,HbcursosE,lbRamoE,HbramosE,lbClassE,ntfClassE,cbestagioE,btnSubEdit,btnVoltarL);
            this.setCenter(vbAddAluno);
        });
        btnSubEdit.setOnAction(actionEvent -> {
            if(verificaEdit()){
                if(manager.findAluno(Long.parseLong(ntfnumAlE.getText()))){
                    Cursos aux = Cursos.LEI;
                    Ramos auxR = Ramos.DA;
                    if(!rbLeiE.isDisable())
                        aux = Cursos.LEI;
                    else if(!rbLeiPLE.isDisable()){
                        aux = Cursos.LEI_PL;
                    }
                    if(!rbDAE.isDisable())
                        auxR =Ramos.DA;
                    else if(!rbSIE.isDisable()){
                        auxR = Ramos.SI;
                    }else if(!rbRedesE.isDisable()){
                        auxR = Ramos.RAS;
                    }

                    manager.editaAluno(new Aluno(Long.parseLong(ntfnumAlE.getText()),tfNomeE.getText(),tfEmailE.getText(),aux,auxR,(Double.parseDouble(ntfClassE.getText())/100),!cbestagioE.isDisable()));
                    Alert sucesso = new Alert(Alert.AlertType.INFORMATION,"Editado com sucesso",ButtonType.OK);
                    sucesso.setTitle("Sucesso");
                    sucesso.setHeaderText(String.format("Aluno : %s",ntfnumAl.getText()));
                    sucesso.show();
                    clearEdit();
                    this.setCenter(PvBox);
                }else{
                    Alert aAluno = new Alert(Alert.AlertType.INFORMATION,"Aluno não existe",ButtonType.OK);
                    aAluno.setTitle("Alerta");
                    aAluno.setHeaderText("ERRO");
                    aAluno.setContentText(String.format("%s não existe",ntfnumAl.getText()));
                    aAluno.show();
                    clearEdit();
                    this.setCenter(PvBox);
                }}else{
                Alert preenche = new Alert(Alert.AlertType.INFORMATION,"Tem de preencher",ButtonType.OK);
                preenche.setTitle("Alerta");
                preenche.setHeaderText("ERRO");
                preenche.setContentText(String.format("Tem de preencher ou selecionar todos os campos"));
                preenche.show();
            }
        });
    }

    private void clearEdit() {
        ntfnumAlE.clear();
        ntfClassE.clear();
        tfNomeE.clear();
        tfEmailE.clear();
        rbRedesE.setSelected(false);
        rbSIE.setSelected(false);
        rbDAE.setSelected(false);
        rbLeiE.setSelected(false);
        rbLeiPLE.setSelected(false);
        cbestagioE.setSelected(false);
    }

    private boolean verificaEdit() {
        if(ntfnumAlE.getText().isEmpty()){
            return false;
        }
        if(tfNomeE.getText().isEmpty() )
            return false;
        if(tfEmailE.getText().isEmpty())
            return false;
        if(rbLeiE.isDisable() && rbLeiE.isDisable())
            return false;
        if(rbDAE.isDisable() && rbSIE.isDisable() && rbSIE.isDisable())
            return false;
        if(ntfClassE.getText().isEmpty())
            return false;
        return true;
    }

    private void clearAdd() {
        ntfnumAl.clear();
        ntfClass.clear();
        tfNome.clear();
        tfEmail.clear();
        rbRedes.setSelected(false);
        rbSI.setSelected(false);
        rbDA.setSelected(false);
        rbLei.setSelected(false);
        rbLeiPL.setSelected(false);
        cbestagio.setSelected(false);

    }

    private boolean verificaAdd() {
        if(ntfnumAl.getText().isEmpty()){
           return false;
        }
        if(tfNome.getText().isEmpty() )
            return false;
        if(tfEmail.getText().isEmpty())
            return false;
        if(rbLei.isDisable() && rbLei.isDisable())
            return false;
        if(rbDA.isDisable() && rbSI.isDisable() && rbSI.isDisable())
            return false;
        if(ntfClass.getText().isEmpty())
            return false;
        return true;
    }


    private void update() {
        if (manager.getState() != Apoio_poeState.GERIR_ALUNOS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);
    }

}
