package simulation;

/**
 * @author lzhang
 * @since 8/30/19
 */

/*
UTF-8 is a character encoding that maps each symbol to one, two, three, or four bytes.
For example, the Euro sign, €, corresponds to the three bytes 11100010 10000010 10101100.
The rules for mapping characters are as follows:

For a single-byte character, the first bit must be zero.
For an n-byte character, the first byte starts with n ones and a zero. The other n - 1 bytes all start with 10.
Visually, this can be represented as follows.

 Bytes   |           Byte format
-----------------------------------------------
   1     | 0xxxxxxx
   2     | 110xxxxx 10xxxxxx
   3     | 1110xxxx 10xxxxxx 10xxxxxx
   4     | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
Write a program that takes in an array of integers representing byte values, and returns whether it is a valid UTF-8 encoding.
 */
public class UTF8Encoding {
    public static boolean isValidEncoding(int[] v) {
        if(v.length < 1 || v.length > 4) {
            return false;
        }
        boolean valid = true;
        switch (v.length) {
            case 1:
                valid = v[0] >= 0 && v[0] <= 0x0000007F;
                break;
            case 2:
                valid = v[0] >= 0x000000C0 && v[0] <= 0x000000DF && v[1] >= 0x00000080 && v[1] <= 0x000000BF;
                break;
            case 3:
                valid = v[0] >= 0x000000E0 && v[0] <= 0x000000EF
                        && v[1] >= 0x00000080 && v[1] <= 0x000000BF
                        && v[2] >= 0x00000080 && v[2] <= 0x000000BF;
                break;
            case 4:
                valid = v[0] >= 0x000000F0 && v[0] <= 0x000000F7
                        && v[1] >= 0x00000080 && v[1] <= 0x000000BF
                        && v[2] >= 0x00000080 && v[2] <= 0x000000BF
                        && v[3] >= 0x00000080 && v[3] <= 0x000000BF;
                break;
        }
        return valid;
    }
}
