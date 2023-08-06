package br.upe.enenhariasoftware.psw.jabberpoint.model;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;

import java.io.IOException;

public interface Accessible {

    public abstract void loadFile(Presentation presentation, String fileName) throws IOException;

    public abstract void saveFile(Presentation presentation, String fileName) throws IOException;

}