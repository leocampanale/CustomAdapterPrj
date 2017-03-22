package it.poggiolevante.customadapterprj.data;

/**
 * Created by root on 21/03/17.
 */

public class Previsione {
   String giorno;
    String ora;
    String citta;
    double temp;
    double umidita;
    int cielo;  //  0=sereno; 1=nuvoloso; 2 = pioggia

    public Previsione(String giorno, String ora, String citta, double temp, double umidita, int cielo) {
        this.giorno = giorno;
        this.ora = ora;
        this.citta = citta;
        this.temp = temp;
        this.umidita = umidita;
        this.cielo = cielo;
    }

    public String getGiorno() {
        return giorno;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getUmidita() {
        return umidita;
    }

    public void setUmidita(double umidita) {
        this.umidita = umidita;
    }

    public int getCielo() {
        return cielo;
    }

    public void setCielo(int cielo) {
        this.cielo = cielo;
    }
}
