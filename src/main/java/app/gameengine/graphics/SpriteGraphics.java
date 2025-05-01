package app.gameengine.graphics;

import app.Configuration;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import java.util.Arrays;

public class SpriteGraphics extends ImageView {

    private static final ArrayList<Integer> DEFAULT_IMAGE_LOCATION = new ArrayList<>(Arrays.asList(1, 3));

    public SpriteGraphics(RenderableAsSprite renderableObject) {
        this(renderableObject, Configuration.zoom);
    }

    public SpriteGraphics(RenderableAsSprite renderableObject, double zoom) {
        Image image = AssetManager.getImage(renderableObject.getSpriteSheetFilename());
        this.setImage(image);
        if (image == AssetManager.DEFAULT_IMAGE) {
            this.setViewport(new Rectangle2D(
                    Configuration.spriteSize * DEFAULT_IMAGE_LOCATION.get(0),
                    Configuration.spriteSize * DEFAULT_IMAGE_LOCATION.get(1),
                    Configuration.spriteSize,
                    Configuration.spriteSize
            ));
        } else {
            resetSpriteViewport(renderableObject);
        }

        if (!Configuration.integerScale) {
            this.setScaleX(zoom);
            this.setScaleY(zoom);
        }
    }

    public void resetSpriteViewport(RenderableAsSprite renderableObject) {
        ArrayList<SpriteLocation> spriteLocations = renderableObject.getAnimations();
        int frameIndex = ((int)(renderableObject.getTimeInAnimationState() / Configuration.animationTime)) % spriteLocations.size();
        SpriteLocation frameLocation = spriteLocations.get(frameIndex);

        int x = frameLocation.getColumn();
        int y = frameLocation.getRow();

        int tileSize = renderableObject.getSpriteSize();
        if (Configuration.integerScale) {
            tileSize *= (int)Configuration.zoom;
        }
        this.setViewport(new Rectangle2D(tileSize * x, tileSize * y, tileSize, tileSize));
    }

}
