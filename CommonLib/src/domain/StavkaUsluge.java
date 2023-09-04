/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Aleksandra
 */
public class StavkaUsluge implements AbstractDomainObject{
    private Usluga usluga;
    private int rbStavke;
    private Knjiga knjiga;

    public StavkaUsluge() {
    }

    public StavkaUsluge(Usluga usluga, int rbStavke, Knjiga knjiga) {
        this.usluga = usluga;
        this.rbStavke = rbStavke;
        this.knjiga = knjiga;
    }
    
    public Usluga getUsluga() {
        return usluga;
    }

    public void setUsluga(Usluga usluga) {
        this.usluga = usluga;
    }

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }
    
    @Override
    public String getTableName() {
        return "StavkaUsluge";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (uslugaID, rbStavke, knjiga) ";
    }

    @Override
    public String getInsertValues() {
        return " " + usluga.getUslugaID()+ ", " + rbStavke + ", "
                + " " + knjiga.getKnjigaID();
    }

    @Override
    public String getUpdateValues() {
        return "";
    }

    @Override
    public String getPrimaryKeyValue() {
        return " uslugaID = " + usluga.getUslugaID();
    }

    @Override
    public String alias() {
        return "su";
    }

    @Override
    public String join() {
        return "INNER JOIN Usluga u ON (u.UslugaID = su.uslugaID) "
                +"INNER JOIN Korisnik ko ON (ko.korisnikID = u.korisnik)"
                +"INNER JOIN Bibliotekar b ON (b.bibliotekarID = u.bibliotekar)"
		+"INNER JOIN Knjiga k ON (k.knjigaID = su.knjiga) "
                +"INNER JOIN Autor a ON (k.autor=a.autorID)";
    }

    @Override
    public String condition() {
        return "WHERE u.uslugaID= "+usluga.getUslugaID()+" ORDER BY k.naziv";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            
           /*Korisnik korisnik = new Korisnik(rs.getLong("korisnikID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getDate("datumRodjenja"),  rs.getString("kontakt"));
            
            Bibliotekar bibliotekar = new Bibliotekar(rs.getLong("bibliotekarID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("username"), rs.getString("password"));

            Usluga u = new Usluga(rs.getLong("uslugaID"),
                    rs.getDate("datumIzdavanja"), rs.getDate("datumIsteka"),
                    korisnik, bibliotekar,null);*/
            Usluga u = new Usluga(rs.getLong("uslugaID"),
                    rs.getDate("datumIzdavanja"), rs.getDate("datumIsteka"),
                    null, null,null);
            
            Autor autor = new Autor(rs.getLong("autorID"),
                    rs.getString("ime"), rs.getString("drzava"));
            
            Knjiga k = new Knjiga(rs.getLong("knjigaID"),
                    rs.getString("naziv"),rs.getInt("kolicina"),
                    Zanr.valueOf(rs.getString("zanr")), autor);

            StavkaUsluge stavkaUsluge= new StavkaUsluge(u, rs.getInt("rbStavke"), k);

            lista.add(stavkaUsluge);
            
        }

        rs.close();
        return lista;
    }

    
    
    
}
