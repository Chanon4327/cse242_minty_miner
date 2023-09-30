package edu.lehigh.minty.miners.model;

import edu.lehigh.minty.miners.Main;

public class Header {
    private final String previousHeaderHash;
    private final String merkleRootHash;
    private final long timestamp; // seconds since epoch

    private final byte[] nonce; // bruteforce

    private final byte[] target; // we choose (its a sha 256 hash) 50% probability with nonce

    public Header(String previousHeaderHash, String merkleRootHash, long timestamp, byte[] nonce, byte[] target) {
        this.previousHeaderHash = previousHeaderHash;
        this.merkleRootHash = merkleRootHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.target = target;
    }

    public String getPreviousHeaderHash() {
        return previousHeaderHash;
    }

    public String getMerkleRootHash() {
        return merkleRootHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public byte[] getNonce() {
        return nonce;
    }

    public byte[] getTarget() {
        return target;
    }


    public String toHash() {
        return Main.hash(previousHeaderHash
                .concat(merkleRootHash)
                .concat(Long.toString(timestamp))
                .concat(new String(nonce))
                .concat(new String(target)));
    }

    public static class Builder {
        private String previousHeaderHash;
        private String merkleRootHash;
        private long timestamp; // seconds since epoch

        private byte[] nonce; // bruteforce

        private byte[] target; // we choose (its a sha 256 hash) 50% probability with nonce


        public String getPreviousHeaderHash() {
            return previousHeaderHash;
        }

        public String getMerkleRootHash() {
            return merkleRootHash;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public byte[] getNonce() {
            return nonce;
        }

        public byte[] getTarget() {
            return target;
        }

        public Builder setPreviousHeaderHash(String previousHeaderHash) {
            this.previousHeaderHash = previousHeaderHash;
            return this;
        }

        public Builder setMerkleRootHash(String merkleRootHash) {
            this.merkleRootHash = merkleRootHash;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setNonce(byte[] nonce) {
            this.nonce = nonce;
            return this;
        }

        public Builder setTarget(byte[] target) {
            this.target = target;
            return this;
        }

        public Header build() {
            return new Header(previousHeaderHash, merkleRootHash, timestamp, nonce, target);
        }
    }
}