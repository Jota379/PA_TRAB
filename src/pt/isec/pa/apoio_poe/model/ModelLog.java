package pt.isec.pa.apoio_poe.model;

import java.util.ArrayList;
import java.util.List;

public class ModelLog {
    private static ModelLog instance = null;

    public static ModelLog getInstance(){
        if(instance == null)
            instance = new ModelLog();
        return instance;
    }

    ArrayList<String> log;

    private ModelLog(){
        log = new ArrayList<>();
    }

    public void add(String msg){
        log.add(msg);
    }

    public List<String> getLog() {
        return new ArrayList<>(log);
    }

    public void reset(){
        log.clear();
    }

}
