package cs1302.arcade;

import java.util.ArrayList;

/**
 * This is the skeleton/logic of Mancala.
 */
public class Mancala {

    ArrayList<Integer> topBoard = new ArrayList<>();
    ArrayList<Integer> botBoard = new ArrayList<>();
    // will be used to check rounds
    boolean round;
    boolean gameOver = false;

    /**
     * Mancala class constructor, creates arrays for top and bottom players.
     */
    public Mancala() {
        for (int i = 0; i < 6; i++) {
            topBoard.add(4);
        }
        topBoard.add(0);
        // creating bottom pits
        for (int i = 0; i < 6; i++) {
            botBoard.add(4);
        }
        botBoard.add(0);
        round = true;
    } // Mancala Default Constructor

    /**
     * Moves stones at specified index, returns false if game ends.
     * @param index is the pit that will move its stones.
     * @return true or false depending if the game ended.
     */
    public boolean move(int index) {

        boolean tagCheck;
        if (round) {
            if (index > 5 || index < 0) {
                return true;
            }
            tagCheck = play(index, topBoard, botBoard);
        } else {
            if (index > 12 || index < 7) {
                return true;
            }
            tagCheck = play(index - 7, botBoard, topBoard);
        }
        // checking if play method finished then will continue
        if (tagCheck) {
            int botStoreTotal = 0;
            int topStoreTotal = 0;
            for (int i = 0; i < 6; i++) {
                botStoreTotal += botBoard.get(i);
                topStoreTotal += topBoard.get(i);
            }
            if (topStoreTotal == 0 || botStoreTotal == 0) {
                endGame(topStoreTotal, botStoreTotal);
                return false;
            }

        }
        return true;
    } //move

    /**
     * Gives us the score of the stores at the end of the game.
     * @param topBonus is added stones that are left.
     * @param botBonus is added stones that are left on the bottom pits.
     */
    public void endGame(int topBonus, int botBonus) {
        int topScore = topBoard.get(6) + topBonus;
        int bottomScore = botBoard.get(6) + botBonus;
        topBoard.set(6, topScore);
        botBoard.set(6, bottomScore);
        gameOver = true;
    }

    /**
     * Implements the move method and checks if game ended.
     * @param move is the pit to be moved.
     * @param boardAlpha is the initial board's pits to be moved.
     * @param boardBeta is the later board's pits to be moved.
     * @return tag is the checker for if the game ended.
     */
    public boolean play(int move, ArrayList<Integer> boardAlpha,
        ArrayList<Integer> boardBeta) {
        boolean tag = false;
        int toMove = boardAlpha.get(move);
        if (toMove ==  0) {
            System.out.println("tried to move an empty spot");
            return tag;
        }

        boardAlpha.set(move, 0);

        int currentIndex = move + 1;
        for (int i = toMove; i > 0; i--) {
            if (currentIndex < 7) { //making sure still on alphaBoard
                boardAlpha.set(currentIndex, boardAlpha
                    .get(currentIndex) + 1);
                currentIndex++;
            } else if (currentIndex < 13) {
                boardBeta.set(currentIndex - 7, boardBeta
                    .get(currentIndex - 7) + 1);
                currentIndex++;
            } else {
                boardAlpha.set(0, boardAlpha.get(0) + 1);
                currentIndex = 1;
            }
        }
        // must reset index back one
        currentIndex--;

        if (currentIndex < 6 && boardAlpha.get(currentIndex) == 1) {
            boardAlpha.set(6, boardAlpha.get(6)
                + boardBeta.get(5 - currentIndex) + 1);
            boardAlpha.set(currentIndex, 0);
            boardBeta.set(5 - currentIndex, 0);
            tag = true;
        }
        if (currentIndex != 6) {
            round = !round;
        }
        return tag || move == 5;
    } //endGame

    /**
     * Gets the value/element at the specified index.
     * @param index is the location index.
     * @return the value of the specified index.
     */
    public int getPitValueAt(int index) {
        if (index >= 0 && index < 7) {
            return topBoard.get(index);
        } else if (index > 6 && index < 14) {
            return botBoard.get(index - 7);
        } else {
            return -1;
        }
    }

    /**
     * Gets the value/element in the Top player's store.
     * @return top store's value.
     */
    public int getTopStoreValue() {
        return topBoard.get(6);
    } //getTopStoreValue

    /**
     * Gets the value/element in the Bottom player's store.
     * @return bottom store's value.
     */
    public int getBotStoreValue() {
        return botBoard.get(6);
    } //getBotStoreValue
}
