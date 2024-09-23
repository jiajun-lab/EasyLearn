package As6;

import java.awt.*;

public class DynamicMover implements Mover{
    private int x;
    private int y;
    private int xDirection;
    private int yDirection;
    private Sprite sprite;
    private boolean bouncing;

    public DynamicMover(int x, int y, Sprite sprite, boolean bouncing) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        this.bouncing = bouncing;
    }

    @Override
    public void setMovementVector(int xIncrement, int yIncrement) {
        xDirection = xIncrement;
        yDirection = yIncrement;
    }

    @Override
    public void draw(Graphics surface) {
// Draw the sprite
        sprite.draw(surface, x, y);

        // Move the object each time we draw it
        x += xDirection;
        y += yDirection;

        // If we have hit the edge and are moving in the wrong direction, reverse direction
        // We check the direction because if a box is placed near the wall, we would get "stuck"
        // rather than moving in the right direction
        if(bouncing) {
            if ((x <= 0 && xDirection < 0) ||
                    (x + sprite.getWidth() >= SimpleDraw.WIDTH && xDirection > 0)) {
                xDirection = -xDirection;
            }
            if ((y <= 0 && yDirection < 0) ||
                    (y + sprite.getHeight() >= SimpleDraw.HEIGHT && yDirection > 0)) {
                yDirection = -yDirection;
            }
        }
    }
}
