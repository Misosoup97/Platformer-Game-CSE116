package app.gameengine.model.gameobjects;

import app.gameengine.model.physics.Vector2D;

public abstract class StaticGameObject extends GameObject {

    protected int x;
    protected int y;

    public StaticGameObject(int x, int y) {
        super(new Vector2D(x, y));
        this.x = x;
        this.y = y;
    }

    @Override
    public void collideWithStaticObject(StaticGameObject otherObject) {
        // This should never happen
    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {

    }

}
