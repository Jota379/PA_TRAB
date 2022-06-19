package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.ModelLog;
import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.propostas.AutoProposta;
import pt.isec.pa.apoio_poe.model.data.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;
import pt.isec.pa.apoio_poe.model.fsm.*;
import pt.isec.pa.apoio_poe.ui.utils.PAInput;

import java.io.FileReader;
import java.util.Scanner;

public class Apoio_aoCMD {
    Apoio_poeManager fsm;

    public Apoio_aoCMD(Apoio_poeManager fsm) {
        this.fsm = fsm;
    }
    boolean finish = false;

    public void start(){
        ModelLog log = ModelLog.getInstance();

        while(!finish){
            for(String str : log.getLog())
                System.out.println("Log"+str);
            log.reset();
            switch (fsm.getState()){
                case LOAD_STATE -> loadUI();
                case CONFIGURACAO -> configuracaoUI();
                case GERIR_ALUNOS -> gerirAlunosUI();
                case GERIR_DOCENTES ->  gerirDocentesUI();
                case GERIR_PROPOSTAS -> gerirPropostasUI();
                case CANDIDATURA -> candidaturaUI();
                case GERIR_CAND -> gerirCandidaturaUI();
                case ATRIBUICAO_PROP -> atribuicaoPropostaUI();
                case ATRIBUICAO_ORIENTADORES -> atribuicaoOrientadoresUI();
                case GERIR_ORIENTADORES -> gerirOrientadoresUI();
                case CONSULTA -> consultaUI();
            }

        }
    }

    private void loadUI() {
        switch (PAInput.chooseOption("Carregar?","Carregar ficheiro Binario","CriarNovo","Quit")){
            case 1->fsm.load();
            case 2->fsm.avancar();
            default -> finish = true;
        }
    }

    private void configuracaoUI() {
        if (!fsm.isClose()) {
            switch (PAInput.chooseOption("Configuracao", "Gerir Alunos", "Gerir Docentes", "Gerir Propostas", "Fechar fase", "Avançar", "Savar Tudo")) {
                case 1 -> fsm.gerirAlunos();
                case 2 -> fsm.gerirDocentes();
                case 3 -> fsm.gerirPropostas();
                case 4 -> { if(!fsm.fechar())
                                System.out.println("Não existem propostas suficientes para todos os alunos");}
                case 5 -> fsm.avancar();
                case 6 -> fsm.save();
            }
        } else {
            switch (PAInput.chooseOption("Configuracao FECHADA", "ver Alunos", "ver Docentes", "ver Propostas", "Avançar", "Savar Tudo")) {
                case 1 -> System.out.println(fsm.listaAlunos());
                case 2 -> System.out.println(fsm.listaDocentes());
                case 3 -> System.out.println(fsm.listaPropostas());
                case 4 -> fsm.avancar();
                case 5 -> fsm.save();
            }
        }
    }

    private void gerirAlunosUI() {
        switch (PAInput.chooseOption("Configuracao\nGerir Alunos","Adicionar Aluno","Ver Alunos","Editar Aluno","Remover Aluno","Importar CSV","Exportar","Voltar")){
            case 1->addAluno();
            case 2-> System.out.println(fsm.listaAlunos());
            case 3->editaAluno();
            case 4->eliminaAluno();
            case 5->addAlunosFromFile(PAInput.readString("FILENAME: ",true));
            case 6->exportAlunostoFile(PAInput.readString("FILENAME: ",true));
            case 7->fsm.voltar();
        }

    }

    private void eliminaAluno() {
        long num =PAInput.readLong("Numero de Aluno: ");
        if(fsm.findAluno(num)){
            fsm.remAluno(num);
        }
        else{
            System.out.println("Esse numero de aluno não existe");
        }
    }

    boolean addAluno(){
        long num =PAInput.readLong("Numero de Aluno: ");
        String nome = PAInput.readString("Nome: ",false);
        String email = PAInput.readString("Email: ",true);
        int curso = PAInput.chooseOption("Curso","LEI","LEI-PL");
        int ramo = PAInput.chooseOption("Ramo","DA","RAS","SI");
        double classificao = PAInput.readNumber("Classificacao");
        boolean possibilidade=false;
        switch (PAInput.chooseOption("Possibilidade de estagio além de projeto?","Sim","Nao")){
            case 1->possibilidade=true;
            case 2->possibilidade=false;
        }

        return fsm.addAluno(new Aluno(num,nome,email, Cursos.values()[curso-1], Ramos.values()[ramo-1],classificao,possibilidade));

    }

