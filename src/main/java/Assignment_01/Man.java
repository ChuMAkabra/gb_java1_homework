package Assignment_01;

public class Man extends Participant{
    public Man(String name, int maxDistance, float maxHeight) {
        this.species = getClass().getName();
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }
}
