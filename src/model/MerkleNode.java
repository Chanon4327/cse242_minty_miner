package model;

public class MerkleNode {
    private final String address;
    private final int balance;
    private final String hash;

    public MerkleNode(String address, int balance, String hash) {
        this.address = address;
        this.balance = balance;
        this.hash = hash;
    }

    public MerkleNode(String address, int balance) {
        this.address = address;
        this.balance = balance;
        this.hash = toHash(address, balance);
    }

    public String getAddress() {
        return address;
    }

    public int getBalance() {
        return balance;
    }

    public String getHash() {
        return hash;
    }

    public static String toHash(String address, int balance) {
        // todo: create sha-256 hash, of address and balance concatenated
        return null;
    }
}
