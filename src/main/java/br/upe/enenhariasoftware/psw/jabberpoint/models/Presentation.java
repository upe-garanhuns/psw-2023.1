/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software
 * Disciplina de Projeto de Software - 2023.1
 * <p>
 * Licensed under the Apache License, Version 2.0
 * https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.models;

import java.util.ArrayList;

public class Presentation {
	private String title;
	private ArrayList<Slide> showList = null;
	private int currentSlideNumber = 0;

	public Presentation() {
		clear();
	}

	public int getSize() {
		return showList.size();
	}

	public void prevSlide() {
		if (currentSlideNumber > 0) {
			setCurrentSlideNumber(currentSlideNumber - 1);
		}
	}

	public void nextSlide() {
		if (currentSlideNumber < (showList.size() - 1)) {
			setCurrentSlideNumber(currentSlideNumber + 1);
		}
	}

	public void clear() {
		showList = new ArrayList<>();
		setCurrentSlideNumber(-1);
	}

	public void append(Slide slide) {
		showList.add(slide);
	}

	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()) {
			return null;
		}
		return showList.get(number);
	}

	public Slide getCurrentSlide() {
		return getSlide(currentSlideNumber);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCurrentSlideNumber() {
		return currentSlideNumber;
	}

	public void setCurrentSlideNumber(int number) {
		this.currentSlideNumber = number;
	}

}
