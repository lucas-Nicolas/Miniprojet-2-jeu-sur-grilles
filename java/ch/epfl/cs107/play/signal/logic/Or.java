package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal {
    private Logic a,b;

    /**
     * applies the logical operator or to a pair of logics.
     *
     * @param a,b two instances of Logic, not null
     */
    public Or(Logic a, Logic b){
        this.a=a;
        this.b=b;
    }
    @Override
    public boolean isOn() {
        if(a!=null && b!=null)
            if(a.isOn() || b.isOn()){
                return true;
            }
        return false;
    }
}
