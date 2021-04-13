package bitwise;

/**
 * @author lzhang
 * @since 7/16/20
 */

/*
    Given a bitmask m, efficiently visit all submasks of m in descending order, without repetition

    Runtime: O(3^N), N is the the total number of bits
 */
public class SubmaskEnumeration {

    //This implementation does not process submask of 0, so if we need to process submask of 0, we need
    //to do it outside the for-loop.
    public void enumerateSubmask(int m) {
        for(int i = m; i > 0; i = (i - 1) & m) {
            //do something with submask i
        }
        //do something with submask 0 if needed
    }
}
