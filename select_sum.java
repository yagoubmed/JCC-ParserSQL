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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static mm.Server.crp;
import static mm.Server.k_k;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Function;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;

/**
 *
 * @author djouhaina
 */
public class select_sum {
    
    //--------------- Declaration global ----------
      Connection con=null;
      PreparedStatement pst = null;
      ResultSet rs = null; 
      long sum_slr;
      String SQL_Crpyt ; 
      String decrpt_Result;
      private CCJSqlParserManager parserManager = new CCJSqlParserManager();
    //--------------------------------------------------------------
        
 //---------------------- decrp long ------------------------------------
       
        public static long  decrp_long(long c){
         int k_k=2333,k_p=211;
           Random rnd = new Random();
           int g=k_k*k_k;
           long a= c % g ;
           long m=(a)%(k_p);
           return m;
    }
      
      //----------------------------------------------------------------
      
      void Select_sum(String sql) throws JSQLParserException, SQLException{
         
          try {
          ////---------------- Parse Statment -----------------
                Select select = (Select) parserManager.parse(new StringReader(sql));
                PlainSelect  ps = (PlainSelect) select.getSelectBody();
                Function fun = (Function) ((SelectExpressionItem) ps.getSelectItems().get(0)).
                getExpression();
                System.out.println("SUM : "+ fun.getName());
                System.out.println("slr : "+ ((Column) fun.getParameters().getExpressions().get(0)).
                getFullyQualifiedName());
                String col_sum = ((Column) fun.getParameters().getExpressions().get(0)).
                getFullyQualifiedName();
                String Table = ((Table) ps.getFromItem()).getName();
                
         //--------------------------------- Crypt Request SELECT sum ----------------------
             
              SQL_Crpyt ="SELECT SUM(" + "RW_"+crp(col_sum)+") From T_" +crp(Table)+";";             
              System.out.print("SQL_Crpyt  :  "+SQL_Crpyt);

         //-------------------------    Open a connection And Excute -----------------------------------------
              
             Connection con =connect.getConnection();
             pst = con.prepareStatement(SQL_Crpyt);
 
             JOptionPane.showMessageDialog(null, "Executed ! ");
             
         //-------------------------------- Extract data from result set --------------------
            
             
             rs = pst.executeQuery(SQL_Crpyt);
             rs.next();
             sum_slr = rs.getLong(1);
             System.out.print("rowsum  :  "+sum_slr);
            System.out.println("ici  :  "+ decrp_long(sum_slr));
            
          //----------------------  Decrypt le  result -------------------------------------------
                         
              decrpt_Result=String.valueOf(decrp_long(sum_slr));
             System.out.print("decrpt_Result  :  "+decrpt_Result);

         //----------------------------- Send Result to Server -------------
        //    String data[] = new String[2];
           //        data[0]= SQL_Crpyt;
           //        data[1] =  decrpt_Result;
             //             System.out.print("data[0]  :  "+data[0]);
              //            System.out.print("Data[1]  :  "+data[1]);
                   
            
        } catch (SQLException | JSQLParserException ex) {
             Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
         }
            
    
}


   
}
