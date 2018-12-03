package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {
    // TODO implements me #PROJECT #TUTO
    public List<DiscreteCoordinates> getCurrentCells();
    public boolean takeCellSpace();//entités traversables ou non ?
    public boolean isViewInteractable();//interactions à distance ou non ?
    public boolean isCellInteractable();//interactions au contact ou non ?
}
