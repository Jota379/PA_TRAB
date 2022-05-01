package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.io.Serializable;
import java.util.Set;

public class Apoio_poeContext implements Serializable {
    private static final long serialVersionUID=1L;
    Apoio_poe ap;
    IApoio_poeState estado;

    public Apoio_poeContext() {
        this.ap = new Apoio_poe();
        this.estado = Apoio_poeState.LOAD_STATE.createState(this,ap);
    }

    public Apoio_poeState getEstado() {
        return estado.getEstado();
    }

    public void setEstado(IApoio_poeState estado) {
        this.estado = estado;
    }

    void changeState(IApoio_poeState newState){
        estado = newState;
    }

    public boolean fechar(){
        return estado.fechar();
    }

    public boolean avançar(){
        return estado.avancar();
    }

    void voltar(){
       estado.voltar();
    }

    void gerirAlunos(){
        estado.gerirAlunos();
    }

    public String listaAlunos() {
        return estado.listaAlunos();
    }

    public boolean addAluno(Aluno a) {
        return estado.addAluno(a);
    }

    public boolean remAluno(long num) {
        return estado.remAluno(num);
    }

    public boolean editAluno(Aluno a) {
        return estado.editAluno(a);
    }

    void gerirDocentes(){
        estado.gerirDocentes();
    }


    public String listaDocentes() {
        return estado.listaDocentes();
    }


    public boolean addDocente(Docente docente) {
        return estado.addDocente(docente);
    }

    public boolean remDocente(String email) {
        return estado.remDocente(email);
    }

    public boolean editDocente(Docente docente) {
        return estado.editDocente(docente);
    }

    void gerirPropostas(){
        estado.gerirPropostas();
    }

    public String listaPropostas() {
        return estado.listaPropostas();
    }

    public boolean addPropostas(Proposta p) {
        return estado.addPropostas(p);
    }

    public boolean remPropostas(String id_da_proposta) {
        return estado.remPropostas(id_da_proposta);
    }

    public boolean editPropostas(Proposta pt) {
        return estado.editPropostas(pt);
    }

    void gerirCandidaturas(){
        estado.gerirCandidaturas();
    }

    public void listaAlunos2() {

    }

    public void listaPropostas2() {

    }

    public void atribuicaoAuto() {

    }

    public void atribuicaoMan() {

    }

    public void listaAlunos3() {

    }

    public void listaPropostas3() {

    }

    void gerirOrientadores(){
        estado.gerirOrientadores();
    }

    public void listasFase4() {

    }

    public boolean findAluno(long num) {
        return estado.findAluno(num);
    }

    public boolean findDocente(String email) {
        return estado.findDocente(email);
    }

    public Proposta getProposta(String id_da_proposta) {
        return estado.getProposta(id_da_proposta);
    }

    public Set<Aluno> getAllAlunos() {
        return ap.listaAlunos;
    }

    public Set<Docente> getAllDocentes() {
        return ap.listaDocentes;
    }

    public Set<Proposta> getAllPropostas() {
        return ap.listaPropostas;
    }
}
