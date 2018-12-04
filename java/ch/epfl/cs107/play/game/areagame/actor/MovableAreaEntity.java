package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * MovableAreaEntity are AreaEntity able to move on a grid
 */
public abstract class MovableAreaEntity extends AreaEntity {

    // Indicate how many frames the current move is supposed to take
    private int framesForCurrentMove;
    /// Indicate if the actor is currently isMoving
    private boolean isMoving;
    // The target cell (i.e. where the mainCell will be after the motion)
    private DiscreteCoordinates targetMainCellCoordinates;

    // TODO implements me #PROJECT #TUTO
    protected final List<DiscreteCoordinates> getLeavingCells() {
        return getCurrentCells();
    }

    ;

    protected final List<DiscreteCoordinates> getEnteringCells() {
        List<DiscreteCoordinates> coord = getCurrentCells();
        List<DiscreteCoordinates> entering = new LinkedList<>();
        for (int i = 0; i < coord.size(); i++) {
            entering.add(coord.get(i).jump(getOrientation().toVector()));
        }
        return entering;
    }

    ;


    /**
     * Default MovableAreaEntity constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param position    (Coordinate): Initial position of the entity. Not null
     * @param orientation (Orientation): Initial orientation of the entity. Not null
     */
    public MovableAreaEntity(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        // TODO implements me #PROJECT #TUTO
        resetMotion();
    }

    public MovableAreaEntity(Area area, DiscreteCoordinates position){
        super(area, position);
        resetMotion();
    }

    /**
     * Initialize or reset the current motion information
     */
    protected void resetMotion() {
        isMoving = false;
        framesForCurrentMove = 0;
        targetMainCellCoordinates = getCurrentMainCellCoordinates();
    }

    /**
     * @param "frameForMove (int): number of frames used for simulating motion
     * @return (boolean): returns true if motion can occur
     */

    protected boolean move(int framesForMove) {
        // TODO implements me #PROJECT #TUTO
        if (!isMoving || getCurrentMainCellCoordinates() == targetMainCellCoordinates) {
            if (getOwnerArea().leaveAreaCells(this,getLeavingCells()) && getOwnerArea().enterAreaCells(this,getEnteringCells())) {
                framesForCurrentMove = framesForMove;
                if (framesForCurrentMove > 1) {
                    framesForCurrentMove = 1;
                }
                Vector orientation = getOrientation().toVector();
                targetMainCellCoordinates = getCurrentMainCellCoordinates().jump(orientation);
                isMoving = true;
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }



    /// MovableAreaEntity implements Actor

    @Override
    public void update(float deltaTime) {
        // TODO implements me #PROJECT #TUTO
        if((isMoving) && (getCurrentMainCellCoordinates()!=targetMainCellCoordinates)){
            Vector distance = getOrientation().toVector();
            distance = distance.mul(1.0f / framesForCurrentMove);
            setCurrentPosition(getPosition().add(distance));
            resetMotion();
            //todo sans le reset Motion sa part a l'inifinie
            }else{
            resetMotion();
        }
    }

    /// Implements Positionable

    @Override
    public Vector getVelocity() {
        // TODO implements me #PROJECT #TUTO
        // the velocity must be computed as the orientation vector (getOrientation().toVector() mutiplied by 
    	// framesForCurrentMove
        Vector distance = getOrientation().toVector();
        distance = distance.mul(framesForCurrentMove);
        return distance;
    }

    @Override
    protected void setOrientation(Orientation orientation) {
        if(!isMoving){
            super.setOrientation(orientation);
        }
    }


}
