package br.upe.enenhariasoftware.psw.jabberpoint.controller;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import br.upe.enenhariasoftware.psw.jabberpoint.model.XMLAccessor;
import br.upe.enenhariasoftware.psw.jabberpoint.view.BitmapItem;

public class BitmapItemController extends BitmapItem {
	
	private String imageName;
	
	private static final Logger logger = Logger.getLogger(XMLAccessor.class.getName());
	
	public BitmapItemController (int level, String name) {
		super();

		imageName = name;

		try {
			bufferedImage = ImageIO.read(new File(imageName));
		} catch (IOException e) {
			logger.log(Level.SEVERE, FILE + imageName + NOTFOUND, e);
		}

	}

	public BitmapItemController() {
		this(0, null);
	}

	public String getName() {
		return imageName;
	}

	public String toString() {
		return "BitmapItem[" + getLevel() + "," + imageName + "]";
	}

}
