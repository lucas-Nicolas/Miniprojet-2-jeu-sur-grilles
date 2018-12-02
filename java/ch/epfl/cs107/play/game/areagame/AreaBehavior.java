package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;
import javafx.scene.control.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * AreaBehavior manages a map of Cells.
 */
public abstract class AreaBehavior
{

    /// The behavior is an Image of size height x width
    // TODO implements me #PROJECT #TUTO
    private final Image behaviorMap;
    private final int width, height;
    private final Cell[][] cells;
    private Set<Interactable> contenue;

    /**
     * Default AreaBehavior Constructor
     * @param window (Window): graphic context, not null
     * @param fileName (String): name of the file containing the behavior image, not null
     */
    public AreaBehavior(Window window, String fileName){
        // TODO implements me #PROJECT #TUTO
        behaviorMap= window.getImage(ResourcePath.getBehaviors(fileName), null, false);
        width = behaviorMap.getWidth();
        height = behaviorMap.getHeight();
        cells = new Cell[width][height];

    }

    // TODO implements me #PROJECT #TUTO
    /** * Each game will have its own Cell extension. */
    public abstract class Cell implements Interactable {
        private DiscreteCoordinates coordone;
        public Cell(int x, int y){
            coordone = new DiscreteCoordinates(x,y);
        }

        public List<DiscreteCoordinates> getCurrentCells() {
            // todo je suis pas sure du tout a revoir avec Lucas.
            ArrayList<DiscreteCoordinates> coor = new ArrayList<DiscreteCoordinates>();
            coor.add(coordone);
            return coor;
        };
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getBehaviorMap() {
        return behaviorMap;
    }
}
