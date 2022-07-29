package game0.Object;

import game0.thread.*;
import game0.Item.*;
import game0.charac.*;

public class AnHoObject {

    public game_suspect a;
    public game_sub b;
    public game_item trash_cup;
    public game_item ji_cup;
    public game_suspect sung;
    public int sel;

    public AnHoObject(game_suspect a,game_sub b,game_item trash_cup, game_item ji_cup, game_suspect sung, int sel){
        this.a=a;
        this.b=b;
        this.trash_cup=trash_cup;
        this.ji_cup=ji_cup;
        this.sung=sung;
        this.sel=sel;
    }

    public synchronized void an(){
        switch (sel){
            case 1:
                menuThread.chatting("최지안 : 어제 스터디 과제하러 왔어요");
                menuThread.chatting("최지안 : 어제 연경이랑 경호랑 10시30분쯤에 집갔어요.");
                if(b!=null) {
                    if (b.name == "곽경호") {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                a.trust++;
                break;
            case 2:
                menuThread.chatting("최지안 : 의심가는 사람이요?");
                menuThread.chatting("최지안 : 지윤선배 아닐까요?");
                menuThread.chatting("최지안 : 지윤선배가 회장님이 쓰러지시고 낯빛이 안좋으신데");
                menuThread.chatting("최지안 : 그리고 요즘 일도 많이 시켜서 힘들다고 하셨어요");
                sung.reason="회장님이 시키는 일이 많아 힘들어서";
                break;
            case 3:
                if(b!=null) {
                    if (b.name == "곽경호" && a.trust_max <= a.trust && ji_cup.have == 1) {
                        notify();
                        try {
                            wait();
                        } catch (InterruptedException e) {}

                        a.ali = "동아리 내에는 있었지만 딱히 움직인 적은 없음";
                    }
                }
                else{
                    menuThread.chatting("최지안 : 회장님이 쓰러지시기 전에 컨디션이 안좋다고 말씀하시긴 했어요.");
                    menuThread.chatting("최지안 : 더 이상은... 잘 모르겠어요");
                }
                break;
        }

    }

    public synchronized void ho(){
        if(sel==1){
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
            menuThread.chatting("곽경호 : 어제 연경이누나가 두고 온게 있다고");
            menuThread.chatting("곽경호 : 잠깐 1층에서 기다리긴 했어요.");
            notify();
        }
        else{
//            try {
//                wait();
//            } catch (InterruptedException e) {
//            }
            menuThread.chatting("곽경호 : 어제하고 오늘 딱히 누나는 안움직이긴 했어요.");
            menuThread.chatting("곽경호 : 카페에서 따로 사와서 종이컵근처도 안갔고요");
            notify();
        }
    }
}
