package mm.comparaison;


import mm.*;
import java.util.Random;



/**
 *
 * @author LENOVO
 */
public class NewClass3 {
	public String message1;
    public char[] p1;
    public int[] y;

    public NewClass3(String message1) {
        this.message1 = message1;
        p1 = new char[message1.length()];
        y = new int[p1.length];
    }

    public int pgcd(int a, int b) {

        int r, q = 0;

        for (;;) {
            r = a % b;
            q = (a - r) / b;
            if (r == 0) {
                break;
            }
            a = b;
            b = r;
        }

        return b;
    }

    public String encrypting(int k_k, int k_p) {
        char[] p1 = new char[message1.length()];
        for (int i = 0; i < p1.length; i++) {
            p1[i] = message1.charAt(i);
        }
        String sss = "";
       // Random rnd= new Random() ;
       // int r = rnd.nextInt((k_p * k_p) - k_p) ;
       //int r=0;
        for (int i = 0; i < p1.length; i++) {
           int r=(p1[i]*k_k)%(k_p * k_p);
           y[i] = (p1[i]+ r * k_p) % (k_p * k_p )+ (p1[i] * k_k * k_k);
            sss = sss + "" + y[i];
         //   System.out.println("ss length"+ sss.length());
        }

        return sss;
    }

    public String dercrypting(int k_k,int k_p) {
		char ch = ' ';
       String text = "";
	  //  System.out.println("try de "+message1);
	  String[] ls = message1.split("(?<=\\G.........)");
        for(String p:ls){
			//System.out.println(p);
			ch = (char) ((char) (Integer.parseInt(p)% (k_k*k_k))% k_p);
			text+=ch;
		}
		/*
		String p="";
		String[] lx=message1.split("");
		for(int i=1;i<lx.length;i++){
			String s=lx[i];
			p+=s;
			ch = (char) ((char) (Integer.parseInt(p)% (k_k*k_k))% k_p);
			System.out.println("lx is"+ch);
			if(p.length()>6)
				p="";
		}*/

		return text;

    }

	public String encode(String numberToString) {		
        String hashed = "";
        Random r = new Random();

        for (int i = 0; i < numberToString.length(); i++) {

            int random = r.nextInt(3);

            hashed += numberToString.charAt(i) + generateRandomString(random);

        }

        return hashed;
    }

	public String decode(String value) {		// this method returns the original int as string

        String decoded = "";

        for (int i = 0; i < value.length(); i++) {

            if (Character.isDigit(value.charAt(i))) {
                decoded += value.charAt(i);
            }

        }

        return decoded;

    }

	private String generateRandomString(int n) {

        if (n == 0) {
            return "";
        }

        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz"
			+ "!?%$&@$+*/^£=" // hna zdlet  whed  les romozze  bach  nd9e9 mlihe 
			+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            int index
				= (int) (AlphaNumericString.length()
				* Math.random());

            sb.append(AlphaNumericString
					  .charAt(index));
        }

        return sb.toString();
    }

    public boolean isPremier(int n) {
        boolean isPremier = true;
        if (n <= 1) {
            isPremier = false;
        } else if (n != 0 && n != 1) {
            for (int i = 2; i <= n / 2; i++) {
                if (n != i && n % i == 0) {
                    isPremier = false;
                    break;
                }
            }
        }
        return isPremier;
    }

}


