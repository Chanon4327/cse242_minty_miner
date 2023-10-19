package edu.lehigh.minty.miners;

import edu.lehigh.minty.miners.model.Block;
import edu.lehigh.minty.miners.model.Blockchain;
import edu.lehigh.minty.miners.model.Header;
import org.junit.Test;
import org.junit.Assert;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BlockTest {
    @Test
    public void testInvalidBlock() {

        // lists of merkle tree lines
        ArrayList<List<String>> blockLists = new ArrayList<>();

        // filling list
        for (int i = 0; i < 1; i++) {
            List<String> lines = new ArrayList<>();
            for (int i1 = 0; i1 < 50; i1++) {
                lines.add(fakeAddress() + " " + ThreadLocalRandom.current().nextInt(0, 0xffff) + "\n");
            }
            blockLists.add(lines);
        }

        // create blockchain


        Blockchain blockchain = new Blockchain(blockLists);

        // validate the blockchain
        assertTrue("Blockchain was not validated!", blockchain.validateBlockChain());

        // validate a block from within the blockchain
        assertTrue("Random block in blockchain was not validated!",blockchain
                .validateBlock(blockchain.getBlocks().get(ThreadLocalRandom.current().nextInt(0, blockchain.getBlocks().size()))));

        // generate a bad block

        Header.Builder builder = new Header.Builder();

        // back to the future
        builder.setTimestamp(Instant.now().plusSeconds(10000).getEpochSecond());

        // randomly get a nonce
        builder.setNonce(blockchain.getBlocks().get(0).getHeader().getNonce());

        // randomly get a target
        builder.setTarget(blockchain.getBlocks().get(3).getHeader().getTarget());

        String hash = blockchain.getBlocks().get(2).getHeader().getMerkleRootHash();

        // incorrect merkle hash
        builder.setMerkleRootHash(hash);

        // same hash as root hash which should be indicator
        builder.setPreviousHeaderHash(hash);


        Block b = new Block(builder, blockchain.getBlocks().get
                (ThreadLocalRandom.current().nextInt(0, blockchain.getBlocks().size())).getMerkleTree());

        assertFalse("Bad block was validated!", blockchain.validateBlock(b));


    }


    private String fakeAddress() {
        char[] validChars = new char[]{'a', 'b', 'c', 'd', 'e', 'f', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 40; i++) {
            sb.append(validChars[ThreadLocalRandom.current().nextInt(0, validChars.length)]);
        }
        return sb.toString();
    }
}
