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

    public static MerkleNode fromString(String serialized) {

        String[] split = serialized.split(" ");

        if (split.length != 2) {
            throw new RuntimeException("Invalid serialized string length (" + split.length + ") \n" + serialized);
        }

        if (split[0].length() != 40) {
            throw new RuntimeException("Invalid address length " + split[0].length() + " != 40,\n " + split[0]);
        }


        Number number;
        try {
            number = Integer.parseInt(split[1].strip());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid balance " + split[1]);
        }

        if (number.intValue() < 0) {
            throw new RuntimeException("Invalid balance " + number.intValue() + " < 0,\n " + split[1]);
        }


        return new MerkleNode(split[0], number.intValue());

    }

    @Override
    public String toString() {
        return address + " " + balance;
    }
}
