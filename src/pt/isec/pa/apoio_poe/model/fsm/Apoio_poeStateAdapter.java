package pt.isec.pa.apoio_poe.model.fsm;

import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.data.Candidatura;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.data.propostas.Proposta;

import java.io.Serializable;

abstract public class Apoio_poeStateAdapter implements IApoio_poeState, Serializable {
    Apoio_poe ap;
    Apoio_poeContext context;

    public Apoio_poeStateAdapter(Apoio_poe ap, Apoio_poeContext context) {
        this.ap = ap;
        this.context = context;
    }

    protected void changeState(Apoio_poeState newState){
        context.changeState(newState.createState(context,ap));
    }

    @Override
    public Apoio_poeState getEstado() {
        return null;
    }

    @Override
    public boolean fechar() {
        return false;
    }

    @Override
    public boolean avancar() {
        return false;
    }

    @Override
    public void voltar() {
        return;
    }

    @Override
    public void gerirAlunos() {

    }

    @Override
    public String listaAlunos() {
        return null;
    }

    @Override
    public boolean addAluno(Aluno a) {
        return false;
    }

    @Override
    public boolean remAluno(long num) {
        return false;
    }

    @Override
    public boolean editAluno(Aluno a) {
        return false;
    }

    @Override
    public boolean findAluno(long num) {
        return false;
    }

    @Override
    public boolean findDocente(String email) {
        return false;
    }

    @Override
    public void gerirDocentes() {

    }

    @Override
    public String listaDocentes() {
        return null;
    }

    @Override
    public boolean addDocente(Docente docente) {
        return false;
    }

    @Override
    public boolean remDocente(String email) {
        return false;
    }

    @Override
    public boolean editDocente(Docente docente) {
        return false;
    }

    @Override
    public void gerirPropostas() {

    }

    @Override
    public String listaPropostas() {
        return null;
    }

    @Override
    public boolean addPropostas(Proposta p) {
        return false;
    }

    @Override
    public boolean remPropostas(String id_da_proposta) {
        return false;
    }

    @Override
    public boolean editPropostas(Proposta pt) {
        return false;
    }

    @Override
    public void gerirCandidaturas() {

    }

    @Override
    public void listaAlunos2() {

    }

    @Override
    public void listaPropostas2() {

    }

    @Override
    public void atribuicaoAuto() {

    }

    @Override
    public void atribuicaoMan() {

    }

    @Override
    public void listaAlunos3() {

    }

    @Override
    public void listaPropostas3() {

    }

    @Override
    public void gerirOrientadores() {

    }

    @Override
    public void listasFase4() {

    }

    @Override
    public Proposta getProposta(String id_da_proposta) {
        return null;
    }

    @Override
    public boolean isClose() {
        return false;
    }

    @Override
    public String getlistaAsCand() {
        return null;
    }

    @Override
    public String getlistaAcCand() {
        return null;
    }

    @Override
    public String getlistaAauto() {
        return null;
    }

    @Override
    public String getlistaAutoPropostas() {
        return null;
    }

    @Override
    public String listaPropostasDocentes() {
        return null;
    }

    @Override
    public String PropostasComCand() {
        return null;
    }

    @Override
    public String PropostasSemCand() {
        return null;
    }

    @Override
    public void addCandidatura(long numero, String projID) {

    }

    @Override
    public String listaCandidaturas() {
        return null;
    }

    @Override
    public boolean findCandidatura(long numAluno) {
        return false;
    }

    @Override
    public void remCandidatura(long numAluno) {

    }

    @Override
    public void remCandidatura(long num, String id_da_proposta) {

    }

    @Override
    public boolean addCandidatura(Candidatura c) {
        return false;
    }
}
