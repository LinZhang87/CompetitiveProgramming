package math;

/**
 * @author lzhang
 * @since 12/3/19
 */

/*
    Given an integer, find out the number of digits this integer has
 */
public class DigitsOfANumber {
    /*
        Convert int to string then return string length, the least optimal
     */
    public static int stringBased(long v) {
        v = Math.abs(v);
        return String.valueOf(v).length();
    }

    /*
        Logarithmic Approach, need to handle input 0 separately log10(0) is undefined.
     */
    public static int logarithmic(long v) {
        v = Math.abs(v);
        if(v == 0) {
            return 1;
        }
        return (int)(Math.log10(v)) + 1;
    }

    /*
        Repeated Multiplication
     */
    public static int repeatedMultiplication(long v) {
        v = Math.abs(v);
        if(v == 0) {
            return 1;
        }
        long d = v;
        int len = 0;
        while(d > 0) {
            d /= 10;
            len++;
        }
        return len;
    }

    /*
        Dividing with Powers of Two

        If we know about the range of our number, then we can use a variation that will further reduce our comparisons.
        This method divides the number by powers of two (e.g. 1, 2, 4, 8 etc.):

        It takes advantage of the fact that any number can be represented by the addition of powers of 2.
        For example, 15 can be represented as 8+4+2+1, which all are powers of 2.

        For a 15 digit number, we would be doing 15 comparisons in our previous approach, which we have reduced to just 4 in this method.

        long type is slightly more than 9 * 10^18; int type is slightly more than 2 * 10^9.
     */
    public static int dividingWithPowersOfTwo(long v) {
        v = Math.abs(v);
        int len = 1;
        if(v >= (long)1e16) {
            len += 16;
            v /= (long)1e16;
        }
        if(v >= (long)1e8) {
            len += 8;
            v /= (long)1e8;
        }
        if(v >= (long)1e4) {
            len += 4;
            v /= (long)1e4;
        }
        if(v >= 100) {
            len += 2;
            v /= 100;
        }
        if(v >= 10) {
            len += 1;
        }
        return len;
    }

    /*
         If we need the range of the input, we can use divide and conquer, for simplicity, make input tpye int instead of long
         Fastest because we're not performing any type of conversion, multiplication, addition or object initialization.
     */
    public static int divideAndConquer(int v) {
        v = Math.abs(v);
        if(v < 100000) {
            if(v < 100) {
                if(v < 10) {
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else {
                if(v < 1000) {
                    return 3;
                }
                else if(v < 10000) {
                    return 4;
                }
                else {
                    return 5;
                }
            }
        }
        if (v < 10000000) {
            if (v < 1000000) {
                return 6;
            } else {
                return 7;
            }
        } else {
            if (v < 100000000) {
                return 8;
            } else {
                if (v < 1000000000) {
                    return 9;
                } else {
                    return 10;
                }
            }
        }
    }
}
