package edu.lehigh.minty.miners.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MerkleTree {
    private final List<MerkleNode> tree;

    private MerkleTree(ArrayList<String> lines) {

        List<MerkleNode> tempNodes = lines.stream().map(MerkleNode::fromString).filter(Objects::nonNull).toList();


        tree = calculateMerkle(tempNodes);

    }

    public String getRootHash() {
        return tree.get(0).getHash();
    }

    public List<MerkleNode> getTree() {
        return tree;
    }

    private List<MerkleNode> calculateMerkle(List<MerkleNode> tmp) {
        if (tmp.size() <= 1) {
            return tmp;
        }


        List<MerkleNode> merklemerkle = new ArrayList<MerkleNode>();
        for (int i = 0; i < tmp.size()-1; i += 2) {
            // otherwise concat hash both and concat them
            String stringHash = MerkleNode.hash(tmp.get(i).getHash().concat(tmp.get(i + 1).getHash()));
            merklemerkle.add(new MerkleNode(null, -1, stringHash));
        }

        // Modulo for remainder to get last remaining child if uneven array size
        if (tmp.size() % 2 == 1) {
            String hash = tmp.get(tmp.size()-1).getHash();
            // Hash of the parent node is just the hash of the child node for odd number of nodes
            merklemerkle.add(new MerkleNode(null, -1, hash));
        }

        return calculateMerkle(merklemerkle); // recursive call

    }


    public static MerkleTree readFile(String filename) {

        ArrayList<String> lines = new ArrayList<String>();


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {

            String line;

            // Read and print from the file line by line
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return new MerkleTree(lines);

    }
}
