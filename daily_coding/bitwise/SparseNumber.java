package bitwise;

/**
 * @author lzhang
 * @since 7/1/19
 */

/*
This problem was asked by Oracle.

We say a number is sparse if there are no adjacent ones in its binary representation.
For example, 21 (10101) is sparse, but 22 (10110) is not. For a given input N, find the smallest sparse number greater than or equal to N.

Do this in faster than O(N log N) time.
 */

/*
First try: let's assume the input N is a signed integer and there is always a valid answer that is within the 32 bit signed integer range.

1. first find the leftmost adjacent 1s, if none, simply return the input N as it is already a sparse number.
2. If found, since we are going to find the next greater sparse number, it means we must add a carry to the next higher bit of this adjacent 1 group
and make all the lower bits to 0. From here, we have two cases:
(a) the next next higher bit is 0, then we just found the next greater sparse number;
(b) the next next higher bit is 1, then we just created a new adjacent 1 group. Repeat adding carry until we found a sparse number.

Runtime: O(Length of N)
O(Length of N) time to find the the leftmost adjacent 1s;

Each recursive calls takes constant time and we have O(Length of N) recursive calls.
 */

public class SparseNumber {

    public static int nextGreaterOrEqual(int N) {
        if(N > 0xAAAAAAAA && N < 0) {
            return 0;
        }
        int mask = 1 << 31;
        int i;
        boolean higherSet = false;
        for(i = 0; i < 32; i++) {
            if((N & mask) != 0 && higherSet) {
                break;
            }
            else if((N & mask) != 0) {
                higherSet = true;
            }
            else {
                higherSet = false;
            }
            mask = mask >>> 1;
        }
        if(i == 32) {
            return N;
        }
        N = N | (1 << (31 - i + 2));
        N = N & (0xFFFFFFFF << (31 - i + 2));
        return nextGreaterOrEqual(N);
    }

        public static int nextGreaterOrEqualIterative(int N) {
        if(N > 0xAAAAAAAA && N < 0) {
            return 0;
        }
        int mask = 1 << 31;
        int i;
        boolean higherSet = false;
        for(i = 0; i < 32; i++) {
            if((N & mask) != 0 && higherSet) {
                break;
            }
            else if((N & mask) != 0) {
                higherSet = true;
            }
            else {
                higherSet = false;
            }
            mask = mask >>> 1;
        }
        if(i == 32) {
            return N;
        }
        i -= 3;
        mask = 1 << (31 - i);
        while(i >= 0 && (N & mask) != 0) {
            i -= 2;
            mask = mask << 2;
        }
        i += 1;
        //set high bit
        N = N | (1 << (31 - i));
        //clear all lower bits
        return N & (0xFFFFFFFF << (31 - i));
    }

    private static boolean isSparse(int n) {
        return (n & (n << 1)) == 0;
    }

    private static boolean rangeCheck(int l, int r) {
        while(l < r) {
            if(isSparse(l)) {
                return true;
            }
            l++;
        }
        return false;
    }


    public static void main(String[] args) {
        // 0 -> 0
        System.out.println(nextGreaterOrEqual(0));
        // 1 -> 1
        System.out.println(nextGreaterOrEqual(1));
        // 14 -> 16
        System.out.println(nextGreaterOrEqual(14));
        // 21 -> 21
        System.out.println(nextGreaterOrEqual(21));
        // 22 -> 32
        System.out.println(nextGreaterOrEqual(22));
        // -1 -> 0
        System.out.println(nextGreaterOrEqual(-1));
        // -10 -> 0
        System.out.println(nextGreaterOrEqual(-10));
        // Integer.MIN_VALUE + 3 -> Integer.MIN_VALUE + 4
        System.out.println(nextGreaterOrEqual(Integer.MIN_VALUE + 3));
        System.out.println(Integer.MIN_VALUE + 4);


        // 0 -> 0
        System.out.println(nextGreaterOrEqualIterative(0));
        // 1 -> 1
        System.out.println(nextGreaterOrEqualIterative(1));
        // 14 -> 16
        System.out.println(nextGreaterOrEqualIterative(14));
        // 21 -> 21
        System.out.println(nextGreaterOrEqualIterative(21));
        // 22 -> 32
        System.out.println(nextGreaterOrEqualIterative(22));
        // -1 -> 0
        System.out.println(nextGreaterOrEqualIterative(-1));
        // -10 -> 0
        System.out.println(nextGreaterOrEqualIterative(-10));
        // Integer.MIN_VALUE + 3 -> Integer.MIN_VALUE + 4
        System.out.println(nextGreaterOrEqualIterative(Integer.MIN_VALUE + 3));
        System.out.println(Integer.MIN_VALUE + 4);

//        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE + 3));
//        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE + 4));
//        System.out.println(Integer.toBinaryString(-10));
//        System.out.println(Integer.toBinaryString(-9));



//        //The max positive sparse integer is 0x55555555
//        System.out.println(rangeCheck(0x55555555 + 1, Integer.MAX_VALUE));
//        //The max negative sparse integer is 0xAAAAAAAA
//        System.out.println(rangeCheck(0xAAAAAAAA + 1 , -1));
    }
}
