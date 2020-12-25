/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mm.comparaison;

import java.security.KeyPair;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import static mm.comparaison.OurMethode.crp;
import static mm.comparaison.OurMethode.decrp;
import static mm.comparaison.RSA.decrypt;
import static mm.comparaison.RSA.encrypt;
import static mm.comparaison.RSA.generateKeyPair;
import static mm.comparaison.RSA.sign;
import static mm.comparaison.RSA.verify;
/**
 *
 * @author lenovo
 */



public class TimeExecution {

   
     
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            final String secretKey = "ssshhhhhhhhhhh!!!!";

       
        String OriginalQuerys="select * from employee";
      
       //------------ Our method-------------- 
        long startTime = System.nanoTime(); 
        String CryptQuerys=crp(OriginalQuerys);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        System.out.println("duration crp  :  "+duration);  
        System.out.println("duration millisecond  crp :  "+duration/1000000);  
        System.out.println("\n");
        
        long startTime2 = System.nanoTime();
        String Decrypt_querys=decrp(CryptQuerys);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);  //divide by 1000000 to get milliseconds.
        System.out.println("duration  decrypt :  "+duration2);  
        System.out.println("duration millisecond decrypt :  "+duration2/1000000);
        System.out.println("this is decrpit : "+Decrypt_querys);
           
    //------------------------------- BlowFish -------------------------
                System.out.println("\n------------------------ BlowFish 1-----------------------------\n");

            BlowfishAlgorithm blowfishAlgorithm = new BlowfishAlgorithm();
            long startTime_BlowFish_crp1 = System.nanoTime(); 
            
            String cipherText1 = blowfishAlgorithm.encrypt(OriginalQuerys);
             
            long endTime_BlowFish_crp1 = System.nanoTime();
            long duration_Blowfish_crp1 = (endTime_BlowFish_crp1 - startTime_BlowFish_crp1);  //divide by 1000000 to get milliseconds.
            System.out.println("duration  crp :  "+duration_Blowfish_crp1);  
            System.out.println("duration millisecond crp :  "+duration_Blowfish_crp1/1000000);  
           
             System.out.println("Cipher Text: " + cipherText1);
        
                     System.out.println("\n");

              //-----Decrypt---
            long startTime_BlowFish_decrp1 = System.nanoTime(); 
          
             String decryptText = blowfishAlgorithm.decrypt(cipherText1);
            long endTime_BlowFish_decrp1 = System.nanoTime();
            long duration_Blowfish_decrp1 = (endTime_BlowFish_decrp1 - startTime_BlowFish_decrp1);  //divide by 1000000 to get milliseconds.
            System.out.println("duration  decrypt :  "+duration_Blowfish_decrp1);  
            System.out.println("duration millisecond decrypt :  "+duration_Blowfish_decrp1/1000000);  
            
             
             System.out.println("Text after Decryption: " +decryptText );
        
    //------------------------------------- AES -----------------------
            System.out.println("\n------------------------ AES -----------------------------\n");

                long startTime_AES_crp = System.nanoTime(); 
                String encryptedString = AES.encrypt(OriginalQuerys, secretKey) ;
                long endTime_AES_crp = System.nanoTime();
                long duration_AES_crp = (endTime_AES_crp - startTime_AES_crp);  //divide by 1000000 to get milliseconds.
                System.out.println("duration crp  :  "+duration_AES_crp);  
                System.out.println("duration millisecond  crp :  "+duration_AES_crp/1000000);  
                System.out.println("Original : "+OriginalQuerys);
                System.out.println("this is  crp :  "+encryptedString);
                        System.out.println("\n");

                    //------Decrypt
                long startTime_AES_decrp = System.nanoTime(); 
                String decryptedString = AES.decrypt(encryptedString, secretKey) ;
                long endTime_AES_decrp = System.nanoTime();
                long duration_AES_decrp = (endTime_AES_decrp - startTime_AES_decrp);  //divide by 1000000 to get milliseconds.
                System.out.println("duration decrypt  :  "+duration_AES_decrp);  
                System.out.println("duration millisecond decrypt :  "+duration_AES_decrp/1000000);  
                
                System.out.println("this is decrypt : "+decryptedString);
 //---------------------------------------------- RSA ------------------------
             System.out.println("\n------------------------ RSA -----------------------------\n");

            KeyPair pair = generateKeyPair();
            //KeyPair pair = getKeyPairFromKeyStore();

        //Encrypt the message
         long startTime_RSA_crp = System.nanoTime(); 
        String cipherText = encrypt(OriginalQuerys, pair.getPublic());
        long endTime_RSA_crp = System.nanoTime();
        long duration_RSA_crp = (endTime_RSA_crp - startTime_RSA_crp);  //divide by 1000000 to get milliseconds.
        System.out.println("duration crp  :  "+duration_RSA_crp);  
        System.out.println("duration millisecond crp :  "+duration_RSA_crp/1000000); 
        System.out.println("this is crypt : "+cipherText);
                        System.out.println("\n");


        //Now decrypt it
        long startTime_RSA_decrp = System.nanoTime(); 
        String decipheredMessage = decrypt(cipherText, pair.getPrivate());
        long endTime_RSA_decrp = System.nanoTime();
        long duration_RSA_decrp = (endTime_RSA_decrp - startTime_RSA_decrp);  //divide by 1000000 to get milliseconds.
        System.out.println("duration decrypt  :  "+duration_RSA_decrp);  
        System.out.println("duration millisecond decrypt :  "+duration_RSA_decrp/1000000); 
        
        System.out.println("this is decrypt : "+decipheredMessage);

        //Let's sign our message
//        String signature = sign("foobar", pair.getPrivate());
//
//        //Let's check the signature
//        boolean isCorrect = verify("foobar", signature, pair.getPublic());
//        System.out.println("Signature correct: " + isCorrect);
    
    }
    
}
