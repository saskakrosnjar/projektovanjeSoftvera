/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aleksandra
 */
public interface AbstractDomainObject extends Serializable{
    
    String getTableName();
    
    String getColumnNamesForInsert();
    
    String getInsertValues();
    
    String getUpdateValues();
    
    String getPrimaryKeyValue();
    
    String alias();
    
    String join();
    
    String condition();
        
    List<AbstractDomainObject> getAll(ResultSet rs) throws SQLException;


}
