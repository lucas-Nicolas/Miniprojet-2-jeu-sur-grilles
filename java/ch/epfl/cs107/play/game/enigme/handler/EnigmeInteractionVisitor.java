package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.enigme.actor.HealthPotion;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.*;

public interface  EnigmeInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(Apple apple) { }
    default void interactWith(Door door) {}
    default void interactWith(EnigmeBehavior.EnigmeCell enigmeCell){}
    default void interactWith(PressurePlate plate) {}
    default void interactWith(EnigmePlayer player){}
    default void interactWith(Key key){}
    default void interactWith(Switch bouton){}
    default void interactWith(MovableRock movableRock){}
    default void interactWith(HealthPotion potion){}
    default void interactWith(Safe safe) {}
}
