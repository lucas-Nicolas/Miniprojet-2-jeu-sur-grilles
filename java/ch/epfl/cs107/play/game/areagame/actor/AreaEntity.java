package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.enigme.actor.Dialog;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;


/**
 * Actors leaving in a grid
 */
public abstract class AreaEntity extends Entity implements Interactable{

    /// an AreaEntity knows its own Area
    private Area ownerArea;
    /// Orientation of the AreaEntity in the Area
    private Orientation orientation;
    // / Coordinate of the main Cell linked to the entity
    private DiscreteCoordinates currentMainCellCoordinates;
    private Dialog dialog;

    /**
     * Default AreaEntity constructor
     * @param area (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public AreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {

        super(position.toVector());

        ownerArea = area;
        this.orientation = orientation;
        currentMainCellCoordinates = position;
    }

    public AreaEntity( Area area, DiscreteCoordinates position) {
        super(position.toVector());
        this.ownerArea = area;
        this.currentMainCellCoordinates = position;
        orientation = Orientation.DOWN;
    }

    public AreaEntity(DiscreteCoordinates position, Area ownerArea, Orientation orientation, String dialog) {
        super(position.toVector());
        this.ownerArea = ownerArea;
        this.orientation = orientation;
        this.currentMainCellCoordinates = position;
        this.dialog = new Dialog(dialog,ownerArea.getTitle(),ownerArea);
    }

    public AreaEntity(DiscreteCoordinates position, Area ownerArea, String dialog) {
        super(position.toVector());
        this.ownerArea = ownerArea;
        this.currentMainCellCoordinates = position;
        this.dialog = new Dialog(dialog,ownerArea.getTitle(),ownerArea);
        orientation = Orientation.DOWN;
    }

    /**
     * Getter for the coordinates of the main cell occupied by the AreaEntity
     * @return (DiscreteCoordinates)
     */
    protected DiscreteCoordinates getCurrentMainCellCoordinates(){
        return currentMainCellCoordinates;
    }

    protected Orientation getOrientation(){
        return orientation;
    }

    protected void setOrientation(Orientation orientation){
        this.orientation=orientation;
    }

    protected void setCurrentPosition(Vector v){
        if(DiscreteCoordinates.isCoordinates(v)) {
            super.setCurrentPosition(v.round());
            currentMainCellCoordinates = new DiscreteCoordinates((int)v.round().x , (int)v.round().y);
        }else{
            super.setCurrentPosition(v);
        }
    }
    protected Area getOwnerArea(){
        return ownerArea;
    }

    protected void setOwnerArea(Area ownerArea) {
        this.ownerArea = ownerArea;
    }

    protected Dialog getDialog(){
        return dialog;
    }
}
