package game0.Item;
import java.util.Scanner;

import game0.*;

public class game_item {
    public String name;     //이름
    public String explain1; //사용 전 설명
    public String explain2; //사용 후 설명
    public int have;        //가지고 있는지 1이면 보유중
    public int condition;   //사용 조건이 충족되면 1
    public int item_used;   //사용 후라면 1
    public game_item(String name, String explain1, String explain2, int have, int condition){
        this.name=name;
        this.explain1=explain1;
        this.explain2=explain2;
        this.have=have;
        this.condition=condition;
        this.item_used=0;
    }
    public String item_name(){
        if(have==1){
            return name;
        }
        else{
            return "????";
        }
    }
    public void item_profile(){
        if(have==1){
            if(condition==1 && item_used==0){
                System.out.println("사용 가능한 아이템입니다 사용하시겠습니까?(Y=1,N=0)");
                Scanner sc = new Scanner(System.in);
                if (1==sc.nextInt()) {
                    item_used = 1;
                }
            }
            System.out.println("이름 : "+name);
            if(item_used==1){
                System.out.println("설명 : "+explain2);
            }
            else{
                System.out.println("설명 : "+explain1);
            }
        }
        else{
            System.out.println("아직 획득하지 못한 아이템입니다.");
        }
    }
}
