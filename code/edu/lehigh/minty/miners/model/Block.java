package edu.lehigh.minty.miners.model;


// todo: Please set your target such that the probability of success is 50%
// todo: nonce concatenated with the root hash of the Merkle tree is hashed by SHA-256 to a value less than or equal to the specified target

// todo: first input file was X.txt, the output file must be named X.block.out and contains the list of blocks
//starting with the last block in the specified print format and showing the complete address/balance list.

import java.util.Arrays;
import java.util.Map;

public class Block {
    private final Header header;

    private final MerkleTree merkleTree; // full list of accounts

    public Block(Header header, MerkleTree merkleTree) {
        this.header = header;
        this.merkleTree = merkleTree;

        printBlock();
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
