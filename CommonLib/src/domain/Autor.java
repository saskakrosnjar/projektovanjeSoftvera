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
public class Autor implements AbstractDomainObject{
    private Long autorID;
    private String ime;
    private String drzava;

    public Autor() {
    }

    public Autor(Long autorID, String ime, String drzava) {
        this.autorID = autorID;
        this.ime = ime;
        this.drzava = drzava;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Long getAutorID() {
        return autorID;
    }

    public void setAutorID(Long autorID) {
        this.autorID = autorID;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.autorID);
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
        final Autor other = (Autor) obj;
        return this.autorID == other.autorID;
    }

    @Override
    public String toString() {
        return ime;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public String getTableName() {
        return " Autor ";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "";
    }

    @Override
    public String getInsertValues() {
        return "";
    }

    @Override
    public String getUpdateValues() {
        return "";
    }

    @Override
    public String getPrimaryKeyValue() {
        return "autroID="+autorID;
    }

    @Override
    public String alias() {
        return " a ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> getAll(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            Autor autor = new Autor(rs.getLong("autorID"),
                    rs.getString("ime"), rs.getString("drzava"));

            lista.add(autor);
        }

        rs.close();
        return lista;
    }

    @Override
    public String condition() {
        return "ORDER BY ime";
    }
    
}
