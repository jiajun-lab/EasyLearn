package As6;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawGraphics {
    // Bouncer movingSprite;
    ArrayList<Mover> movers;
    /** Initializes this class for drawing. */
    public DrawGraphics() {
        movers = new ArrayList<>();
        Rectangle box1 = new Rectangle(15, 20, Color.RED);
        DynamicMover movingSprite1 = new DynamicMover(150, 150, box1, true);
        movingSprite1.setMovementVector(1, 3);

        Rectangle box2 = new Rectangle(15, 20, Color.BLUE);
        DynamicMover movingSprite2 = new DynamicMover(150, 150, box2,true);
        movingSprite2.setMovementVector(3, 1);

        Rectangle box3 = new Rectangle(15, 20, Color.GREEN);
        DynamicMover movingSprite3 = new DynamicMover(150, 150, box3,false);
        movingSprite3.setMovementVector(-3, -1);

        Rectangle box4 = new Rectangle(15, 20, Color.BLUE);
        DynamicMover movingSprite4 = new DynamicMover(150, 150, box4,false);
        movingSprite4.setMovementVector(-1, -3);

        Oval oval1 = new Oval(15, 20, Color.CYAN);
        DynamicMover movingSprite5 = new DynamicMover(150, 150, oval1,false);
        movingSprite5.setMovementVector(0, -3);

        movers.add(movingSprite1);
        movers.add(movingSprite2);
        movers.add(movingSprite3);
        movers.add(movingSprite4);
        movers.add(movingSprite5);
    }

    /** Draw the contents of the window on surface. */
    public void draw(Graphics surface) {
        for (Mover mover : movers) {
            mover.draw(surface);
        }
    }
}
