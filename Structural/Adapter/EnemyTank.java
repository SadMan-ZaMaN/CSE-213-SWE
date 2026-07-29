package Structural.Adapter;

public class EnemyTank implements EnemyAttacker{

    @Override
    public void fireWeapon() {
        // TODO Auto-generated method stub
        int attackDamane = 10;
        System.out.println("Enemy Tank does " + attackDamane + " damage");
    }

    @Override
    public void driveForward() {
        // TODO Auto-generated method stub
        int movement = 2;
        System.out.println("Enemy Tank moved"+ movement);
    }

    @Override
    public void assignDriver(String driver) {
        // TODO Auto-generated method stub
        System.out.println(driver + " is driving the tank");
    }
    
}
