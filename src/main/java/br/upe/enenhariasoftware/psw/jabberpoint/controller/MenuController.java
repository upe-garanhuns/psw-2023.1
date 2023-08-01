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
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

import br.upe.enenhariasoftware.psw.jabberpoint.model.Accessor;
import br.upe.enenhariasoftware.psw.jabberpoint.model.Presentation;
import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.About;

public class MenuController extends MenuBar {

    private static final long serialVersionUID = 227L;

    private Frame parentFrame;
    private Presentation presentation;

    protected static final String ABOUT = "About";
    protected static final String FILE = "File";
    protected static final String EXIT = "Exit";
    protected static final String GOTO = "Go to";
    protected static final String HELP = "Help";
    protected static final String NEW = "New";
    protected static final String NEXT = "Next";
    protected static final String OPEN = "Open";
    protected static final String PAGENR = "Slide number";
    protected static final String PREV = "Previous";
    protected static final String SAVE = "Save";
    protected static final String VIEW = "View";
    protected static final String TESTFILE = "classpath:test.xml";
    protected static final String SAVEFILE = "classpath:dump.xml";
    protected static final String IOEX = "IO Exception: ";
    protected static final String LOADERR = "Failed to load";
    protected static final String SAVEERR = "Failed to save";

    // Aqui é controller? Por mim é, mas com dúvidas
    public MenuController(Frame parentFrame, Presentation presentation) {
        this.parentFrame = parentFrame;
        this.presentation = presentation;

        fileMenu();

        viewMenu();

        helpMenu();

    }

    // Eu fiquei com dúvida aqui
    // Apesar de estar na cara que ele é controller pq ele
    // pega a ação do ActionListener e chama o método, ele
    // instancia o menu e adiciona os menuItem, e eu penso
    // que isso é view, mas não tenho nenhuma certeza
    private void fileMenu(){
        Menu fileMenu = new Menu(FILE);

        fileMenu.add(menuItem(OPEN, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                openFile();
            }
        }));

        fileMenu.add(menuItem(NEW, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                clearPresentation();
            }
        }));

        fileMenu.add(menuItem(SAVE, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                saveFile();
            }
        }));

        fileMenu.addSeparator();

        fileMenu.add(menuItem(EXIT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                exit();
            }
        }));

        add(fileMenu);
    }

    // Considero o mesmo do fileMenu()
    private void viewMenu(){
        Menu viewMenu = new Menu(VIEW);

        viewMenu.add(menuItem(NEXT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                nextSlide();
            }
        }));

        viewMenu.add(menuItem(PREV, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                previousSlide();
            }
        }));

        viewMenu.add(menuItem(GOTO, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                goToSlide();
            }
        }));

        add(viewMenu);
    }

    // Conidero o mesmo do anterior
    // Eu realmente não sei se podia separar as responsabilidades
    // desses métodos
    private void helpMenu(){
        Menu helpMenu = new Menu(HELP);

        helpMenu.add(menuItem(ABOUT, new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event) {
                about();
            }
        }));

        setHelpMenu(helpMenu);
    }

    // Também considero controller, porque apesar de mexer com a
    // apresentação e o XML, ele não faz a coisa, ele chama o método
    // que faz a coisa
    private void openFile(){
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

    // Assim... Ele mexe com a presentation, mas
    // não de uma forma que me faça pensar que ele é
    // model. Eu considero controller
    private void clearPresentation() {
        presentation.clear();
        parentFrame.repaint();
    }

    // Parece model, porque mexe com o XML, mas eu
    // considero controller porque ele não executa a ação
    // em si, ele apenas chama o método que faz isso.
    private void saveFile(){
        Accessor xmlAccessor = new XMLAccessor();
        try {
            xmlAccessor.saveFile(presentation, SAVEFILE);
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(parentFrame, IOEX + exc, SAVEERR, JOptionPane.ERROR_MESSAGE);
        }
    }

    // Pra mim, é controller
    private void exit(){
        presentation.exit(0);
    }

    // Também considero controller
    private void nextSlide(){
        presentation.nextSlide();
    }

    // Considero controller, assim como o goToSlide
    private void previousSlide(){
        presentation.prevSlide();
    }

    // Esse método permite que o usuário digite um número e ele pula
    // para a página desse número
    // Eu considero controller, porque não tem nada a ver com a view e
    // não mexe diretamente na apresentação em si
    private void goToSlide(){
        String pageNumberStr = JOptionPane.showInputDialog(PAGENR);
        int pageNumber = Integer.parseInt(pageNumberStr);
        presentation.setSlideNumber(pageNumber - 1);
    }

    // Ele chama a classe About.
    // Eu considero que seja view, pois ele não tem nada
    // a ver com a apresentação.
    private void about(){
        About.show(parentFrame);
    }

    // É criado um item de menu, e é adicionado o ActionListener.
    // Esse método é mais utilitário e não se encaixa bem em nenhum, mas
    // eu colocaria em view, afinal ele cria elementos que servirão apenas
    // para a view e que não interfere na lógica da apresentação em si.
    private MenuItem menuItem(String name, ActionListener actionListener){
        MenuItem menuItem = new MenuItem(name, new MenuShortcut(name.charAt(0)));
        menuItem.addActionListener(actionListener);
        return menuItem;
    }
}