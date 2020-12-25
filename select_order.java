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
import static mm.Server.decrp;
import static mm.Server.decrp_long;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

/**
 *
 * @author djouhaina
 */
public class select_order {
    //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      String SQL_Crpyt , decrpt_Result,data[]=null;
      int count;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();
      //----------------------------------------------------------------------
      
      String[] Select_order(String sql) throws JSQLParserException, SQLException{
          
          try{
          //------------------- Parse Statement  ------------------------------------------
             System.out.println ("---------------- Parse Statement Result ----------");
             	Select select = (Select) parserManager.parse(new StringReader(sql));
                 PlainSelect ps = (PlainSelect) select.getSelectBody();
                 System.out.println("colone 1:"+ps.getSelectItems().get(0).toString());
                 System.out.println("colone 2:"+ ps.getSelectItems().get(1).toString());
                 System.out.println("colone 3:"+ ps.getSelectItems().get(2).toString());
                 System.out.println("mytable : "+ ((Table) ps.getFromItem()).getName());
                 String tab=( ((Table) ps.getFromItem()).getName());
                 String OrderBy=(((Column) ps.getOrderByElements().get(0).getExpression())
                        .getFullyQualifiedName());
        
            //---------------------------- Crypt ----------------------------------------
                 System.out.println("colone 1:"+ crp(ps.getSelectItems().get(0).toString()));
                 System.out.println("colone 2:"+ crp(ps.getSelectItems().get(1).toString()));
                 System.out.println("colone 3:"+ crp(ps.getSelectItems().get(2).toString()));
                SQL_Crpyt = "SELECT RW_" +crp(ps.getSelectItems().get(0).toString())
                     +",RW_" +crp(ps.getSelectItems().get(1).toString())
                     +",RW_"+ crp(ps.getSelectItems().get(2).toString()) +" FROM T_"
                     +crp(tab)  
                     + " ORDER BY RW_"
                     + crp(OrderBy)
                     + " DESC ;";

                System.out.println("SQL Crypt= " +SQL_Crpyt);
          //-------------------------    Open a connection And Excute -----------------------------------------
              
             Connection con =connect.getConnection();
             pst = con.prepareStatement(SQL_Crpyt);
             JOptionPane.showMessageDialog(null, "Select all in descending order for the Salary column ! ");
                   
    //------------------------  Extract data from result set crypt --------------------------------------
            
               rs = pst.executeQuery(SQL_Crpyt);
               String data[] = new String[100];
               data[0]= SQL_Crpyt; 
               count=1;
                while (rs.next()) {
                      String id = rs.getString("RW_"+crp(ps.getSelectItems().get(0).toString()));

                      String name = rs.getString("RW_"+crp(ps.getSelectItems().get(1).toString()));

                      long salary = rs.getLong("RW_"+crp(ps.getSelectItems().get(2).toString()));

                      String result= "RW_"+crp(ps.getSelectItems().get(0).toString()) +": "+ id + "\n RW_"+crp(ps.getSelectItems().get(1).toString())+": " + name + "\n RW_"+crp(ps.getSelectItems().get(2).toString()) +": " +salary+ "\n -----------------------\n";
                      System.out.println("result= " +result);
                     
         //----------------------  Decrypt le  result -------------------------------------------
    
                      decrpt_Result=decrp(crp(ps.getSelectItems().get(0).toString())) +": "+ decrp(id)+ "\n " +decrp(crp(ps.getSelectItems().get(1).toString()))+": " + decrp(name) + "\n "+decrp(crp(ps.getSelectItems().get(2).toString())) +": " +decrp_long(salary)+ "\n -------------------------\n";
                      System.out.println("decrpt result :"+ decrpt_Result);
                      data[count]=decrpt_Result;
                      count++;
                }
                             
                   return data;
            } catch (SQLException | JSQLParserException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }  
          
          return null;   
      }
      
}
