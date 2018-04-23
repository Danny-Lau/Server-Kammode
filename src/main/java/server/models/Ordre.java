package server.models;

public class Ordre {
    String ordreDato, ordrePris;
    int ordreID;


    public Ordre(String ordreDato, String ordrePris, int ordreID) {
        this.ordreDato = ordreDato;
        this.ordrePris = ordrePris;
        this.ordreID = ordreID;
    }

    public Ordre() {
    }

    public String getOrdreDato() {
        return ordreDato;
    }

    public void setOrdreDato(String ordreDato) {
        this.ordreDato = ordreDato;
    }

    public String getOrdrePris() {
        return ordrePris;
    }

    public void setOrdrePris(String ordrePris) {
        this.ordrePris = ordrePris;
    }

    public int getOrdreID() {
        return ordreID;
    }

    public void setOrdreID(int ordreID) {
        this.ordreID = ordreID;
    }
}
