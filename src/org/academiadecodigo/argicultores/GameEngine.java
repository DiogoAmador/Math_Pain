package org.academiadecodigo.argicultores;

import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private int level;
    private int maxLevels;
    private int firstOp;
    private int secondOp;
    private int thirdOp;
    private int forthOp;
    private int fifthOp;

    private HashMap<String, Integer> levels;


    public GameEngine(int maxLevels) {
        this.maxLevels = maxLevels;
        this.level = 1;
        levels = new HashMap<>();
        levelGenerator(1);
        levelGenerator(2);


    }

    public void startGame() {
        level = 1;

    }

    public String levelGenerator(int nivel) {

        String question = "";

        switch (nivel) {
            case 1:

                firstOp = getRandom(1, 10);
                secondOp = getRandom(1, 10);
                int result = firstOp + secondOp;
                question = firstOp + " + " + secondOp + " ? ";

                levels.put(question, result);
                break;

            case 2:

                firstOp = getRandom(5, 15);
                secondOp = getRandom(1, 10);
                thirdOp = getRandom(1, 10);
                int result2 = firstOp + secondOp + thirdOp;
                question = firstOp + " + " + secondOp + " + " + thirdOp + " ? ";

                levels.put(question, result2);
                break;

            case 3:

                firstOp = getRandom(10, 15);
                secondOp = getRandom(1, 10);
                thirdOp = getRandom(10, 15);
                int result3 = firstOp + secondOp + thirdOp;
                question = firstOp + " + " + secondOp + " + " + thirdOp + " ? ";

                levels.put(question, result3);
                break;

            case 4:

                firstOp = getRandom(10, 15);
                secondOp = getRandom(1, 10);
                thirdOp = getRandom(10, 15);
                forthOp = getRandom(1, 10);
                int result4 = firstOp + secondOp + thirdOp - forthOp;
                question = firstOp + " + " + secondOp + " + " + thirdOp + " - " + forthOp + " ? ";

                levels.put(question, result4);
                break;

            case 5:

                firstOp = getRandom(1, 10);
                secondOp = getRandom(1, 10);
                thirdOp = getRandom(10, 15);
                forthOp = getRandom(1, 10);
                fifthOp = getRandom(1,5);
                int result5 = firstOp + secondOp + thirdOp - forthOp + fifthOp;
                question = firstOp + " + " + secondOp + " + " + thirdOp + " - " + forthOp + " + " + fifthOp + " ? ";

                levels.put(question, result5);
                break;
        }


        return question;

    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public int getRandom(int min, int max) {
        return (int) Math.ceil(Math.random() * max - min) + min;
    }

    public HashMap<String, Integer> getLevels() {
        return levels;
    }

    public int getMaxLevels() {
        return maxLevels;
    }
}
