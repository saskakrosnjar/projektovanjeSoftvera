/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.usluga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Knjiga;
import domain.StavkaUsluge;
import domain.Usluga;
import java.util.ArrayList;
import so.AbstractSO;
import so.knjiga.SOUpdateKnjiga;

/**
 *
 * @author Aleksandra
 */
public class SODeleteUsluga extends AbstractSO{

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if(!(ado instanceof Usluga)){
            throw new Exception("Prosledjeni objekat nije instanca klase Usluga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Usluga usluga=(Usluga) ado;
        StavkaUsluge su = new StavkaUsluge();
        su.setUsluga(usluga);
        ArrayList<AbstractDomainObject> listaStavki = (ArrayList<AbstractDomainObject>) DBBroker.getInstance().select((AbstractDomainObject) su);
        ArrayList<StavkaUsluge> lista = (ArrayList<StavkaUsluge>) (ArrayList<?>) listaStavki;
        System.out.println(listaStavki);
        for (StavkaUsluge stavkaUsluge : lista) {
            Knjiga knjiga= stavkaUsluge.getKnjiga();
            System.out.println(knjiga);
            knjiga.setKolicina(knjiga.getKolicina()+1);
            System.out.println(knjiga.getKolicina());
            try{
            SOUpdateKnjiga so=new SOUpdateKnjiga();
            so.templateExecute(knjiga);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        DBBroker.getInstance().delete(ado);
    }
    
}
