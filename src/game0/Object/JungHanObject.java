package game0.Object;


import static game0.thread.menuThread.chatting;
import static game0.thread.menuThread.choice_chat;
import game0.Item.*;
import game0.charac.*;
import game0.thread.JungHanThread;

public class JungHanObject {
    public game_suspect a;
    public game_suspect won;
    public game_sub b;
    public int sel;

    public JungHanObject(game_suspect a,game_suspect won, game_sub b,int sel){
        this.a=a;
        this.won=won;
        this.b=b;
        this.sel=sel;
    }


    public synchronized void jung(){
        switch (sel){
            case 1:
                chatting("정승민 : 동방 들어왔을때 저보고 컨디션이 안좋다고 하더라고요");
                chatting("정승민 : 그리고 처음 들어왔을때 인원이");
                chatting("정승민 : 성지윤, 회장님, 언노운 회장님");
                chatting("정승민 : 이렇게 있었네요.");
                break;
            case 2:
                chatting("정승민 : 의심가는 사람이라...");
                chatting("정승민 : 언노운 회장이 아닐까요");
                chatting("정승민 : 아무래도 가온누리 회장님이 잘하시다보니까");
                chatting("정승민 : 이래저래 부러워서 질투심에 그러지 않았나 싶네요");
                chatting("정승민 : 가온누리에는 없을꺼 같네요");
                won.reason="가온누리를 이기기위해서";
                break;
            case 3:
                chatting("정승민 : 알바가기전에 잠깐 동방에 있다가 가려고 했었죠.");
                if(b!=null) {
                    if (b.name == "최한얼") {
                        notify();
                        try {
                            wait();
                        } catch (InterruptedException e) {
                        }
                        a.ali = "동방 들어오자마자 최한얼과 캡디실로 나감";
                    }
                }
                break;
        }
    }

    public synchronized void han(){
//        try {
//            wait();
//        } catch (InterruptedException e) {
//        }

        chatting("최한얼 : 여기 와서 그냥 저랑 동아리일로");
        chatting("최한얼 : 말할것이 있어서 둘이 캡디실에서 회의하고 있었어요");
        notify();
    }
}
