/**
 * UPE - Campus Garanhuns Curso de Bacharelado em Engenharia de Software Disciplina de Projeto de
 * Software - 2023.1
 *
 * Licensed under the Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 *
 * @author Ian F. Darwin, Helaine Lins
 */
package br.upe.enenhariasoftware.psw.jabberpoint.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class XMLAccessor extends Accessor {

  // Responsável por ler e escrever as apresentações (Presentations) XML
  // Se comunica com os outros models
  // não vejo nada de view ou controller aqui
  private static final Logger logger = LogManager.getLogger(XMLAccessor.class);

  protected static final String DEFAULT_API_TO_USE = "dom";

  protected static final String SHOWTITLE = "showtitle";
  protected static final String SLIDETITLE = "title";
  protected static final String SLIDE = "slide";
  protected static final String ITEM = "item";
  protected static final String LEVEL = "level";
  protected static final String KIND = "kind";
  protected static final String TEXT = "text";
  protected static final String IMAGE = "image";

  protected static final String PCE = "Parser Configuration Exception";
  protected static final String UNKNOWNTYPE = "Unknown Element type";
  protected static final String NFE = "Number Format Exception";

  private String getTitle(Element element, String tagName) {
    NodeList titles = element.getElementsByTagName(tagName);
    return titles.item(0).getTextContent();

  }

  private void addItem(Slide slide, Element xmlSlide){
    NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
    int maxItems = slideItems.getLength();

    for (int itemNumber = 0; itemNumber < maxItems; itemNumber++) {
      Element item = (Element) slideItems.item(itemNumber);
      loadSlideItem(slide, item);
    }
  }

  private void createSlides(IPresentation presentation, Element doc){

    NodeList slides = doc.getElementsByTagName(SLIDE);
    int max = slides.getLength();

    for (int slideNumber = 0; slideNumber < max; slideNumber++) {
      Element xmlSlide = (Element) slides.item(slideNumber);

      Slide slide = new Slide();
      slide.setTitle(getTitle(xmlSlide, SLIDETITLE));

      presentation.append(slide);

      addItem(slide,xmlSlide);
    }
  }

  private DocumentBuilder configuringDocumentBuilder() throws ParserConfigurationException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);

    return factory.newDocumentBuilder();
  }

  public void loadFile(IPresentation presentation, String filename) throws IOException {
    try {
      DocumentBuilder builder = configuringDocumentBuilder();

      Document document = builder.parse(new File(filename));

      Element doc = document.getDocumentElement();
      presentation.setTitle(getTitle(doc, SHOWTITLE));

      createSlides(presentation, doc);

    } catch (IOException iox) {
      logger.error(iox.toString());
    } catch (SAXException sax) {
      logger.error(sax.toString());
    } catch (ParserConfigurationException pcx) {
      logger.error(pcx.toString());
    }

  }

  private void addSlideItem(String type, Slide slide, Element item, int level) {
    if (TEXT.equals(type)) {
      slide.appendText(level, item.getTextContent());
    } else {
      if (IMAGE.equals(type)) {
        slide.appendBitmap(level, item.getTextContent());
      } else {
        logger.error(UNKNOWNTYPE);
      }
    }
  }

  private int getLevelItem(Element item) {
    int level = 1;

    NamedNodeMap attributes = item.getAttributes();

    String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

    if (leveltext != null) {
      try {
        level = Integer.parseInt(leveltext);
      } catch (NumberFormatException x) {
        logger.error(x.toString());
      }
    }
    return level;
  }

  protected void loadSlideItem(Slide slide, Element item) {
    NamedNodeMap attributes = item.getAttributes();

    String type = attributes.getNamedItem(KIND).getTextContent();

    int level = getLevelItem(item);

    addSlideItem(type, slide, item, level);
  }

  private void writePresentationXML(IPresentation presentation, PrintWriter printWriter){
    printWriter.println("<?xml version=\"1.0\"?>");
    printWriter.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
    printWriter.println("<presentation>");

    printWriter.print("<showtitle>");
    printWriter.print(presentation.getTitle());
    printWriter.println("</showtitle>");

    for (int slideNumber = 0; slideNumber < presentation.getSize(); slideNumber++) {
      Slide slide = presentation.getSlide(slideNumber);

      printWriter.println("<slide>");
      printWriter.println("<title>" + slide.getTitle() + "</title>");

      List<ISlideItem> slideItems = slide.getSlideItems();

      for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
        ISlideItem slideItem = slideItems.get(itemNumber);
        printWriter.print("<item kind=");

        if (slideItem instanceof TextItem) {
          printWriter.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
          printWriter.print(((TextItem) slideItem).getText());
        } else {
          if (slideItem instanceof BitmapItem) {
            printWriter.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
            printWriter.print(((BitmapItem) slideItem).getName());
          } else {
            logger.info("Ignoring " + slideItem);
          }
        }
        printWriter.println("</item>");
      }
      printWriter.println("</slide>");
    }
    printWriter.println("</presentation>");
  }

  public void saveFile(IPresentation presentation, String filename) throws IOException {
    PrintWriter printWriter = new PrintWriter(new FileWriter(filename));

    writePresentationXML(presentation,printWriter);

    printWriter.close();
  }

}