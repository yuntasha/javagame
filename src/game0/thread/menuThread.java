package game0.thread;

import game0.Item.game_item;
import game0.Object.AnHoObject;
import game0.Object.JungHanObject;
import game0.charac.*;
import game0.thread.*;
import game0.*;
import java.util.Scanner;
import java.util.Random;
import java.io.*;





public class menuThread {
    public game_murder kim;

    public game_suspect an;
    public game_suspect sung;
    public game_suspect won;
    public game_suspect jung;
    public game_item ji_cup;
    public game_item trash_cup;
    public game_item cup;
    public game_item cctv;

    public game_sub NULL_SUB = new game_sub("없음",0,"없음");

    public menuThread(game_suspect an,game_murder kim,game_suspect sung,game_suspect won,game_suspect jung,game_item ji_cup,game_item trash_cup,game_item cup,game_item cctv){
        this.an=an;
        this.kim=kim;
        this.sung=sung;
        this.won=won;
        this.jung=jung;
        this.ji_cup=ji_cup;
        this.trash_cup=trash_cup;
        this.cup=cup;
        this.cctv=cctv;
    }

    public static int menu(){
        System.out.println("============================");
        System.out.println("1.대화");
        System.out.println("2.아이템");
        System.out.println("3.사람들 프로필 보기");
        System.out.println("4.범인 결정");
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public static int profile_menu(){
        System.out.println("============================");
        System.out.println("1.최지안");
        System.out.println("2.김연경");
        System.out.println("3.성지윤");
        System.out.println("4.최연겸");
        System.out.println("5.정승민");
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }
    public int It_Menu() {
        System.out.println("============================");
        System.out.println("1." + ji_cup.item_name());
        System.out.println("2." + trash_cup.item_name());
        System.out.println("3." + cup.item_name());
        System.out.println("4." + cctv.item_name());
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public game_item change(int a){
        switch(a){
            case 1:
                return ji_cup;
            case 2:
                return trash_cup;
            case 3:
                return cup;
            case 4:
                return cctv;
        }
        return null;
    }
    public void Item_Menu(){
        int i = It_Menu();
        if(i==1){
            ji_cup.item_profile();
            if(ji_cup.item_used==1&&trash_cup.item_used==0){
                trash_cup.condition=1;
            }
        }
        else if(i==2){
            trash_cup.item_profile();
        }
        else if(i==3){
            cup.item_profile();
        }
        else if(i==4){
            cctv.item_profile();
        }
        else{
            System.out.println("잘못된 번호 입니다.");
        }
    }

    public void com(game_murder a,game_sub b){
        a.chat_chance--;
        kimThread k=new kimThread(kim,jung,cctv,b);
        k.start();
        try {
            k.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void com(game_suspect a,game_sub b){
        switch (a.name){
            case "최지안":

                if(a.trust>=a.trust_max && trash_cup.have==0){
                    menuThread.chatting("최지안 : 이상한 점이 하나 있었어요");
                    menuThread.chatting("최지안 : 쓰레기통에 쓰레기 봉투가 없어서 정리하는데");
                    menuThread.chatting("최지안 : 아예 비어있는 쓰레기통에 쓰지도 않은 새거같은 종이컵이 있었어요.");
                    menuThread.chatting("(버려진 새 종이컵을 흭득했다.)");
                    trash_cup.have=1;
                }
                else{
                    menuThread.chatting("최지안 : 네? 물어볼게 있어요?");
                    int sel=menuThread.choice_chat("어제 동아리방에 온 이유","의심가는 사람이 있는지","오늘 있었던 일에 대하여");
                    AnHoObject AnHo = new AnHoObject(an,b,trash_cup,ji_cup,sung,sel);
                    AnThread An = new AnThread(AnHo);
                    AnHoThread Ho = new AnHoThread(AnHo);
                    An.start();
                    if((sel==1 || sel==3)&&b.name == "곽경호"){
                        Ho.start();
                    }
                    try {
                        An.join();
                    } catch (InterruptedException e) {}
                }
                break;
            case "성지윤":
                int kk=0;
                JiThread Ji = new JiThread(a,b, ji_cup, an,kk);
                SungJiThread SungJi = new SungJiThread(a,kk);
                Ji.start();
                try {
                    Ji.join();
                } catch (InterruptedException e) {}

                SungJi.start();
                try {
                    SungJi.join();
                } catch (InterruptedException e) {}

                break;
            case "최원겸":
                WontThread Won = new WontThread(a,b,kim,cctv);
                Won.start();
                try {
                    Won.join();
                } catch (InterruptedException e) {}
                break;
            case "정승민":

                chatting("정승민 : . . .");
                int sel=choice_chat("혹시 오늘 사건에 대해 아는 것이 있는지","의심가는 사람이 있는지","오늘 동아리방에 온 이유");
                JungHanObject JungHan = new JungHanObject(a,won,b,sel);
                JungThread Jung = new JungThread(JungHan);
                JungHanThread Han = new JungHanThread(JungHan);

                Jung.start();
                if(sel == 3 && b.name == "최한얼"){
                    Han.start();
                }
                try {
                    Jung.join();
                } catch (InterruptedException e) {

                }
                break;
        }
    }

    public void com(game_murder a){
        a.chat_chance--;
        kimThread k=new kimThread(kim,jung,cctv);
        k.start();
        try {
            k.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public int human_select(){
        System.out.println("============================");
        System.out.println("1.곽경호");
        System.out.println("2.최한얼");
        System.out.println("3.이종민");
        System.out.println("4.김현지");
        System.out.println("5.데려가지 않는다");
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public void com(game_suspect a){
        switch (a.name){
            case "최지안":
                if(a.trust>=a.trust_max && trash_cup.have==0){
                    chatting("최지안 : 이상한 점이 하나 있었어요");
                    chatting("최지안 : 쓰레기통에 쓰레기 봉투가 없어서 정리하는데");
                    chatting("최지안 : 아예 비어있는 쓰레기통에 쓰지도 않은 새거같은 종이컵이 있었어요.");
                    chatting("(버려진 새 종이컵을 흭득했다.)");
                    trash_cup.have=1;
                }
                else{
                    chatting("최지안 : 네? 물어볼게 있어요?");
                    int sel=choice_chat("어제 동아리방에 온 이유","의심가는 사람이 있는지","오늘 있었던 일에 대하여");
                    switch (sel){
                        case 1:
                            chatting("최지안 : 어제 스터디 과제하러 왔어요");
                            chatting("최지안 : 어제 연경이랑 경호랑 10시30분쯤?에 집갔어요.");
                            a.trust+=2;
                            break;
                        case 2:
                            chatting("최지안 : 의심가는 사람이요?");
                            chatting("최지안 : 지윤선배 아닐까요?");
                            chatting("최지안 : 지윤선배가 회장님이 쓰러지시고 낯빛이 안좋으신데");
                            chatting("최지안 : 그리고 요즘 일도 많이 시켜서 힘들다고 하셨어요");
                            a.trust++;
                            sung.reason="회장님이 시키는 일이 많아 힘들어서";
                            break;
                        case 3:
                            chatting("최지안 : 회장님이 쓰러지시기 전에 컨디션이 안좋다고 말씀하시긴 했어요.");
                            chatting("최지안 : 더 이상은... 잘 모르겠어요");
                            a.trust++;
                            break;
                    }
                }
                break;
            case "성지윤":
                if(a.trust>=a.trust_max && ji_cup.have==0){
                    chatting("성지윤 : 별거 아니라고 생각하긴 했는데");
                    chatting("성지윤 : 제꺼 종이컵에 이상한게 묻어있긴 했어요");
                    chatting("성지윤 : 지섭이형꺼 아마 아래에 있던컵 같은데");
                    chatting("(성지윤이 사용하던 컵을 흭득했다.)");
                    ji_cup.have=1;
                }
                else{
                    chatting("성지윤 : 물어보신다고요?");
                    int sel=choice_chat("얼굴이 창백해진것에 대하여","의심가는 사람이 있는지","오늘 동아리방에 온 것에 대하여");
                    switch (sel){
                        case 1:
                            chatting("성지윤 : 그냥 컨디션이 안좋네요");
                            ji_cup.condition=1;
                            a.trust++;
                            break;
                        case 2:
                            chatting("성지윤 : 의심가는 사람이라...");
                            chatting("성지윤 : 지안이 아닐까");
                            chatting("성지윤 : 요즘 스터디 힘들어하던데");
                            chatting("성지윤 : 누구라고 말할 수가 없네");
                            a.trust--;
                            an.reason="스터디가 힘들어서";
                            break;
                        case 3:
                            chatting("성지윤 : 오늘요");
                            chatting("성지윤 : 오늘 뭐 언제왔더라?");
                            chatting("성지윤 : 그냥 뭐 혼자 프로젝트하기 심심해서 동아리방에 한번 와봤어요");
                            a.trust++;
                            break;
                    }
                }
                break;
            case "최원겸":
                if(a.trust>=a.trust_max && cctv.have==0){
                    chatting("최원겸 : 진짜 저는 범인이 아니고 다른 뜻은 없습니다.");
                    chatting("최원겸 : 사실 가온누리 이겨보려고 CCTV를 몰래 설치했었는데");
                    chatting("최원겸 : 이거보고 꼭 잡아주세요.");
                    chatting("최원겸 : 파일 보내드릴게요");
                    chatting("(가온누리 동아리방을 찍는 CCTV영상을 획득했다.)");
                    cctv.have=1;
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
                            break;
                    }
                }
                break;
            case "정승민":
                chatting("정승민 : . . .");
                int sel=choice_chat("혹시 오늘 사건에 대해 아는 것이 있는지","의심가는 사람이 있는지","오늘 동아리방에 온 이유");
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
                        break;
                }
                break;
        }
    }

    public static int choice_chat(String a,String b,String c){
        System.out.println("=========================");
        System.out.println("1."+a);
        System.out.println("2."+b);
        System.out.println("3."+c);
        System.out.println("=========================");

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        while(i>=4||i<=0){
            System.out.println("다시 적어주세요");
            i = sc.nextInt();
        }
        return i;
    }

    public static void chatting(String chat){
        System.out.println(chat);
        pause();
    }

    public static void pause() {
        try {
            System.in.read();
        } catch (IOException e) { }

    }

    public int Ending(){
        chatting("범인을 찾았습니다");
        chatting("범행은");
        switch(select("어제 일어났습니다.","오늘 일어났습니다")){
            case 1:
                chatting("어제 일어났습니다.");
                chatting("증거는...");
                if(It_Menu()==4&&cctv.have==1){
                    chatting("가온누리 내 CCTV를 돌려본 결과 오늘은 없었습니다.");
                    chatting("하지만 결정적 사인은 독살이기 때문에 준비만 갖춰지면");
                    chatting("굳이 오늘 움직일 필요가 없습니다.");
                    chatting("따라서 범인이 될 수 있는 사람은");
                    chatting("최지안, 김연경 이렇게 둘이 남습니다.");
                    chatting("따라서 범인은...");
                    if(select("최지안","김연경")==2){
                        chatting("김연경이 됩니다.");
                        chatting("김연경 : 저요?");
                        chatting("김연경 : 증거라도 있으시면서 저라고 말하시는 건가요?");
                        return 1;

                    }
                    else{
                        chatting("최지안 : 네?");
                        chatting("최지안 : 제가 왜요?");
                        return 0;
                    }

                }
                else{
                    return 0;
                }
            case 2:
                chatting("오늘 범행이 발생했습니다.");
                chatting("그에대한 증거로...");
                int sel = It_Menu();
                return 0;
        }
        System.out.println("다른날은 아닙니다...");
        return 0;
    }

    public static int select(String a,String b, String c){
        System.out.println("============================");
        System.out.println("1."+a);
        System.out.println("2."+b);
        System.out.println("3."+c);
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public static int select(String a,String b){
        System.out.println("============================");
        System.out.println("1."+a);
        System.out.println("2."+b);
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }

    public static int select(String a,String b, String c,String d){
        System.out.println("============================");
        System.out.println("1."+a);
        System.out.println("2."+b);
        System.out.println("3."+c);
        System.out.println("4."+d);
        System.out.println("============================");
        System.out.println("번호를 고르세요.");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        return i;
    }
}
