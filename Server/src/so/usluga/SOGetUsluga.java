/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.StavkaUsluge;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOGetUsluga extends AbstractSO{
    Usluga usluga;
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Usluga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        usluga= (Usluga) ado;
        System.out.println(usluga);
        StavkaUsluge su = new StavkaUsluge();
        su.setUsluga(usluga);
        ArrayList<AbstractDomainObject> konacnaLista=(ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) su);
         
        ArrayList<StavkaUsluge> lista = (ArrayList<StavkaUsluge>) (ArrayList<?>) konacnaLista;
        usluga.setListaStavki(lista);
    }

    public Usluga getUsluga() {
        return usluga;
    }

    
    
    
}
