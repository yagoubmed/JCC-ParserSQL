/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mm.Server.crp;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.delete.Delete;

/**
 *
 * @author djouhaina
 */
public class delete {
     //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      String SQL_Crypt;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();

    String Delete(String sql){
    
         try { 

            ////---------------- Parse Statment -----------------
                 System.out.print("------------ Prase Delete Statment----------- \n");
            Delete delete = (Delete) parserManager.parse(new StringReader(sql));
                      Expression where_expression = delete.getWhere();
                     if (where_expression == null)
                      System.out.print("where Statement  : null ");
                      String str = where_expression.toString();
                      System.out.println("Where Statement: " +str);
                      System.out.println(" Target Table: " + delete.getTable().getName());
              
                      String r1=str.substring(0,2).trim();
                      String r2=str.substring(3,4).trim();
                      String r3=str.substring(5).trim();
                 
            //--------------------------------- Crypt Request DELETE ----------------------
              
            
                     String SQL_Crpyt= "DELETE FROM T_" + crp(delete.getTable().getName())+ " WHERE RW_" 
                                       + crp(r1) + " = " + crp(r3)+";";
             
                     System.out.print("SQL_Crpyt  :  "+SQL_Crpyt);

            //---------------------------------- Open a connection  ------------------
            
                     Connection con =connect.getConnection();
            
            //----------------------------------  Execute Statment --------------------
             
            pst = con.prepareStatement(SQL_Crpyt);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
            
            //-------------------------------------------------------------------------
             return (SQL_Crpyt);

            
        }
          catch(SQLException ex){ 
        JOptionPane.showMessageDialog(null, ex);
        
    } catch (JSQLParserException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }    
          return null;
    }  
}
