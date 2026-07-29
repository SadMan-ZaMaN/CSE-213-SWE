package Structural.Bridge;

public class TVdevice extends EntertainmentDevices{
    public TVdevice(int newDeviceState, int newMaxSettings){
        deviceState = newDeviceState;
        maxSetting = newMaxSettings;
    }

    @Override
    public void buttonFivePressed() {
        System.out.println("Channel Down");
        deviceState++;
    }

    @Override
    public void buttonSixPressed() {
        System.out.println("Channel UP");
        deviceState--;
    }

    
}
