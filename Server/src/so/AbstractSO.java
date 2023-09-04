/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domain.AbstractDomainObject;
import java.sql.SQLException;
/**
 *
 * @author Aleksandra
 */
public abstract class AbstractSO {
    
    protected abstract void validate(AbstractDomainObject ado) throws Exception;
    protected abstract void execute(AbstractDomainObject ado) throws Exception;
    
    public void templateExecute(AbstractDomainObject ado) throws Exception{
       try{
           validate(ado);
           execute(ado);
           commit();
       }catch(Exception e){
           rollback();
           throw e;
       }
    }

    private void commit() throws SQLException{
        DBBroker.getInstance().getConnection().commit();
    }

    private void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
    
}
