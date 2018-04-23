package server.models;

public class vare {
   String vare_beskrivelse,  pris, variant_1;
   int vare_id, sælger_sælger_id, antal;

    public vare(String vare_beskrivelse, String pris, String variant_1, int vare_id, int sælger_sælger_id, int antal) {
        this.vare_beskrivelse = vare_beskrivelse;
        this.pris = pris;
        this.variant_1 = variant_1;
        this.vare_id = vare_id;
        this.sælger_sælger_id = sælger_sælger_id;
        this.antal = antal;
    }


    public vare() {
    }

    public String getVare_beskrivelse() {
        return vare_beskrivelse;
    }

    public void setVare_beskrivelse(String vare_beskrivelse) {
        this.vare_beskrivelse = vare_beskrivelse;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getVariant_1() {
        return variant_1;
    }

    public void setVariant_1(String variant_1) {
        this.variant_1 = variant_1;
    }

    public int getVare_id() {
        return vare_id;
    }

    public void setVare_id(int vare_id) {
        this.vare_id = vare_id;
    }

    public int getSælger_sælger_id() {
        return sælger_sælger_id;
    }

    public void setSælger_sælger_id(int sælger_sælger_id) {
        this.sælger_sælger_id = sælger_sælger_id;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}
