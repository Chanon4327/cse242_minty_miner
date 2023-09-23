package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MerkleTree {
    private final ArrayList<MerkleNode> tree = new ArrayList<MerkleNode>();

    public MerkleTree() {
        merkleTree(tree);
    }

    public static void calculateRoot(ArrayList<String> lines, MerkleTree tree) {
        // todo:calculate root and tree
        System.out.println(tree);
    }

    private ArrayList<MerkleNode> merkleTree(ArrayList<MerkleNode> hashList) {

        // if the root is found, return it
        if (hashList.size() == 1) {
            return hashList;
        }

        ArrayList<String> parentHashList = new ArrayList<String>();
        for (int i = 0; i < hashList.size(); i+=2) {

            // if only one child just use the child's hash
            if (hashList.size() % 2 == 1) {
                String childHash = hashList.get(i).getHash();
                MerkleNode singleChild = new MerkleNode(childHash, i);
                tree.add(i, singleChild);
            }

            // otherwise concat hash both and concat them
            String stringHash = hashList.get(i).getHash().concat(hashList.get(i+1).getHash());
            parentHashList.add(stringHash);
            MerkleNode left = new MerkleNode(stringHash, i);
            tree.add(i, left);

        }

        return merkleTree(tree); // recursive call

    }


    public static MerkleTree readFile(String filename) {

        ArrayList<String> lines = new ArrayList<String>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line = reader.readLine();

            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filename, e);
        }

        MerkleTree tree = new MerkleTree();

        calculateRoot(lines, tree);

        return tree;

    }
}
