package Controller;

import javafx.beans.NamedArg;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.scene.input.InputEvent;

/**
 * Created by User on 22.11.2017.
 */
public class Mouseevent extends InputEvent {
    public Mouseevent(@NamedArg("source") Object source, @NamedArg("target") EventTarget target, @NamedArg("eventType") EventType<? extends InputEvent> eventType) {
        super(source, target, eventType);
    }
}
