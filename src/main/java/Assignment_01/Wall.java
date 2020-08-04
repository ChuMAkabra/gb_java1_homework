package Assignment_01;

public class Wall implements Hurdles{
    private final float height;

    public Wall(float height) {
        this.height = height;
    }

    public float getHeight() {
        return height;
    }

    @Override
    public boolean attempt(Participant participant) {
        boolean runSuccess = participant.getMaxHeight() >= height;
        String result = participant.getSpecies() + " " + participant.getName() + " с лимитом " +
                participant.getMaxHeight() + "м " +
                ((runSuccess) ? "перепрыгнул " : "не перепрыгнул ") + height + "м";
        System.out.println(result);
        return runSuccess;
    }
}