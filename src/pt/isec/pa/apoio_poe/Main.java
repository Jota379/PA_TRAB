package pt.isec.pa.apoio_poe;

import pt.isec.pa.apoio_poe.model.fsm.Apoio_poeManager;
import pt.isec.pa.apoio_poe.ui.text.Apoio_aoCMD;

public class Main {

    public static void main(String[] args) {
        Apoio_poeManager apm;
        Apoio_aoCMD apcmd;
        apm = new Apoio_poeManager();
        apcmd = new Apoio_aoCMD(apm);
        apcmd.start();
    }
}
