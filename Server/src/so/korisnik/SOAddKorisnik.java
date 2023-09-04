/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.korisnik;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Korisnik;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOAddKorisnik extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Korisnik)){
            throw new Exception("Prosledjeni objekat nije instanca klase Korisik");
        }
        
        Korisnik korisnik = (Korisnik) ado;

        ArrayList<Korisnik> korisnici = 
                (ArrayList<Korisnik>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
        for (Korisnik k : korisnici) {
            if(korisnik.getIme().equals(k.getIme()) && korisnik.getPrezime().equals(k.getPrezime()) 
                    && korisnik.getDatumRodjenja().equals(k.getDatumRodjenja()) 
                    && korisnik.getKontaktTelefon().equals(k.getKontaktTelefon())){
                throw new Exception("Sistem ne moze da kreira korisnika!");
            }
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
