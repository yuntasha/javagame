package game0.thread;

import game0.thread.*;
import game0.Item.*;
import game0.charac.*;



public class JiThread extends Thread{

    public game_suspect a;
    public game_sub b;
    public game_item ji_cup;
    public game_suspect an;
    public int kk;

    public JiThread(game_suspect a,game_sub b, game_item ji_cup, game_suspect an,int kk){
        this.a=a;
        this.b=b;
        this.ji_cup=ji_cup;
        this.an=an;
        this.kk=kk;
    }

    public void run(){
        if(a.trust>=a.trust_max && ji_cup.have==0){
            menuThread.chatting("성지윤 : 별거 아니라고 생각하긴 했는데");
            menuThread.chatting("성지윤 : 제꺼 종이컵에 이상한게 묻어있긴 했어요");
            menuThread.chatting("성지윤 : 지섭이형꺼 아마 아래에 있던컵 같은데");
            menuThread.chatting("(성지윤이 사용하던 컵을 흭득했다.)");
            kk=2;
            ji_cup.have=1;
        }
        else {
            menuThread.chatting("성지윤 : 물어보신다고요?");
            int sel = menuThread.choice_chat("얼굴이 창백해진것에 대하여", "의심가는 사람이 있는지", "오늘 동아리방에 온 것에 대하여");
            switch (sel) {
                case 1:
                    menuThread.chatting("성지윤 : 그냥 컨디션이 안좋네요");
                    ji_cup.condition = 1;
                    a.trust++;
                    kk=2;
                    break;
                case 2:
                    menuThread.chatting("성지윤 : 의심가는 사람이라...");
                    menuThread.chatting("성지윤 : 지안이 아닐까");
                    menuThread.chatting("성지윤 : 요즘 스터디 힘들어하던데");
                    menuThread.chatting("성지윤 : 누구라고 말할 수가 없네");
                    a.trust++;
                    an.reason = "스터디가 힘들어서";
                    kk=2;
                    break;
                case 3:
                    menuThread.chatting("성지윤 : 오늘요");
                    menuThread.chatting("성지윤 : 오늘 뭐 언제왔더라?");
                    if (b.name == "김현지") {
                        kk=1;
                        menuThread.chatting("김현지 : 오늘 지윤언니 11시쯤 오셨어요.");
                        menuThread.chatting("성지윤 : 아 그렇나");
                        menuThread.chatting("김현지 : 그러곤 지윤언니 물 한컵 받고");
                        menuThread.chatting("김현지 : 문쪽 책상에서 계속 작업하고 있었어요.");
                        a.ali = "문쪽에 앉아서 딱히 움직임이 없었음";
                    }
                    break;
            }
        }
    }
}
