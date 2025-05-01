package app;

import java.util.ArrayList;

import app.gameengine.Game;
import app.gameengine.Level;
import app.gameengine.graphics.BlankTile;
import app.gameengine.graphics.SpriteGraphics;
import app.gameengine.model.gameobjects.GameObject;
import app.games.GameFactory;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main entry point of the game.
 *
 * This is where the main method that starts the game is. 
 *
 * This class also handles rendering all the graphics of the level, configuring
 * the window that displays the game, and more.
 */
public class StartGame extends Application {

    private Game game;
    private Group group;
    private Text text;
    private Scene scene;
    private Stage stage;

    @Override
    public void init() {
        this.game = GameFactory.getGame(Configuration.game);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        double scaleFactor = Configuration.spriteSize * Configuration.zoom;
        int width = (int) (this.game.getCurrentLevel().getWidth() * scaleFactor);
        int height = (int) (this.game.getCurrentLevel().getHeight() * scaleFactor);

        this.group = new Group();
        this.text = new Text("default");
        this.scene = new Scene(new VBox(this.text, this.group), width, height);

        stage.setTitle("CSE116 Studios");
        stage.setScene(scene);
        stage.show();

        scene.setOnKeyPressed(this.game.getCurrentLevel().getPlayerControls());
        scene.setOnKeyReleased(this.game.getCurrentLevel().getPlayerControls());

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                long start = System.currentTimeMillis();
                Level currentLevel = game.getCurrentLevel();
                if (currentLevel.isLoaded()) {
                    game.update(now);
                }
                currentLevel.setLoaded();
                renderGraphics();
                long end = System.currentTimeMillis();
                if (end - start > 5) {
                    // we only have 13 ms to process a frame
                    System.out.println("getting slow: " + (end - start));
                }
                if (game.getCurrentLevel() != currentLevel) {
                    currentLevel = game.getCurrentLevel();
                    scene.setOnKeyPressed(game.getCurrentLevel().getPlayerControls());
                    scene.setOnKeyReleased(game.getCurrentLevel().getPlayerControls());
                }

            }
        }.start();

    }

    private void renderGraphics() {
        int width = this.game.getCurrentLevel().getWidth();
        int height = this.game.getCurrentLevel().getHeight();
        double scaleFactor = Configuration.spriteSize * Configuration.zoom;
        this.stage.setWidth(width * scaleFactor + 10);
        this.stage.setHeight(height * scaleFactor + this.group.getLayoutY() + this.text.getLayoutY() + 35);
        this.group.getChildren().clear();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                SpriteGraphics tile = new SpriteGraphics(new BlankTile(i, j,
                        this.game.getCurrentLevel().getGroundTileSpriteSheet(),
                        this.game.getCurrentLevel().getGroundTileSpriteLocation()));
                tile.setX(i * scaleFactor);
                tile.setY(j * scaleFactor);
                this.group.getChildren().add(tile);
            }
        }

        ArrayList<GameObject> allObjects = new ArrayList<>();

        // render terrain
        allObjects.addAll(this.game.getCurrentLevel().getStaticObjects());

        // render game objects
        allObjects.addAll(this.game.getCurrentLevel().getDynamicObjects());

        // render the player
        allObjects.add(this.game.getPlayer());

        for (GameObject object : allObjects) {
            SpriteGraphics tile = new SpriteGraphics(object);
            tile.setX(object.getLocation().getX() * scaleFactor);
            tile.setY(object.getLocation().getY() * scaleFactor);
            this.group.getChildren().add(tile);
//            this.game.getTiles().put(object, tile);
        }

        // render the UI
        String ui = this.game.getUI();
        this.text.setText(ui);

    }

    public static void main(String[] args) {
        launch();
    }

}
