package app.gameengine.graphics;

import app.gameengine.model.gameobjects.StaticGameObject;

import java.util.ArrayList;
import java.util.Arrays;


public class BlankTile extends StaticGameObject {


    public BlankTile(int x, int y, String spriteSheetFilename, SpriteLocation spriteLocation) {
        super(x, y);
        this.spriteSheetFilename = spriteSheetFilename;
        this.animations.put("default", new ArrayList<>(Arrays.asList(new SpriteLocation(spriteLocation.getColumn(),spriteLocation.getRow()))));
    }


}
