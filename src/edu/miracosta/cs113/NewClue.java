package edu.miracosta.cs113;

/**
 * RandomClue.java : Your job is to ask your AssistantJack and get the correct
 * answer in <= 20 tries.  RandomClue is ONE solution to the problem,
 * where a set of random numbers is generated every attempt until all three
 * random numbers match the solution from the AssistantJack object.
 *
 * This is a sample solution, a driver using random number implementation.
 * You can use this file as a guide to create your own SEPARATE driver for
 * your implementation that can solve it in <= 20 times consistently.
 *
 * @author Nery Chapeton-Lamas (material from Kevin Lewis)
 * @version 1.0
 *
 */

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import model.Theory;
import model.AssistantJack;

public class NewClue {

    /**
     * Driver method for random guessing approach
     *
     * @param args not used for driver
     */
    public static void main(String[] args) {
        // DECLARATION + INITIALIZATION
        int answerSet, solution = -1, murder, weapon, location;
        Theory answer;
        AssistantJack jack;
        Scanner keyboard = new Scanner(System.in);
        Random random = new Random();

        /**
         * Record incorrect weapon from checkAnswer.
         */
        Set<Integer> incorrectWeaponSet = new HashSet<>();
        /**
         * Record incorrect location from checkAnswer.
         */
        Set<Integer> incorrectLocationSet = new HashSet<>();
        /**
         * Record incorrect murder from checkAnswer.
         */
        Set<Integer> incorrectMurderSet = new HashSet<>();

        // INPUT
        System.out.println("Which theory would like you like to test? (1, 2, 3[random]): ");
        answerSet = keyboard.nextInt();
        keyboard.close();

        // PROCESSING
        jack = new AssistantJack(answerSet);

        do {
            weapon = random.nextInt(6) + 1;
            location = random.nextInt(10) + 1;
            murder = random.nextInt(6) + 1;

//            if (!incorrectWeaponSet.contains(weapon) && !incorrectLocationSet.contains(location) && !incorrectMurderSet.contains(murder)) {
            if (!(incorrectWeaponSet.contains(weapon) || incorrectLocationSet.contains(location) || incorrectMurderSet.contains(murder))) {
                solution = jack.checkAnswer(weapon, location, murder);
                if (solution == 1) { // weapon is incorrect.
                    incorrectWeaponSet.add(weapon);
                } else if (solution == 2) { // location is incorrect
                    incorrectLocationSet.add(location);
                } else if (solution == 3) { // murder is incorrect
                    incorrectMurderSet.add(murder);
                }
            }

        } while (solution != 0);

        answer = new Theory(weapon, location, murder);

        // OUTPUT
        System.out.println("Total Checks = " + jack.getTimesAsked() + ", Solution " + answer);

        if (jack.getTimesAsked() > 20) {
            System.out.println("FAILED!! You're a horrible Detective...");
        } else {
            System.out.println("WOW! You might as well   be called Batman!");
        }

    }

}