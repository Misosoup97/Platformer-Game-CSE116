package app.gameengine;

import app.gameengine.graphics.SpriteGraphics;
import app.gameengine.model.gameobjects.GameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Vector2D;

import java.util.HashMap;

/**
 * Controls the UI and levels of the game.
 *
 * You can extend this class if you would like to build a different
 * game. The game that is played by default is `SampleTopDownGame.java`
 */
public class Game {

    private Player player = new Player(new Vector2D(0.0, 0.0), 10);
    private Level currentLevel;
    private long lastUpdate = 0L;
    protected String displayString = "This is where UI information would go, like HP, number of keys, or inventory";

    public Game() {
    }


    public Player getPlayer() {
        return player;
    }
    
    public String getUI() {
        return this.getCurrentLevel().getName() + " - " + this.player.getHP() + "/" + this.player.getMaxHP() + " - "
        + this.displayString;
    }


    public Level getCurrentLevel() {
        return this.currentLevel;
    }
    
    public void loadLevel(Level level) {
        this.currentLevel = level;
        this.player.getLocation().setX(level.getPlayerStartLocation().getX());
        this.player.getLocation().setY(level.getPlayerStartLocation().getY());
        this.currentLevel.getDynamicObjects().removeIf(GameObject::isPlayer); // prevents the player from being in the level more than once
        this.currentLevel.getDynamicObjects().add(this.player);
    }

    public void update(long timestamp) {
        if (this.lastUpdate != 0) {
            double dt = (timestamp - this.lastUpdate) / 1000000000.0;
            this.currentLevel.update(dt);
            if (this.player.isDestroyed()) {
                this.player.setHP(this.player.getMaxHP());
                this.player.revive();
                this.loadLevel(this.currentLevel);
            }
        }
        this.lastUpdate = timestamp;
    }

    public void advanceLevel(){

    }

}
