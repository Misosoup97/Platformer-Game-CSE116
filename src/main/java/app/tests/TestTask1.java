package app.tests;

import app.gameengine.model.gameobjects.DynamicGameObject;
import app.gameengine.model.gameobjects.Player;
import app.gameengine.model.physics.Hitbox;
import app.gameengine.model.physics.PhysicsEngine;
import app.gameengine.model.physics.Vector2D;
import app.games.commonobjects.Wall;
import javafx.util.Pair;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TestTask1 {

    static final double EPSILON = 0.0001;
    
    public void comparePlayers(Player player1, Player player2) {
        //compare location
        Vector2D loc1 = player1.getLocation();
        double X1 = loc1.getX();
        double Y1 = loc1.getY();
        Vector2D loc2 = player2.getLocation();
        double X2 = loc2.getX();
        double Y2 = loc2.getY();
        assertEquals(X1, X2, EPSILON);
        assertEquals(Y1, Y2, EPSILON);

        //compare velocity
        Vector2D v1 = player1.getVelocity();
        double v1X = v1.getX();
        double v1Y = v1.getY();
        Vector2D v2 = player2.getVelocity();
        double v2X = v2.getX();
        double v2Y = v2.getY();
        assertEquals(v1X, v2X, EPSILON);
        assertEquals(v1Y, v2Y, EPSILON);

        //compare orientation
        Vector2D o1 = player1.getOrientation();
        double o1x = o1.getX();
        double o1y = o1.getY();
        Vector2D o2 = player2.getOrientation();
        double o2x = o2.getX();
        double o2y = o2.getY();
        assertEquals(o1x, o2x, EPSILON);
        assertEquals(o1y, o2y, EPSILON);

        //compare current HP
        int hp1 = player1.getHP();
        int hp2 = player2.getHP();
        assertEquals(hp1, hp2);

        //compare maxHP
        int Max_hp1 = player1.getMaxHP();
        int Max_hp2 = player2.getMaxHP();
        assertEquals(Max_hp1, Max_hp2);
    }

    @Test
    public void DynamicGameObject(){
        Vector2D vector = new Vector2D(2.0, 3.0);
        Player player = new Player(vector, 80);
        Vector2D locP = player.getLocation();
        double locX = locP.getX();
        double locY = locP.getY();
        Vector2D v = player.getVelocity();
        double vX = v.getX();
        double vY = v.getY();
        double oriX = player.getOrientation().getX();
        double oriY = player.getOrientation().getY();
        int currentHP = player.getHP();
        int maxHP = player.getMaxHP();

        //check location (x,y)
        assertEquals(2.0, locX, EPSILON);
        assertEquals(3.0, locY, EPSILON);
        //velocity
        assertEquals(0.0, vX, EPSILON);
        assertEquals(0.0, vY, EPSILON);
        //orientation
        assertEquals(0.0, oriX, EPSILON);
        assertEquals(1.0, oriY, EPSILON);
        //maxHP
        assertEquals(80, maxHP);
        //hp
        assertEquals(80, currentHP);
        //setHP
        player.setHP(100);
        currentHP = player.getHP();
        assertEquals(80, currentHP);
        //takeDamage
        player.takeDamage(30);
        currentHP = player.getHP();
        assertEquals(50, currentHP);
        player.takeDamage(0);
        currentHP = player.getHP();
        assertEquals(50, currentHP);
        player.takeDamage(-30);
        currentHP = player.getHP();
        assertEquals(50, currentHP);
    }

    @Test
    public void updateObject(){
        //test 1: gen case
        Vector2D velocity = new Vector2D(1.0,4.5);
        Player player = new Player(velocity, 50);
        player.getLocation().setX(2.0);
        player.getLocation().setY(2.0);
        player.getVelocity().setX(1.0);
        player.getVelocity().setY(4.5);
        PhysicsEngine eng = new PhysicsEngine();
        eng.updateObject(player, 3.5);
        assertEquals(5.5, player.getLocation().getX(),EPSILON);
        assertEquals(17.75, player.getLocation().getY(),EPSILON);

        //0 velocity
        Vector2D velocity1 = new Vector2D(0.0,0.0);
        Player player1 = new Player(velocity1, 50);
        player1.getLocation().setX(-50.0);
        player1.getLocation().setY(1.0);
        player1.getVelocity().setX(0.0);
        player1.getVelocity().setY(0.0);
        PhysicsEngine eng1 = new PhysicsEngine();
        eng1.updateObject(player1, 9.9);
        assertEquals(-50.0, player1.getLocation().getX(),EPSILON);
        assertEquals(1.0, player1.getLocation().getY(),EPSILON);

        //moving right (only horizontally)
        Vector2D velocity2 = new Vector2D(0,0);
        Player player2 = new Player(velocity2, 50);
        player2.getLocation().setX(0.0);
        player2.getLocation().setY(1.0);
        player2.getVelocity().setX(-2.5);
        player2.getVelocity().setY(0.0);
        PhysicsEngine eng2 = new PhysicsEngine();
        eng2.updateObject(player2, 2.2);
        assertEquals(-5.5, player2.getLocation().getX(),EPSILON);
        assertEquals(1.0, player2.getLocation().getY(),EPSILON);
    }

    @Test
    public void detectCollision(){
        //test 1: gen case, overlap
        Vector2D loc1 = new Vector2D(2.0,0);
        Vector2D dim1 = new Vector2D(3.0,3.0);
        Hitbox hb1 = new Hitbox(loc1, dim1);

        Vector2D loc2 = new Vector2D(1.0,0);
        Vector2D dim2 = new Vector2D(3.0,3.0);
        Hitbox hb2 = new Hitbox(loc2, dim2);

        PhysicsEngine eng = new PhysicsEngine();
        assertTrue("test 1", eng.detectCollision(hb1, hb2));


        //test 2: full overlap
        Vector2D loc3 = new Vector2D(2.0,0);
        Vector2D dim3 = new Vector2D(1.0,1.0);
        Hitbox hb3 = new Hitbox(loc3, dim3);

        Vector2D loc4 = new Vector2D(2.0,0);
        Vector2D dim4 = new Vector2D(1.0,1.0);
        Hitbox hb4 = new Hitbox(loc4, dim4);

        PhysicsEngine eng1 = new PhysicsEngine();
        eng1.detectCollision(hb3, hb4);
        assertTrue("test 2", eng1.detectCollision(hb3, hb4));

        //test 3: edge case: touching
        Vector2D loc5 = new Vector2D(2.0,0);
        Vector2D dim5 = new Vector2D(1.0,1.0);
        Hitbox hb5 = new Hitbox(loc5, dim5);

        Vector2D loc6 = new Vector2D(2.0,-1.0);
        Vector2D dim6 = new Vector2D(1.0,1.0);
        Hitbox hb6 = new Hitbox(loc6, dim6);

        PhysicsEngine eng2 = new PhysicsEngine();
        assertTrue("test 3", eng2.detectCollision(hb5, hb6));

        //test 4: edge case: corners
        Vector2D loc7 = new Vector2D(2.0,0);
        Vector2D dim7 = new Vector2D(1.0,1.0);
        Hitbox hb7 = new Hitbox(loc7, dim7);

        Vector2D loc8 = new Vector2D(1.0,1.0);
        Vector2D dim8 = new Vector2D(1.0,1.0);
        Hitbox hb8 = new Hitbox(loc8, dim8);

        PhysicsEngine eng3 = new PhysicsEngine();
        assertTrue("test 4", eng3.detectCollision(hb7, hb8));

        //test 5: add cases to check for false
        Vector2D loc9 = new Vector2D(-2.0,0);
        Vector2D dim9 = new Vector2D(1.0,1.0);
        Hitbox hb9 = new Hitbox(loc9, dim9);

        Vector2D loc10 = new Vector2D(1.0,-1.5);
        Vector2D dim10 = new Vector2D(1.0,1.0);
        Hitbox hb10 = new Hitbox(loc10, dim10);

        PhysicsEngine eng4 = new PhysicsEngine();
        assertFalse("test 5", eng4.detectCollision(hb9, hb10));

        //test 6: add cases to check for false
        Vector2D loc11 = new Vector2D(-2.0,0);
        Vector2D dim11 = new Vector2D(1.0,1.0);
        Hitbox hb11 = new Hitbox(loc11, dim11);

        Vector2D loc12 = new Vector2D(4.0,-1.5);
        Vector2D dim12 = new Vector2D(1.0,1.0);
        Hitbox hb12 = new Hitbox(loc12, dim12);

        PhysicsEngine eng5 = new PhysicsEngine();
        assertFalse("test 6", eng5.detectCollision(hb11, hb12));

        //test 7: add cases to check for false
        Vector2D loc13 = new Vector2D(-2.0,-1.5);
        Vector2D dim13 = new Vector2D(1.0,1.0);
        Hitbox hb13 = new Hitbox(loc13, dim13);

        Vector2D loc14 = new Vector2D(8.0,-1.5);
        Vector2D dim14 = new Vector2D(1.0,1.0);
        Hitbox hb14 = new Hitbox(loc14, dim14);

        PhysicsEngine eng6 = new PhysicsEngine();
        assertFalse("test 7", eng6.detectCollision(hb13, hb14));

        //test 8: dim 0- 1 component
        Vector2D loc15 = new Vector2D(-2.0,-1.5);
        Vector2D dim15 = new Vector2D(0.0,1.0);
        Hitbox hb15 = new Hitbox(loc15, dim15);

        Vector2D loc16 = new Vector2D(-2.5,-1.0);
        Vector2D dim16 = new Vector2D(1.0,1.0);
        Hitbox hb16 = new Hitbox(loc16, dim16);

        PhysicsEngine eng7 = new PhysicsEngine();
        assertTrue("test 8", eng7.detectCollision(hb15, hb16));

        //test 9: one box engulfing the other
        Vector2D loc17 = new Vector2D(-2.0,-1.5);
        Vector2D dim17 = new Vector2D(1.0,1.0);
        Hitbox hb17 = new Hitbox(loc17, dim17);

        Vector2D loc18 = new Vector2D(-2.5,-1.0);
        Vector2D dim18 = new Vector2D(100.0,100.0);
        Hitbox hb18 = new Hitbox(loc18, dim18);

        PhysicsEngine eng8 = new PhysicsEngine();
        assertTrue("test 9", eng8.detectCollision(hb17, hb18));

        //test 10: thin rectangle through box
        Vector2D loc19 = new Vector2D(-6,4);
        Vector2D dim19 = new Vector2D(100,1);
        Hitbox hb19 = new Hitbox(loc19, dim19);

        Vector2D loc20 = new Vector2D(-4,4);
        Vector2D dim20 = new Vector2D(1,1);
        Hitbox hb20 = new Hitbox(loc20, dim20);

        PhysicsEngine eng9 = new PhysicsEngine();
        assertTrue("test 10", eng9.detectCollision(hb19, hb20));

        //test 11: one box engulfing the other (objects switched)
        Vector2D loc22 = new Vector2D(-2.0,-1.5);
        Vector2D dim22 = new Vector2D(1.0,1.0);
        Hitbox hb22 = new Hitbox(loc22, dim22);

        Vector2D loc21 = new Vector2D(-2.5,-1.0);
        Vector2D dim21 = new Vector2D(100.0,100.0);
        Hitbox hb21 = new Hitbox(loc21, dim21);

        PhysicsEngine eng10 = new PhysicsEngine();
        assertTrue("test 11", eng8.detectCollision(hb21, hb22));

        //test 12: only neg quad
        Vector2D loc23 = new Vector2D(-2.0,-1.5);
        Vector2D dim23 = new Vector2D(1.0,1.0);
        Hitbox hb23 = new Hitbox(loc23, dim23);

        Vector2D loc24 = new Vector2D(-2.5,-1.0);
        Vector2D dim24 = new Vector2D(1.0,.75);
        Hitbox hb24 = new Hitbox(loc24, dim24);

        PhysicsEngine eng11 = new PhysicsEngine();
        assertTrue("test 12", eng11.detectCollision(hb23, hb24));
    }

    @Test
    public void testWallCollisionsSimple() {
        // we give you the tests for wall collisions. Don't change them
        //
        // However, you should read through these tests to better understand what you should
        // be testing for and how you should be testing
        Player player = new Player(new Vector2D(0, 0), 10);
        Wall w1 = new Wall(1, 0);
        Wall w2 = new Wall(0, 1);
        Wall w3 = new Wall(-1, 0);
        Wall w4 = new Wall(0, -1);

        // Move right
        player.getLocation().setX(0.5);
        player.getLocation().setY(0);
        w1.collideWithDynamicObject(player);
        assertEquals("test 1X: ", 0.0, player.getLocation().getX(), EPSILON);
        assertEquals("test 1Y: ", 0.0, player.getLocation().getY(), EPSILON);

        // Move down
        player.getLocation().setX(0);
        player.getLocation().setY(0.5);
        w2.collideWithDynamicObject(player);
        assertEquals("test 2X: ",0.0, player.getLocation().getX(), EPSILON);
        assertEquals("test 2Y: ",0.0, player.getLocation().getY(), EPSILON);

        // Move left
        player.getLocation().setX(-0.5);
        player.getLocation().setY(0);
        w3.collideWithDynamicObject(player);
        assertEquals("test 3X: ",0.0, player.getLocation().getX(), EPSILON);
        assertEquals("test 3Y: ",0.0, player.getLocation().getY(), EPSILON);

        // Move up
        player.getLocation().setX(0);
        player.getLocation().setY(-0.5);
        w4.collideWithDynamicObject(player);
        assertEquals("test 4X: ",0.0, player.getLocation().getX(), EPSILON);
        assertEquals("test 4Y: ",0.0, player.getLocation().getY(), EPSILON);
    }

    @Test
    public void testWallCollisionsComplex() {
        Player player = new Player(new Vector2D(0.0, 0.0), 10);
        Wall w1 = new Wall(5, 2);

        player.getLocation().setX(4.5);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(4.5, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.0);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(5.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.5);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(5.5, player.getLocation().getX(), EPSILON);
        assertEquals(1.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(1.2);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.2, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(1.5);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.5, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(2.5);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.5, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.9);
        player.getLocation().setY(2.8);
        w1.collideWithDynamicObject(player);
        assertEquals(6.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.8, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(5.2);
        player.getLocation().setY(2.8);
        w1.collideWithDynamicObject(player);
        assertEquals(5.2, player.getLocation().getX(), EPSILON);
        assertEquals(3.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(2.7);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.7, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(2.0);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(2.0, player.getLocation().getY(), EPSILON);

        player.getLocation().setX(4.2);
        player.getLocation().setY(1.5);
        w1.collideWithDynamicObject(player);
        assertEquals(4.0, player.getLocation().getX(), EPSILON);
        assertEquals(1.5, player.getLocation().getY(), EPSILON);
    }

}
