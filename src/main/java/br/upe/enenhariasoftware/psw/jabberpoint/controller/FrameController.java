package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;

public class FrameController {

    public FrameController(Presentation presentation) {
    }

    public void windowClosing() {
        System.exit(0);
    }
}
