package ch.epfl.cs107.play.game.enigme;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.demo2.Demo2Player;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room0;
import ch.epfl.cs107.play.game.enigme.area.demo2.Room1;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class Demo2 extends AreaGame {
    private Demo2Player player;
    private final int SCALE_FACTOR = 22;
    @Override
    public int getFrameRate() {
        return 24;
    }

    @Override
    public String getTitle() {
        return "Demo2";
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if(super.begin(window, fileSystem)){
            Area room0 = new Room0();
            Area room1 = new Room1();

            addArea(room0);
            addArea(room1);
            setCurrentArea("LevelSelector", true);
            player = new Demo2Player(room0,new DiscreteCoordinates(5,5));
            getCurrentArea().registerActor(player);
            getCurrentArea().setViewCandidate(player);
            return true;
        }
        return false;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if(player.isGoingThroughDoor()){
            if(getCurrentArea().getTitle().equals("LevelSelector")){
                setCurrentArea("Level1",false);
                player.enterArea(getCurrentArea(), new DiscreteCoordinates(5,2));
                getCurrentArea().setViewCandidate(player);

            }else if(getCurrentArea().getTitle().equals("Level1")){
                setCurrentArea("LevelSelector",false);
                player.enterArea(getCurrentArea(), new DiscreteCoordinates(5,5));
                getCurrentArea().setViewCandidate(player);
            }
        }




    }
}
