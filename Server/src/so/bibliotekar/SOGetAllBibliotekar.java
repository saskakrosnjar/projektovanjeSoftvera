/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.bibliotekar;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Bibliotekar;
import domain.StavkaUsluge;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOGetAllBibliotekar extends AbstractSO{
    ArrayList<Bibliotekar> lista;
        
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Bibliotekar)){
            throw new Exception("Prosledjeni objekat nije instanca klase Bibliotekar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Bibliotekar bibliotekar=(Bibliotekar) ado;
        ArrayList<AbstractDomainObject> konacnaLista = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select(ado); 
        lista= (ArrayList<Bibliotekar>) (ArrayList<?>) konacnaLista;
        
    }

    public ArrayList<Bibliotekar> getLista() {
        return lista;
    }
    
}
