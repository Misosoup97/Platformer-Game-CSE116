package app.games.topdownobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.gameengine.model.physics.Vector2D;

/**
 * Simple base projectile class
 *
 * In future tasks, you will be extending this class through polymorphism to
 * allow for different types of projectiles that do different things.
 */
public class Projectile extends DynamicGameObject {

    private int damage;

    public Projectile(Vector2D location, int damage) {
        super(location, 100);
        this.damage = damage;
        this.spriteSheetFilename = "Objects/Bullet.png";
        this.defaultSpriteLocation = new SpriteLocation(1,1);
    }


    @Override
    public void collideWithStaticObject(StaticGameObject otherObject) {
        this.destroy();
    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if(!otherObject.isPlayer()){
            otherObject.destroy();
            this.destroy();
        }
    }


}
