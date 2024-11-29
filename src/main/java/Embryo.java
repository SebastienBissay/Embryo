import grid.Grid;
import particle.Ray;
import processing.core.PApplet;

import static parameters.Parameters.*;
import static save.SaveUtil.saveSketch;

public class Embryo extends PApplet {
    public static void main(String[] args) {
        PApplet.main(Embryo.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());
        stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        noFill();
        noLoop();
    }

    @Override
    public void draw() {
        Grid grid = new Grid(this);
        for (int k = 0; k < PARTICLE_NUMBER; k++) {
            grid.addParticle();
        }

        for (int k = 0; k < NUMBER_OF_RAYS; k++) {
            Ray ray = new Ray(this);
            ray.trace(grid, 0);
        }
        saveSketch(this);
    }
}
