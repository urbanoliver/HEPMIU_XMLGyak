package domhepmiu1115;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomModifyHepmiu {
	public static void main(String[] args) {
		try {
			File xmlFile = new File("orarendHepmiu.xml");
			File modifiedXmlFile = new File("orarendHepmiu1.xml");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = factory.newDocumentBuilder();

			TransformerFactory transformerFactory = TransformerFactory.newInstance();

			Transformer transformer;

			transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			StreamResult console = new StreamResult(System.out);
			StreamResult modFile = new StreamResult(modifiedXmlFile);

			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			System.out.println("a) �raad� hozz�ad�sa");
			Element root = document.getDocumentElement();
			cleanDocument(root);
			addLecturer(document);

			DOMSource source = new DOMSource(document);
			transformer.transform(source, console);
			transformer.transform(source, modFile);

			System.out.println("\nb) Gyakorlatok el�ad�ss� alak�t�sa");
			changeCourseType(document);
			source = new DOMSource(document);
			transformer.transform(source, console);

			System.out.println("\nc) �j �ra hozz�ad�sa");
			addCourse(document);
			source = new DOMSource(document);
			transformer.transform(source, console);

		} catch (IOException | SAXException | ParserConfigurationException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private static void cleanDocument(Node root) {

		NodeList nodes = root.getChildNodes();
		List<Node> toDelete = new ArrayList<>();

		for (int i = 0; i < nodes.getLength(); i++) {
			if (nodes.item(i).getNodeType() == Node.TEXT_NODE && nodes.item(i).getTextContent().strip().equals("")) {
				toDelete.add(nodes.item(i));
			} else {
				cleanDocument(nodes.item(i));
			}
		}

		for (Node node : toDelete) {
			root.removeChild(node);
		}
	}

	private static void addLecturer(Document document) {
		Element root = document.getDocumentElement();
		Element newLecturer = document.createElement("oraado");

		newLecturer.appendChild(document.createTextNode("Nagy Anna"));
		NodeList courses = root.getElementsByTagName("ora");
		for (int i = 0; i < courses.getLength(); i++) {
			Node course = courses.item(i);
			if (((Element) course).getElementsByTagName("oraado").getLength() == 0) {
				course.appendChild(newLecturer);

				break;
			}
		}

	}

	private static void changeCourseType(Document document) {

		Element root = document.getDocumentElement();

		NodeList courses = root.getElementsByTagName("ora");

		for (int i = 0; i < courses.getLength(); i++) {
			Node course = courses.item(i);
			Attr type = (Attr) ((Element) course).getAttributeNode("tipus");
			if (type.getNodeValue().equals("gyakorlat"))
				type.setNodeValue("el�ad�s");
		}

	}

	private static void addCourse(Document document) {
		Element root = document.getDocumentElement();

		Element newCourse = document.createElement("ora");

		Element newName = document.createElement("targy");
		newName.appendChild(document.createTextNode("Angol nyelv"));

		Element newTime = document.createElement("idopont");

		Element day = document.createElement("nap");

		day.appendChild(document.createTextNode("p�ntek"));

		Element from = document.createElement("tol");

		from.appendChild(document.createTextNode("8"));

		Element to = document.createElement("ig");

		to.appendChild(document.createTextNode("10"));

		newTime.appendChild(day);
		newTime.appendChild(from);

		newTime.appendChild(to);

		Element newPlace = document.createElement("helyszin");
		newPlace.appendChild(document.createTextNode("Info 101"));

		Element newTeacher = document.createElement("oktato");
		newTeacher.appendChild(document.createTextNode("Kis Janos"));

		Element newMajor = document.createElement("szak");
		newMajor.appendChild(document.createTextNode("GEFIT102"));

		newCourse.setAttribute("id", "20");
		newCourse.setAttribute("tipus", "gyakorlat");
		newCourse.appendChild(newName);
		newCourse.appendChild(newTime);
		newCourse.appendChild(newPlace);
		newCourse.appendChild(newTeacher);
		newCourse.appendChild(newMajor);

		root.appendChild(newCourse);
	}
}