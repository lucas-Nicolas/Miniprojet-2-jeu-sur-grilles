package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public class Enigme1 extends EnigmeArea {
    private final String title = "Enigme1";
    private final float scaleFactor = 15;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {

        return super.begin(window, fileSystem);
    }

    @Override
    public void setCameraScaleFactor() {
        super.setCameraScaleFactor(scaleFactor);
    }
}
