package app.games.topdownobjects;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.controller.WASDMovement;
import app.gameengine.model.physics.PhysicsEngine;

/**
 * Basic level in the style of classic zelda
 */
public class TopDownLevel extends Level {

	private static final double defaultSpeed = 7.0;
	private static final PhysicsEngine physicsEngine = new PhysicsEngine();

	public TopDownLevel(Game game, int width, int height, String name) {
		super(game, physicsEngine, width, height, name);
		this.gameControls = new WASDMovement(game, defaultSpeed);
		this.wallOffBoundary();
	}


}
