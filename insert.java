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
import static mm.Server.crp_long;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 *
 * @author djouhaina
 */
public class insert {
    //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      String SQL_Crypt;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();

      //----------------------------------------------
      
        String Insert(String sql){
         try {
             
            //----------------------- Parse statement -------------------------------
            
            System.out.print("------------ Prase Insert  Statment----------- \n");
            Insert insert = (Insert) parserManager.parse(new StringReader(sql));
            
             String v1=String.valueOf((((LongValue) ((ExpressionList) insert.getItemsList()).getExpressions().get(0)).getValue()));
             String v2=(((StringValue) ((ExpressionList) insert.getItemsList()).getExpressions().get(1)).getValue());
             long v3=(((LongValue) ((ExpressionList) insert.getItemsList()).getExpressions().get(2)).getValue());
             String col1=insert.getColumns().get(0).getColumnName();
             String col2=insert.getColumns().get(1).getColumnName();
             String col3=insert.getColumns().get(2).getColumnName();
             String tabb=insert.getTable().getName();
    //---------------------------- Crypt ----------------------------------------
            String bb=crp(insert.getTable().getName());
                    System.out.println("table name = " + bb);
                    System.out.println("col1= " + crp(insert.getColumns().get(0).getColumnName()));
                    System.out.println("col2= " + crp(insert.getColumns().get(1).getColumnName()));
                    System.out.println("col3= " + crp(insert.getColumns().get(2).getColumnName()));

                    System.out.println ("ici crypt  : "+ bb);
                    SQL_Crypt= "INSERT INTO "+"T_"+crp(tabb)
                       +" (RW_"
                     +crp(col1) 
                     + ",RW_"
                     + crp(col2)
                     + ",RW_"
                     + crp(col3)
                     +" )"
                     +"VALUES('"
                     +crp(v1)
                     +"' , '"
                     +crp(v2)
                     +"' , "
                     +crp_long(v3)
                     + ");";
             System.out.println("SQL_Crypt= " +SQL_Crypt);
       
    //------------------   Open a connection  ------------------------------------
               
             Connection con =connect.getConnection();

    //------------------- Execute Statement -----------------------------------------
             System.out.println("SQL_Crypt= " +SQL_Crypt);
             pst = con.prepareStatement(SQL_Crypt);
             pst.executeUpdate();
             JOptionPane.showMessageDialog(null, "inserted successfully");

             return (SQL_Crypt);
             
        } catch (SQLException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         } catch (JSQLParserException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }
             return (null);

    }
    
}
