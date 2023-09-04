/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Bibliotekar;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOLogin extends AbstractSO{
    Bibliotekar ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Bibliotekar)){
            throw new Exception("Prosledjeni objekat nije instanca klase Bibliotekar!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Bibliotekar bibliotekar=(Bibliotekar) ado;
        ArrayList<Bibliotekar> bibliotekari = 
                (ArrayList<Bibliotekar>) (ArrayList<?>) DBBroker.getInstance().select(ado); 
        for (Bibliotekar b : bibliotekari) {
            if(b.getUsername().equals(bibliotekar.getUsername()) 
                    && b.getPassword().equals(bibliotekar.getPassword())){
                ulogovani=b;
                return;
            }
        }
        throw new Exception("Ne postoji uneti bibliotekar");
    }

    public Bibliotekar getUlogovani() {
        return ulogovani;
    }
    
}   
