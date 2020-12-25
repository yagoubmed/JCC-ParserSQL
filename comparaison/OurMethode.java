/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm.comparaison;

import java.util.Base64;

/**
 *
 * @author djouhaina
 */
public class OurMethode {
     static int k_k=2333,k_p=211;
      
      ///-------------------------- crypt function our methode ----------------
    
    public static String  crp(String s){
         NewClass3 n=new NewClass3(Base64.getEncoder().encodeToString(s.getBytes()));
		String en=n.encrypting(k_k,k_p);
		System.out.println("Encryption is :"+en);
                return en;
    }
    ///------------------------ decrypt function our methode -------------------------
    
       public static String  decrp(String s){
          
           NewClass3 nc=new NewClass3(s);
		String de=nc.dercrypting(k_k,k_p);
                //System.out.println("Decryptin is:"+de);
                return new String (Base64.getDecoder().decode(de));
       }
}
