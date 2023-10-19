package edu.lehigh.minty.miners;

import edu.lehigh.minty.miners.model.Blockchain;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        List<String> filePaths = new ArrayList<>();
        if (args.length < 1) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a file path: ");
            while (true) {
                String filePath = scanner.nextLine();
                filePaths.add(filePath);


                if (filePath.equals("exit") || filePath.equals("quit") || filePath.equals("q") || filePath.equals("e")) {
                    System.out.println("Calculating Blockchain...");
                    break;
                }

                System.out.print("Enter another file path or type 'exit' to quit: ");

            }

            scanner.close();

        } else {
            filePaths = Arrays.stream(args).collect(Collectors.toList());
        }

        Blockchain blockchain = new Blockchain(filePaths);
    }


    public static String hash(String str){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            BigInteger number = new BigInteger(1, md.digest(str.getBytes(StandardCharsets.UTF_8)));
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


}