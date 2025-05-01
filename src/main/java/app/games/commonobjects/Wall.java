package app.games.commonobjects;

import app.gameengine.graphics.SpriteLocation;
import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.StaticGameObject;
import app.gameengine.model.physics.Hitbox;
import app.gameengine.model.physics.Vector2D;

/**
 * A wall object the player can collide with, serves as a building block
 * for your levels.
 */
public class Wall extends StaticGameObject {

    public Wall(int x, int y) {
        super(x, y);
        this.spriteSheetFilename = "Ground/Cliff.png";
        this.defaultSpriteLocation = new SpriteLocation(3, 0);
    }
    public void collideWithDynamicObject(DynamicGameObject obj){
        //D stands for dynamicGameObj
        Vector2D loc = obj.getLocation();
        double D_locX = loc.getX();
        double D_locY = loc.getY();

        //W is wall
        Vector2D W_loc = this.getLocation();
        double W_locX = W_loc.getX();
        double W_locY = W_loc.getY();

        //coordinates
        double D_left = D_locX-(.5);
        double D_right = D_locX+(.5);
        double D_top = D_locY-(.5);
        double D_bot = D_locY+(.5);

        double W_left = W_locX-(.5);
        double W_right = W_locX+(.5);
        double W_top = W_locY-(.5);
        double W_bot = W_locY+(.5);

        //distances
        double R_Shift = Math.abs(D_left-W_right);
        double L_Shift = Math.abs(D_right-W_left);
        double U_Shift = Math.abs(D_top-W_bot);
        double D_Shift = Math.abs(D_bot-W_top);

        if(R_Shift<=L_Shift && R_Shift<=U_Shift && R_Shift<=D_Shift ) {
            loc.setX(W_right+.5);
        }
        else if(R_Shift>=L_Shift && L_Shift<=U_Shift && L_Shift<=D_Shift ) {
            loc.setX(W_left-.5);
        }
        else if(R_Shift>=U_Shift && L_Shift>=U_Shift && U_Shift<=D_Shift ) {
            loc.setY(W_bot+.5);
        }
        else if(R_Shift>=D_Shift && L_Shift>=D_Shift && U_Shift>=D_Shift ) {
            loc.setY(W_top-.5);
        }
    }


}
