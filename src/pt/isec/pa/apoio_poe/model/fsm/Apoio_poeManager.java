package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.io.*;

public class Apoio_poeManager{
    Apoio_poeContext apc;
    private static final String FILENAME = "apoe.bin";

    public Apoio_poeManager() {
        this.apc = new Apoio_poeContext();
    }

    public void load() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))){
            Apoio_poeContext newAPC = (Apoio_poeContext) ois.readObject();
            apc = newAPC;
        }catch (Exception e){
            System.err.println("ERRO A FAZER LOAD");
        }
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
    }

    public void gerirAlunos(){
        apc.gerirAlunos();
    }
    public void gerirDocentes(){
        apc.gerirDocentes();
    }
    public void gerirPropostas(){
        apc.gerirPropostas();
    }
    public void gerirCandidaturas(){
        apc.gerirCandidaturas();
    }
    public void gerirOrientadores(){
        apc.gerirOrientadores();
    }

    public void fechar() {
        apc.fechar();
    }

    public void export() {
    }

    public boolean addAluno(Aluno a) {
        return apc.addAluno(a);
    }

    public String listaAlunos() {
        return apc.listaAlunos();
    }

    public boolean editaAluno(Aluno a) {
        return apc.editAluno(a);
    }

    public boolean findAluno(long num) {
        return apc.findAluno(num);
    }


    public void remAluno(long num) {
        apc.remAluno(num);
    }

    public void voltar() {
        apc.voltar();
    }

    public boolean addDocente(Docente docente) {
        return apc.addDocente(docente);
    }

    public String listaDocentes() {
        return apc.listaDocentes();
    }

    public boolean findDocente(String email) {
        return apc.findDocente(email);
    }

    public boolean editaDocente(Docente docente) {
        return apc.editDocente(docente);
    }

    public void remDocente(String email) {
        apc.remDocente(email);
    }

    public String listaPropostas() {
        return apc.listaPropostas();
    }

    public Boolean addProposta(Proposta p) {
        return apc.addPropostas(p);
    }

    public Proposta getProposta(String id_da_proposta) {
        return apc.getProposta(id_da_proposta);
    }

    public void editaProposta(Proposta pt) {
        apc.editPropostas(pt);
    }

    public boolean eliminaProposta(String id_da_proposta) {
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
}
