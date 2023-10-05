import java.math.BigInteger;
import java.util.Random;

public class TargetSetter {

    // Constants for target calculations
    private static final int NUM_BITS = 256; // 256 bits for SHA-256
    private static final BigInteger MAX_TARGET = BigInteger.ONE.shiftLeft(NUM_BITS).subtract(BigInteger.ONE);
    
    // Set the success probability to 50%
    private static final double SUCCESS_PROBABILITY = 0.5;

    // Target threshold for 50% probability
    private BigInteger targetThreshold;

    public TargetSetter() {
        // Calculate target threshold for 50% probability
        targetThreshold = calculateTargetForSuccessProbability(SUCCESS_PROBABILITY);
    }

    public BigInteger getTargetThreshold() {
        return targetThreshold;
    }

    public BigInteger calculateTargetForSuccessProbability(double successProbability) {
        // Calculate the target threshold based on success probability
        // Function to adjust the target difficulty to achieve the desired success probability
        return MAX_TARGET.multiply(BigInteger.ONE.subtract(BigInteger.valueOf((long)(successProbability * 100))));
    }

    public BigInteger getRandomNonce() {
        // Generates a random nonce within the range [0, MAX_TARGET]
        return new BigInteger(NUM_BITS, new Random());
    }
}
