package particle;

import grid.Grid;
import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

public class Ray {
    private final PApplet pApplet;
    private PVector position;
    private float angle;
    private boolean collided = false;

    public Ray(PApplet pApplet) {

        this.pApplet = pApplet;
        position = new PVector(WIDTH, pApplet.random(HEIGHT));
        angle = RAY_INITIAL_ANGULAR_FACTOR * PI + RAY_INITIAL_GAUSSIAN_FACTOR * pApplet.randomGaussian();
    }

    public void trace(Grid g, int depth) {
        do {
            if ((position.x < 0) || (position.x > WIDTH) || (position.y < 0) || (position.y > HEIGHT)) {
                return;
            }

            PVector newPosition = PVector.add(position,
                    PVector.fromAngle(angle)
                            .mult(constrain(1 + RAY_GAUSSIAN_FACTOR * pApplet.randomGaussian(),
                                    RAY_MINIMUM_SPEED,
                                    RAY_MAXIMUM_SPEED)));

            int xInd = floor(newPosition.x / GRID_SIZE);
            int yInd = floor(newPosition.y / GRID_SIZE);

            for (int k = max(xInd - 1, 0); k < min(xInd + 1, g.getNumHCells()); k++) {
                for (int l = max(yInd - 1, 0); l < min(yInd + 1, g.getNumVCells()); l++) {
                    for (Particle p : g.getGrid()[k][l].getContent()) {
                        if (PVector.sub(p.getPosition(), newPosition).magSq() < sq(p.getRadius())) {
                            float f = RAY_ANGULAR_FACTOR * PVector.sub(p.getPosition(), newPosition).mag() / p.getRadius();
                            angle += (PI / f) * ((pApplet.random(1) > 0.5f) ? 1 : -1);
                            collided = true;
                        }
                    }
                }
            }
            if (collided && pApplet.random(1) > 0.5f) {
                pApplet.point(newPosition.x, newPosition.y);
            }
            position = newPosition;
            depth++;
        }
        while (depth < MAXIMUM_RECURSION);
    }
}
