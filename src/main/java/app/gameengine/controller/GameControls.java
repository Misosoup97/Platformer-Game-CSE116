package app.gameengine.controller;

import app.gameengine.Game;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;


/**
 * Handles all button presses in the game. 
 *
 * You shouldn't need to modify this class. If you want to implement custom
 * buttons, check out `WASDMovement.java`
 */
public abstract class GameControls implements EventHandler<KeyEvent> {

    protected Game game;
    protected HashMap<KeyCode, Boolean> keyStates = new HashMap<>();

    public GameControls(Game game){
        this.game = game;
    }

    @Override
    public void handle(KeyEvent event) {
        if(event.getEventType() == KeyEvent.KEY_PRESSED) {
            this.keyStates.put(event.getCode(), true);
        }else if(event.getEventType() == KeyEvent.KEY_RELEASED){
            this.keyStates.put(event.getCode(), false);
        }
    }

}