    public void addAlunosFromFile(String filename) {
        //  FileReader fr;
        //  BufferedReader br;
        fsm.addAlunosFromFile(filename);
    }

    public void exportAlunostoFile(String filename) {
        try {
            fsm.exportAlunos(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    void editaAluno(){
        long num =PAInput.readLong("Numero de Aluno: ");
        if(fsm.findAluno(num)){
            String nome = PAInput.readString("Novo Nome: ",false);
            String email = PAInput.readString("Novo Email: ",true);
            int curso = PAInput.chooseOption("Novo Curso","LEI","LEI-PL");
            int ramo = PAInput.chooseOption("Novo Ramo","DA","RAS","SI");
            double classificao = PAInput.readNumber("Classificacao");
            boolean possibilidade=false;
            switch (PAInput.chooseOption("Possibilidade de estagio além de projeto?","Sim","Nao")){
                case 1->possibilidade=true;
                case 2->possibilidade=false;
            }
            if(fsm.editaAluno(new Aluno(num,nome,email,Cursos.values()[curso-1], Ramos.values()[ramo-1],classificao,possibilidade)))
                System.out.println("SUCESSO");
        }
        else{
            System.out.println("Esse numero de aluno não existe");
        }

    }

    private void gerirDocentesUI() {
        switch (PAInput.chooseOption("Configuracao\nGerir Docentes","Adicionar Docente","Ver Docentes","Editar Docentes","Remover Docente","Importar","Export","Voltar")){
            case 1->   {if(!addDocente())
                        System.out.println("Não Conseguiu adicionar o docente");}
            case 2-> System.out.println(fsm.listaDocentes());
            case 3->editaDocente();
            case 4->eliminaDocente();
            case 5->addDocentesFromFile(PAInput.readString("FILENAME: ",true));
            case 6->exportDocentestoFile(PAInput.readString("FILENAME: ",true));
            case 7->fsm.voltar();//funciona como um voltar
        }
    }

    private void eliminaDocente() {
        String email =PAInput.readString("Email do docente: ",true);
        if(fsm.findDocente(email)){
            fsm.remDocente(email);
        }
        else{
            System.out.println("Esse email de docente não existe");
        }
    }

    private void editaDocente() {
        String email =PAInput.readString("Email do docente: ",true);
        if(fsm.findDocente(email)){
            String nome = PAInput.readString("Novo Nome: ",false);
            if(fsm.editaDocente(new Docente(nome,email)))
                System.out.println("SUCESSO");
        }
        else{
            System.out.println("Esse email de docente não existe");
        }

    }

    private boolean addDocente() {
        String nome = PAInput.readString("Nome: ",false);
        String email = PAInput.readString("Email: ",true);
        return fsm.addDocente(new Docente(nome,email));

    }

    public void addDocentesFromFile(String filename) {
        fsm.addDocentesFromFile(filename);

    }

    public void exportDocentestoFile(String filename) {
        try {
            fsm.exportDocentes(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gerirPropostasUI() {
        switch (PAInput.chooseOption("Configuracao\nPropostas","Adicionar Propostas","Ver Propostas","Editar Propostas","Remover Propostas","Importar","Exportar","voltar")){
            case 1->addProposta();
            case 2-> System.out.println(fsm.listaPropostas());
            case 3->editaProposta();
            case 4->eliminaProposta();
            case 5->addPropostaFromFile(PAInput.readString("FILENAME: ",true));
            case 6->exportPropostatoFile(PAInput.readString("FILENAME: ",true));
            case 7->fsm.voltar();
        }

    }

    private void addPropostaFromFile(String filename) {
        fsm.addPropostaFromFile(filename);

    }

    private void exportPropostatoFile(String filename) {
        try {
            fsm.exportPropostas(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminaProposta() {
        if(!fsm.eliminaProposta(PAInput.readString("ID da Proposta",true)))
            System.out.println("Não Existe Proposta com esse id");

    }

    private void editaProposta() {
        Proposta pt;
        pt=fsm.getProposta(PAInput.readString("ID da Proposta",true));
        if(pt==null) {
            System.out.println("Não existe uma proposta com esse ID");
            return;
        }
        String pid = pt.getPid();
        if(pt instanceof Estagio){
            String ramos = pedeRamosProp();
            String titulo = PAInput.readString("Novo Titulo: ",false);
            String entidade = PAInput.readString("Nova Entidade: ",false);
            Long numero = PAInput.readLong("Novo Numero do Aluno pretendido(caso não tenha,coloque -1): ");
            if(numero == -1){
                fsm.editaProposta(new Estagio(ramos,titulo,entidade,pid));
            }else{
                fsm.editaProposta(new Estagio(ramos,titulo,entidade,numero,pid));
            }
        }else if(pt instanceof Projeto){
            String ramos = pedeRamosProp();
            String titulo = PAInput.readString("Titulo: ",false);
            String email = PAInput.readString("email do docente: ",false);
            if(!fsm.findDocente(email)) {
                System.out.println("Docente Nao Registado");
                return;
            }
            Long numero = PAInput.readLong("Numero do Aluno pretendido(caso não tenha,coloque -1): ");
            if(numero == -1){
                fsm.editaProposta(new Projeto(ramos,titulo,email,pid));
            }else{
                if(!fsm.findAluno(numero)){
                    System.out.println("Aluno Nao Registado");
                    return;
                }
                fsm.editaProposta(new Projeto(ramos,titulo,email,numero,pid));
            }
        }else if(pt instanceof AutoProposta){
            String titulo = PAInput.readString("Novo Titulo: ",false);
            Long numero = PAInput.readLong("Novo Numero do Aluno a propor: ");
            if(!fsm.findAluno(numero)){
                System.out.println("Aluno Nao Registado");
                return;
            }
            fsm.editaProposta(new AutoProposta(titulo,numero,pid));
        }
    }

    private void addProposta() {
        switch (PAInput.chooseOption("Tipo de Proposta","T1-Estágio","T2-Projeto","T3-Estagio/Projeto Autoproposto")){
            case 1->addPropostaT1();
            case 2->addPropostaT2();
            case 3->addPropostaT3();
        }
    }

    private void addPropostaT3() {
        String titulo = PAInput.readString("Titulo: ",false);
        Long numero = PAInput.readLong("Numero do Aluno a propor: ");
        if(!fsm.findAluno(numero)){
            System.out.println("Aluno Nao Registado");
            return;
        }
        fsm.addProposta(new AutoProposta(titulo,numero));
    }

    private void addPropostaT2() {
        String ramos = pedeRamosProp();
        String titulo = PAInput.readString("Titulo: ",false);
        String email = PAInput.readString("email do docente: ",false);
        if(!fsm.findDocente(email)) {
            System.out.println("Docente Nao Registado");
            return;
        }
        Long numero = PAInput.readLong("Numero do Aluno pretendido(caso não tenha,coloque -1): ");
        if(numero == -1){
            fsm.addProposta(new Projeto(ramos,titulo,email));
        }else{
            if(!fsm.findAluno(numero)){
                System.out.println("Aluno Nao Registado");
                return;
            }
            fsm.addProposta(new Projeto(ramos,titulo,email,numero));
        }
    }

    private String pedeRamosProp(){
        switch (PAInput.chooseOption("Tipo de Proposta","DA","RAS","SI","DA | RAS","DA | SI","RAS | SI","DA | RAS | SI")){
            case 1 -> {return "DA";}
            case 2 -> {return "RAS";}
            case 3-> {return "SI";}
            case 4-> {return "DA | RAS";}
            case 5-> {return "DA | SI";}
            case 6-> {return "RAS | SI";}
            case 7->{return "DA | RAS | SI";}
        }
        return null;
    }

    private void addPropostaT1() {
        String ramos = pedeRamosProp();
        String titulo = PAInput.readString("Titulo: ",false);
        String entidade = PAInput.readString("Entidade: ",false);
        Long numero = PAInput.readLong("Numero do Aluno pretendido(caso não tenha,coloque -1): ");
        if(numero == -1){
            fsm.addProposta(new Estagio(ramos,titulo,entidade));
        }else{
            fsm.addProposta(new Estagio(ramos,titulo,entidade,numero));
        }


    }

    private void candidaturaUI() {
        switch (PAInput.chooseOption("Candidatura(EM DESENVOLVIMENTO)","Gerir Candidaturas","Listas de Alunos","Listas de Propostas","Fechar","Avançar","voltar")){
            case 1->fsm.gerirCandidaturas();
            case 2->listasdealunosUI();
            case 3->listadepropostasUI();
            case 4->fsm.fechar();
            case 5->fsm.avancar();
            case 6 ->fsm.voltar();
        }
    }

    private void listadepropostasUI() {
        switch (PAInput.chooseOption("Gerir Candidaturas listas de Propostas","AutoPropostas","Propostas Docentes","Propostas com Cand","Propostas sem cand","voltar")) {
            case 1 -> System.out.println(fsm.listaAutoPropostas());
            case 2 -> System.out.println(fsm.listaPropostasDocentes());
            case 3 -> System.out.println(fsm.PropostasComCand());
            case 4 -> System.out.println(fsm.PropostasSemCand());
            case 5 -> fsm.voltar();
        }
    }

    private void listasdealunosUI(){
        switch (PAInput.chooseOption("Candidatura listas de alunos","com autoproposta","com candidatura já registada","sem candidatura registada","voltar")){
            case 1-> System.out.println(fsm.listaAauto());
            case 2-> System.out.println(fsm.listaAcCand());
            case 3-> System.out.println(fsm.listaAsCand());
            case 4 ->candidaturaUI();
        }
    }


    private void gerirCandidaturaUI() {
        switch (PAInput.chooseOption("CANDIDATURA\nGerir Candidaturas","Adicionar Candidaturas","Ver Candidaturas","Editar Candidaturas","Remover Candidaturas","Importar CSV","Exportar","Voltar")){
            case 1->addCandidatura();
            case 2-> System.out.println(fsm.listaCandidaturas());
            case 3->editaCandidaturas();
            case 4->eliminaCandidaturas();
            case 5->addCandidaturaFromFile(PAInput.readString("FILENAME: ",true));
            case 6->exportCandidaturatoFile(PAInput.readString("FILENAME: ",true));
            case 7->fsm.voltar();
        }

    }

    private void exportCandidaturatoFile(String filename) {
        try {
            fsm.exportCandidaturas(filename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCandidaturaFromFile(String readString) {
        fsm.addCandidaturaFromFile(readString);
    }

    private void eliminaCandidaturas() {
        long numAluno =PAInput.readLong("Numero do aluno a candidatar-se: ");
        if(fsm.findCandidatura(numAluno)){
            fsm.remCandidatura(numAluno);
        }
        else{
            System.out.println("Esse aluno não se esta a candidatar ou não existe");
        }
    }


    private void editaCandidaturas() {
        long num =PAInput.readLong("Numero de Aluno: ");
        if(fsm.findCandidatura(num)){
            switch (PAInput.chooseOption("Edição Candidatura","remoção de candidatura a um projeto","Adicionar um projeto à candidatura")){
                case 1 -> fsm.remCandidatura(num,PAInput.readString("ID da Proposta",true));
                case 2-> fsm.addCandidatura(num,PAInput.readString("ID da Proposta",true));
            }
        }
        else{
            System.out.println("Aluno não tem candidatura");
        }

    }

    private void addCandidatura() {
        long numero = PAInput.readLong("Numero do Aluno numero de aluno do Candidato: ");
        if(!fsm.findAluno(numero)){
            System.out.println("Aluno Nao Registado");
            return;
        }
        String projID = PAInput.readString("ID do projeto: ",true);
        fsm.addCandidatura(numero,projID);
    }

    private void atribuicaoPropostaUI() {
        switch (PAInput.chooseOption("Atribuicao Propostas(EM DESENVOLVIMENTO)","Avançar","voltar")){
            case 1->fsm.avancar();
            case 2 ->fsm.voltar();
        }
    }

    private void atribuicaoOrientadoresUI() {
        switch (PAInput.chooseOption("AtribuicaoOrientadores(EM DESENVOLVIMENTO)","Gerir Orientadores","Avançar","voltar")){
            case 1->fsm.gerirOrientadores();
            case 2->fsm.avancar();
            case 3 ->fsm.voltar();
        }
    }

    private void gerirOrientadoresUI() {
        switch (PAInput.chooseOption("Gerir Orientadores(EM DESENVOLVIMENTO)","voltar")){
            case 1 ->fsm.voltar();
        }
    }

    private void consultaUI() {
        switch (PAInput.chooseOption("Cunsulta(EM DESENVOLVIMENTO)","sair")){
            case 1 ->finish=true;
        }
    }
}
