package ch.epfl.cs107.play.game.enigme.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.enigme.actor.*;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.LogicNumber;
import ch.epfl.cs107.play.signal.logic.MultipleAnd;
import ch.epfl.cs107.play.window.Window;

import java.util.LinkedHashSet;
import java.util.Set;

public class Level4 extends EnigmeArea {
    private final String title = "Level4";

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        boolean begin = super.begin(window, fileSystem);
        //porte de retour
        registerActor(new Door(this,"LevelSelector",new DiscreteCoordinates(4,7), Orientation.DOWN,new DiscreteCoordinates(11,29),new DiscreteCoordinates(12,29)));

        //keys
        Key keyToDoor1 = new Key(this,new DiscreteCoordinates(6,8));
        Key keyOfDoorIsland = new Key(this, new DiscreteCoordinates(14,13));
        Key keyToHeaven = new Key(this, new DiscreteCoordinates(25,20));



        //Switches
        Lever lever1 = new Lever(this,new DiscreteCoordinates(25,5));
        Lever lever2 = new Lever(this,new DiscreteCoordinates(25,4));
        Lever lever3 = new Lever(this,new DiscreteCoordinates(25,3));
        Lever lever4 = new Lever(this,new DiscreteCoordinates(25,2));
        Set<Logic> accesIsland = new LinkedHashSet<>();
        accesIsland.add(lever4);
        accesIsland.add(lever3);
        accesIsland.add(lever2);
        accesIsland.add(lever1);
        LogicNumber keyToIslandRock = new LogicNumber(7,accesIsland);

        PressurePlate plateToSignalRock = new PressurePlate(this,new DiscreteCoordinates(27,7));
        PressurePlate fakePlate = new PressurePlate(this,new DiscreteCoordinates(27,7)) ;
        PressurePlate  plateToDoor2= new PressurePlate(this, new DiscreteCoordinates(1,28));
        PressurePlate lastPlate = new PressurePlate(this,new DiscreteCoordinates(20,22));


        PressureSwitch pressureSwitch = new PressureSwitch(this , new DiscreteCoordinates(21,13));
        PressureSwitch pressureSwitch1 = new PressureSwitch(this, new DiscreteCoordinates(21,12));
        PressureSwitch pressureSwitch2 = new PressureSwitch(this , new DiscreteCoordinates(21,11));
        PressureSwitch pressureSwitch3= new PressureSwitch(this, new DiscreteCoordinates(23,13));
        PressureSwitch pressureSwitch4 = new PressureSwitch(this , new DiscreteCoordinates(23,12));
        PressureSwitch pressureSwitch5 = new PressureSwitch(this, new DiscreteCoordinates(23,11));
        PressureSwitch pressureSwitch6 = new PressureSwitch(this , new DiscreteCoordinates(24,13));
        PressureSwitch pressureSwitch7 = new PressureSwitch(this, new DiscreteCoordinates(24,11));

        MultipleAnd safeKey1 = new MultipleAnd(pressureSwitch,pressureSwitch1,pressureSwitch2,pressureSwitch3,pressureSwitch4,pressureSwitch6,pressureSwitch5,pressureSwitch7);

        //torch
        TorchAnimated torch = new TorchAnimated(this,new DiscreteCoordinates(3,28),false);

        //apples
        for (int i = 19; i <22 ; i++) {
            for (int j = 24; j <27 ; j++) {
                if (!(i == 20 && j == 25)) {
                    registerActor(new Apple(this, new DiscreteCoordinates(j, i)));
                }
            }
        
        }
        //SignalRock
        SignalRock signalRock1 = new SignalRock(keyToIslandRock,this,new DiscreteCoordinates(15,13));
        SignalRock signalRock2 = new SignalRock(plateToSignalRock,this,new DiscreteCoordinates(4,25));

        //movable Rocks

        MovableRock movableRock1 = new MovableRock(this,new DiscreteCoordinates(21,7));
        MovableRock movableRock2 = new MovableRock(this,new DiscreteCoordinates(21,6));
        MovableRock movableRock3 = new MovableRock(this,new DiscreteCoordinates(18,22));

        //safes
        Safe safe = new Safe(this , new DiscreteCoordinates(27, 13), safeKey1);
        Safe safe1 = new Safe(this, new DiscreteCoordinates(27,11),safeKey1);
        Safe safe2 = new Safe(this, new DiscreteCoordinates(4,28),torch);
        Safe safe3 = new Safe(this, new DiscreteCoordinates(17,24),torch);
        Safe safe4 = new Safe(this, new DiscreteCoordinates(28,28),keyToHeaven);

        //potions
        HealthPotion healthPotion1 = new HealthPotion(this,new DiscreteCoordinates(27,14),safe);
        HealthPotion healthPotion2 = new HealthPotion(this,new DiscreteCoordinates(27,12),safe);
        HealthPotion healthPotion3 = new HealthPotion(this,new DiscreteCoordinates(26,13),safe);
        HealthPotion healthPotion4 = new HealthPotion(this,new DiscreteCoordinates(26,10),safe1);
        HealthPotion healthPotion5 = new HealthPotion(this,new DiscreteCoordinates(27,10),safe1);

        HealthPotion healthPotion8 = new HealthPotion(this,new DiscreteCoordinates(4,28),safe2);
        HealthPotion healthPotion6 = new HealthPotion(this,new DiscreteCoordinates(3,27),safe2);
        HealthPotion healthPotion7 = new HealthPotion(this,new DiscreteCoordinates(4,26),safe2);

        Key keyToDoor3 = new Key(this,new DiscreteCoordinates(17,22),safe3);










        return begin && registerActor(keyToDoor1,keyToDoor3,signalRock1,signalRock2,lever1,lever2,lever3,lever4,
                plateToSignalRock, plateToDoor2,lastPlate,fakePlate,
                torch,
                pressureSwitch,pressureSwitch1,pressureSwitch2,pressureSwitch3,pressureSwitch4,pressureSwitch6,pressureSwitch5,pressureSwitch7,
                keyOfDoorIsland,keyToHeaven,
                healthPotion1,healthPotion2,healthPotion3,healthPotion4,healthPotion5,healthPotion6,healthPotion7,healthPotion8,
                safe,safe1,safe2,safe3,safe4,movableRock1,movableRock2,movableRock3,
                new SignalDoor(keyToDoor1,this,"Level4",new DiscreteCoordinates(9,5), Orientation.RIGHT,new DiscreteCoordinates(7,5)),
                new SignalDoor(keyOfDoorIsland,this,"LevelSelector",new DiscreteCoordinates(4,5), Orientation.DOWN,new DiscreteCoordinates(11,29)),
                new SignalDoor(keyOfDoorIsland,this,"LevelSelector",new DiscreteCoordinates(4,5), Orientation.DOWN,new DiscreteCoordinates(12,29)),
                new SignalDoor(lastPlate,this,"Level4",new DiscreteCoordinates(2,27),Orientation.UP, new DiscreteCoordinates(16,22)),
                new SignalDoor(plateToDoor2,this,"Level4",new DiscreteCoordinates(16,23),Orientation.UP, new DiscreteCoordinates(2,28)),
                new SignalDoor(keyToDoor3,this,"Level4",new DiscreteCoordinates(21,28),Orientation.UP, new DiscreteCoordinates(17,25)),
                new SignalDoor(keyToDoor3,this,"Level4",new DiscreteCoordinates(17,24),Orientation.UP, new DiscreteCoordinates(21,27)),
                new SignalDoor(safe4,this,"Level4",new DiscreteCoordinates(12,28),Orientation.UP, new DiscreteCoordinates(29,28)));
    }
}
