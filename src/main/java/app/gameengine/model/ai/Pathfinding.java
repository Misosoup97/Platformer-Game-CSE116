package app.gameengine.model.ai;

import app.gameengine.model.datastructures.LinkedListNode;
import app.gameengine.model.physics.Vector2D;

public class Pathfinding {
    //    public static LinkedListNode<Vector2D> findPath(Vector2D start, Vector2D end) {
//        start.setX(Math.floor(start.getX()));
//        start.setY(Math.floor(start.getY()));
//        end.setX(Math.floor(end.getX()));
//        end.setY(Math.floor(end.getY()));
//        LinkedListNode<Vector2D> startNode = new LinkedListNode<>(start, null);
//        while (start.getX() != end.getX()) {
//            if (start.getX() < end.getX()) {
//                Vector2D tempVector = new Vector2D(startNode.getValue().getX() + 1, startNode.getValue().getY());
//                startNode.append(tempVector);
//            } else {
//                Vector2D tempVector = new Vector2D(startNode.getValue().getX() - 1, startNode.getValue().getY());
//                startNode.append(tempVector);
//            }
//        }
//        while (start.getY() != end.getY()) {
//            if (start.getY() < end.getY()) {
//                Vector2D tempVector = new Vector2D(startNode.getValue().getX(), startNode.getValue().getY() + 1);
//                startNode.append(tempVector);
//            } else {
//                Vector2D tempVector = new Vector2D(startNode.getValue().getX(), startNode.getValue().getY() - 1);
//                startNode.append(tempVector);
//            }
//        }
//        return startNode;
//    }
    public static LinkedListNode<Vector2D> findPath(Vector2D start, Vector2D end) {
        start.setX(Math.floor(start.getX()));
        start.setY(Math.floor(start.getY()));
        end.setX(Math.floor(end.getX()));
        end.setY(Math.floor(end.getY()));

        LinkedListNode<Vector2D> startNode = new LinkedListNode<>(start, null);
        findPathHelper(start,end, startNode);
        return startNode;
    }

    public static void findPathHelper(Vector2D current,Vector2D end, LinkedListNode<Vector2D> startNode) {
        Vector2D temp=null;
        //base case
        if(current.getX()==end.getX() && current.getY()==end.getY()){
            return;
        }
        if(current.getX()!=end.getX()){
            if(current.getX()<end.getX()) {
                temp = new Vector2D(current.getX() + 1, current.getY());
                startNode.append(temp);
            }
            else{
                temp = new Vector2D(current.getX() - 1, current.getY());
                startNode.append(temp);
            }
        }
        else if(current.getY()!=end.getY()){
            if(current.getY()<end.getY()) {
                temp = new Vector2D(current.getX(), current.getY()+1);
                startNode.append(temp);
            }
            else{
                temp = new Vector2D(current.getX() , current.getY()-1);
                startNode.append(temp);
            }
        }
        findPathHelper(temp, end, startNode);
    }
}
