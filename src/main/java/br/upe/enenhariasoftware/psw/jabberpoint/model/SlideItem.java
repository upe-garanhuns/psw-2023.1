/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

public abstract class SlideItem {

	private int level = 0;

	protected SlideItem(int lev) {
		level = lev;
	}

	protected SlideItem() {
		this(0);
	}

	public int getLevel() {
		return level;
	}

}