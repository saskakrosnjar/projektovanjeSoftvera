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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Aleksandra
 */
public class SOAddUsluga extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Usluga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga");
        }
        
        Usluga usluga = (Usluga) ado;
        
        if (usluga.getDatumIzdavanja().after(usluga.getDatumIsteka())) {
            throw new Exception("Datum izdavanja ne sme biti nakon datuma isteka");
        }

        if (usluga.getListaStavki().isEmpty()) {
            throw new Exception("Usluga mora da ima bar jednu stavku!");
        }
        
        

        ArrayList<Usluga> usluge = 
                (ArrayList<Usluga>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        
       StavkaUsluge su = new StavkaUsluge();
       su.setUsluga(usluga);
       ArrayList<AbstractDomainObject> listaStavki=(ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) su);
       ArrayList<StavkaUsluge> lista = (ArrayList<StavkaUsluge>) (ArrayList<?>) listaStavki;
            for (StavkaUsluge stavkaUsluge : lista) {
                if(stavkaUsluge.getKnjiga().getKolicina()<=0){
                    throw new Exception("Sistem ne moze da kreira uslugu!");
                }
            }
        
        for (Usluga u : usluge) {
            if(usluga.getDatumIzdavanja().equals(u.getDatumIzdavanja()) 
                    && usluga.getDatumIsteka().equals(u.getDatumIsteka()) 
                    && usluga.getKorisnik().getKorisnikID().equals(u.getKorisnik().getKorisnikID())){
                throw new Exception("Sistem ne moze da kreira uslugu!");
            }
            
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long uslugaID = tableKeys.getLong(1);

        Usluga usluga = (Usluga) ado;
        usluga.setUslugaID(uslugaID);

        for (StavkaUsluge stavkaUsluge : usluga.getListaStavki()) {
            stavkaUsluge.setUsluga(usluga);
            DBBroker.getInstance().insert(stavkaUsluge);
        }
        
    }
    
}
