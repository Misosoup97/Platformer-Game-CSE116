package app.gameengine.model.gameobjects;

import app.gameengine.model.physics.Vector2D;

public abstract class DynamicGameObject extends GameObject {

    int maxHP;
    int currentHP;
    Vector2D orientation = new Vector2D(0.0, 1.0);
    protected Vector2D velocity = new Vector2D(0.0, 0.0);

    public DynamicGameObject(Vector2D location, int maxHP) {
        super(location);
        this.maxHP = maxHP;
        this.currentHP = this.maxHP;

    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public int getHP() {

        return this.currentHP;
    }

    public void setHP(int currentHP) {
        if(currentHP>this.maxHP){
            this.currentHP = this.maxHP;
        }
        else{
            this.currentHP=currentHP;
        }
    }

    public Vector2D getOrientation() {

        return this.orientation;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public void takeDamage(int damage){
        if(damage>0){
            this.currentHP-=damage;
        }
    }

    @Override
    public boolean isDestroyed() {
        return super.isDestroyed();
    }

    @Override
    public void revive() {
        super.revive();
    }

    @Override
    public void collideWithStaticObject(StaticGameObject otherObject) {

    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {

    }

}
