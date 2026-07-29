package Structural.Bridge;

abstract class EntertainmentDevices {
    public int deviceState;     // current channel or chapter
    public int maxSetting;
    public int volumeLevel = 0;

    public abstract void buttonFivePressed();
    public abstract void buttonSixPressed();
    
    public void deviceFeedback(){
        if(deviceState < 0 || deviceState > maxSetting){
            deviceState = 0;
            System.out.println("On " + deviceState);
        }
    }

    public void buttonSevenPressed(){
        volumeLevel++;
        System.out.println("Volume at: " + volumeLevel);
    }

    public void buttonEightPressed(){
        volumeLevel--;
        System.out.println("Volume at: " + volumeLevel);
    }

}
