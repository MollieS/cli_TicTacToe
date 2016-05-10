package test;

import main.*;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class GamePlayTest {

    private GamePlay gameplay;
    private DisplayFake display;
    private InputFake input;

    @Before
    public void setUp() {
        Board board = new Board();
        ConsoleBoard consoleBoard = new ConsoleBoard();
        Player player1 = new HumanPlayer("X");
        Player player2 = new HumanPlayer("O");
        GameEngine gameEngine = new GameEngine(player1, player2, board);
        this.input = new InputFake();
        this.display = new DisplayFake();
        this.gameplay = new GamePlay(gameEngine, input, display, consoleBoard);
    }

    @Test
    public void promptsUserForLocation() {
        input.set("5", "1", "4", "2", "6");
        gameplay.start();
        assertTrue(displayContains("choose a location"));
    }

    @Test
    public void onlyAcceptsNumbers() {
        input.set("Hello", "5", "1", "4", "2", "6");
        gameplay.start();
        assertTrue(displayContains("Please choose a valid option"));
    }

    @Test
    public void loopsUntilValidLocation() {
        input.set("10", "5", "1", "4", "2", "6", "7", "9");
        gameplay.start();
        assertTrue(displayContains("Please choose a location from 1 to 9"));
    }

    private boolean displayContains(String s) {
        return display.read().contains(s);
    }

    @Test
    public void playsADraw() {
        input.set("1", "2", "3", "4", "6", "7", "8", "9", "5");
        gameplay.start();
        assertTrue(displayContains(drawBoard()));
        assertTrue(displayContains("It's a draw!"));
    }

    @Test
    public void returnsWinner() {
        input.set("5", "1", "4", "2", "6");
        gameplay.start();
        assertTrue(displayContains("X wins!"));
    }

    @Test
    public void cannotChooseTakenLocation() {
        input.set("1", "4", "2", "5", "2", "3");
        gameplay.start();
        assertTrue(displayContains("Already taken"));
    }

    private String drawBoard() {
        return "-------------" + "\n" +
                "| X | O | X |" + "\n" +
                "-------------" + "\n" +
                "| O | X | X |" + "\n" +
                "-------------" + "\n" +
                "| O | X | O |" + "\n" +
                "-------------";
    }
}