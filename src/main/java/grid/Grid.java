package grid;

import particle.Particle;
import processing.core.PApplet;
import processing.core.PVector;

import static parameters.Parameters.*;
import static processing.core.PApplet.*;
import static processing.core.PConstants.PI;

public class Grid {
    private static PApplet pApplet;
    private final int numHCells;
    private final int numVCells;
    private final Cell[][] grid;

    public Grid(PApplet pApplet) {
        if (Grid.pApplet == null) {
            Grid.pApplet = pApplet;
        }
        numHCells = ceil(WIDTH / GRID_SIZE);
        numVCells = ceil(HEIGHT / GRID_SIZE);
        grid = new Cell[numHCells][numVCells];
        for (int i = 0; i < numHCells; i++) {
            for (int j = 0; j < numVCells; j++) {
                grid[i][j] = new Cell();
            }
        }
    }


    public void addParticle() {
        Particle particle = new Particle();
        particle.setRadius(pApplet.random(1, PARTICLE_RADIUS));
        do {
            particle.setPosition(PVector.fromAngle(pApplet.random(-PI, PI))
                    .mult(exp(abs(PARTICLE_GAUSSIAN_FACTOR * pApplet.randomGaussian()))
                            * pApplet.random(.5f * max(WIDTH, HEIGHT)))
                    .add(PARTICLE_STARTING_POSITION));
        }
        while (!particle.isValid());

        int xInd = floor(particle.getPosition().x / GRID_SIZE);
        int yInd = floor(particle.getPosition().y / GRID_SIZE);
        grid[xInd][yInd].addParticle(particle);
    }

    public int getNumHCells() {
        return numHCells;
    }

    public int getNumVCells() {
        return numVCells;
    }

    public Cell[][] getGrid() {
        return grid;
    }
}
