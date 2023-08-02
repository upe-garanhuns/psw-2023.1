package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.io.IOException;

public interface Savable {
    default void saveFile(Presentation presentation, String fileName) throws IOException {
    }
}
