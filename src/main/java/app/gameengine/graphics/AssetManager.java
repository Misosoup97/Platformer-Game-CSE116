package app.gameengine.graphics;

import app.Configuration;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class AssetManager {

    // Prevents loading a sprite sheet more than once

    private static HashMap<String, Image> loadedAssets = new HashMap<>();

    private static final String ASSETS_DIRECTORY = "data/MiniWorldSprites/";
    private static final String DEFAULT_IMAGE_FILENAME = "User Interface/UiIcons.png";
    protected static final Image DEFAULT_IMAGE = getDefaultImage();

    public static Image getImage(String filename) {
        if (loadedAssets.containsKey(filename)) {
            // image has already been loaded
            return loadedAssets.get(filename);
        }
        try {
            String imageFilename = ASSETS_DIRECTORY + filename;
            Image image = new Image(new FileInputStream(imageFilename), 0, 0, true, true);
            if (Configuration.integerScale) {
                loadedAssets.put(filename, scaleImage(image));
            } else {
                loadedAssets.put(filename, image);
            }

            return image;
        } catch (FileNotFoundException e) {
            System.out.println("** Invalid Sprite sheet " + filename + ". Using default image **");
            if (Configuration.integerScale) {
                loadedAssets.put(filename, scaleImage(DEFAULT_IMAGE));
            } else {
                loadedAssets.put(filename, DEFAULT_IMAGE);
            }
            return DEFAULT_IMAGE;
        }
    }

    private static Image getDefaultImage() {
        String defaultImageFilename = ASSETS_DIRECTORY + DEFAULT_IMAGE_FILENAME;
        try {
            return new Image(new FileInputStream(defaultImageFilename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static Image scaleImage(Image image) {
        BufferedImage original = SwingFXUtils.fromFXImage(image, null);
        java.awt.Image scaled = original.getScaledInstance(original.getWidth() * (int) Configuration.zoom, original.getHeight() * (int) Configuration.zoom, java.awt.Image.SCALE_FAST);
        BufferedImage out = new BufferedImage(scaled.getWidth(null), scaled.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = out.createGraphics();
        g2d.drawImage(scaled, 0, 0, null);
        return SwingFXUtils.toFXImage(out, null);
    }
}
