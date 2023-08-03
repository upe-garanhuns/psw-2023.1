/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint;

import java.io.IOException;

public abstract class Accessor {

  protected Accessor() {}

  public static Accessor getDemoAccessor() {
    return new DemoPresentation();
  }

  public abstract void loadFile(Presentation presentation, String fileName) throws IOException;

}
