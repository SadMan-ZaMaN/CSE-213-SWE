package Structural.Bridge;

public class TVRemotePause extends RemoteButton {
    public TVRemotePause(EntertainmentDevices newDevices){
        super(newDevices);
    }

    public void buttonNinePressed(){
        System.out.println("TV was Paused");
    }
}
