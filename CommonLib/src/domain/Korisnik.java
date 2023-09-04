/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Aleksandra
 */
public class Korisnik implements AbstractDomainObject{
    private Long korisnikID;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String kontaktTelefon;
    //private boolean zaduzen;

    public Korisnik() {
    }

    public Korisnik(Long korisnikID, String ime, String prezime, Date datumRodjenja, String kontaktTelefon) {
        this.korisnikID = korisnikID;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.kontaktTelefon = kontaktTelefon;
    }

    public String getKontaktTelefon() {
        return kontaktTelefon;
    }

    public void setKontaktTelefon(String kontaktTelefon) {
        this.kontaktTelefon = kontaktTelefon;
    }

    public Long getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(Long korisnikID) {
        this.korisnikID = korisnikID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.korisnikID);
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
        final Korisnik other = (Korisnik) obj;
        if (this.korisnikID != other.korisnikID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }

    @Override
    public String getTableName() {
        return " Korisnik ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (ime, prezime, datumRodjenja, kontakt)";
    }

    @Override
    public String getInsertValues() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + new java.sql.Date(datumRodjenja.getTime()) + "', '" + kontaktTelefon +"' ";
    }

    @Override
    public String getUpdateValues() {
         return " prezime = '" + prezime + "', kontakt = '" + kontaktTelefon + "' ";
    }

    @Override
    public String getPrimaryKeyValue() {
        return "korisnikID="+korisnikID;
    }

    @Override
    public String alias() {
        return " ko ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Korisnik korisnik = new Korisnik(rs.getLong("korisnikID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getDate("datumRodjenja"),  rs.getString("kontakt"));

            lista.add(korisnik);
        }

        rs.close();
        return lista;
    }

    @Override
    public String condition() {
        return "";
    }

}
