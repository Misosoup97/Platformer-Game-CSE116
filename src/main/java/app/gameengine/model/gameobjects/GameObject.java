package app.gameengine.model.gameobjects;

import app.gameengine.Level;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.physics.Collidable;
import app.gameengine.graphics.RenderableAsSprite;
import app.gameengine.model.physics.Hitbox;
import app.gameengine.model.physics.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public abstract class GameObject implements RenderableAsSprite, Collidable {

    private final Vector2D location;
    private final Vector2D dimensions = new Vector2D(1.0, 1.0);
    protected final Vector2D hitBoxSize = new Vector2D(1.0, 1.0);
    private final Hitbox hitBox;
    private boolean destroyed = false;

    private double timeInAnimationState = 0.0;
    private String animationState = "default";

    protected String spriteSheetFilename = "";
    protected SpriteLocation defaultSpriteLocation = new SpriteLocation(0, 0);
    protected HashMap<String, ArrayList<SpriteLocation>> animations = new HashMap<>();
    protected boolean freezeAnimations = false;

    public GameObject(Vector2D location) {
        this.location = location;
        this.hitBox = new Hitbox(this.location, this.hitBoxSize);
    }

    public boolean isPlayer() {
        return false;
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void revive() {
        this.destroyed = false;
    }

    public void update(double dt, Level level) {
        if (!this.freezeAnimations) {
            this.timeInAnimationState += dt;
        }
    }

    public Vector2D getLocation() {
        return this.location;
    }

    public Vector2D getDimensions() {
        return this.dimensions;
    }

    @Override
    public Hitbox getHitBox() {
        return this.hitBox;
    }

    public String getSpriteSheetFilename() {
        return this.spriteSheetFilename;
    }

    public ArrayList<SpriteLocation> getAnimations() {
        return this.animations.getOrDefault(this.animationState,
                new ArrayList<>(Arrays.asList(this.defaultSpriteLocation)
                ));
    }

    public double getTimeInAnimationState() {
        return this.timeInAnimationState;
    }

    public void freezeAnimations() {
        this.freezeAnimations = true;
    }

    public void setAnimationState(String newState) {
        if (!this.animationState.equals(newState)) {
            this.animationState = newState;
            this.timeInAnimationState = 0.0;
        }
        this.freezeAnimations = false;
    }

    public abstract void collideWithStaticObject(StaticGameObject otherObject);

    public abstract void collideWithDynamicObject(DynamicGameObject otherObject);

}
