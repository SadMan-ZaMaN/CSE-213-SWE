package Structural.Bridge;

public class TestTheRemote {
    public static void main(String[] args) {
        RemoteButton theTV = new TVRemoteMute(new TVdevice(1,200));

        RemoteButton theTV2 = new TVRemotePause(new TVdevice(1,200));

        System.out.println("Test TV with Mute");
        theTV.buttonFivePressed();
        theTV.buttonNinePressed();

        System.out.println("\n The tv with Pause");
        theTV2.buttonFivePressed();
        theTV2.buttonSixPressed();
        theTV2.buttonSixPressed();
        theTV2.buttonSixPressed();
        theTV2.buttonSixPressed();

        theTV2.buttonNinePressed();
        theTV2.deviceFeedback();
    }
}
