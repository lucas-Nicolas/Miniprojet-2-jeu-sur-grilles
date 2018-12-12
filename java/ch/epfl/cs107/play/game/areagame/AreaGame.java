package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

import javax.swing.undo.CannotUndoException;
import java.util.HashMap;
import java.util.Map;


/**
 * AreaGame is a type of Game displayed in a (MxN) Grid called Area
 * An AreaGame has multiple Areas
 */
abstract public class AreaGame implements Game {

    // Context objects
    private Window window;
    private FileSystem fileSystem;
    private Map<String, Area> areas;
    private Area currentArea;
    /**
     * Add an Area to the AreaGame list
     * @param a (Area): The area to add, not null
     */
    protected final void addArea(Area a){
        areas.put(a.getTitle(), a);
    }

    /**
     * Setter for the current area: Select an Area in the list from its key
     * - the area is then begin or resume depending if the area is already started or not and if it is forced
     * @param key (String): Key of the Area to select, not null
     * @param forceBegin (boolean): force the key area to call begin even if it was already started
     * @return (Area): after setting it, return the new current area
     */
    protected final Area setCurrentArea(String key, boolean forceBegin){
        String name=null;
        if(currentArea!=null){
            currentArea.suspend();
            name = currentArea.getTitle();
        }
        currentArea = areas.get(key);
        if (currentArea!=null){
            if(!currentArea.isSavedGame()||forceBegin){
                currentArea.begin(window, fileSystem);
            }else{
                currentArea.resume(window, fileSystem);
            }
        }else{
            currentArea= areas.get(name);
            if(currentArea==null){
                //because we can not undo the change we throw this exeption
                throw new CannotUndoException();
            }
        }
        return null;
    }


    /**@return (Window) : the Graphic and Audio context*/
    protected final Window getWindow(){

        return window;
    }

    /**@return (FIleSystem): the linked file system*/
    protected final FileSystem getFileSystem(){

        return fileSystem;
    }


    /// AreaGame implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        this.fileSystem = fileSystem;
        this.window = window;
        areas = new HashMap<>();
        return true;
    }


    protected Area getCurrentArea() {
        return currentArea;
    }

    @Override
    public void update(float deltaTime) {
        currentArea.update(deltaTime);
    }

    @Override
    public void end() {
    }
    protected Map<String, Area> getAreas(){
        return areas;
    }

}
