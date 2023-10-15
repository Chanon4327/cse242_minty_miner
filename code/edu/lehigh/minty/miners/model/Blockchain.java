package edu.lehigh.minty.miners.model;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

public class Blockchain {
    private final List<Block> blocks = new LinkedList<>();

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

    /***
     * Validates a block by getting the block's merkleRoot. Calculating the true merkle root 
     * from the block's merkle tree and comparing the two strings.
     * @param block block to be validated
     * @return true if block is valid, false otherwise
     */
    public boolean validateBlock(Block block) {
        String blkMerkle = block.getHeader().getMerkleRootHash();   // get the merkle root from block header
        MerkleTree blkTree = block.getMerkleTree();                 // create merkle Tree from block's ledger
        blkTree.calculateMerkle(blkTree.getTree());                 // calculate merkle root from block ledger
        String trueMerkle = blkTree.getRootHash();                  // retrieve merkle root from tree

        return blkMerkle == trueMerkle;
    }
    
    /***
     * Validating the entire block chain.
     * @return true if entire blockchain is valid, false otherwise
     */
    public boolean validateBlockChain() {
        for(int i = 0; i<blocks.size(); i++) {
            Block current = blocks.get(i);
            if(i == 0){     // root node
                if(current.getHeader().toHash() != merkleTree.getMerkleRootHash()){
                    return false;
                }
            }
            else{   // comparing previous hash to current.previous.hash
                Block previous = blocks.get(i-1);
                if(previous.getHeader().toHash() != current.getPreviousHeaderHash()){       
                    return false;
                }
            }
            if(validateBlock(current) == false) {
                return false;
            }
        }
        return true;
    }
}
