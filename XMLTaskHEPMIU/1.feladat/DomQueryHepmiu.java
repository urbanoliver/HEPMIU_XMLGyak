package hu.domparse.hepmiu;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class DomQueryHepmiu {

	public static void main(String[] args) {
		try {
			File xmlFile = new File("XMLHepmiu.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();					//SZUKSEGES DOM OSZTALYOK PELDANYOSITASA
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			// Query for elements based on a specific tag name
			NodeList nodeList = doc.getElementsByTagName("your_tag_name");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if (node.getNodeType() == Node.ELEMENT_NODE) {											//XML ELEMENT LEKEERDEZESE TAG NEV ALAPJAN
					Element element = (Element) node;
					// Access elements within the node and retrieve data
					String data = element.getElementsByTagName("specific_element").item(0).getTextContent();
					System.out.println("Retrieved Data: " + data);										//LEKERDEZETT ELEMENT KIIRASA
					// Add more lines for other elements or attributes as needed
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
