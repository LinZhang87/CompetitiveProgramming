package bitwise;

/**
 * @author lzhang
 * @since 6/1/19
 */

public class Bitwise {
    public static void main(String[] args) {
        //Get the value of LSB that is on(first from the right)
        //Use T = (S & (-S))
        int a = 0b01010;
        int b = 0b10101;
        System.out.println(a & (-a));
        System.out.println(b & (-b));

        //To turn on all bits in a set of size n, use S = (1 << n) - 1
        //Be careful with overflows

        //Determine if S is a power of 2: S & (S - 1) == 0

        //To get the value of the least significant bit that is on(first from the right), use T = (S & (-S))

        //Turn off the last bit in S
        //toggle all bits; get the value of the LSB that is on, then toggle all bits back and bit-or this LSB on value;
        int S = 0b101000;
        System.out.println(Integer.toBinaryString(S & (~(S & (-S))) ));
        //Turn on the last 0 in S
        S = 0b101001;
        S ^= 0xFFFFFFFF;
        System.out.println(Integer.toBinaryString((S^0xFFFFFFFF) | (S & (-S))));

        //Turn off the last consecutive run of 1s in S
        S = 0b100111;
        int i = 0;
        while(i < 32 && (S & (1 << i)) == 0) {
            i++;
        }
        while(i < 32 && (S & (1 << i)) != 0) {
            S &= (~(1 << i));
            i++;
        }
        System.out.println(Integer.toBinaryString(S));

        //Turn on the last consecutive run of 0s in S
        S = 0b100100;
        i = 0;
        while(i < 32 && (S & (1 << i)) != 0) {
            i++;
        }
        while(i < 32 && (S & (1 << i)) == 0) {
            S |= (1 << i);
            i++;
        }
        System.out.println(Integer.toBinaryString(S));
    }
}
