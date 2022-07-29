package game0.thread;

import game0.charac.*;

public class SungJiThread extends Thread{
    public game_suspect sung;
    public int kk;

    public SungJiThread(game_suspect sung,int kk){
        this.sung=sung;
        this.kk=kk;
    }

    public void run(){

        if (kk == 1) {
            menuThread.chatting("김현지 : 오늘 지윤언니 11시쯤 오셨어요.");
            menuThread.chatting("성지윤 : 아 그렇나");
            menuThread.chatting("김현지 : 그러곤 지윤언니 물 한컵 받고");
            menuThread.chatting("김현지 : 문쪽 책상에서 계속 작업하고 있었어요.");
            sung.ali = "문쪽에 앉아서 딱히 움직임이 없었음";
        }

    }


}
