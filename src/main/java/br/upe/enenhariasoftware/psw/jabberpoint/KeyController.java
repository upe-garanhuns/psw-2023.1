/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyController extends KeyAdapter {
	private final Presentation presentation;
	public KeyController(Presentation p) {
		presentation = p;
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		switch (keyEvent.getKeyCode()) {
			case KeyEvent.VK_PAGE_DOWN, KeyEvent.VK_DOWN, KeyEvent.VK_ENTER, '+' -> presentation.nextSlide();
			case KeyEvent.VK_PAGE_UP, KeyEvent.VK_UP, '-' -> presentation.prevSlide();
			case 'q', 'Q' -> System.exit(0);
			default -> {
				break;
			}
		}
	}
}
