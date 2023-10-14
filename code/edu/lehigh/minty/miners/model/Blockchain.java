package edu.lehigh.minty.miners.model;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    private final List<Block> blocks = new LinkedList<>();\

    public Blockchain(List<String> blockFiles) {
        for (int i=0; i<blockFiles.size(); i++) {
            String blockFile = blockFiles.get(i);


            Header.Builder builder = new Header.Builder();

            MerkleTree merkleTree = MerkleTree.readFile(blockFile);
            builder.setMerkleRootHash(merkleTree.getRootHash())
                            .setPreviousHeaderHash(i == 0 ? "0": blocks.get(i-1).getHeader().toHash())
                    .setTarget();

            // todo: bruteforce nonce here

            builder.setTimestamp(Instant.now().getEpochSecond());

            blocks.add(new Block(builder.build(), merkleTree));
        }
    }

    public int balance(String address) {
        // Iterate through blockchain - newest block to oldest
        for (int i = blocks.size() - 1; i >= 0; i--) {
            Block block = blocks.get(i);

            // Check if the address exists in the block's Merkle tree
            MerkleNode merkleNode = block.getMerkleTree().findNodeByAddress(address);

            if (merkleNode != null) {
                // Address exists --> Call the proofOfMembership method to get the proof
                List<String> proof = merkleNode.proofOfMembership();

                // Call to calculate balance based on proof
                int balance = calculateBalance(merkleNode, proof);

                // Return balance value
                return balance;
            }
        }

        // Address not found in any block
        System.out.println("Address " + address + " not found in the blockchain.");
        return -1;
    }

    public int calculateBalance(String address) {
        // Iterate through the Merkle tree to find the MerkleNode with the given address
        MerkleNode merkleNode = merkleTree.findNodeByAddress(address);
        
        if (merkleNode != null) {
            // Address found --> Call getBalance from MerkleNode to return balance value
            return merkleNode.getBalance();
        }
        
        // Address not found --> return default balance (-1)
        return -1;
    }
}
