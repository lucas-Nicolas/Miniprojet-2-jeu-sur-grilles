package ch.epfl.cs107.play.game.demo1;


import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.actor.GraphicsEntity;
import ch.epfl.cs107.play.game.actor.ShapeGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.actor.Text;
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
    private MovingRock movingRock;
    private final TextGraphics boum = new TextGraphics("BOUM !!!", 0.1f, Color.RED);




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
            movingRock.update(deltaTime);
        }


        //draw
        actor1.draw(window);
        movingRock.draw(window);
        movingRock.getText().draw(window);


        //on affiche boum si la distance entre le centre du rocher et celui du cercle est inférieure à la somme de leurs rayons respectifs
        if (distanceFromMovingRock(movingRock,actor1 )< 0.2f+0.05){
            boum.draw(window);
        }
    }
    public boolean begin(Window window, FileSystem fileSystem){
        Vector pos = new Vector(0.2f,0.1f);
        float radius= 0.2f;
        this.fileSystem = fileSystem;
        this.window = window;
        actor1 = new GraphicsEntity(Vector.ZERO,
                new ShapeGraphics(new Circle(radius), null,
                        Color.RED, 0.005f));
        movingRock = new MovingRock(pos,"Hello, I am a moving rock");

        return true;}

    public double distanceFromMovingRock(MovingRock a, Actor b){
        return  Math.sqrt(Math.pow(a.getPosition().x+0.05-b.getPosition().x,2)+Math.pow(a.getPosition().y +0.05- b.getPosition().y,2));
    }

}