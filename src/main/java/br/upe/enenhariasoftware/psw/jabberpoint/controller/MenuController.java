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

import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.IPresentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.AboutView;

public class MenuController{

    private static final long serialVersionUID = 227L;

    private Frame parentFrame;
    private IPresentation presentation;

    protected static final String PAGENR = "Slide number";
    protected static final String TESTFILE = "classpath:test.xml";
    protected static final String SAVEFILE = "classpath:dump.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Failed to load";
    protected static final String SAVEERR = "Failed to save";

    public MenuController(Frame parentFrame, IPresentation presentation) {
        this.parentFrame = parentFrame;
        this.presentation = presentation;
    }

    public void openFile(){
        presentation.clear();
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.loadFile(presentation, new File(TESTFILE).getAbsolutePath());
            presentation.setSlideNumber(0);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parentFrame, IOEX + exc, LOADERR, JOptionPane.ERROR_MESSAGE);
        }
        parentFrame.repaint();
    }

    public void clearPresentation() {
        presentation.clear();
        parentFrame.repaint();
    }

    public void saveFile(){
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parentFrame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    public void nextSlide(){
        presentation.nextSlide();
    }

    public void previousSlide(){
        presentation.previousSlide();
    }

    public void goToSlide(){
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }

    public void about(){
        AboutView.show(parentFrame);
    }

    public void exit(){
        System.exit(0);
    }
}