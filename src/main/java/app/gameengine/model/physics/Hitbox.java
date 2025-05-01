package app.gameengine.model.physics;

public class Hitbox {

    // The location of the upper left corner of the box
    private final Vector2D location;

    // width and height of the box
    private final Vector2D dimensions;

    public Hitbox(Vector2D location, Vector2D dimensions) {
        this.location = location;
        this.dimensions = dimensions;
    }

    public Vector2D getLocation() {
        return this.location;
    }

    public Vector2D getDimensions() {
        return this.dimensions;
    }

}
