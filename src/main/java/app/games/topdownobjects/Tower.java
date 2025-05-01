package app.games.topdownobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;

/**
 * Tower object, serves as a building block for levels in your game.
 */
public class Tower extends StaticGameObject {

    public Tower(int x, int y){
        super(x, y);
        this.spriteSheetFilename = "Buildings/Red/RedTower.png";
        this.defaultSpriteLocation = new SpriteLocation(0, 1);
    }

    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if(otherObject.isPlayer()){
            otherObject.destroy();
        }
    }

}
