package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File; //VALTOZTATAS SZUKSEGES 

public class DomReadHepmiu {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Element gyokerElem = doc.getDocumentElement();
			System.out.println("Gyökér elem: " + gyokerElem.getNodeName());

			kiirTartalom(gyokerElem, "");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void kiirTartalom(Node node, String indent) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			System.out.println(indent + node.getNodeName());

			if (node.hasAttributes()) {
				NamedNodeMap attrib = node.getAttributes();
				for (int i = 0; i < attrib.getLength(); i++) {
					Node attribute = attrib.item(i);
					System.out.println(
							indent + attribute.getNodeName() + " = " + attribute.getNodeValue());
				}
			}

			if (node.hasChildNodes()) {
				NodeList gyerek = node.getChildNodes();
				for (int i = 0; i < gyerek.getLength(); i++) {
					Node child = gyerek.item(i);
					kiirTartalom(child, indent + "  ");
				}
			}
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			String datas = node.getNodeValue().trim();
			if (!datas.isEmpty()) {
				System.out.println(indent + datas);
			}
		}
	}

}
