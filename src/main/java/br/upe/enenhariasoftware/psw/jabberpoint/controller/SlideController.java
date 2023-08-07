package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideModel;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideView;

public class SlideController {

	private SlideModel slideModel;
	private SlideView slideView;

	public SlideController() {

	}

	public SlideController(SlideModel slide) {
		this.slideModel = slide;
		slideView = new SlideView(slide);
	}

	public SlideView getSlideView() {
		return slideView;
	}

	public void setSlideView(SlideView slideView) {
		this.slideView = slideView;
	}

	public SlideModel getSlide() {
		return slideModel;
	}

	public void setSlide(SlideModel slide) {
		this.slideModel = slide;
	}

}