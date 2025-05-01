package app;

/**
 * This class provides all the configuration necessary for the game
 */
public class Configuration {

    /**
     * game is used in `GameFactory.java` to launch the game. If you wanted to
     * create a different style of game, you would change this string and update
     * the switch statement in `GameFactory.java`
     */
    public static final String game = "sample top down game";

    /**
     * Sprite size in pixels
     */
    public static final int spriteSize = 16;

    /**
     * How big you want the game window to be.
     *
     * You should use a whole number for the best results, otherwise the game
     * won't be pixel perfect.
     */
    public static final double zoom = 3.0;

    /**
     * Set to true for a sharper image
     */
    public static final boolean integerScale = true;

    /**
     * Time in seconds to spend on each frame of animation
     */
    public static final double animationTime = 0.1;

}
