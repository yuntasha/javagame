package game0.charac;

import game0.charac.*;

public class game_main extends game_character {
    public String ali;
    public String reason;
    public game_main(String name,int age,String ali,String reason){
        super(name, age);
        this.ali=ali;
        this.reason=reason;
    }

    public void show_profile(){
        System.out.println("=====================================================");
        System.out.println("이름 : "+ name);
        System.out.println("나이 : "+ age);
        System.out.println("알리바이 : "+ ali);
        System.out.println("예상범행동기 : "+ reason);
        System.out.println("=====================================================");
    }
}
