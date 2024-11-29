package grid;

import particle.Particle;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Particle> content = new ArrayList<>();

    public List<Particle> getContent() {
        return content;
    }

    public void addParticle(Particle particle) {
        content.add(particle);
    }
}
