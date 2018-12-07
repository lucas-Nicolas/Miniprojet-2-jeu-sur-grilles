package ch.epfl.cs107.play.signal.logic;

import java.util.Set;
import java.util.function.Consumer;

public class LogicNumber extends LogicSignal {
    private float nb;
    private Set<Logic> e;

    public LogicNumber(float nb, Set<Logic> e){
        this.nb=nb;
        this.e=e;
    }

    private float evaluationEnUnNombre(){
        float a= 0f;
        int n=0;
        for (Logic logic: e) {
            if(logic.isOn()){
                a+=Math.pow(2,n);
            }
        }
        return 0f;
    };


    //todo javadoc
    @Override
    public boolean isOn() {
        float a= 0f;
        int n=0;
        for (Logic logic: e) {
            if(logic.isOn()){
                a+=(float) Math.pow(2,n);
            }
        }
        if(12<n || nb<0 || Math.pow(2,e.size())<nb){
            return false;
        }else if(a==nb){
            return true;
        }
        return false;

    }
}
