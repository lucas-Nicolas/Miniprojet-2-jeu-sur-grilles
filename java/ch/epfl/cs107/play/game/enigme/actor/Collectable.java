package ch.epfl.cs107.play.game.enigme.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.handler.EnigmeInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public abstract class Collectable extends AreaEntity {
    private boolean isCollected;
    private Logic key;

    /**
     * Initialise un collectable associé à un logic constant pour imiter le comportement d'un collectable n'ayant aucune contrainte sur son apparition.
     * @param area (Area) not null
     * @param position (DiscreteCoordinates) not null.
     */
    public Collectable(Area area, DiscreteCoordinates position) {
        super(area, position);
        key = Logic.TRUE;
    }

    /**
     * Change la construction des Collectables pour qu'un collectable puisse être ramassé dans un coffre en le prenant en argument en tant que logic lors de la construction.
     * @param area (Area) not null
     * @param position (DiscreteCoordinates) not null.
     * @param key (Safe) not null
     */
    public Collectable(Area area, DiscreteCoordinates position, Safe key) {
        super(area, position);
        this.key = key;
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Le collectable ne prend de place que si le coffre est ouvert ou s'il n'est pas associé à un coffre
     * @return
     */
    @Override
    public boolean takeCellSpace() {
        return key.isOn();
    }

    /**
     * Le collectable n'accepte les interactions que si le coffre est ouvert ou s'il n'est pas associé à un coffre
     * @return
     */
    @Override
    public boolean isViewInteractable() {
        return key.isOn();
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    /**
     * Permet aux autres entités d'indiquer quand elles veulent ramasser le Collectable.
     * @param collected (boolean), collecté
     */
    protected void setIsCollected(boolean collected){
        isCollected = collected;
    }

    /**
     * Indique l'état du collectable.
     * @return
     */
    protected boolean isCollected(){
        return isCollected;
    }

    /**
     * Fait quitter l'aire au collectable s'il a été ramassé
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {

        if(isCollected){
            getOwnerArea().unregisterActor(this);
        }
    }

    /**
     * Permet aux classes héritant de collectable d'avoir l'information sur l'état du coffre dans lequel elles se trouvent et d'agir en conséquence.
     * @return
     */
    protected Logic getKey() {
        return key;
    }

}
