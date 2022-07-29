package game0.thread;

import game0.Object.JungHanObject;

import static game0.thread.menuThread.chatting;

public class JungHanThread extends Thread{

    private JungHanObject JungHan;

    public JungHanThread(JungHanObject JungHan){
        this.JungHan = JungHan;
    }

    public void run(){
        JungHan.han();
    }
}
