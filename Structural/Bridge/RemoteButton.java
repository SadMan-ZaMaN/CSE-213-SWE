package Structural.Bridge;

public abstract class RemoteButton {
    private EntertainmentDevices theDevice;

    public RemoteButton(EntertainmentDevices newDevice){
        theDevice = newDevice;
    }

    public void buttonFivePressed(){
        theDevice.buttonFivePressed();
    }

    public void buttonSixPressed(){
        theDevice.buttonSixPressed();
    }

    public void deviceFeedback(){
        theDevice.deviceFeedback();
    }

    public abstract void buttonNinePressed();
}
