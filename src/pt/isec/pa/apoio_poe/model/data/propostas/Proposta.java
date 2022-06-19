package pt.isec.pa.apoio_poe.model.data.propostas;

import java.io.Serializable;

public class Proposta implements Serializable {
    private static int countID = 0;
    private static int getNewID() {
        return ++countID;
    }
    private int idTemp;
    String Pid;//Projeto ID
    long numAluno;

    public Proposta() {

        idTemp = getNewID();
        if(idTemp<10){
            Pid = "P00" + Integer.toString(idTemp);
        }
        else if(idTemp<100){
            Pid = "P0" + Integer.toString(idTemp);
        }
        else{
            Pid = "P" + Integer.toString(idTemp);
        }
    }

    public static void setCountID(int v){
        countID = v;
    }

    public String getPid() {
        return Pid;
    }

    public int getPidint(){
        String numstr;
        numstr=Pid.substring(1);
        System.out.println(numstr);
        int aux = Integer.parseInt(numstr);
        return aux;
    }

    public void setPid(String pid) {
        Pid = pid;
    }

    public String toStringFile() {
        return null;
    }

    public long getNumAluno() {
        return numAluno;
    }
}
