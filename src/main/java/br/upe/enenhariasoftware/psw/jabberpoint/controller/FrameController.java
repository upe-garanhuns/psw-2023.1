package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class FrameController {

    public FrameController(Presentation presentation) {
    }

    public void windowClosing() {
        System.exit(0);
    }

    public KeyController createKeyController(Presentation presentation) {
        return new KeyController(presentation);
    }

    public MenuView createMenuControllerMenuBar(Frame frame, Presentation presentation) {
        return new MenuView(new MenuController(frame, presentation));
    }
}
