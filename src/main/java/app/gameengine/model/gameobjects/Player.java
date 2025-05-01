package app.gameengine.model.gameobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.physics.Vector2D;

import java.util.ArrayList;
import java.util.Arrays;

public class Player extends DynamicGameObject {

    public Player(Vector2D location, int maxHP) {
        super(location, maxHP);
        this.initializeAnimations();
    }

    @Override
    public boolean isPlayer(){
        return true;
    }

    public void cycleInventory(){

    }

    private void initializeAnimations(){
        this.spriteSheetFilename = "Characters/Soldiers/Melee/CyanMelee/AxemanCyan.png";
        this.animations.put("walk_down", new ArrayList<>(Arrays.asList(
                new SpriteLocation(0, 0),
                new SpriteLocation(1, 0),
                new SpriteLocation(2, 0),
                new SpriteLocation(3, 0),
                new SpriteLocation(4, 0)
        )));
        this.animations.put("walk_up", new ArrayList<>(Arrays.asList(
                new SpriteLocation(0, 1),
                new SpriteLocation(1, 1),
                new SpriteLocation(2, 1),
                new SpriteLocation(3, 1),
                new SpriteLocation(4, 1)
        )));
        this.animations.put("walk_right", new ArrayList<>(Arrays.asList(
                new SpriteLocation(0, 2),
                new SpriteLocation(1, 2),
                new SpriteLocation(2, 2),
                new SpriteLocation(3, 2),
                new SpriteLocation(4, 2)
        )));
        this.animations.put("walk_left", new ArrayList<>(Arrays.asList(
                new SpriteLocation(0, 3),
                new SpriteLocation(1, 3),
                new SpriteLocation(2, 3),
                new SpriteLocation(3, 3),
                new SpriteLocation(4, 3)
        )));

    }

}
