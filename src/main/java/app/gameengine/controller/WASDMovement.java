package app.gameengine.controller;

import app.gameengine.Game;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Handles movement of the player when the wasd or arrow buttons are pressed.
 *
 * Also handles shooting and tabbing between weapons.
 */
public class WASDMovement extends GameControls {

    private double playerSpeed;

    public WASDMovement(Game game, double playerSpeed) {
        super(game);
        this.playerSpeed = playerSpeed;
    }

    @Override
    public void handle(KeyEvent event) {
        super.handle(event);

        boolean upPressed = this.keyStates.getOrDefault(KeyCode.UP, false) || this.keyStates.getOrDefault(KeyCode.W, false);
        boolean leftPressed = this.keyStates.getOrDefault(KeyCode.LEFT, false) || this.keyStates.getOrDefault(KeyCode.A, false);
        boolean downPressed = this.keyStates.getOrDefault(KeyCode.DOWN, false) || this.keyStates.getOrDefault(KeyCode.S, false);
        boolean rightPressed = this.keyStates.getOrDefault(KeyCode.RIGHT, false) || this.keyStates.getOrDefault(KeyCode.D, false);



        if (upPressed && downPressed) {
            this.game.getPlayer().getVelocity().setY(0);
            this.game.getPlayer().getOrientation().setY(0);
            this.game.getPlayer().freezeAnimations();
        } else if (upPressed) {
            this.game.getPlayer().getVelocity().setY(-this.playerSpeed);
            this.game.getPlayer().getOrientation().setX(0.0);
            this.game.getPlayer().getOrientation().setY(-1.0);
            this.game.getPlayer().setAnimationState("walk_up");
        } else if (downPressed) {
            this.game.getPlayer().getVelocity().setY(this.playerSpeed);
            this.game.getPlayer().getOrientation().setX(0.0);
            this.game.getPlayer().getOrientation().setY(1.0);
            this.game.getPlayer().setAnimationState("walk_down");
        } else {
            this.game.getPlayer().getVelocity().setY(0.0);
        }

        if (leftPressed && rightPressed) {
            this.game.getPlayer().getVelocity().setX(0);
            this.game.getPlayer().getOrientation().setX(0);
            this.game.getPlayer().freezeAnimations();
        } else if (leftPressed) {
            this.game.getPlayer().getVelocity().setX(-this.playerSpeed);
            this.game.getPlayer().getOrientation().setX(-1.0);
            this.game.getPlayer().getOrientation().setY(0.0);
            this.game.getPlayer().setAnimationState("walk_left");
        } else if (rightPressed) {
            this.game.getPlayer().getVelocity().setX(this.playerSpeed);
            this.game.getPlayer().getOrientation().setX(1.0);
            this.game.getPlayer().getOrientation().setY(0.0);
            this.game.getPlayer().setAnimationState("walk_right");
        } else {
            this.game.getPlayer().getVelocity().setX(0.0);
        }

        if (this.game.getPlayer().getVelocity().getX() != 0.0 && this.game.getPlayer().getVelocity().getY() != 0.0) {
            this.game.getPlayer().getVelocity().setX(this.game.getPlayer().getVelocity().getX() * 0.707);
            this.game.getPlayer().getVelocity().setY(this.game.getPlayer().getVelocity().getY() * 0.707);
        }

        if (this.game.getPlayer().getVelocity().getX() == 0.0 && this.game.getPlayer().getVelocity().getY() == 0.0) {
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
