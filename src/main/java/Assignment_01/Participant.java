package Assignment_01;

public abstract class Participant {
    protected String species;
    protected String name;
    protected int maxDistance;
    protected float maxHeight;

    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public float getMaxHeight() {
        return maxHeight;
    }
}