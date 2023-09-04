/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.knjiga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOAddKnjiga extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Knjiga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Knjiga");
        }
        
        Knjiga knjiga = (Knjiga) ado;

        ArrayList<Knjiga> knjige = 
                (ArrayList<Knjiga>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        
        for (Knjiga k : knjige) {
            if(k.getNaziv().equals(knjiga.getNaziv()) 
                    && k.getAutor().getAutorID().equals(k.getAutor().getAutorID())){
                throw new Exception("Sistem ne moze da kreira novu knjigu!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
