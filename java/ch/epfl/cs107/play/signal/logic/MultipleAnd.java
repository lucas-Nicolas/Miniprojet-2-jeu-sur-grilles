package ch.epfl.cs107.play.signal.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultipleAnd extends LogicSignal {
    private ArrayList<Logic> multipleLogic=new ArrayList<>();

    /**
     * Takes an array of Logics as an input and applies the logic operator and to them all
     * @param a, ellipse of logics, not null
     */
    public MultipleAnd(Logic... a){
        multipleLogic.addAll(Arrays.asList(a));
    }

    @Override
    public boolean isOn() {
        for (Logic signal : multipleLogic) {
            if(signal==null || !signal.isOn()){
                return false;
            }
        }
        return true;
    }
}
