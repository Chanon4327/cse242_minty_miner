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
}
