package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public class Not extends LogicSignal {
    /**
     * Applies the logical operator not to a LogicSignal s
     */

    private Logic s;
    public Not(Logic signal){
        s=signal;
    }

    @Override
    public boolean isOn() {
        if(s!=null && !s.isOn()){
            return true;
        }
        return false;
    }
}
