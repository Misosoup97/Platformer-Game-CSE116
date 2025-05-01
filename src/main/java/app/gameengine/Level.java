package app.gameengine;

import app.gameengine.controller.GameControls;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.GameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.games.commonobjects.Wall;
import app.gameengine.model.physics.PhysicsEngine;
import app.gameengine.model.physics.Vector2D;
import app.games.topdownobjects.Projectile;

import java.util.ArrayList;

/**
 * Basic level structure in the game. Initially, the only type of level that
 * exists is a top-down level, but in future tasks you will be creating a
 * platformer level which will use the same game engine
 */
public abstract class Level {

    protected GameControls gameControls;
    protected Game game;
    protected PhysicsEngine physicsEngine;

    protected int width;
    protected int height;
    protected ArrayList<StaticGameObject> staticGameObject = new ArrayList<>();
    protected ArrayList<DynamicGameObject> dynamicObjects = new ArrayList<>();

    protected boolean isLoaded;

    protected Vector2D playerStartLocation = new Vector2D(1.0, 1.0);

    protected String groundTileSpriteSheet = "Ground/Grass.png";
    protected SpriteLocation groundTileSpriteLocation = new SpriteLocation(2, 0);

    protected String name;

    public Level(Game game, PhysicsEngine physicsEngine, int width, int height, String name) {
        this.game = game;
        this.name = name;
        this.physicsEngine = physicsEngine;
        this.width = width;
        this.height = height;
        this.isLoaded = false;
    }

    public Player getPlayer() {
        return this.game.getPlayer();
    }

    public GameControls getPlayerControls() {
        return this.gameControls;
    }

    public void wallOffBoundary() {
        for (int i = 0; i < this.getWidth(); i++) {

            this.getStaticObjects().add(new Wall(i, 0));
            this.getStaticObjects().add(new Wall(i, this.getHeight() - 1));
        }
        for (int i = 1; i < this.getHeight() - 1; i++) {
            this.getStaticObjects().add(new Wall(0, i));
            this.getStaticObjects().add(new Wall(this.getWidth() - 1, i));
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

    public void setLoaded() {
        this.isLoaded = true;
    }

    public String getGroundTileSpriteSheet() {
        return this.groundTileSpriteSheet;
    }

    public SpriteLocation getGroundTileSpriteLocation() {
        return this.groundTileSpriteLocation;
    }

    public ArrayList<StaticGameObject> getStaticObjects() {
        return this.staticGameObject;
    }

    public ArrayList<DynamicGameObject> getDynamicObjects() {
        return this.dynamicObjects;
    }

    public Vector2D getPlayerStartLocation() {
        return this.playerStartLocation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void actionButtonPressed() {
        Projectile projectile = new Projectile(new Vector2D(this.game.getPlayer().getLocation().getX(), this.game.getPlayer().getLocation().getY()), 5);
        projectile.getVelocity().setX(this.game.getPlayer().getOrientation().getX() * 10);
        projectile.getVelocity().setY(this.game.getPlayer().getOrientation().getY() * 10);
        this.getDynamicObjects().add(projectile);
    }

    public void jumpButtonPressed() {

    }

    public void jumpButtonReleased() {

    }

    public void update(double dt) {
        this.physicsEngine.updateLevel(this, dt);
        this.dynamicObjects.removeIf(GameObject::isDestroyed);
        for (DynamicGameObject object : this.getDynamicObjects()) {
            object.update(dt, this);
        }
        for (StaticGameObject object : this.getStaticObjects()) {
            object.update(dt, this);
        }
    }

}
