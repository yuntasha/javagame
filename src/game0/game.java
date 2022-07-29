package game0;

import game0.charac.*;
import game0.Item.*;
import game0.thread.*;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class game {
    public static void main(String[] args){
        //사람 만들기
        game_murder kim = new game_murder("김연경",21,"NULL","NULL", 5);

        game_suspect an = new game_suspect("최지안",21,"NULL","NULL", 3,4);
        game_suspect sung = new game_suspect("성지윤",23,"NULL","NULL",3,5);
        game_suspect won = new game_suspect("최원겸",24,"NULL","NULL",3,4);
        game_suspect jung = new game_suspect("정승민",24,"NULL","NULL",3,4);

        game_sub ho = new game_sub("곽경호",20,"최지안");
        game_sub han = new game_sub("최한얼", 24,"정승민");
        game_sub min = new game_sub("이종민",24,"김연경");
        game_sub ji = new game_sub("김현지",20,"성지윤");

        //아이템 제작
        game_item ji_cup = new game_item("성지윤이 사용하던 컵","성지윤이 사용하던 컵이다.","컵 밑부분에 독이 묻어있다.",0,0); // 0 0
        game_item trash_cup = new game_item("쓰레기통에 버려진 컵","세게 쥐었는지 구겨져있고 뭔가가 담긴 흔적은 없다.","김연경의 지문이 묻어있다.",0,0); // 0 0
        game_item cup = new game_item("회장님이 사용한 컵", "회장님을 제외한 다른 사람의 지문은 없었다.","NULL",1,0); // 1 0
        game_item cctv = new game_item("CCTV 영상","오늘 하루동안 회장님의 컵에 손댄 사람은 없다.","NULL",0,0);  // 0 0

        //서브 스레드 선언
        menuThread me_nu = new menuThread(an,kim,sung,won,jung,ji_cup,trash_cup,cup,cctv);

        opening();

        int menuNum= me_nu.menu();
        while(menuNum!=4){
            if(menuNum==1){
                int i=me_nu.profile_menu();
                int sp=me_nu.human_select();
                switch (i){
                    case 1:
                        switch (sp){
                            case 1:
                                me_nu.com(an,ho);
                                break;
                            case 2:
                                me_nu.com(an,han);
                                break;
                            case 3:
                                me_nu.com(an,min);
                                break;
                            case 4:
                                me_nu.com(an,ji);
                                break;
                            default:
                                me_nu.com(an);
                                break;
                        }
                        break;
                    case 2:
                        switch (sp){
                            case 1:
                                me_nu.com(kim,ho);
                                break;
                            case 2:
                                me_nu.com(kim,han);
                                break;
                            case 3:
                                me_nu.com(kim,min);
                                break;
                            case 4:
                                me_nu.com(kim,ji);
                                break;
                            default:
                                me_nu.com(kim);
                                break;
                        }
                        break;
                    case 3:
                        switch (sp){
                            case 1:
                                me_nu.com(sung,ho);
                                break;
                            case 2:
                                me_nu.com(sung,han);
                                break;
                            case 3:
                                me_nu.com(sung,min);
                                break;
                            case 4:
                                me_nu.com(sung,ji);
                                break;
                            default:
                                me_nu.com(sung);
                                break;
                        }
                        break;
                    case 4:
                        switch (sp){
                            case 1:
                                me_nu.com(won,ho);
                                break;
                            case 2:
                                me_nu.com(won,han);
                                break;
                            case 3:
                                me_nu.com(won,min);
                                break;
                            case 4:
                                me_nu.com(won,ji);
                                break;
                            default:
                                me_nu.com(won);
                                break;
                        }
                        break;
                    case 5:
                        switch (sp){
                            case 1:
                                me_nu.com(jung,ho);
                                break;
                            case 2:
                                me_nu.com(jung,han);
                                break;
                            case 3:
                                me_nu.com(jung,min);
                                break;
                            case 4:
                                me_nu.com(jung,ji);
                                break;
                            default:
                                me_nu.com(jung);
                                break;
                        }
                        break;
                    default:
                        menuThread.chatting("딴거 그만좀 선택하십쇼");
                        break;
                }
            }
            else if(menuNum==2){
                me_nu.Item_Menu();
                menuThread.pause();
            }
            else if(menuNum==3){
                int p_num= me_nu.profile_menu();
                if(p_num==1){
                    an.show_profile();
                }
                else if(p_num==2){
                    kim.show_profile();
                }
                else if(p_num==3){
                    sung.show_profile();
                }
                else if(p_num==4){
                    won.show_profile();
                }
                else if(p_num==5){
                    jung.show_profile();
                }
                else{
                    System.out.println("없는 사람입니다.");
                }
                menuThread.pause();

            }
            else{
                menuThread.chatting("올바른 번호를 입력하세요");
            }
            if(kim.chat_chance<=0) {
                Random rand = new Random();
                if(rand.nextInt(3)==0){
                    menuThread.chatting("우.....우웩");
                    menuThread.chatting("뭐야 왜 피가...");
                    menuThread.chatting("(눈앞이 캄캄해진다.)");
                    menuThread.chatting("(Game Over)");
                    return;
                }
            }
            menuNum= me_nu.menu();
        }
        int end=me_nu.Ending();
        if(end==0){
            me_nu.chatting("(아직 증거가 부족하다)");
            me_nu.chatting("Game Over");
            me_nu.chatting("**모든 증거물을 모아 추리를 해주세요**");
            return;
        }
        else{
            menuThread.chatting("증거는 당연히 있습니다");
            menuThread.chatting("우선 회장님이 마시던 음료수에서 독성분이 발견이 됬는데");
            if(me_nu.It_Menu()==1 && ji_cup.item_used==1){
                menuThread.chatting("이것은 성지윤이 마시던 컵입니다.");
                menuThread.chatting("회장님 다음에 사용한 컵은 성지윤 컵인데");
                menuThread.chatting("컵 아랫부분에서 독성분이 검출되었습니다.");
                menuThread.chatting("따라서 종이컵 자체에 독이 들어있다고 볼 수 있습니다.");
                menuThread.chatting("김연경 : 하지만 독을 썼다고 제가 범인은 아니지 않나요?");
                menuThread.chatting("결정적인 증거와 증언이 있습니다.");
                menuThread.chatting("결정적인 증거...");
                int I_sel=me_nu.It_Menu();
                int P_sel= me_nu.select("곽경호","최원겸","최한얼","정승민");
                if(I_sel==2 && P_sel==1 && trash_cup.item_used==1){
                    menuThread.chatting("쓰레기통에서 발견된 새 종이컵입니다.");
                    menuThread.chatting("내부에는 아무것도 묻어있지않았지만");
                    menuThread.chatting("쓰레기통에 버려져있었습니다.");
                    menuThread.chatting("어제 집에 돌아갈때 3분이서 함께 가셨다고 하셨는데 자세히 말씀해 주실 수 있나요?");
                    menuThread.chatting("곽경호 : 어제 10시 30분쯤 집에 가려고 할 때 연경이 누나가 뭐 두고왔다고");
                    menuThread.chatting("곽경호 : 잠깐 올라갔다온다고 했는데 한 2분 기다리고 함께 갔습니다");
                    menuThread.chatting("어제자 CCTV를 보면 범인은 10시 32분경 다시 동방에 나타나서");
                    menuThread.chatting("종이컵을 만지고 종이컵을 하나 버리고 다시 나갑니다.");
                    menuThread.chatting("다른 것은 하나도 안 만지고 종이컵 하나 건들고 나왔습니다.");
                    menuThread.chatting("그 종이컵은 바로 이 종이컵으로 김연경의 지문이 묻어있는 것을 확인했습니다.");
                    menuThread.chatting("제일 위에 있는 종이컵을 통해 바로 밑에 있는 종이컵 안에 독을 묻히고");
                    menuThread.chatting("오늘 늦게 나타나서 자신은 아무런 관련이 없는듯 늦게 왔습니다");
                    menuThread.chatting("범행을 인정하십니까?");
                    menuThread.chatting("김연경 : ...");
                    menuThread.chatting("김연경 : 인정합니다.");
                    menuThread.chatting("김연경 : 가온누리 동아리 회장을 너무하고 싶었지만");
                    menuThread.chatting("김연경 : 혹시 못할 수도 있다는 불안함에 범행을 저지르게 되었습니다.");
                    menuThread.chatting("그렇게 김연경은 체포되었고");
                    menuThread.chatting("재판중 경찰이 미란다의 원칙을 말하지 않고 체포하여");
                    menuThread.chatting("무죄로 다시 세상에 나오게 되었다.");
                    menuThread.chatting("THE END");
                }
                else {
                    me_nu.chatting("(아직 증거가 부족하다)");
                    me_nu.chatting("Game Over");
                    me_nu.chatting("**모든 증거물을 모아 추리를 해주세요**");
                    return;
                }
            }
            else{
                me_nu.chatting("(아직 증거가 부족하다)");
                me_nu.chatting("Game Over");
                me_nu.chatting("**모든 증거물을 모아 추리를 해주세요**");
                return;
            }
        }
    }

    public static void opening(){
        menuThread.chatting("시작하려면 Enter를 누르시오.");
        menuThread.chatting("잘하셨습니다. 진행을 하려면 Enter를 누르면 이렇게 진행이 됩니다.");
        menuThread.chatting("이 게임은 픽션이며 실제인물과는 아마 관련이 전혀 없음을 알립니다.");
        menuThread.chatting("과몰입 하지말아주세요.");
        menuThread.chatting("자신의 이름이 불편하신 분께서는 신재희에게 톡을 주시면 교체하겠습니다.");
        menuThread.chatting("이 게임의 존재를 외부에 알리지 말아주세요.");
        menuThread.chatting("그럼 시작하겠습니다");
        menuThread.chatting("현실에선 동아리 회장인 내가 이세계에서는 피해자?");
        menuThread.chatting("오후 1시 21분경");
        menuThread.chatting("여느 때와 다름없이 가온누리에서 공부를 하던 동아리 NPC들");
        menuThread.chatting("쿵");
        menuThread.chatting("큰 소리가 나며 회장님이 책상에 쓰러지셨다.");
        menuThread.chatting("(경찰서)");
        menuThread.chatting(".");
        menuThread.chatting(".");
        menuThread.chatting(".");
        menuThread.chatting("따르르릉");
        menuThread.chatting("네 ㅁㅁ경찰서입니다.");
        menuThread.chatting("최지안 : 회장님이 숨을 안쉬세요...");
        menuThread.chatting("네?");
        menuThread.chatting("잠시후");
        menuThread.chatting("경찰에서 이런저런 현장 수사를 거친뒤...");
        menuThread.chatting("용의자는 5명으로 좁혀졌다.");
        menuThread.chatting("최지안");
        menuThread.chatting("김연경");
        menuThread.chatting("성지윤");
        menuThread.chatting("최원겸");
        menuThread.chatting("정승민");
        menuThread.chatting("이렇게 5명이다.");

        menuThread.chatting("**대화나 아이템사용을 통해 증거를 수집할 수 있습니다.**");
    }
}
