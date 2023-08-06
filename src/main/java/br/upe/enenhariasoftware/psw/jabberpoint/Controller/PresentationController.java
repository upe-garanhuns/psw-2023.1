package br.upe.enenhariasoftware.psw.jabberpoint.Controller;

import br.upe.enenhariasoftware.psw.jabberpoint.Model.PresentationModel;

public class PresentationController {

  private PresentationModel presentation;

  public PresentationController(PresentationModel presentation) {
    this.presentation = presentation;
  }

  public PresentationModel getPresentation() {
    return presentation;
  }

  public void setPresentation(PresentationModel presentation) {
    this.presentation = presentation;
  }

}
