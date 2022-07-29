package game0.thread;

import game0.thread.*;
import game0.Item.*;
import game0.charac.*;
import game0.Object.AnHoObject;

public class AnThread extends Thread{

    private AnHoObject AnHo;

    public AnThread(AnHoObject AnHo){
        this.AnHo = AnHo;
    }

    public void run(){
        AnHo.an();
    }
}
