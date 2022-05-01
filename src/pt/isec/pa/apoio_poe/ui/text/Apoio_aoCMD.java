package pt.isec.pa.apoio_poe.ui.text;

import pt.isec.pa.apoio_poe.model.ModelLog;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Cursos;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.Ramos;
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
        switch (PAInput.chooseOption("Configuracao","Gerir Alunos","Gerir Docentes","Gerir Propostas","Fechar fase","Avançar","Savar Tudo")){
            case 1->fsm.gerirAlunos();
            case 2->fsm.gerirDocentes();
            case 3->fsm.gerirPropostas();
            case 4->fsm.fechar();
            case 5->fsm.avancar();
            case 7->fsm.save();
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
        try(Scanner sc = new Scanner(new FileReader(filename))){
            while(sc.hasNextLine()){
                try{
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");
                    System.out.println(dividedLine[0]);
                    if(dividedLine.length!=7)
                        throw new Exception("Número de itens na linha diferente do esperado:\t" + line);


                    long numAluno = Long.parseLong(dividedLine[0]);
                    String nomeAluno = dividedLine[1];
                    String emailAluno = dividedLine[2];

                    if(!dividedLine[3].equals("LEI-PL") && !dividedLine[3].equals("LEI"))
                        throw new Exception("Curso diferente do esperado:\t" + dividedLine[3]);
                    String curso = dividedLine[3];

                    if(!dividedLine[4].equals("SI") && !dividedLine[4].equals("DA")&& !dividedLine[4].equals("RAS"))
                        throw new Exception("Ramo diferente do esperado:\t" + dividedLine[4]);
                    String ramo = dividedLine[4];

                    double classif = Double.parseDouble(dividedLine[5]);

                    boolean estagio=false;
                    if(dividedLine[6].equals("true"))
                        estagio = true;
                    else if(dividedLine[6].equals("false"))
                        estagio = false;
                    else
                        throw new Exception("Valor boolean diferente do esperado:\t" + dividedLine[6]);

                    Aluno newAluno = new Aluno(numAluno, nomeAluno, emailAluno, Cursos.valueOf(curso),Ramos.valueOf(ramo), classif, estagio);

                    if(!fsm.addAluno(newAluno))
                        throw new Exception("Aluno com este email já existe:\t" + dividedLine[2]);
                }catch(Exception e){
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Erro: Ficheiro <"+filename+"> contém falha");
        }
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
        try(Scanner sc = new Scanner(new FileReader(filename))){
            while(sc.hasNextLine()){
                try{
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");

                    String nome = dividedLine[0];
                    String email =dividedLine[1];

                    Docente newDocente = new Docente(nome, email);

                    if(!fsm.addDocente(newDocente))
                        throw new Exception("Docente com este email já existe:\t" + dividedLine[1]);
                }catch(Exception e){
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Erro: Ficheiro <"+filename+"> contém falha");
        }
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
        try (Scanner sc = new Scanner(new FileReader(filename))) {
            while (sc.hasNextLine()) {
                Proposta newProposta;

                try {
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");

                    String tipo = dividedLine[0];
                    if (tipo.equals("T1")) {
                        String idProp = dividedLine[1];
                        if (idProp.length() != 4)
                            throw new Exception("Proposta com id errado\t" + idProp);
                        String ramos = dividedLine[2];

                        String titulo = dividedLine[3];
                        String entidade = dividedLine[4];
                        System.out.println(entidade);


                        if (dividedLine.length == 5)
                            if (!fsm.addProposta(new Estagio(ramos, titulo, entidade, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                        else if (dividedLine.length == 6) {
                            long numAluno = Long.parseLong(dividedLine[5]);
                                if (!fsm.addProposta(new Estagio(ramos, titulo, entidade, numAluno, idProp)))
                                    throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);

                        } else
                            throw new Exception("Estágio com numero de informações erradas\t" + line);

                    } else if (tipo.equals("T2")) {
                        String idProp = dividedLine[1];
                        if (idProp.length() != 4)
                            throw new Exception("Proposta com id errado\t" + idProp);
                        String ramos = dividedLine[2];
                        String titulo = dividedLine[3];
                        String docente = dividedLine[4];


                        if (dividedLine.length == 5) {
                            if (!fsm.addProposta(new Projeto(ramos, titulo, docente, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                        }else if (dividedLine.length == 6) {
                            long numAluno = Long.parseLong(dividedLine[5]);
                            if (!fsm.addProposta(new Projeto(ramos, titulo, docente, numAluno, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                        }else{
                            throw new Exception("Projeto com numero de informações erradas\t" + line);
                        }

                    } if (tipo.equals("T3")) {
                        String idProp = dividedLine[1];
                        if (idProp.length() != 4)
                            throw new Exception("Proposta com id errado\t" + idProp);
                        String titulo = dividedLine[2];
                        long numAluno = Long.parseLong(dividedLine[3]);


                        if (dividedLine.length == 4)
                            if (!fsm.addProposta(new AutoProposta( titulo, numAluno, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                        else{
                            throw new Exception("Projeto com numero de informações erradas\t" + line);
                        }
                    } else {
                        throw new Exception("Proposta com tipo desconhecido\t" + tipo);
                    }


                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro: Ficheiro <" + filename + "> contém falha");
        }
        fsm.arranjaPropostaID();
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
        switch (PAInput.chooseOption("Tipo de Proposta","PA","RAS","SI","PA | RAS","PA | SI","RAS | SI","PA | RAS | SI")){
            case 1 -> {return "PA";}
            case 2 -> {return "RAS";}
            case 3-> {return "SI";}
            case 4-> {return "PA | RAS";}
            case 5-> {return "PA | SI";}
            case 6-> {return "RAS | SI";}
            case 7->{return "PA | RAS | SI";}
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
        switch (PAInput.chooseOption("Candidatura(EM DESENVOLVIMENTO)","Gerir Candidaturas","Avançar","voltar")){
            case 1->fsm.gerirCandidaturas();
            case 2->fsm.avancar();
            case 3 ->fsm.voltar();
        }
    }

    private void gerirCandidaturaUI() {
        switch (PAInput.chooseOption("Gerir Candidaturas(EM DESENVOLVIMENTO)","voltar")){
            case 1 ->fsm.voltar();
        }
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
