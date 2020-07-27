package Assignment_01;

public abstract class Participant {
    protected String species;
    protected String name;
    protected int maxDistance;
    protected float maxHeight;

    public boolean run(Treadmill treadmill) {
        int distance = treadmill.getDistance();
        boolean runSuccess = maxDistance >= distance;
        String result = species + " " + name + " с лимитом " + maxDistance + "м " +
                (runSuccess ? "пробежал " : "не пробежал ") + distance + "м";
        System.out.println(result);
        return runSuccess;
    }

    public boolean jump(Wall wall) {
        float height = wall.getHeight();
        boolean runSuccess = maxHeight >= height;
        String result = species + " " + name + " с лимитом " + maxHeight + "м " +
                ((runSuccess) ? "перепрыгнул " : "не перепрыгнул ") + height + "м";
        System.out.println(result);
        return runSuccess;
    }
}