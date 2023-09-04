/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.components;


import controller.ClientController;
import domain.Knjiga;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandra
 */
public class TableModelKnjiga extends AbstractTableModel{
    
    private List<Knjiga> listaKnjiga;
    private String[] columnNames=new String[]{"Autor","Naziv","Zanr","Na stanju"};
    private String parametar="";

    public TableModelKnjiga() {
        try {
            listaKnjiga=ClientController.getInstance().getAllKnjiga();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKnjiga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(listaKnjiga==null){
            return 0;
        }else{
            return listaKnjiga.size();
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
        Knjiga knjiga=listaKnjiga.get(rowIndex);
        switch(columnIndex){
            case 0:
                return knjiga.getAutor().getIme();
            case 1:
                return knjiga.getNaziv();
            case 2:
                return knjiga.getZanr();
            case 3:
                return knjiga.getKolicina();
            default:
                return "n/a";
        }
    }
    public Knjiga getKnjiga(int rowIndex) {
        return listaKnjiga.get(rowIndex);
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            listaKnjiga=ClientController.getInstance().getAllKnjiga();
            if(!parametar.equals("")){
                ArrayList<Knjiga> novaLista=new ArrayList<>();
                for (Knjiga knjiga : listaKnjiga) {
                    if(knjiga.getNaziv().toLowerCase().contains(parametar.toLowerCase()) 
                            || knjiga.getAutor().getIme().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(knjiga);
                    }
                }
                listaKnjiga=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void smanjiKolicinu(int selectedRow) {
        int staraKolicina=listaKnjiga.get(selectedRow).getKolicina();
        if(staraKolicina==0){
            return;
        }
        int novaKolicina=staraKolicina-1;
        listaKnjiga.get(selectedRow).setKolicina(novaKolicina);
        fireTableDataChanged();
    }

    public void povecajKolicinu(Knjiga knjiga) {
        for (Knjiga k : listaKnjiga) {
            if(k.equals(knjiga)){
                int novaKolicina=knjiga.getKolicina()+1;
                knjiga.setKolicina(novaKolicina);
                fireTableDataChanged();
            }
        }
    }

    
    
}
