package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.*;
import pt.isec.pa.apoio_poe.model.data.propostas.AutoProposta;
import pt.isec.pa.apoio_poe.model.data.propostas.Estagio;
import pt.isec.pa.apoio_poe.model.data.propostas.Projeto;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Apoio_poeManager implements Serializable{
    Apoio_poeContext apc;
    PropertyChangeSupport pcs;
    private static final String FILENAME = "apoe.bin";

    public Apoio_poeManager() {
        this.apc = new Apoio_poeContext();
        pcs = new PropertyChangeSupport(this);
    }

    public void load() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))){
            Apoio_poeContext newAPC = (Apoio_poeContext) ois.readObject();
            apc = newAPC;
        }catch (Exception e){
            System.err.println("ERRO A FAZER LOAD");
        }
        pcs.firePropertyChange(null,null,null);
    }

    public void save() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            oos.writeObject(apc);
        }catch (Exception e){
            System.err.println("ERRO A SALVAR");
        }
    }

    public Apoio_poeState getState() {
        return apc.getEstado();
    }

    public void avancar() {
        apc.avançar();
        pcs.firePropertyChange(null,null,null);
    }

    public void gerirAlunos(){
        apc.gerirAlunos();
        pcs.firePropertyChange(null,null,null);
    }
    public void gerirDocentes(){
        apc.gerirDocentes();
        pcs.firePropertyChange(null,null,null);
    }
    public void gerirPropostas(){
        apc.gerirPropostas();
        pcs.firePropertyChange(null,null,null);
    }
    public void gerirCandidaturas(){
        apc.gerirCandidaturas();
        pcs.firePropertyChange(null,null,null);
    }
    public void gerirOrientadores(){
        apc.gerirOrientadores();
        pcs.firePropertyChange(null,null,null);
    }

    public boolean fechar() {
        pcs.firePropertyChange(null,null,null);
        return apc.fechar();
    }

    public boolean isClose(){
        return apc.isClose();
    }

    public void export() {
    }

    public boolean addAluno(Aluno a) {
        pcs.firePropertyChange(null,null,null);
        return apc.addAluno(a);
    }

    public String listaAlunos() {
        String str = apc.listaAlunos();
        pcs.firePropertyChange(null,null,null);
        return str;
    }

    public boolean editaAluno(Aluno a) {
        pcs.firePropertyChange(null,null,null);
        return apc.editAluno(a);
    }

    public boolean findAluno(long num) {

        return apc.findAluno(num);
    }


    public void remAluno(long num) {
        apc.remAluno(num);
        pcs.firePropertyChange(null,null,null);
    }

    public void voltar() {
        apc.voltar();
        pcs.firePropertyChange(null,null,null);
    }

    public boolean addDocente(Docente docente) {
        pcs.firePropertyChange(null,null,null);
        return apc.addDocente(docente);
    }

    public String listaDocentes() {
        return apc.listaDocentes();
    }

    public boolean findDocente(String email) {

        return apc.findDocente(email);
    }

    public boolean editaDocente(Docente docente) {
        pcs.firePropertyChange(null,null,null);
        return apc.editDocente(docente);
    }

    public void remDocente(String email) {
        pcs.firePropertyChange(null,null,null);
        apc.remDocente(email);
    }

    public String listaPropostas() {
        return apc.listaPropostas();
    }

    public Boolean addProposta(Proposta p) {
        pcs.firePropertyChange(null,null,null);
        return apc.addPropostas(p);
    }

    public Proposta getProposta(String id_da_proposta) {
        return apc.getProposta(id_da_proposta);
    }

    public void editaProposta(Proposta pt) {
        pcs.firePropertyChange(null,null,null);
        apc.editPropostas(pt);
    }

    public boolean eliminaProposta(String id_da_proposta) {
        pcs.firePropertyChange(null,null,null);
        return apc.remPropostas(id_da_proposta);
    }

    public void exportAlunos(String filename) throws Exception {
        FileWriter fw = null;
        try {
            File file = new File(filename);
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(Aluno a: apc.getAllAlunos())
                pw.println(a.toStringFile());
            pw.flush();
        }catch(IOException e ) {
            System.err.println("Error!!!!!");
            e.printStackTrace();
            return;
        }catch (Exception e){
            System.err.println("ERROR 1");
            throw new Exception("O meu erro");
        } finally {
            if(fw != null)
                fw.close();
        }
    }

    public void exportDocentes(String filename) throws Exception {
        FileWriter fw = null;
        try {
            File file = new File(filename);
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(Docente d: apc.getAllDocentes())
                pw.println(d.toStringFile());
            pw.flush();
        }catch(IOException e ) {
            System.err.println("Error!!!!!");
            e.printStackTrace();
            return;
        }catch (Exception e){
            System.err.println("ERROR 1");
            throw new Exception("O meu erro");
        } finally {
            if(fw != null)
                fw.close();
        }
    }

    public void exportPropostas(String filename) throws Exception {
        FileWriter fw = null;
        try {
            File file = new File(filename);
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(Proposta p: apc.getAllPropostas())
                pw.println(p.toStringFile());
            pw.flush();
        }catch(IOException e ) {
            System.err.println("Error!!!!!");
            e.printStackTrace();
            return;
        }catch (Exception e){
            System.err.println("ERROR 1");
            throw new Exception("O meu erro");
        } finally {
            if(fw != null)
                fw.close();
        }
    }

    public void arranjaPropostaID() {
        int aux=0;
        for (Proposta p : apc.getAllPropostas()) {
            if(p.getPidint()>aux)
                aux = p.getPidint();
        }
        Proposta.setCountID(aux);
    }

    public String listaAauto() {
        return apc.getlistaAauto();
    }
    public String listaAcCand() {
        return apc.getlistaAcCand();
    }
    public String listaAsCand() {
        return apc.getlistaAsCand();
    }

    public String listaAutoPropostas() {
        return apc.getlistaAutoPropostas();
    }

    public String listaPropostasDocentes() {
        return apc.listaPropostasDocentes();
    }

    public String PropostasComCand() {
        return apc.PropostasComCand();
    }

    public String PropostasSemCand() {
        return apc.PropostasSemCand();
    }

    public String listaCandidaturas() {
        return apc.listaCandidaturas();
    }

    public void addCandidatura(long numero, String projID) {
        apc.addCandidatura(numero,projID);
    }

    private boolean addCandidatura(Candidatura c){
       return apc.addCandidatura(c);
    }

    public boolean findCandidatura(long numAluno) {
        return apc.findCandidatura(numAluno);
    }

    public void remCandidatura(long numAluno) {
        apc.remCandidatura(numAluno);
    }

    public void remCandidatura(long num, String id_da_proposta) {
        apc.remCandidatura(num,id_da_proposta);
    }

    public void addAlunosFromFile(String filename) {
        pcs.firePropertyChange(null,null,null);
        //  FileReader fr;
        //  BufferedReader br;
        try(Scanner sc = new Scanner(new FileReader(filename))){
            while(sc.hasNextLine()){
                try{
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");
                    System.out.println(dividedLine[0]);
                    //if(dividedLine.length!=7)
                    //    throw new Exception("Número de itens na linha diferente do esperado:\t" + line);


                    long numAluno = Long.parseLong(dividedLine[0]);
                    String nomeAluno = dividedLine[1];
                    String emailAluno = dividedLine[2];

                    if(!dividedLine[3].equals("LEI-PL") && !dividedLine[3].equals("LEI"))
                        throw new Exception("Curso diferente do esperado:\t" + dividedLine[3]);
                    if(dividedLine[3].equals("LEI-PL"))
                        dividedLine[3]= "LEI_PL";
                    String curso = dividedLine[3];

                    if(!dividedLine[4].equals("SI") && !dividedLine[4].equals("DA")&& !dividedLine[4].equals("RAS"))
                        throw new Exception("Ramo diferente do esperado:\t" + dividedLine[4]);
                    String ramo = dividedLine[4];

                    double classif = Double.parseDouble(dividedLine[5]);

                    boolean estagio;
                    if(dividedLine[6].equals("true"))
                        estagio = true;
                    else if(dividedLine[6].equals("false"))
                        estagio = false;
                    else
                        throw new Exception("Valor boolean diferente do esperado:\t" + dividedLine[6]);

                    Aluno newAluno = new Aluno(numAluno, nomeAluno, emailAluno, Cursos.valueOf(curso), Ramos.valueOf(ramo), classif, estagio);
                    boolean verifica = addAluno(newAluno);
                    if(!verifica)
                        throw new Exception("Aluno com este numero já existe:\t" + dividedLine[0]);
                }catch(Exception e){
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Erro: Ficheiro <"+filename+"> contém falha");
        }
    }

    public void addDocentesFromFile(String filename) {
        pcs.firePropertyChange(null,null,null);
        try(Scanner sc = new Scanner(new FileReader(filename))){
            while(sc.hasNextLine()){
                try{
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");

                    String nome = dividedLine[0];
                    String email =dividedLine[1];

                    Docente newDocente = new Docente(nome, email);

                    if(!addDocente(newDocente))
                        throw new Exception("Docente com este email já existe:\t" + dividedLine[1]);
                }catch(Exception e){
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Erro: Ficheiro <"+filename+"> contém falha");
        }
    }

    public void addPropostaFromFile(String filename) {
        pcs.firePropertyChange(null,null,null);
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
                            if (!addProposta(new Estagio(ramos, titulo, entidade, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                            else if (dividedLine.length == 6) {
                                long numAluno = Long.parseLong(dividedLine[5]);
                                if (!addProposta(new Estagio(ramos, titulo, entidade, numAluno, idProp)))
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
                            if (!addProposta(new Projeto(ramos, titulo, docente, idProp)))
                                throw new Exception("Proposta com este id já existe:\t" + dividedLine[2]);
                        }else if (dividedLine.length == 6) {
                            long numAluno = Long.parseLong(dividedLine[5]);
                            if (!addProposta(new Projeto(ramos, titulo, docente, numAluno, idProp)))
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
                            if (!addProposta(new AutoProposta( titulo, numAluno, idProp)))
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
        arranjaPropostaID();
    }

    public void addCandidaturaFromFile(String filename) {
        pcs.firePropertyChange(null,null,null);
        try(Scanner sc = new Scanner(new FileReader(filename))){
            while(sc.hasNextLine()){
                try{
                    String line = sc.nextLine();
                    String[] dividedLine = line.split(",");

                    long num = Long.parseLong(dividedLine[0]);
                    Set<String> pids = new HashSet<>();
                    for(int i = 1;i< dividedLine.length;i++){
                        pids.add(dividedLine[i]);
                    }
                    Candidatura c = new Candidatura(pids,num);
                    if(!addCandidatura(c))
                        throw new Exception("Docente com este email já existe:\t" + dividedLine[1]);
                }catch(Exception e){
                    continue;
                }
            }
        }catch (Exception e){
            System.err.println("Erro: Ficheiro <"+filename+"> contém falha");
        }
    }


    public void exportCandidaturas(String filename) throws Exception{
        pcs.firePropertyChange(null,null,null);
        FileWriter fw = null;
        try {
            File file = new File(filename);
            fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for(Candidatura c: apc.getAllCandidaturas())
                pw.println(c.toStringFile());
            pw.flush();
        }catch(IOException e ) {
            System.err.println("Error!!!!!");
            e.printStackTrace();
            return;
        }catch (Exception e){
            System.err.println("ERROR 1");
            throw new Exception("O meu erro");
        } finally {
            if(fw != null)
                fw.close();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
}
