package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MerkleTree {
    private final ArrayList<MerkleNode> tree = new ArrayList<MerkleNode>();


    public static void calculateRoot(ArrayList<String> lines, MerkleTree tree) {
        // todo:calculate root and tree

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
