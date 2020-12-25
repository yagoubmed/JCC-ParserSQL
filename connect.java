/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import javax.swing.JOptionPane;

public class connect  {
     public static Connection getConnection()
     {
           Connection con=null;
    
   try{
       // connection local
        
       String connectString="jdbc:mysql://localhost:3306/sonoo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
       
       //Step-1 //load driver
       Class.forName("com.mysql.cj.jdbc.Driver");  // load the driver
       
        //Step-2 //Create Connection Object
                               //connection string, username, password
       con=DriverManager.getConnection(connectString,"root","");
      
       
       // connection in cloud
        // con = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/sql12329166","sql12329166","sql12329166");
        System.out.println("Ok,Connection is done");
   }
   catch(Exception e) {
      System.out.println("inter.connect.connect()");
      System.out.println("Error: "+ e.getMessage());
   }
   return con;
     }
}