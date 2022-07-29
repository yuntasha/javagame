package game0.thread;

import static game0.thread.menuThread.chatting;
import static game0.thread.menuThread.choice_chat;
import game0.Item.*;
import game0.Object.JungHanObject;
import game0.charac.*;


public class JungThread extends Thread{

    private JungHanObject JungHan;

    public JungThread(JungHanObject JungHan){
        this.JungHan = JungHan;
    }

    public void run(){
        JungHan.jung();
    }
}
