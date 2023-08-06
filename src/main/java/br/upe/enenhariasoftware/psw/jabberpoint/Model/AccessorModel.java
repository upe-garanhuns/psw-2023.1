/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint.Model;

import java.io.IOException;

public abstract class AccessorModel {

  public static final String DEMO_NAME = "Demo presentation";
  public static final String DEFAULT_EXTENSION = ".xml";

  public static AccessorModel getDemoAccessor() {
    return new DemoPresentationModel();
  }

  protected AccessorModel() {}

  public abstract void loadFile(PresentationModel presentation, String fileName) throws IOException;

  public abstract void saveFile(PresentationModel presentation, String fileName) throws IOException;

}
