package Structural.Adapter;

public class EnemyRobot {
    public void smashWithHand(){
        int attackDamage = 10;
        System.out.println("Enemy robot causes damage " + attackDamage + " with its hand");
    }

    public void walkForward(){
        System.out.println("Enemy Robot walked 2pixel forward");
    }

    public void reactToHuman(String driver){
        System.out.println("Enemy robot tramps on " + driver);
    }
}
