package Assignment_01;

public class Cat extends Participant{
    public Cat(String name, int maxDistance, float maxHeight) {
        this.species = getClass().getName();
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }
}
