/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import static mm.Server.crp;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import java.io.StringReader;
import java.sql.Connection;
import net.sf.jsqlparser.JSQLParserException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jsqlparser.statement.create.table.CreateTable;

/**
 *
 * @author djouhaina
 */
public class create {
       Connection con=null;
       PreparedStatement pst = null;
       ResultSet rs = null;  
       private CCJSqlParserManager parserManager = new CCJSqlParserManager();

    //-----------------------------------------------------------
     String Creation(String sql) throws JSQLParserException, SQLException{
         
          try {
                  System.out.println("ici in class create " );
                

         //---------------- Parse Statment --------------------------------
         CreateTable createTable = (CreateTable) parserManager.parse(new StringReader(sql));
         System.out.println("mycol1 : " + createTable.getColumnDefinitions().get(0). getColumnName());
         System.out.println("mycol2 : " + createTable.getColumnDefinitions().get(1).getColumnName());
         System.out.println("mycol3 : " + createTable.getColumnDefinitions().get(2).getColumnName());
         String col1=createTable.getColumnDefinitions().get(0). getColumnName();
         String col2=createTable.getColumnDefinitions().get(1). getColumnName();
         String col3=createTable.getColumnDefinitions().get(2). getColumnName();
         System.out.println("mytable : " + createTable.getTable().getName());
         String mytab=createTable.getTable().getName();
         
         //----------------------- measuring elapsed time using Spring StopWatch ------------
         
         
         //--------------------------------- Crypt Request SELECT AVG ----------------------
                 
         String SQL_Crpyt ="CREATE TABLE T_" + crp(mytab)
                 +" (RW_" + crp(col1) + " VARCHAR(500) PRIMARY KEY,RW_"
                 + crp(col2) +" VARCHAR(500) NOT NULL,RW_"
                 + crp(col3)+ " double);";
         System.out.print("SQL_Crpyt  :  "+SQL_Crpyt);
          //mes_c.append(SQL_Crpyt);
         

         //-------------------------    Open a connection And Excute -------------------------
         
         Connection con =connect.getConnection();
          pst = con.prepareStatement(SQL_Crpyt);
          pst.executeUpdate();
         JOptionPane.showMessageDialog(null, "Created Successfully!   ");
         //----------------------------- --------- -------------
         
        // mes_r.append(" Created Successfully ");
           return (SQL_Crpyt);
    } catch (JSQLParserException ex) {
             Logger.getLogger(create.class.getName()).log(Level.SEVERE, null, ex);              
}
           return null;
}
}
