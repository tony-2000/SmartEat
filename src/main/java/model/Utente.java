package model;

import java.sql.Date;

public class Utente {

    private String CF;
    private String nome;
    private String cognome;
    private char sesso;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String email;
    private String residenza;
    private String password;
    private boolean amministratore;
    private boolean accepted;

    public String getCF() {return CF;}

    public void setCF(String CF) {this.CF = CF;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public char getSesso() {return sesso;}

    public void setSesso(char sesso) {this.sesso = sesso;}

    public Date getDataDiNascita() {return dataDiNascita;}

    public void setDataDiNascita(Date dataDiNascita) {this.dataDiNascita = dataDiNascita;}

    public String getLuogoDiNascita() {return luogoDiNascita;}

    public void setLuogoDiNascita(String luogoDiNascita) {this.luogoDiNascita = luogoDiNascita;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getResidenza() {return residenza;}

    public void setResidenza(String residenza) {this.residenza = residenza;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public boolean isAmministratore() {return amministratore;}

    public void setAmministratore(boolean amministratore) {this.amministratore = amministratore;}

    public boolean isAccepted() {return accepted;}

    public void setAccepted(boolean accepted) {this.accepted = accepted;}
}
