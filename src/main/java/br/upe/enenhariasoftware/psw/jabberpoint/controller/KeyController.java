/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import br.upe.enenhariasoftware.psw.jabberpoint.model.PresentationModel;

public class KeyController extends KeyAdapter {

    private PresentationModel presentationModel;

    public KeyController(PresentationModel presentationModel) {
        this.presentationModel = presentationModel;
    }

    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_PAGE_DOWN:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_ENTER:
            case '+':
                presentationModel.nextSlide();
                break;
            case KeyEvent.VK_PAGE_UP:
            case KeyEvent.VK_UP:
            case '-':
                presentationModel.prevSlide();
                break;
            case 'q':
            case 'Q':
                System.exit(0);
                break;
            default:
                break;
        }
    }
}
