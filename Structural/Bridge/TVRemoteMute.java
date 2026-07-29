package Structural.Bridge;

public class TVRemoteMute extends RemoteButton{
    public TVRemoteMute(EntertainmentDevices newDevices){
        super(newDevices);
    }

    public void buttonNinePressed(){
        System.out.println("TV was Muted");
    }
}
