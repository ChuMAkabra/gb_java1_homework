package Assignment_01;

public class Treadmill implements Hurdles {
    private final int distance;

    public Treadmill(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean attempt(Participant participant) {
        boolean runSuccess = participant.getMaxDistance() >= distance;
        String result = participant.getSpecies() + " " + participant.getName() + " с лимитом " +
                participant.getMaxDistance() + "м "
                + (runSuccess ? "пробежал " : "не пробежал ") + distance + "м";
        System.out.println(result);
        return runSuccess;
    }
}
