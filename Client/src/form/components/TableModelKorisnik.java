/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.components;

import controller.ClientController;
import domain.Korisnik;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandra
 */
public class TableModelKorisnik extends AbstractTableModel{
    
    private List<Korisnik> listaKorisnika;
    private String[] columnNames=new String[]{"Ime","Prezime","Datum rođenja","Kontakt telefon"};
    private String parametar="";
    
    
    public TableModelKorisnik() {
        try {
            listaKorisnika=ClientController.getInstance().getAllKorisnik();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKorisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(listaKorisnika==null){
            return 0;
        }else{
            return listaKorisnika.size();
        }
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        if(column>columnNames.length){
            return "n/a";
        }else{
            return columnNames[column];
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik korisnik=listaKorisnika.get(rowIndex);
        switch(columnIndex){
            case 0:
                return korisnik.getIme();
            case 1:
                return korisnik.getPrezime();
            case 2:
                return korisnik.getDatumRodjenja();
            case 3:
                return korisnik.getKontaktTelefon();
            default:
                return "n/a";
        }
    }

    public Korisnik getKorisnik(int rowIndex) {
        return listaKorisnika.get(rowIndex);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }
    

    public void refreshTable() {
        try {
            listaKorisnika=ClientController.getInstance().getAllKorisnik();
            if(!parametar.equals("")){
                ArrayList<Korisnik> novaLista=new ArrayList<>();
                for (Korisnik korisnik : listaKorisnika) {
                    if(korisnik.getIme().toLowerCase().contains(parametar.toLowerCase()) 
                            || korisnik.getPrezime().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(korisnik);
                    }
                }
                listaKorisnika=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Korisnik getListItem(int selectedRow) {
        return listaKorisnika.get(selectedRow);
    }

    
}
