/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.components;

import domain.Knjiga;
import domain.StavkaUsluge;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aleksandra
 */
public class TableModelStavke extends AbstractTableModel{
    
    private List<StavkaUsluge> listaStavki;
    private String[] columnNames=new String[]{"RB","Knjiga"};
    private int rb=0;

    public TableModelStavke() {
        listaStavki=new ArrayList<>();
    }

   /* public TableModelStavke(Usluga usluga) {
        try {
            listaStavki=ClientController.getInstance().getAllStavkeUsluge(usluga);
            for (StavkaUsluge stavkaUsluge : listaStavki) {
                System.out.println(stavkaUsluge.toString());
            }
            
        } catch (Exception ex) {
            Logger.getLogger(TableModelStavke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    
    
    @Override
    public int getRowCount() {
        if(listaStavki==null){
            return 0;
        }else{
            return listaStavki.size();
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
        StavkaUsluge su=listaStavki.get(rowIndex);
        switch(columnIndex){
            case 0:
                return su.getRbStavke();
            case 1:
                return su.getKnjiga();
            default:
                return "n/a";
        }
    }

    public boolean postojiKnjiga(Knjiga knjiga) {
        
        for (StavkaUsluge stavkaUsluge : listaStavki) {
            if(stavkaUsluge.getKnjiga().equals(knjiga)){
                System.out.println(stavkaUsluge.getKnjiga());
                return true;
            }
        }
        return false;
    }

    public void dodajStavku(StavkaUsluge stavkaUsluge) {
        rb=listaStavki.size();
        stavkaUsluge.setRbStavke(++rb);
        listaStavki.add(stavkaUsluge);
        fireTableDataChanged();
    }

    public void deleteStavka(int rowIndex) {
        listaStavki.remove(rowIndex);
        rb=0;
        for (StavkaUsluge stavkaUsluge : listaStavki) {
            stavkaUsluge.setRbStavke(++rb);
        }
        fireTableDataChanged();
    }

    public List<StavkaUsluge> getListaStavki() {
        return listaStavki;
    }
    
    public StavkaUsluge getStavkaUsluge(int rowIndex){
        return listaStavki.get(rowIndex);
    }

    public void setListaStavki(List<StavkaUsluge> listaStavki) {
        this.listaStavki = listaStavki;
        fireTableDataChanged();
    }
    
    
}
