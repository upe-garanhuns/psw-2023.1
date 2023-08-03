package br.upe.enenhariasoftware.psw.jabberpoint.views;

import br.upe.enenhariasoftware.psw.jabberpoint.controllers.SlideViewerFrame;

import javax.swing.*;
import java.io.IOException;

public class ErrorViewer {
    private ErrorViewer (){
    }
    public static void showIOException(IOException exception, SlideViewerFrame frame, String message, String title){
        JOptionPane.showMessageDialog(frame, message + " " + exception, title, JOptionPane.ERROR_MESSAGE);
    }
}
