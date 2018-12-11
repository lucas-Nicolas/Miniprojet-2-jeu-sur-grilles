package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class CellSwitch extends Switch {
    private boolean switchedOnPreviousFrame;
    private boolean canBeSwitched;


    public CellSwitch(Area area, DiscreteCoordinates position) {
        super(area, position, false);
    }


    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * Permet aux boutons s'activant par contact de ne pas s'activer et se désactiver à l'infini lorsque le joueur se tient dessus.
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        canBeSwitched=!switchedOnPreviousFrame;
        switchedOnPreviousFrame = false;

    }

    @Override
    protected void setIsActivated() {
        if(canBeSwitched) {
            super.setIsActivated();;
        }
        switchedOnPreviousFrame = true;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
            super.acceptInteraction(v);
    }

}
