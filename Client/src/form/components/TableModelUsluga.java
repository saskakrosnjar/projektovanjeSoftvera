/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.components;


import controller.ClientController;
import domain.Korisnik;
import domain.Usluga;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandra
 */
public class TableModelUsluga extends AbstractTableModel implements Runnable{
    
    private List<Usluga> listaUsluga;
    private String[] columnNames=new String[]{"Korisnik","Iznajmljeno","Rok zaduzenja"};
    private String parametar="";

    public TableModelUsluga(){
        try {
            listaUsluga=ClientController.getInstance().getAllUsluga();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setListaKorisnika(List<Korisnik> listaKorisnika) {
        this.listaUsluga = listaUsluga;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        if(listaUsluga==null){
            return 0;
        }else{
            return listaUsluga.size();
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
        Usluga usluga=listaUsluga.get(rowIndex);
        switch(columnIndex){
            case 0:
                return usluga.getKorisnik();
            case 1:
                return usluga.getDatumIzdavanja();
            case 2:
                return usluga.getDatumIsteka();
            default:
                return "n/a";
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(5000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelKorisnik.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteUsluga(int rowIndex) {
        try {
            Usluga usluga=listaUsluga.get(rowIndex);
            ClientController.getInstance().deleteUsluga(usluga);
        } catch (Exception ex) {
            
            Logger.getLogger(TableModelUsluga.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usluga getUsluga(int rowIndex) {
        return listaUsluga.get(rowIndex);
    }
    
    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            listaUsluga=ClientController.getInstance().getAllUsluga();
            if(!parametar.equals("")){
                ArrayList<Usluga> novaLista=new ArrayList<>();
                for (Usluga usluga : listaUsluga) {
                    if(usluga.getKorisnik().getIme().toLowerCase().contains(parametar.toLowerCase()) 
                            || usluga.getKorisnik().getPrezime().toLowerCase().contains(parametar.toLowerCase())){
                        novaLista.add(usluga);
                    }
                }
                listaUsluga=novaLista;
            }
            fireTableDataChanged();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
}
