package app.games.commonobjects;

import app.gameengine.Game;
import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;

/**
 * Allows you to progress to the next level in the game.
 */
public class Goal extends StaticGameObject {

    private Game game;


    public Goal(int x, int y, Game game){
        super(x, y);
        this.game = game;
        this.spriteSheetFilename = "Buildings/Cyan/CyanWorkshops.png";
        this.defaultSpriteLocation = new SpriteLocation(1, 1);
    }


    @Override
    public void collideWithDynamicObject(DynamicGameObject otherObject) {
        if(otherObject.isPlayer()){
            this.game.advanceLevel();
        }
    }
}
