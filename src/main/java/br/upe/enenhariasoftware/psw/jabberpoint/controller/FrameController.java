package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;

import javax.swing.*;
import java.io.IOException;

public class FrameController {

    public FrameController(Presentation presentation) {
    }

    public void windowClosing() {
        System.exit(0);
    }

    public void showExceptionMessage(IOException ex) {
        JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ",
                JOptionPane.ERROR_MESSAGE);
    }
}
