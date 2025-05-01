package app.games;

import app.gameengine.Game;

public class GameFactory {

    public static Game getGame(String gameName) {
        Game game = null;
        switch (gameName) {
            case "sample top down game":
                game = new SampleTopDownGame();
                break;

            /*
             * As is mentioned in `Configuration.java`, you can set the
             * `game` instance variable to be any string you want. Then, add
             * the string here in this switch statement and start up your
             * own game. For information on how a game should be setup,
             * checkout `SampleTopDownGame.java`
             */
            case "your game here":
                // game = new YourAmazingGame();
                break;
            default:
                System.out.println("No such game");
        }
        return game;
    }

}
