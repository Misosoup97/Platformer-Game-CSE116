package app.gameengine.controller;

import app.gameengine.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * These controls will be used in future tasks. It controls movements of left and right with the up button
 * sending a jump signal to the game through a method call
 */
public class PlatformerMovement extends GameControls {

    private double strafeSpeed;

    public PlatformerMovement(Game game, double strafeSpeed) {
        super(game);
        this.strafeSpeed = strafeSpeed;
    }

    @Override
    public void handle(KeyEvent event) {
        super.handle(event);

        boolean upPressed = this.keyStates.getOrDefault(KeyCode.UP, false) || this.keyStates.getOrDefault(KeyCode.W, false);
        boolean leftPressed = this.keyStates.getOrDefault(KeyCode.LEFT, false) || this.keyStates.getOrDefault(KeyCode.A, false);
        boolean rightPressed = this.keyStates.getOrDefault(KeyCode.RIGHT, false) || this.keyStates.getOrDefault(KeyCode.D, false);

        if (leftPressed && rightPressed) {
            this.game.getPlayer().getVelocity().setX(0);
            this.game.getPlayer().freezeAnimations();
        } else if (leftPressed) {
            this.game.getPlayer().getVelocity().setX(-this.strafeSpeed);
            this.game.getPlayer().getOrientation().setX(-1.0);
            this.game.getPlayer().setAnimationState("walk_left");
        } else if (rightPressed) {
            this.game.getPlayer().getVelocity().setX(this.strafeSpeed);
            this.game.getPlayer().getOrientation().setX(1.0);
            this.game.getPlayer().setAnimationState("walk_right");
        } else {
            this.game.getPlayer().getVelocity().setX(0.0);
        }

        if (upPressed ) {
            this.game.getCurrentLevel().jumpButtonPressed();
        }else{
            this.game.getCurrentLevel().jumpButtonReleased();
        }

        if (this.game.getPlayer().getVelocity().getX() == 0.0) {
            this.game.getPlayer().freezeAnimations();
        }

        if (event.getEventType() == KeyEvent.KEY_PRESSED) {
            if (event.getCode() == KeyCode.SPACE) {
                this.game.getCurrentLevel().actionButtonPressed();
            }
            if (event.getCode() == KeyCode.TAB) {
                this.game.getPlayer().cycleInventory();
            }
        }
    }
}
