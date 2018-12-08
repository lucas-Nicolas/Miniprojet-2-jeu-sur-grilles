package ch.epfl.cs107.play.signal.logic;

import ch.epfl.cs107.play.signal.Signal;

public interface Logic extends Signal {
    public boolean isOn();

    default public float getIntensity(){
      if(isOn()){
          return 1.0f;
      }
      return 0.0f;
    }

    @Override
    default public float getIntensity(float t){
        return getIntensity();
    }

    Logic TRUE = new Logic() {
        @Override public boolean isOn() {
            return true;
        }
    };

    Logic FALSE = new Logic() {
        @Override
        public boolean isOn() {
            return false;
        }
    };
}

