package ch.epfl.cs107.play.game.demo1;

import ch.epfl.cs107.play.game.*;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.demo1.actor.MovingRock;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.math.Circle;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.swing.SwingWindow;

import java.awt.*;


public class Demo1 implements Game {
    private Window window;
    private  FileSystem fileSystem;
    private Actor actor1;
    private MovingRock actor2;



    public String getTitle(){
        return "Demo1";
    }
    public int getFrameRate(){
        return 24;
    }
    public void end(){}
    public void update(float deltaTime){
        //donner une vie imaginaire au cercle(le mettre à jour)
        Keyboard keyboard = window.getKeyboard();
        Button downArrow = keyboard.get(Keyboard.DOWN);
        if(downArrow.isDown()){
            actor2.update(deltaTime);
        }


        //draw
        actor1.draw(window);
        actor2.draw(window);
        actor2.getText().draw(window);
    }
    public boolean begin(Window window, FileSystem fileSystem){
        Vector pos = new Vector(0.3f,0.1f);
        float radius= 0.2f;
        this.fileSystem = fileSystem;
        this.window = window;
        actor1 = new GraphicsEntity(Vector.ZERO,
                new ShapeGraphics(new Circle(radius), null,
                        Color.RED, 0.005f));
        actor2 = new MovingRock(pos,"Hello, I am a moving rock");

        return true;}

}