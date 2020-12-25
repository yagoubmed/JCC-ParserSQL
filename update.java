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
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.update.Update;

/**
 *
 * @author djouhaina
 */
public class update {
   
    //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      String SQL_Crypt;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();

    
    
     String Update(String sql) throws JSQLParserException, SQLException{
          try {
        
              //----------------- Parse Statement ---------------------------------------
        
                System.out.println("statement :   " +sql);
                
                System.out.println ("---------------- Parse Statement Update ----------");

		Update update = (Update) parserManager.parse(new StringReader(sql));
                   //--------------------where Stat---------
                 System.out.println("where St :   " + update.getWhere());

                String S = update.getWhere().toString().trim();
                String S1=S.substring(0,2).trim();
                String S2=S.substring(3,4).trim();
                String S3=S.substring(5).trim();
  
                   //--------------- Size  --------------------
                        String SQL_Crypt = null;
                        int size=update.getColumns().size();
                        System.out.println("Size :   " + size);
                 //------------------------- 
                if (size==1)
                {
                    System.out.println("col1 :   " + ((Column) update.getColumns().get(0)).getColumnName());
                    String col1 = ((Column) update.getColumns().get(0)).getColumnName();
                    System.out.println("Mytable :   " + update.getTable().getName());
                    System.out.println("col1 :   " + ((Column) update.getColumns().get(0)).getColumnName());
                   
                    String string1="nm";
                    String string2="slr";
                    if(string1.equals(col1)==true)
                   {
                    String name_;
                    name_ =((StringValue) update.getExpressions().get(0)).getValue();
                    System.out.println("name : "+ name_);
                    SQL_Crypt = "UPDATE " + "T_"+crp(update.getTable().getName())+ " SET RW_" +crp(col1)+" = "+ crp(name_) +" WHERE  RW_"+ crp(S1)+" = " + crp(S3) +";";
                    System.out.println("SQL_Crypt : "+ SQL_Crypt);

                   }
                   else
                   {
                       if(string2.equals(col1)== true)
                       {
                          long salary_;
                          salary_ =(((LongValue) update.getExpressions().get(0)).getValue());
                          System.out.println("Salary :  " + salary_);
                          SQL_Crypt = "UPDATE " + "T_"+crp(update.getTable().getName())+ " SET RW_" +crp(col1)+" = " +crp_long(salary_) +" WHERE "+ "RW_"+crp(S1)+" = " +crp(S3) +";"; 
                          System.out.println("SQL_Crypt : "+ SQL_Crypt);
                         // mes_c.append(SQL_Crypt);

                       }
                   }                         
                }
                //------------------------------------------------
                if(size==2){
                     
		System.out.println("Mytable :   " + update.getTable().getName());
		System.out.println("col1 :   " + ((Column) update.getColumns().get(0)).getColumnName());
                String col1= ((Column) update.getColumns().get(0)).getColumnName();
		System.out.println("col2 :   " + ((Column) update.getColumns().get(1)).getColumnName());
                String col2= ((Column) update.getColumns().get(1)).getColumnName();
		System.out.println(" Name = :   " + ((StringValue) update.getExpressions().get(0)).getValue());
                String nm_Value=((StringValue) update.getExpressions().get(0)).getValue();
		System.out.println("Salary = :   " + ((LongValue) update.getExpressions().get(1)).getValue());
                long Salary_Value=(((LongValue) update.getExpressions().get(1)).getValue());
                SQL_Crypt = "UPDATE T_" + crp(update.getTable().getName())+ " SET " +"RW_"+crp(col2)+" = "+crp(nm_Value) +" AND "+ "RW_"+crp(col2)+" = "+crp_long(Salary_Value) +" WHERE "+ "RW_"+crp(S1)+" = " + crp(S3) +";"; 
                 System.out.println("SQL_Crypt : "+ SQL_Crypt);
                 //mes_c.append(SQL_Crypt);

                }
        //--------------    Open a connection  ---------------------------------
           
                Connection con =connect.getConnection();  
        
        //------------------------ Connection to Database --------------------------------
                  
                pst = con.prepareStatement(SQL_Crypt);
      
         //-----------------------   Execute a query  -------------------------------------
             
         
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated Successfully");
           return (SQL_Crypt); 
        }
          catch(SQLException ex){ 
          JOptionPane.showMessageDialog(null, ex);
        
    } catch (JSQLParserException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }    

            return (null);    
}
}