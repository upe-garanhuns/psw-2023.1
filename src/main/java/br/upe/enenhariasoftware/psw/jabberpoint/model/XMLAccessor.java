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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.upe.enenhariasoftware.psw.jabberpoint.controller.BitmapItemController;
import br.upe.enenhariasoftware.psw.jabberpoint.view.BitmapItem;
import br.upe.enenhariasoftware.psw.jabberpoint.view.Slide;
import br.upe.enenhariasoftware.psw.jabberpoint.view.SlideItem;
import br.upe.enenhariasoftware.psw.jabberpoint.view.TextItem;

public class XMLAccessor extends Accessor {

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
	private static final Logger logger = Logger.getLogger(XMLAccessor.class.getName());

	private String getTitle(Element element, String tagName) {
		NodeList titles = element.getElementsByTagName(tagName);
		return titles.item(0).getTextContent();

	}

	public void loadFile(PresentationModel presentationModel, String filename) throws IOException {
		int slideNumber, itemNumber, max = 0, maxItems = 0;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(new File(filename));

			Element doc = document.getDocumentElement();
			presentationModel.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();

			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);

				Slide slide = new Slide();
				slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				presentationModel.append(slide);

				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();

				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}

		} catch (IOException iox) {
			logger.log(Level.SEVERE, "ES ERROR:", iox);
		} catch (SAXException sax) {
			logger.log(Level.SEVERE, "SAX ERROR", sax);
		} catch (ParserConfigurationException pcx) {
			logger.log(Level.SEVERE, "PARSER CONFIGURATION ERROR: ", pcx);
		}

	}

	protected void loadSlideItem(Slide slide, Element item) {
		int level = 1;

		NamedNodeMap attributes = item.getAttributes();

		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();

		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			} catch (NumberFormatException x) {
				logger.log(Level.SEVERE, NFE);
			}
		}

		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.append(new TextItem(level, item.getTextContent()));
		} else {
			if (IMAGE.equals(type)) {
				slide.append(new BitmapItemController(level, item.getTextContent()));
			} else {
				logger.log(Level.SEVERE, UNKNOWNTYPE);
			}
		}
	}

	public void saveFile(PresentationModel presentationModel, String filename) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(filename));

		out.println("<?xml version=\"1.0\"?>");
		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
		out.println("<presentation>");

		out.print("<showtitle>");
		out.print(presentationModel.getTitle());
		out.println("</showtitle>");

		for (int slideNumber = 0; slideNumber < presentationModel.getSize(); slideNumber++) {
			Slide slide = presentationModel.getSlide(slideNumber);

			out.println("<slide>");
			out.println("<title>" + slide.getTitle() + "</title>");

			ArrayList<SlideItem> slideItems = new ArrayList<>();
			for (int itemNumber = 0; itemNumber < slideItems.size(); itemNumber++) {
				SlideItem slideItem = (SlideItem) slideItems.get(itemNumber);
				out.print("<item kind=");

				if (slideItem instanceof TextItem) {
					out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
					out.print(((TextItem) slideItem).getText());
				} else {
					if (slideItem instanceof BitmapItem) {
						out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
						out.print(((BitmapItemController) slideItem).getName());
					} else {
						System.out.println("Ignoring " + slideItem);
					}
				}

				out.println("</item>");
			}

			out.println("</slide>");
		}

		out.println("</presentation>");

		out.close();
	}

}