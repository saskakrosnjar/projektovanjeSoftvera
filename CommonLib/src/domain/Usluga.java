/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aleksandra
 */
public class Usluga implements AbstractDomainObject{
    private Long uslugaID;
    private Date datumIzdavanja;
    private Date datumIsteka;
    private Korisnik korisnik;
    private Bibliotekar bibliotekar;
    private ArrayList<StavkaUsluge> listaStavki;

    public Usluga() {
    }

    public Usluga(Long uslugaID, Date datumIzdavanja, Date datumIsteka, Korisnik korisnik, Bibliotekar bibliotekar, ArrayList<StavkaUsluge> listaStavki) {
        this.uslugaID = uslugaID;
        this.datumIzdavanja = datumIzdavanja;
        this.datumIsteka = datumIsteka;
        this.korisnik = korisnik;
        this.bibliotekar = bibliotekar;
        this.listaStavki = listaStavki;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public Long getUslugaID() {
        return uslugaID;
    }

    public void setUslugaID(Long uslugaID) {
        this.uslugaID = uslugaID;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Bibliotekar getBibliotekar() {
        return bibliotekar;
    }

    public void setBibliotekar(Bibliotekar bibliotekar) {
        this.bibliotekar = bibliotekar;
    }

    public Date getDatumIzdavanja() {
        return datumIzdavanja;
    }

    public void setDatumIzdavanja(Date datumIzdavanja) {
        this.datumIzdavanja = datumIzdavanja;
    }
    
    public ArrayList<StavkaUsluge> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(ArrayList<StavkaUsluge> listaStavki) {
        this.listaStavki = listaStavki;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.uslugaID);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usluga other = (Usluga) obj;
        if (this.uslugaID != other.uslugaID) {
            return false;
        }
        return true;
    }

    @Override
    public String getTableName() {
        return " Usluga ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "(datumIzdavanja, datumIsteka, korisnik, bibliotekar)";
    }

    @Override
    public String getInsertValues() {
        return "'" + new java.sql.Date(datumIzdavanja.getTime()) + "', '"
                + new java.sql.Date(datumIsteka.getTime()) +"', '" + korisnik.getKorisnikID()
                + "', '" + bibliotekar.getBibliotekarID()+ "' ";
    }

    @Override
    public String getUpdateValues() {
        return " datumIzdavanja= '" + new Timestamp(datumIzdavanja.getTime())+ "', datumIsteka= '" 
                + new Timestamp(datumIsteka.getTime())+"', korisnik= "+korisnik.getKorisnikID()+
                ", bibliotekar= "+bibliotekar.getBibliotekarID();
    }

    @Override
    public String getPrimaryKeyValue() {
        return " uslugaID = " + uslugaID;
    }

    @Override
    public String alias() {
        return " u ";
    }

    @Override
    public String join() {
        return " INNER JOIN Korisnik ko ON (ko.korisnikID = u.korisnik) "
		+ "INNER JOIN Bibliotekar b ON (b.bibliotekarID = u.bibliotekar) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Korisnik korisnik = new Korisnik(rs.getLong("korisnikID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getDate("datumRodjenja"),  rs.getString("kontakt"));
            
            
            Bibliotekar bibliotekar = new Bibliotekar(rs.getLong("bibliotekarID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("username"), rs.getString("password"));

            Usluga usluga = new Usluga(rs.getLong("uslugaID"),
                    rs.getDate("datumIzdavanja"), rs.getDate("datumIsteka"),
                    korisnik, bibliotekar, null);
 
            lista.add(usluga);
        }

        rs.close();
        return lista;
    }

    @Override
    public String condition() {
        return "";
    }

    
    
    
    
}
