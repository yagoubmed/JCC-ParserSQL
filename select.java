/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.io.StringReader;
import java.lang.*;
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
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

/**
 *
 * @author djouhaina
 */
public class select {
      //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      String SQL_Crpyt , decrpt_Result ,r=null;
      int count;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();
             String data[] = new String[100];            

    //--------------------------------------------------------
      
      void Select(String sql) throws JSQLParserException, SQLException{
         ResultSet rs = null;
          try {
            //---------------- Parse Statment -----------------

            Select select = (Select) parserManager.parse(new StringReader(sql));
            PlainSelect ps = (PlainSelect) select.getSelectBody();
            System.out.println("colone 1:"+ps.getSelectItems().get(0).toString());
            String col1=ps.getSelectItems().get(0).toString();
            System.out.println("colone 2:"+ ps.getSelectItems().get(1).toString());
            String col2=ps.getSelectItems().get(1).toString();
            System.out.println("colone 3:"+ ps.getSelectItems().get(2).toString());
            String col3=ps.getSelectItems().get(2).toString();

            System.out.println("mytable : "+ ((Table) ps.getFromItem()).getName());
            String mytable = ((Table) ps.getFromItem()).getName();
            
            
            //-------------------------------------
            	//long startTime = System.nanoTime();

                 
            //------------------------------------------
            
             SQL_Crpyt ="SELECT " + "RW_"+crp(col1)+",RW_" +crp(col2)+",RW_"+crp(col3)
             + " FROM T_" + crp(mytable)+";";
              System.out.println("SQL_Crpyt  :  "+SQL_Crpyt);   
            
           
        //--------------    Open a connection  -------

            Connection con =connect.getConnection();
            pst = con.prepareStatement(SQL_Crpyt);
            JOptionPane.showMessageDialog(null, "Selected All ");

        //-------------------------------- Extract data from result set --------------------

            count=0;
            rs = pst.executeQuery(SQL_Crpyt);
                while (rs.next()) {
                      String id = rs.getString("RW_"+crp(ps.getSelectItems().get(0).toString()));
                      String name = rs.getString("RW_"+crp(ps.getSelectItems().get(1).toString()));
                      long salary = rs.getLong("RW_"+crp(ps.getSelectItems().get(2).toString()));
                                            System.out.println("result= " +salary);
                      String result= "RW_"+crp(ps.getSelectItems().get(0).toString()) +": "+ id + "\n RW_"+crp(ps.getSelectItems().get(1).toString())+": " + name + "\n RW_"+crp(ps.getSelectItems().get(2).toString()) +": " +salary+ "\n -----------------------\n";
                      System.out.println("result= " +result);
                     
         //----------------------  Decrypt le  result -------------------------------------------
        
                      decrpt_Result=decrp(crp(ps.getSelectItems().get(0).toString())) +": "+ decrp(id)+ "\n " +decrp(crp(ps.getSelectItems().get(1).toString()))+": " + decrp(name) + "\n "+decrp(crp(ps.getSelectItems().get(2).toString())) +": " +decrp_long(salary)+ "\n -------------------------\n";
                      System.out.println("decrpt result :"+ decrpt_Result);
                      data[count]=decrpt_Result;
                      System.out.println("decrpt result22 :"+ data[0]);
                      count++;

                }
                             
                   
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
                JOptionPane.ERROR_MESSAGE);

        } catch (JSQLParserException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
         
          }

   

   
    
}
