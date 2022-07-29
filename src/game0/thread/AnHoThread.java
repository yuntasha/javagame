package game0.thread;

import game0.thread.*;
import game0.Item.*;
import game0.charac.*;
import game0.Object.AnHoObject;

public class AnHoThread extends Thread{

    private AnHoObject AnHo;

    public AnHoThread(AnHoObject AnHo){
        this.AnHo = AnHo;
    }

    public void run(){
        AnHo.ho();
    }
}