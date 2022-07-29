package game0.thread;

import static game0.thread.menuThread.chatting;

public class WonMinThread extends Thread{

    public void run(){
        chatting("이종민 : 이분 처음봐서 누구지 하고 보고있었는데");
        chatting("이종민 : 딱히 회장님이랑 얘기도 안하시고 컴퓨터만 보시더라고요.");
    }
}
