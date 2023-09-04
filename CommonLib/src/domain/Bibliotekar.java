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
public class Bibliotekar implements AbstractDomainObject{
    private Long bibliotekarID;
    private String ime;
    private String prezime;
    private String username;
    private String password;
    

    public Bibliotekar() {
    }

    public Bibliotekar(Long bibliotekarID, String ime, String prezime, String username, String password) {
        this.bibliotekarID = bibliotekarID;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
    }

    public Long getBibliotekarID() {
        return bibliotekarID;
    }

    public void setBibliotekarID(Long bibliotekarID) {
        this.bibliotekarID = bibliotekarID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String getTableName() {
        return " Bibliotekar ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return " (ime, prezime, username, password) ";
    }

    @Override
    public String getInsertValues() {
        return "'" + ime + "', '" + prezime + "', "
                + "'" + username + "', '" + password;
    }

    @Override
    public String getUpdateValues() {
        return " Ime = '" + ime + "', Prezime = '" + prezime + "', "
                + "Username = '" + username + "', Password = '" + password + "' ";
    }

    @Override
    public String getPrimaryKeyValue() {
        return "bibliotekarID="+bibliotekarID;
    }

    @Override
    public String alias() {
        return " b ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Bibliotekar bibliotekar = new Bibliotekar(rs.getLong("bibliotekarID"),
                    rs.getString("ime"), rs.getString("prezime"),
                    rs.getString("username"), rs.getString("password"));

            lista.add(bibliotekar);
        }

        rs.close();
        return lista;
    }

    @Override
    public String condition() {
        return "";
    }

    @Override
    public String toString() {
        return ime+" "+prezime;
    }
    
    
}
