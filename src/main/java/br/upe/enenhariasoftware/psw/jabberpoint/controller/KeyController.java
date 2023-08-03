/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;

public class KeyController extends KeyAdapter {

  //Ela é controller
  //porém acessa diretamente a "presentation", usando os métodos nextSlide e prevSlide
  // acredito que esteja certo, pois ele pega recursos do model para manipular os dados recebidos.
  private Presentation presentation;

  public KeyController(Presentation p) {
    presentation = p;
  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    switch (keyEvent.getKeyCode()) {
      case KeyEvent.VK_PAGE_DOWN:
      case KeyEvent.VK_DOWN:
      case KeyEvent.VK_ENTER:
      case '+':
        presentation.nextSlide();
        break;
      case KeyEvent.VK_PAGE_UP:
      case KeyEvent.VK_UP:
      case '-':
        presentation.prevSlide();
        break;
      case 'q':
      case 'Q':
        System.exit(0);
        break; // fix?
      default:
        break;
    }
  }

}