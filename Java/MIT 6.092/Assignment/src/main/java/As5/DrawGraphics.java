package As5;

import java.awt.*;
import java.util.ArrayList;

public class DrawGraphics {
    //    As5.BouncingBox box;
    ArrayList<BouncingBox> boxes;
    /** Initializes this class for drawing. */
    public DrawGraphics() {
        boxes = new ArrayList<>();
        BouncingBox box1 = new BouncingBox(100, 100, Color.RED);
        box1.setMovementVector(2,0);
        BouncingBox box2 = new BouncingBox(100, 100, Color.BLUE);
        box2.setMovementVector(2,2);
        BouncingBox box3 = new BouncingBox(100, 100, Color.GREEN);
        box3.setMovementVector(0,2);
        BouncingBox box4 = new BouncingBox(100, 100, Color.YELLOW);

        boxes.add(box1);
        boxes.add(box2);
        boxes.add(box3);
        boxes.add(box4);
    }
    /** Draw the contents of the window on surface. Called 20 times per second. */
    public void draw(Graphics surface) {
        surface.drawOval(50, 50, 250, 250);
        for (BouncingBox box : boxes) {
            box.draw(surface);
        }
    }

}
