package app.gameengine.graphics;

import app.Configuration;

import java.util.ArrayList;

public interface RenderableAsSprite {

    String getSpriteSheetFilename();
    ArrayList<SpriteLocation> getAnimations();
    double getTimeInAnimationState();

    default int getSpriteSize() {
        return Configuration.spriteSize;
    }

}
