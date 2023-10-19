package edu.lehigh.minty.miners.model;

import edu.lehigh.minty.miners.Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Map;

public class Block {
    private final Header header;

    private final MerkleTree merkleTree; // full list of accounts

    private String previousHeaderHash;

    public String getPreviousHeaderHash() {
        return previousHeaderHash;
    }

    public void setPreviousHeaderHash(String previousHeaderHash) {
        this.previousHeaderHash = previousHeaderHash;
    }

    public Block(Header.Builder header, MerkleTree merkleTree) {

        this.merkleTree = merkleTree;

        // Create a TargetSetter for target success rate
        TargetSetter targetSetter = new TargetSetter();
    
        // Get the target threshold for 50% success rate
        header.setTarget(targetSetter.getTargetThreshold().toByteArray());

        this.header = header.build();
    
        // Continue with mining using target
        mineBlock(targetSetter);

        printBlock();
    }

    public void saveBlockDataToFile(String inputFileName) {
        // Derive the output file name from the input file name
        String outputFileName = inputFileName.replace(".txt", ".block.out");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName, true))) {
            // Write the block information to the output file
            writer.write("BEGIN BLOCK");
            writer.newLine();
            writer.write("BEGIN HEADER");
            // Write header information
            writer.write("Previous Header Hash: " + header.getPreviousHeaderHash());
            writer.newLine();
            writer.write("Merkle Root Hash: " + header.getMerkleRootHash());
            writer.newLine();
            // Write other header fields
            writer.write("END HEADER");
            writer.newLine();
            // Write Merkle tree information
            for (MerkleNode node : merkleTree.getTree()) {
                writer.write(node.toString()); // You may need to implement a toString method in MerkleNode
                writer.newLine();
            }

            writer.write("END BLOCK");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mineBlock(TargetSetter targetSetter) {
        while (true) {
            // Generate a random nonce within the target range
            BigInteger nonce = targetSetter.getRandomNonce();
            
            // Calculate SHA-256 hash
            byte[] hash = Main.hash(merkleTree.getRootHash() + nonce).getBytes();
            
            // Convert hash to a BigInteger
            BigInteger hashValue = new BigInteger(1, hash);
            
            // Check if the hash meets the target
            if (hashValue.compareTo(targetSetter.getTargetThreshold()) <= 0) {
                // If it does, set nonce in the header
                header.setNonce(nonce.toByteArray());
                break;
            }
        }
    }

    public void printBlock() {
        System.out.println();
        System.out.println("BEGIN BLOCK");
        System.out.println("BEGIN HEADER");
        System.out.println("Previous Header Hash: " + header.getPreviousHeaderHash());
        System.out.println("Merkle Root Hash: " + header.getMerkleRootHash());
        System.out.println("Nonce: (String)" +  new String(header.getNonce()));
        System.out.println("Nonce: (Array)" + Arrays.toString(header.getNonce()));
        System.out.println("Target: (String)" +  new String(header.getTarget()));
        System.out.println("Target: (Array)" +  Arrays.toString(header.getTarget()));
        System.out.println("Timestamp:" +  header.getTimestamp());
        System.out.println("END HEADER");
        merkleTree.getTree().forEach(System.out::println);
        System.out.println("END BLOCK");

    }

    public Header getHeader() {
        return header;
    }

    public MerkleTree getMerkleTree() {
        return merkleTree;
    }


}
