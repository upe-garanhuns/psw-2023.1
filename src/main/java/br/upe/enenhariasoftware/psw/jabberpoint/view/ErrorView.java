package br.upe.enenhariasoftware.psw.jabberpoint.view;

import javax.swing.*;
import java.io.IOException;

public class ErrorView {

    private ErrorView() {
        /*
         * Construtor privado para ocultar o construtor público implícito e evitar que outras classes
         * façam instâncias indesejadas da classe ErrorView.
         */
    }

    public static void exceptionMessage(IOException ex) {
        JOptionPane.showMessageDialog(null, "IO Error: " + ex, "Jabberpoint Error ",
                JOptionPane.ERROR_MESSAGE);
    }
}
