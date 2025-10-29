package models;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();

        }
    }


    public Die[] getDice() {
        return dice;
    }

    public void throwDice() {
        //TODO: implement throwDice method.
        for (Die die : dice) {
            die.roll();

        }

    }
}

