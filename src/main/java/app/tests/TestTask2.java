package app.tests;

import app.gameengine.model.ai.Pathfinding;
import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.physics.Vector2D;

import static app.gameengine.model.ai.Pathfinding.findPath;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class TestTask2 {
    static final double EPSILON = 0.0001;

    public void validatePath(LinkedListNode<Vector2D> path) {
        while (path != null) {
            double currentX = path.getValue().getX();
            double currentY = path.getValue().getY();
            assertEquals(Math.floor(currentX), currentX, EPSILON);
            assertEquals(Math.floor(currentY), currentY, EPSILON);
            if (!(path.getNext() == null)) {
                double nextX = path.getNext().getValue().getX();
                double nextY = path.getNext().getValue().getY();

                assertTrue(Math.abs(currentX - nextX) + Math.abs(currentY - nextY) == 1);
            }
            path = path.getNext();

        }
    }

    @Test
    public void testFindPath() {
        //gen case
        Vector2D v1 = new Vector2D(1, 0);
        Vector2D v2 = new Vector2D(2, 0);
        Vector2D v3 = new Vector2D(3, 0);
        Vector2D v4 = new Vector2D(4, 0);
        Vector2D v5 = new Vector2D(5, 0);
        Vector2D v6 = new Vector2D(5, 1);
        LinkedListNode<Vector2D> expected = new LinkedListNode<>(v1, null);
        expected.append(v2);
        expected.append(v3);
        expected.append(v4);
        expected.append(v5);
        expected.append(v6);
        Vector2D v11 = new Vector2D(1, 0);
        Vector2D v21 = new Vector2D(1, 1);
        Vector2D v31 = new Vector2D(2, 1);
        Vector2D v41 = new Vector2D(3, 1);
        Vector2D v51 = new Vector2D(4, 1);
        Vector2D v61 = new Vector2D(5, 1);
        LinkedListNode<Vector2D> expected1 = new LinkedListNode<>(v11, null);
        expected1.append(v21);
        expected1.append(v31);
        expected1.append(v41);
        expected1.append(v51);
        expected1.append(v61);
        LinkedListNode<Vector2D> actual = Pathfinding.findPath(v1, v6);
        validatePath(actual);
        validatePath(expected);
        validatePath(expected1);
        for (int i = 0; i < 6; i++) {
            assertTrue(actual.getValue().getX()==expected.getValue().getX() || actual.getValue().getX()==expected1.getValue().getX());
            assertTrue(actual.getValue().getY()==expected.getValue().getY() || actual.getValue().getY()==expected1.getValue().getY());
            expected = expected.getNext();
            expected1 = expected1.getNext();
            actual = actual.getNext();
        }

        //1 invalid path
        Vector2D v12 = new Vector2D(1, 0);
        Vector2D v22 = new Vector2D(2, 0);
        Vector2D v32 = new Vector2D(2, 1);
        LinkedListNode<Vector2D> expected2 = new LinkedListNode<>(v12, null);
        expected2.append(v22);
        expected2.append(v32);
        Vector2D v112 = new Vector2D(1, 0);
        Vector2D v212 = new Vector2D(1, 1);
        Vector2D v312 = new Vector2D(2, 1);
        LinkedListNode<Vector2D> expected12 = new LinkedListNode<>(v112, null);
        expected12.append(v212);
        expected12.append(v312);
        Vector2D v120 = new Vector2D(1.5, 0.9);
        Vector2D v620 = new Vector2D(2.78, 1);
        LinkedListNode<Vector2D> actual2 = Pathfinding.findPath(v120, v620);
        validatePath(actual2);
        //validatePath(expected2);
        //validatePath(expected12);
        for (int i = 0; i < 3; i++) {
            assertTrue(actual2.getValue().getX()==expected2.getValue().getX() || actual2.getValue().getX()==expected12.getValue().getX());
            assertTrue(actual2.getValue().getY()==expected2.getValue().getY() || actual2.getValue().getY()==expected12.getValue().getY());
            expected2 = expected2.getNext();
            expected12 = expected12.getNext();
            actual2 = actual2.getNext();
        }
    }
}
