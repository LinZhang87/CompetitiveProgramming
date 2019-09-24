package math;

import java.util.Random;

/**
 * @author lzhang
 * @since 5/23/19
 */


/*
This problem was asked by Two Sigma.

Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.

The first game: roll a die repeatedly. Stop rolling once you get a five followed by a six. Your number of rolls is the amount you pay, in dollars.

The second game: same, except that the stopping condition is a five followed by a five.

Which of the two games should Alice elect to play? Does it even matter? Write a program to simulate the two games and calculate their expected value.

Q:
Rolling a die multiple times are consist of independent events, so I expect the expected value should be about the same, around 36.
Game2's expected value is around 42, not 36?
 */

public class MembershipDues {
    public static void main(String[] args) {
        int game1Total = 0, game2Total = 0;

        for(int i = 0; i < 100000; i++) {
            //game1Total += playGame1();
            game2Total += playGame2();
        }

        //System.out.println("the expected due of game 1 is " + game1Total / 100000);
        System.out.println("the expected due of game 2 is " + game2Total / 100000);
    }
    private static int playGame1() {
        int count = 0;
        boolean prevFive = false;

        Random random = new Random();

        while(true) {
            int val = 1 + random.nextInt(6);
            count++;
            if(val == 6 && prevFive) {
                break;
            }
            else if(val == 5) {
                prevFive = true;
            }
            else {
                prevFive = false;
            }
        }
        return count;
    }
    private static int playGame2() {
        int count = 0;
        boolean prevFive = false;

        Random random = new Random();
        while(true) {
            int val = 1 + random.nextInt(6);
            count++;
            if(val == 5 && prevFive) {
                break;
            }
            else if(val == 5) {
                prevFive = true;
            }
            else {
                prevFive = false;
            }
        }
        return count;
    }
}
