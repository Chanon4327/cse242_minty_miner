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

    public String getAddress() {
        return address;
    }

    public int getBalance() {
        return balance;
    }

    public String getHash() {
        return hash;
    }
}
