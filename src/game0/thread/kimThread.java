package game0.thread;

import game0.Item.game_item;
import game0.charac.*;
import game0.thread.*;

public class kimThread extends Thread{


    public game_suspect jung;
    public game_item cctv;
    public game_murder kim;
    public game_sub b;

    public kimThread(game_murder kim,game_suspect jung, game_item cctv,game_sub b){
        this.jung=jung;
        this.cctv=cctv;
        this.kim=kim;
        this.b=b;
    }

    public kimThread(game_murder kim,game_suspect jung, game_item cctv){
        this.jung=jung;
        this.cctv=cctv;
        this.kim=kim;
    }

    public void run(){
        menuThread.chatting("김연경:물어보실게 있다고요?");
        int sel=menuThread.choice_chat("어제 동아리방 온 이유에 대하여","의심가는 사람이 있는지","오늘 동아리방 온 시간");
        switch (sel){
            case 1:
                menuThread.chatting("김연경 : 어제요?");
                menuThread.chatting("김연경 : 그냥 뭐 공부하러 왔어요.");
                menuThread.chatting("김연경 : (누구랑 왔는지 묻는다)");
                menuThread.chatting("김연경 : 지안이랑 저랑 있었어요.");
                if(b!=null) {
                    if (b.name == "곽경호") {
                        KimHoThread k = new KimHoThread();
                        k.start();
                        try {
                            k.join();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        menuThread.chatting("김연경 : 아 맞네");
                    }
                }
                menuThread.chatting("김연경 : 그러고는 뭐 저녁 10시30분정도에 집으로 갔죠");
                if(cctv.have==1){
                    menuThread.chatting("cctv를 확인해보니 10시 30분에 집을 간게 맞다.");
                }
                break;
            case 2:
                menuThread.chatting("김연경 : 음... 의심가는 사람이라면");
                menuThread.chatting("김연경 : 저희 5명중에 있다면 승민선배가 아닐까요?");
                menuThread.chatting("김연경 : 회장님하고 승민선배가 동아리 회비를 담당하는데");
                menuThread.chatting("김연경 : 회장님이 사라지면 회비를 횡령하기 편하니까?");
                jung.reason="회비 횡령";
                break;
            case 3:
                menuThread.chatting("김연경 : 오늘은 늦잠자서 12시쯤에 왔어요.");
                menuThread.chatting("김연경 : 그 후에는 계속 공부하고 있다가 갑자기 회장님이 쓰러지셨어요.");
                break;
        }
    }
}
