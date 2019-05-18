package lesson35.model;

import java.util.Random;

public abstract class Entity {
    protected long id;

    public Entity() { }

    public Entity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void generateId() {
        id = Math.abs(new Random().nextLong());
    }
}
