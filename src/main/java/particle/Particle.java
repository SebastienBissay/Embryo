package particle;

import processing.core.PVector;

import static parameters.Parameters.HEIGHT;
import static parameters.Parameters.WIDTH;

public class Particle {
    private PVector position;
    private float radius;

    public PVector getPosition() {
        return position;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public boolean isValid() {
        return !(position.x < 0 || position.x > WIDTH || position.y < 0 || position.y > HEIGHT);
    }
}
