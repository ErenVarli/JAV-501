package game.garfield.util;

/**
 * Represents a 2D position with X and Y coordinates.
 * Provides methods for accessing, modifying, and comparing positions.
 */
public class Position {
    /**
     * The X of the position.
     */
    private float pX;

    /**
     * The Y of the position.
     */
    private float pY;

    /**
     * Constructs a Position object with specified X and Y coordinates.
     * 
     * @param pX The X-coordinate of the position.
     * @param pY The Y-coordinate of the position.
     */
    public Position(float pX, float pY) {
        this.pX = pX;
        this.pY = pY;
    }

    /**
     * Gets the X of the position.
     * 
     * @return The X position.
     */
    public float getpX() {
        return pX;
    }

    /**
     * Gets the Y of the position.
     * 
     * @return The Y position.
     */
    public float getpY() {
        return pY;
    }

    /**
     * Sets the X-coordinate of the position.
     * 
     * @param pX The new X-coordinate.
     */
    public void setpX(float pX) {
        this.pX = pX;
    }
       
    /**
     * Sets the Y-coordinate of the position.
     * 
     * @param pY The new Y-coordinate.
     */
    public void setpY(float pY) {
        this.pY = pY;
    }

    /**
     * Compares the current position with another position.
     * 
     * @param pos2 The position to compare with.
     * @return {@code true} if both positions have the same X and Y coordinates,
     *         {@code false} otherwise.
     */
    public boolean equals(Position pos2){
        return pX==pos2.getpX() && pY==pos2.getpY();
    }
}
