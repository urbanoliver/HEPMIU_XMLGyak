package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.Scanner;

public class DomQueryHepmiu {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance(); // SZUKSEGES DOM OSZTALYOK
																						// PELDANYOSITASA
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Scanner sc = new Scanner(System.in);

			System.out.println("Adja meg az element nevet!");
			String e_Nev = sc.nextLine();
			System.out.println("Adja meg az element ID-janak nevet!");
			String e_ID_Nev = sc.nextLine();
			System.out.println("Adja meg az element ID-janak erteket!");
			String e_ID_Ertek = sc.nextLine();

			getElementByID(doc, 0, "", e_Nev, e_ID_Nev, e_ID_Ertek);

			sc.close();
			// Query for elements based on a specific tag name

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getElementByID(Document doc, int level, String indent, String elementName, String elementID_Name,
			String elementID) {

		NodeList nodeList = doc.getElementsByTagName(elementName);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			Element element = (Element) node;

			if (element.getAttribute(elementID_Name).equals(elementID)) {
				// Check if the element has the desired ID
				Element keresett_Elem = doc.getDocumentElement();
				kiirTartalom(keresett_Elem, "");
			}
			// Add more lines for other attributes or values as needed
		}
	}

	public static String getIndent(int level) { // FA STRUKTURA LETREHOZASA
		StringBuilder indent = new StringBuilder();
		for (int i = 0; i < level; i++) {
			indent.append("  ");
		}
		return indent.toString();
	}

	private static void kiirTartalom(Node node, String indent) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(indent + node.getNodeName());

			if (node.hasAttributes()) {
				NamedNodeMap attrib = node.getAttributes();
				for (int i = 0; i < attrib.getLength(); i++) {
					Node attribute = attrib.item(i);
					System.out.println(indent + attribute.getNodeName() + " = " + attribute.getNodeValue());
				}
			}

			if (node.hasChildNodes()) {
				NodeList gyerek = node.getChildNodes();
				for (int i = 0; i < gyerek.getLength(); i++) {
					Node child = gyerek.item(i);
					kiirTartalom(child, indent + "  ");
				}
			}
		} 
	}

}
