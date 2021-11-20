/**
 * Author: Yashik Dhanaraj 
 * Period: 5
 * Date: 3/15
 * Time Taken: 5 hours
 * 
 * Summary: This lab took a while. It was just mostly coming up with strategy and figuring out 
 * how I am going to make this work. I spent some time trying to implement new commands and it kept
 * saying "Invalid Command" but in the end I was able to figure out why that wasn't working. Also, 
 * I had to keep editing my readme file because I realized things wouldn't work in the way the code was
 * structured so I had to move things around
 * 
 * 
 */
public class WizardLabDriver {
    public static void main(String[] args) {
        String intro = "Everyone does something illegal once in your lifetime. Sometimes more than once. \n" + 
        "And sometimes you get caught. You ended in prison... because you farted in near an officer's face. \n" + 
        "Because you are frustrated your prison, you start a prison riot and try to leave jail but then get \n" + 
        "caught by the same police that arrested you for farting in his presence. He tells you \"If really think \n" + 
        "you aren't guilty for this very offensive crime, I would like you to escape out of this special prison cell. \n" + 
        "If you do, I will personally escort you jail. If you don't, you will be locked in here for life. From looking at\n" + 
        "you, I know Darwin is right. CLANG. The door shuts behind you. *insert evil laughter here* Don't worry \n" + 
        "I put common sense stuff for you. Good freaking luck. You will need it because you can't even pour the water out \n" + 
        "of boot if the instructions were on the heel. You've got one hour. Tick Tock\"";
        WizardsLab lab = new WizardsLab("This is a Prisoner's Escape.", intro, 35);
        new EscapeApp(lab).runGame();
    }

}
