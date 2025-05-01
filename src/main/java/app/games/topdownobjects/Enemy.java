package app.games.topdownobjects;

import app.gameengine.Level;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.physics.Vector2D;

import static app.gameengine.model.ai.Pathfinding.findPath;

/**
 * Basic enemy class.
 * <p>
 * In future tasks, you will develop tools to give enemies more features:
 * like pathfinding, AI, etc.
 */
public class Enemy extends DynamicGameObject {

    private int strength;
    private LinkedListNode<Vector2D> enemyPath = null;

    public Enemy(Vector2D location) {
        this(location, 3);
    }

    public Enemy(Vector2D location, int strength) {
        super(location, 10);
        this.strength = strength;
        this.spriteSheetFilename = "Characters/Monsters/Demons/ArmouredRedDemon.png";
        this.defaultSpriteLocation = new SpriteLocation(0, 2);
    }

    public void setPath(LinkedListNode<Vector2D> enemyPath) {
        this.enemyPath = enemyPath;
    }

    public LinkedListNode<Vector2D> getPath() {
        return this.enemyPath;
    }

    public void update(double seconds, Level level) {
        this.enemyPath = getPath();
        if (getPath() == null) {
            setPath(findPath(this.getLocation(), level.getPlayer().getLocation()));
            return;
        }
        //distance calculation
        double enemyX = this.getLocation().getX();
        double enemyY = this.getLocation().getY();
        double tileX = this.enemyPath.getValue().getX();
        double tileY = this.enemyPath.getValue().getY();
        double difX = Math.pow(enemyX - tileX, 2);
        double difY = Math.pow(enemyY - tileY, 2);
        double distance = Math.sqrt(difX + difY);

        if (distance < .01) {
            this.getLocation().setX(tileX);
            this.getLocation().setY(tileY);
            this.enemyPath = this.enemyPath.getNext();
        } else {
            if(this.enemyPath.getValue().getX()!=enemyX){
                if(enemyX<this.enemyPath.getValue().getX()){
                    this.getVelocity().setX(1.0);
                    this.getVelocity().setY(0.0);
                }
                else{
                    this.getVelocity().setX(-1.0);
                    this.getVelocity().setY(0.0);
                }
            }
            else{
                if(enemyY<this.enemyPath.getValue().getY()){
                    this.getVelocity().setX(0.0);
                    this.getVelocity().setY(1.0);
                }
                else{
                    this.getVelocity().setX(0.0);
                    this.getVelocity().setY(-1.0);
                }
            }
        }
    }


    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if (otherObject.isPlayer()) {
            otherObject.destroy();
        }
    }

}
