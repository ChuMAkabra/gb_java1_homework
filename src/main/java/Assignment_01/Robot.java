package Assignment_01;

public class Robot extends Participant {
    public Robot(String name, int maxDistance, float maxHeight) {
        this.species = getClass().getName();
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }
}
