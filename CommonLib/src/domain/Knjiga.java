/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Aleksandra
 */
public class Knjiga implements AbstractDomainObject{
    private Long knjigaID;
    private String naziv;
    private int kolicina;
    private Zanr zanr;
    private Autor autor;
    
    
    public Knjiga() {
    }

    public Knjiga(Long knjigaID, String naziv, int kolicina, Zanr zanr, Autor autor) {
        this.knjigaID = knjigaID;
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.zanr = zanr;
        this.autor = autor;
    }
    
    public Zanr getZanr() {
        return zanr;
    }

    public void setZanr(Zanr zanr) {
        this.zanr = zanr;
    }

    public Long getKnjigaID() {
        return knjigaID;
    }

    public void setKnjigaID(Long knjigaID) {
        this.knjigaID = knjigaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.knjigaID);
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
        final Knjiga other = (Knjiga) obj;
        if (this.knjigaID != other.knjigaID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return naziv;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String getTableName() {
        return " Knjiga ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (naziv, kolicina, zanr, autor)";
    }

    @Override
    public String getInsertValues() {
        return "'" + naziv + "', " + kolicina + ", "
                + "'" + zanr.toString() + "', " + autor.getAutorID() +" ";
    }

    @Override
    public String getUpdateValues() {
        return "naziv= '" + naziv + "', kolicina= " + kolicina + ", zanr='"
                +zanr.toString() + "', autor=" + autor.getAutorID();
    }

    @Override
    public String getPrimaryKeyValue() {
        return " knjigaID = "+knjigaID;
    }

    @Override
    public String alias() {
        return "k";
    }

    @Override
    public String join() {
        return "INNER JOIN Autor a ON (k.Autor = a.AutorID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
            Autor autor = new Autor(rs.getLong("autorID"),
                    rs.getString("ime"), rs.getString("drzava"));

            Knjiga knjiga = new Knjiga(rs.getLong("knjigaID"),
                    rs.getString("naziv"),rs.getInt("kolicina"),
                    Zanr.valueOf(rs.getString("zanr")), autor);

            lista.add(knjiga);
        }

        rs.close();
        return lista;
    }

    @Override
    public String condition() {
        return "";
    }
    
    
}
