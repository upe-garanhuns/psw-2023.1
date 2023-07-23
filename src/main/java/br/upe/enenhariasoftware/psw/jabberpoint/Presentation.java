/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 * 
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 * 
 * @author Ian F. Darwin, Helaine Lins
 */

package br.upe.enenhariasoftware.psw.jabberpoint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Presentation implements Serializable {

  private static final long serialVersionUID = 197L;

  private String title;
  private List<Slide> slides = null;
  private SlideViewerComponent slideViewComponent = null;
  private int currentSlideNumber = 0;

  public Presentation() {
    slideViewComponent = null;
    clear();
  }

  public Presentation(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
    clear();
  }

  public int getSize() {
    return slides.size();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String nt) {
    title = nt;
  }

  public void setShowView(SlideViewerComponent slideViewerComponent) {
    this.slideViewComponent = slideViewerComponent;
  }

  public int getSlideNumber() {
    return currentSlideNumber;
  }

  public void setSlideNumber(int number) {
    currentSlideNumber = number;
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
    if (currentSlideNumber < (slides.size() - 1)) {
      setSlideNumber(currentSlideNumber + 1);
    }
  }

  void clear() {
    slides = new ArrayList<>();
    setSlideNumber(-1);
  }

  public void append(Slide slide) {
    slides.add(slide);
  }

  public Slide getSlide(int number) {
    if (number < 0 || number >= getSize()) {
      return null;
    }
    return slides.get(number);
  }

  public Slide getCurrentSlide() {
    return getSlide(currentSlideNumber);
  }

  public void exit(int n) {
    System.exit(n);
  }
}
