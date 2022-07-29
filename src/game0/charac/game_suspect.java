package game0.charac;

import game0.charac.*;

public class game_suspect extends game_main {
    public int trust;
    public int trust_max;
    public game_suspect(String name,int age,String ali,String reason,int trust,int trust_max){
        super(name, age, ali, reason);
        this.trust=trust;
        this.trust_max=trust_max;
    }
}
