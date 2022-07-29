package game0.thread;


import static game0.thread.menuThread.chatting;
import static game0.thread.menuThread.choice_chat;
import game0.charac.*;
import game0.Item.*;

public class WontThread extends Thread{

    public game_item cctv;
    public game_suspect a;
    public game_murder kim;
    public game_sub b;

    public WontThread(game_suspect a, game_sub b, game_murder kim, game_item cctv){
        this.a=a;
        this.b=b;
        this.kim=kim;
        this.cctv=cctv;
    }

    public void run(){
        if(a.trust>=a.trust_max && cctv.have==0){
            chatting("최원겸 : 따로 할말이 있는데");
            chatting("최원겸 : 혼자 불러주실 수 있습니까");
        }
        else{
            chatting("최원겸 : 물어보셔도됩니다.");
            int sel=choice_chat("언노운이 여기 있는 이유","의심가는 사람이 있는지","회장님이 쓰러진 것에 대하여");
            switch (sel){
                case 1:
                    chatting("최원겸 : 지섭이형이 점심저녁같이 먹기로해서");
                    chatting("최원겸 : 그냥 동아리 방에서 공부하려고 왔습니다.");
                    chatting("최원겸 : 동아리방에 제일 처음 들어간건 지섭이형이고");
                    chatting("최원겸 : 저는 언노운 동방에서 짐챙기고 두번 째로 들어갔어요.");
                    chatting("최원겸 : 그러곤 지윤이가 들어오고 그 뒤는 기억이 안나네요.");
                    break;
                case 2:
                    chatting("최원겸 : 김연경? 그 애가 회장자리를 노리고 있긴하던데");
                    chatting("최원겸 : 저번에 저보고 회장을 하려면 어떻게 해야하냐고 물어보더라고요");
                    chatting("최원겸 : 그래서 아직 지섭이형이 3학년이라 아마 내후년에나 될 꺼라고 했는데");
                    chatting("최원겸 : 눈빛이 심상치 않긴 했습니다.");
                    a.trust=a.trust_max;
                    kim.reason="가온누리 회장 계승중입니다.";
                    break;
                case 3:
                    chatting("최원겸 : 오늘 같이 편의점 갔다가");
                    chatting("최원겸 : 저만 음료수 샀었고 동방에 오자마자");
                    chatting("최원겸 : 종이컵에 음료수 한잔 줬었어요.");
                    chatting("최원겸 : 그러곤 그냥 공부하고 있었는데");
                    chatting("최원겸 : 갑자기...");
                    chatting("(아까 확인했었지만 저 음료수에는 독이 없다.)");
                    if(b!=null) {
                        if (b.name == "이종민") {
                            WonMinThread WonMin = new WonMinThread();
                            WonMin.start();
                            try {
                                WonMin.join();
                            } catch (InterruptedException e) {}
                            a.ali = "음료수에 독이 없었고 그 후 딱히 접촉은 없었다.";
                        }
                    }
                    break;
            }
        }
    }
}
