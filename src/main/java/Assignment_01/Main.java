package Assignment_01;

public class Main {
    private static Participant[] participants;
    private static Hurdles[] hurdles;

    public static void main(String[] args) {
        initiateObjects();
        goRunJump();
    }

    private static void initiateObjects() {
        Man man1 = new Man("Обычный парень", 1501, 1.1F);
        Man man2 = new Man("Усейн Болт", 800, 1.7F);
        Man man3 = new Man("Горе-марафонец Роман", 42195, 1.01F);
        Man man4 = new Man("'Заяц' в метро", 2000, 2.1F);

        Cat cat1 = new Cat("ЖироБарсик", 100, 1.05F);
        Cat cat2 = new Cat("СуперКот", 100, 2.06F);

        Robot robot1 = new Robot("R2D2", 100000, 0.2F);
        Robot robot2 = new Robot("T800", 200000, 1.5F);

        participants = new Participant[] {man1, man2, man3, man4, cat1, cat2, robot1, robot2};

        Wall wall1 = new Wall(0.8F);
        Wall wall2 = new Wall(1.2F);
        Wall wall3 = new Wall(1.8F);
        Wall wall4 = new Wall(2.05F);

        Treadmill treadmill1 = new Treadmill(500);
        Treadmill treadmill2 = new Treadmill(999);
        Treadmill treadmill3 = new Treadmill(1001);
        Treadmill treadmill4 = new Treadmill(42001);
        Treadmill treadmill5 = new Treadmill(100001);

        hurdles = new Hurdles[] {wall1, treadmill1, treadmill2, treadmill3, wall2, wall3, wall4,
                treadmill4, treadmill5};
    }

    private static void goRunJump() {
        for (Participant participant : participants) {
            for (Hurdles hurdle : hurdles) {
                    if (!hurdle.attempt(participant)) break;
                }
            }
        }
    }