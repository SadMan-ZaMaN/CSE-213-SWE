package Structural.Adapter;

public class TestEnemyAttacker {
    public static void main(String[] args) {
        EnemyTank rx7Tank = new EnemyTank();

        EnemyRobot fredTheRobot = new EnemyRobot();

        EnemyAttacker robotAdapter = new EnemyRobotAdapter(fredTheRobot);

        System.out.println("The robot");

        fredTheRobot.reactToHuman("Donald");
        fredTheRobot.smashWithHand();
        fredTheRobot.walkForward();

        System.out.println("The Tank");
        rx7Tank.assignDriver("Frank");
        rx7Tank.fireWeapon();
        rx7Tank.driveForward();

        System.out.println("The Robot with Adapter");   
        // EnemyRobot class er esob method nei..kintu adapter er jonno use kora jacche
        robotAdapter.assignDriver("Parker");
        robotAdapter.fireWeapon();
        robotAdapter.driveForward();
    }
}
