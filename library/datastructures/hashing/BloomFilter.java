package datastructures.hashing;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;

/**
 * @author lzhang
 * @since 9/30/19
 */

public class BloomFilter {
    private BitSet bitSet;
    private static final String MD5 = "MD5";
    private static final String SHA256 = "SHA-256";
    public BloomFilter(int N) {
        bitSet = new BitSet(N);
    }

    public void add(int value) throws NoSuchAlgorithmException {
        int idx1 = MD5Hashing(value);
        bitSet.set(idx1);
        int idx2 = SHA256Hashing(value);
        bitSet.set(idx2);
    }

    public boolean contains(int value) throws NoSuchAlgorithmException {
        int idx1 = MD5Hashing(value);
        int idx2 = SHA256Hashing(value);
        return bitSet.get(idx1) && bitSet.get(idx2);
    }

    private int MD5Hashing(int value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5);
        md.update(intToByteArray(value));
        byte[] digest = md.digest();
        BigInteger bi = new BigInteger(digest);
        bi = bi.mod(BigInteger.valueOf(bitSet.size()));
        return bi.intValue();
    }

    private int SHA256Hashing(int value) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA256);
        md.update(intToByteArray(value));
        byte[] digest = md.digest();
        BigInteger bi = new BigInteger(digest);
        bi = bi.mod(BigInteger.valueOf(bitSet.size()));
        return bi.intValue();
    }

    private byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value
        };
    }
}
