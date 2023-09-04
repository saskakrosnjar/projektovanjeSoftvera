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
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOUpdateUsluga extends AbstractSO{
    
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Usluga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
        
        Usluga usluga=(Usluga) ado;
        
        if (usluga.getDatumIzdavanja().after(usluga.getDatumIsteka())) {
            throw new Exception("Datum izdavanja ne sme biti nakon datuma isteka");
        }

        if (usluga.getListaStavki().isEmpty()) {
            throw new Exception("Usluga mora da ima bar jednu stavku!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
        Usluga usluga=(Usluga) ado;
        System.out.println(usluga);
        System.out.println(usluga.getListaStavki());
        
        DBBroker.getInstance().delete(usluga.getListaStavki().get(0));
        
        
        for (StavkaUsluge su : usluga.getListaStavki()) {
            su.setUsluga(usluga);
            DBBroker.getInstance().insert(su);
            
        }
    }
    
}
