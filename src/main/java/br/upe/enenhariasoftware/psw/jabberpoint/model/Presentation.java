/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.io.Serializable;
import java.util.ArrayList;

import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideViewerComponent;

public class Presentation implements Serializable {

  private String title;
  private ArrayList<Slide> slideList;
  private SlideViewerComponent slideViewComponent;
  private int currentSlideNumber = 0;
  private static final long serialVersionUID = 221L;

  public Presentation() {
    clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }

  public int getSize() {
    return this.slideList.size();
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String newTitle) {
    this.title = newTitle;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSlideNumber() {
    return this.currentSlideNumber;
  }

  public void setSlideNumber(int slideNumber) {
    currentSlideNumber = slideNumber;
    if (slideViewComponent != null) {
      slideViewComponent.update(this, getCurrentSlide());
    }
  }

  public void prevSlide() {
    if (currentSlideNumber > 0) {
      setSlideNumber(currentSlideNumber - 1);
    }
  }

  public void nextSlide() {
    if (currentSlideNumber < (slideList.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  public void clear() {
    slideList = new ArrayList<>();
    setSlideNumber(-1);
  }

  public void append(Slide newSlide) {
    slideList.add(newSlide);
  }

  public Slide getSlide(int slideNumber) {
    return (slideNumber >= 0 && slideNumber < getSize()) ? slideList.get(slideNumber) : null;
  }

  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  public void exit(int n) {
    System.exit(n);
  }
}