package ch.epfl.cs107.play.signal.logic;

public class And extends LogicSignal{
    private Logic a,b;
    /**
     * Apply the logical operator and to of logics
     *
     * @param a,b two instances of Logic, not null
     */
    public And(Logic a, Logic b){
        this.a=a;
        this.b=b;
    }


    @Override
    public boolean isOn() {
        if (a!=null && b !=null){
            if(a.isOn() && b.isOn()){
                return true;
            }
        }
        return false;
    }
}
