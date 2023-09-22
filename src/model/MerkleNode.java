package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MerkleNode {
    private final String address;
    private final int balance;
    private final String hash;

    public MerkleNode(String address, int balance, String hash) {
        this.address = address;
        this.balance = balance;
        this.hash = hash;
    }

    public MerkleNode(String address, int balance) {
        this.address = address;
        this.balance = balance;
        this.hash = hashNode(address, balance);
    }

    public String getAddress() {
        return address;
    }

    public int getBalance() {
        return balance;
    }

    public String getHash() {
        return hash;
    }

    public static String hashNode(String addr, int bal){
        String strBal = Integer.toString(bal);
        String res = addr.concat(strBal);

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            BigInteger number = new BigInteger(1, md.digest(res.getBytes(StandardCharsets.UTF_8)));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 64){
                hexString.insert(0, '0');
            }
            return hexString.toString();

        } catch (Exception e){
            System.out.println("Error hashing.");
        }
        return null;
    }

    public static MerkleNode fromString(String serialized) {

        String[] split = serialized.split(" ");

        if (split.length != 2) {
            throw new RuntimeException("Invalid serialized string length (" + split.length + ") \n" + serialized);
        }

        if (split[0].length() != 40) {
            throw new RuntimeException("Invalid address length " + split[0].length() + " != 40,\n " + split[0]);
        }


        Number number;
        try {
            number = Integer.parseInt(split[1].strip());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid balance " + split[1]);
        }

        if (number.intValue() < 0) {
            throw new RuntimeException("Invalid balance " + number.intValue() + " < 0,\n " + split[1]);
        }


        return new MerkleNode(split[0], number.intValue());

    }

    @Override
    public String toString() {
        return address + " " + balance;
    }
}
