package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import java.util.List;

/**
 * Models objects receptive to interaction (i.e. Interactor can interact with them)
 * @see Interactor
 * This interface makes sense only in the "AreaGame" context with Actor contained into Area Cell
 */
public interface Interactable {

    List<DiscreteCoordinates> getCurrentCells();
    boolean takeCellSpace();//entités traversables ou non ?
    boolean isViewInteractable();//interactions à distance ou non ?
    boolean isCellInteractable();//interactions au contact ou non ?
    void acceptInteraction(AreaInteractionVisitor v);
}
