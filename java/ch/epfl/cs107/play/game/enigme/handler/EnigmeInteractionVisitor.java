package ch.epfl.cs107.play.game.enigme.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.enigme.EnigmeBehavior;
import ch.epfl.cs107.play.game.enigme.actor.Apple;
import ch.epfl.cs107.play.game.enigme.actor.Door;

public interface  EnigmeInteractionVisitor extends AreaInteractionVisitor {

    default void interactWith(Apple apple) { }
    default void interactWith(Door door) {}
    default void interactWith(EnigmeBehavior.EnigmeCell enigmeCell){}
    //default void interactWith(PressurePlate plate) { }
    //default void interactWith(Rock rock) { }
}
