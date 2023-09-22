import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Main{
    public static void main(String args[]){
        String hash = hashNode("Yayayada", 1234);
        System.out.println(hash);
    }

    public static String hashNode(String addr, int bal){
        String strBal = Integer.toString(bal);
        String res = addr.concat(strBal);
        
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            BigInteger number = new BigInteger(1, md.digest(res.getBytes(StandardCharsets.UTF_8)));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64)
            {
                hexString.insert(0, '0');
            }
            return hexString.toString();
            
        } catch (Exception e){
            System.out.println("Error hashing.");
        }
        return null;
    }
}