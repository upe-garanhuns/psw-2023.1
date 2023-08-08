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

import br.upe.enenhariasoftware.psw.jabberpoint.model.SlideModel;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideView;

public class SlideController {

    private SlideModel slideModel;
    private SlideView slideView;

    public SlideController(SlideModel slideModel, SlideView slideView) {
        this.slideModel = slideModel;
        this.slideView = slideView;
    }

    public SlideView getSlideView() {
        return slideView;
    }

    public SlideModel getSlideModel() {
        return slideModel;
    }
}
