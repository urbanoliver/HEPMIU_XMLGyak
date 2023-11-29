package domhepmiu1108;

import java.io.File;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMWriteHepmiu {
	public static void main(String[] args) {
		try {

			File xmlFile = new File("orarendHepmiu.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			Element gyokerElem = doc.getDocumentElement();
			System.out.println("Gyökér elem: " + gyokerElem.getNodeName());
			kiirTartalom(gyokerElem, "");

			writeToFile(doc, "orarend1Hepmi1.xml");

			System.out.println("XML updatetelt változata mentve az orarend1Hepmiu.xml fájlba");

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
		} else if (node.getNodeType() == Node.TEXT_NODE) {
			String datas = node.getNodeValue().trim();
			if (!datas.isEmpty()) {
				System.out.println(indent + datas);
			}
		}
	}

	public static void writeToFile(Document doc, String filename) { // A MODOSITOTT FAJLT LETREHOZO METODUS
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
