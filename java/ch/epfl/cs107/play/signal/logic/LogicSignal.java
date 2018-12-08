package ch.epfl.cs107.play.signal.logic;

abstract class LogicSignal implements Logic{

    public final float getIntensity(){
        return Logic.super.getIntensity();
    }


    @Override
    public final float getIntensity(float t){
        return Logic.super.getIntensity(t);
    }
}
