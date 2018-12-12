package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.*;
import ch.epfl.cs107.play.window.Window;

import javax.xml.ws.handler.LogicalHandler;
import java.util.LinkedHashSet;
import java.util.Set;

public class Tuto extends EnigmeArea {
    private final String title = "Tuto";

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin = super.begin(window, fileSystem);

        //create Door and the Key+Plate to the Door
        Key keyDoor =  new Key(this, new DiscreteCoordinates(1, 3));
        begin = begin && registerActor(keyDoor);
        PressurePlate plate = new PressurePlate(this, new DiscreteCoordinates(17, 6));
        begin = begin && registerActor(plate);
        Logic openDoor = new And(keyDoor, plate);
        begin = begin && registerActor(new SignalDoor(openDoor, this, "LevelSelector", new DiscreteCoordinates(4, 5), Orientation.DOWN, new DiscreteCoordinates(10, 17)));
        begin = begin &&  registerActor(new MovableRock(this, new DiscreteCoordinates(14,4)));
        begin = begin && registerActor(new HealthPotion(this,new DiscreteCoordinates(18,7)));


        //First rock with PressureSwithch's
        PressureSwitch p1= new PressureSwitch(this, new DiscreteCoordinates(4, 4));
        PressureSwitch p2= new PressureSwitch(this, new DiscreteCoordinates(5, 4));
        PressureSwitch p3= new PressureSwitch(this, new DiscreteCoordinates(6, 4));
        PressureSwitch p4= new PressureSwitch(this, new DiscreteCoordinates(5, 5));
        PressureSwitch p5= new PressureSwitch(this, new DiscreteCoordinates(4, 6));
        PressureSwitch p6= new PressureSwitch(this, new DiscreteCoordinates(5, 6));
        PressureSwitch p7= new PressureSwitch(this, new DiscreteCoordinates(6, 6));
        registerActor(p1,p2,p3,p4,p5,p6,p7);
        Logic rock2Password = new MultipleAnd(p1,p2,p3,p4,p5,p6,p7);
        registerActor(new SignalRock(rock2Password,this,new DiscreteCoordinates(5,8)));

        //Second rock with lever or Torch
        Lever l1= new Lever(this, new DiscreteCoordinates(12, 5));
        Lever l2= new Lever(this, new DiscreteCoordinates(11, 5));
        Lever l3= new Lever(this, new DiscreteCoordinates(10, 5));
        Set<Logic> e = new LinkedHashSet<>();
        e.add(l1);
        e.add(l2);
        e.add(l3);
        Logic leverPass =new LogicNumber(5,e);
        Torch torch= new TorchAnimated(this, new DiscreteCoordinates(9, 5), false);
        registerActor(l1,l2,l3,torch);
        Or rock3Password = new Or(leverPass,torch);
        registerActor(new SignalRock(rock3Password,this, new DiscreteCoordinates(10,8)));

        //create the safe and his key
        Key keySafe =  new Key(this, new DiscreteCoordinates(6, 17));
        registerActor(keySafe);
        Safe safe =new Safe(this,new DiscreteCoordinates(8,17),keySafe);
        HealthPotion healthPotion = new HealthPotion(this,new DiscreteCoordinates(7,17), safe);

        registerActor(safe,healthPotion);




        return begin;
    }

    public String getTitle() {
        return title;
    }
}
