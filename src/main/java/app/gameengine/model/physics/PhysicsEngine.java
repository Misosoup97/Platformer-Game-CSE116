package app.gameengine.model.physics;

import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.Level;
import app.gameengine.model.gameobjects.StaticGameObject;

import java.util.ArrayList;

public class PhysicsEngine {
    public PhysicsEngine() {}

    public void updateLevel(Level level, double dt) {

        // Update the location of each dynamic object based on its velocity
        for(DynamicGameObject dynamicObject : level.getDynamicObjects()){
            updateObject(dynamicObject, dt);
        }

        // detect all collisions for each dynamic object
        for (int i = 0; i < level.getDynamicObjects().size(); i++) {
            DynamicGameObject dynamicObject1 = level.getDynamicObjects().get(i);

            // check for collisions with other dynamic objects
            // start at i+1 to avoid reporting collision multiple times
            for (int j = i+1; j < level.getDynamicObjects().size(); j++) {
                DynamicGameObject dynamicObject2 = level.getDynamicObjects().get(j);
                if(detectCollision(dynamicObject1.getHitBox(), dynamicObject2.getHitBox())){
                    dynamicObject1.collideWithDynamicObject(dynamicObject2);
                    dynamicObject2.collideWithDynamicObject(dynamicObject1);
                }
            }

            // check for collisions with static objects
            for(StaticGameObject so : level.getStaticObjects()){
                if(detectCollision(so.getHitBox(), dynamicObject1.getHitBox())){
                    so.collideWithDynamicObject(dynamicObject1);
                    dynamicObject1.collideWithStaticObject(so);
                }
            }
        }

    }

    public void updateObject(DynamicGameObject dynamicObject, double dt){
        Vector2D loc = dynamicObject.getLocation();
        Vector2D velocity = dynamicObject.getVelocity();
        double velocityX = velocity.getX();
        double velocityY = velocity.getY();
        double locX = loc.getX()+velocityX*dt;
        double locY = loc.getY()+velocityY*dt;
        loc.setX(locX);
        loc.setY(locY);
    }
    public boolean detectCollision(Hitbox hb1, Hitbox hb2){
        //hit box 1
        Vector2D hb1Loc = hb1.getLocation();
        double X1 = hb1Loc.getX();
        double Y1 = hb1Loc.getY();
        Vector2D hb1Dim = hb1.getDimensions();
        double hb1DimX = hb1Dim.getX();
        double hb1DimY = hb1Dim.getY();
//        System.out.println("X1: "+X1);
//        System.out.println("Y1: "+Y1);
//        System.out.println("hb1DimX: "+hb1DimX);
//        System.out.println("hb1DimY: "+hb1DimY);

        //hit box 2
        Vector2D hb2Loc = hb2.getLocation();
        double X2 = hb2Loc.getX();
        double Y2 = hb2Loc.getY();
        Vector2D hb2Dim = hb2.getDimensions();
        double hb2DimX = hb2Dim.getX();
        double hb2DimY = hb2Dim.getY();
//        System.out.println("X2: "+X2);
//        System.out.println("Y2: "+Y2);
//        System.out.println("hb2DimX: "+hb2DimX);
//        System.out.println("hb2DimY: "+hb2DimY);

        //coordinates
        double hb1L = X1;
        double hb1R = X1+hb1DimX;
        double hb1T = Y1;
        double hb1B = Y1+hb1DimY;

        double hb2L = X2;
        double hb2R = X2+hb2DimX;
        double hb2T = Y2;
        double hb2B = Y2+hb2DimY;

        boolean xOverlap;
        boolean yOverlap;

        if((hb1DimX == 0 && hb1DimY == 0) || (hb2DimX == 0 && hb2DimY == 0)){
            return false;
        }

        else if(hb1L<=hb2L && hb2L<=hb1R){
            xOverlap=true;
        }
        else if(hb1L<=hb2R && hb2R<=hb1R) {
            xOverlap = true;
        }
        else if(hb2L<=hb1L && hb1L<=hb2R){
            xOverlap=true;
        }
        else if(hb2L<=hb1R && hb1R<=hb2R) {
            xOverlap = true;
        }
        else{
            xOverlap=false;
        }

        if(hb1T<=hb2T && hb2T<=hb1B){
            yOverlap=true;
        }
        else if(hb1T<=hb2B && hb2B<=hb1B){
            yOverlap=true;
        }
        else if(hb2T<=hb1B && hb1B<=hb2B){
            yOverlap=true;
        }
        else if(hb2T<=hb1T && hb1T<=hb2B) {
            yOverlap = true;
        }
        else{
            yOverlap=false;
        }

        if(xOverlap && yOverlap){
            return true;
        }
        else{
            return false;
        }
        // TODO: return true if the 2 hitboxes overlap; false otherwise
    }

}
