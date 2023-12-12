package hu.domparse.hepmiu;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import org.w3c.dom.*;

public class DOMWriteHepmiu {
	public static void main(String[] args) {
		
		writeElementsToFileAndConsole();
	}

	private static void writeElementsToFileAndConsole() {
		try {
			Document document = prepareDocument();
			Element rootElement = document.createElement("Hadsereg_HEPMIU");
			document.appendChild(rootElement);
			addElements(document, rootElement);
			saveDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Document prepareDocument() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();

		return document;
	}

	private static void addElements(Document document, Element rootElement) {
		addHadsereg(document, rootElement,"1", "9000",
				"Hungary","Dél-Alföld","közepes", "erõs", "gyenge");
		addFoparancsnok(document, rootElement, "4545", "1", "Nagy József", "vezérezredes", "56", "30");
		addTisztek(document, rootElement, "2323", "4545", "Király Zoltán", "százados", "42", "22");
		addTisztek(document, rootElement, "2336", "4545", "Nagy Zsolt", "ezredes", "48", "28");
		addTisztek(document, rootElement, "9836", "4545", "Szabó Gyula", "õrnagy", "50", "30");
		addKatonak(document, rootElement, "7874", "9836", "56", "Szabó Kristóf", "tizedes", "20", "1");
		addKatonak(document, rootElement, "4921", "9836", "56", "Tóth Bendegúz", "hadnagy", "28", "10");
		addKatonak(document, rootElement, "7123", "9836", "56", "Kovács István", "tizedes", "23", "3");
		addSzarazfoldierok(document, rootElement, "56", "5000", "250");
		addTengereszet(document, rootElement, "23", "2000", "5");
		addLegiero(document, rootElement, "18", "2000", "120");

	}

	private static void saveDocument(Document document) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "4");

			printDocument(document);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printDocument(Document document) {
		try {
			File xmlFile = new File("XMLHepmiu1.xml");
			PrintWriter writer = new PrintWriter(new FileWriter(xmlFile, true));

			Element rootElement = document.getDocumentElement();
			String rootName = rootElement.getTagName();
			StringJoiner rootAttributes = new StringJoiner(" ");

			NamedNodeMap rootAttributeMap = rootElement.getAttributes();
			for (int i = 0; i < rootAttributeMap.getLength(); i++) {
				Node attribute = rootAttributeMap.item(i);
				rootAttributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
			}

			System.out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

			System.out.print("<" + rootName + " " + rootAttributes.toString() + ">\n");
			writer.print("<" + rootName + " " + rootAttributes.toString() + ">\n");

			NodeList csapatList = document.getElementsByTagName("Csapat");
			printNodeList(csapatList, writer);
			System.out.println("");
			writer.println("");

			System.out.println("</" + rootName + ">");
			writer.append("</" + rootName + ">");

			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void addHadsereg(Document document, Element rootElement, String hadseregID, String létszam,
			String orszagNev, String pozicio, String utoképessseg, String tamadas, String vedelekezes) {
		Element hadsereg = document.createElement("Hadsereg");
		hadsereg.setAttribute("hadseregID", hadseregID);

		Element letszamElement = createElement(document, "létszám", létszam);
		hadsereg.appendChild(letszamElement);

		Element orszagNevElement = createElement(document, "országNév", orszagNev);
		hadsereg.appendChild(orszagNevElement);

		Element pozicioElement = createElement(document, "pozíció", pozicio);
		hadsereg.appendChild(pozicioElement);

		Element fejlettségElement = document.createElement("fejlettség");

		Element utoképességElement = createElement(document, "ütõképesség", utoképessseg);
		fejlettségElement.appendChild(utoképességElement);

		Element tamadasElement = createElement(document, "támadás", tamadas);
		fejlettségElement.appendChild(tamadasElement);

		Element vedelekezesElement = createElement(document, "védekezés", vedelekezes);
		fejlettségElement.appendChild(vedelekezesElement);

		hadsereg.appendChild(fejlettségElement);

		rootElement.appendChild(hadsereg);
	}

	private static void addFoparancsnok(Document document, Element rootElement, String fsorszam, String iranyitja,
			String nev, String rang, String eletkor, String szolgIdo) {
		Element foparancsnok = document.createElement("Fõparancsnok");
		foparancsnok.setAttribute("F_sorszám", fsorszam);
		foparancsnok.setAttribute("irányítja", iranyitja);

		Element nevElement = createElement(document, "név", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "életkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgIdõ", szolgIdo);

		foparancsnok.appendChild(nevElement);
		foparancsnok.appendChild(rangElement);
		foparancsnok.appendChild(eletkorElement);
		foparancsnok.appendChild(szolgIdoElement);

		rootElement.appendChild(foparancsnok);
	}

	private static void addTisztek(Document document, Element rootElement, String tsorszam, String fparancsnok,
			String nev, String rang, String eletkor, String szolgIdo) {
		Element tisztek = document.createElement("Tisztek");
		tisztek.setAttribute("T_sorszám", tsorszam);
		tisztek.setAttribute("F_parancsnok", fparancsnok);

		Element nevElement = createElement(document, "név", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "életkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgIdõ", szolgIdo);

		tisztek.appendChild(nevElement);
		tisztek.appendChild(rangElement);
		tisztek.appendChild(eletkorElement);
		tisztek.appendChild(szolgIdoElement);

		rootElement.appendChild(tisztek);
	}

	private static void addKatonak(Document document, Element rootElement, String ksorszam, String tparancsnok,
			String szolgHelye, String nev, String rang, String eletkor, String szolgIdo) {
		Element katonak = document.createElement("Katonák");
		katonak.setAttribute("K_sorszám", ksorszam);
		katonak.setAttribute("T_parancsnok", tparancsnok);
		katonak.setAttribute("szolgHelye", szolgHelye);

		Element nevElement = createElement(document, "név", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "életkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgIdõ", szolgIdo);

		katonak.appendChild(nevElement);
		katonak.appendChild(rangElement);
		katonak.appendChild(eletkorElement);
		katonak.appendChild(szolgIdoElement);

		rootElement.appendChild(katonak);
	}

	private static void addSzarazfoldierok(Document document, Element rootElement, String szE_ID, String letszam,
			String szfoldiJarmuvekSzama) {
		Element szarazfoldierok = document.createElement("Szárazföldi_erõk");
		szarazfoldierok.setAttribute("SzE_ID", szE_ID);

		Element letszamElement = createElement(document, "létszám", letszam);
		Element szfoldiJarmuvekSzamaElement = createElement(document, "SzföldiJármûvekSzáma", szfoldiJarmuvekSzama);

		szarazfoldierok.appendChild(letszamElement);
		szarazfoldierok.appendChild(szfoldiJarmuvekSzamaElement);

		rootElement.appendChild(szarazfoldierok);
	}

	private static void addTengereszet(Document document, Element rootElement, String teng_ID, String letszam,
			String hajokSzama) {
		Element tengereszet = document.createElement("Tengerészet");
		tengereszet.setAttribute("Teng_ID", teng_ID);

		Element letszamElement = createElement(document, "létszám", letszam);
		Element hajokSzamaElement = createElement(document, "HajókSzáma", hajokSzama);

		tengereszet.appendChild(letszamElement);
		tengereszet.appendChild(hajokSzamaElement);

		rootElement.appendChild(tengereszet);
	}

	private static void addLegiero(Document document, Element rootElement, String leg_ID, String letszam,
			String repulokSzama) {
		Element legiero = document.createElement("Légierõ");
		legiero.setAttribute("Leg_ID", leg_ID);

		Element letszamElement = createElement(document, "létszám", letszam);
		Element repulokSzamaElement = createElement(document, "RepülõkSzáma", repulokSzama);

		legiero.appendChild(letszamElement);
		legiero.appendChild(repulokSzamaElement);

		rootElement.appendChild(legiero);
	}

	private static Element createElement(Document document, String name, String value) {
	    Element element = document.createElement(name);
	    element.appendChild(document.createTextNode(value));
	    return element;
	}

	private static void printNodeList(NodeList nodeList, PrintWriter writer) {
	    for (int i = 0; i < nodeList.getLength(); i++) {
	        Node node = nodeList.item(i);
	        printNode(node, 1, writer);
	        System.out.println("");
	        writer.println("");
	    }
	}

	private static void printNode(Node node, int indent, PrintWriter writer) {

	    if (node.getNodeType() == Node.ELEMENT_NODE) {

	        Element element = (Element) node;
	        String nodeName = element.getTagName();
	        StringJoiner attributes = new StringJoiner(" ");
	        NamedNodeMap attributeMap = element.getAttributes();

	        for (int i = 0; i < attributeMap.getLength(); i++) {
	            Node attribute = attributeMap.item(i);
	            attributes.add(attribute.getNodeName() + "=\"" + attribute.getNodeValue() + "\"");
	        }

	        System.out.print(getIndentString(indent));
	        System.out.print("<" + nodeName + " " + attributes.toString() + ">");

	        writer.print(getIndentString(indent));
	        writer.print("<" + nodeName + " " + attributes.toString() + ">");

	        NodeList children = element.getChildNodes();
	        if (children.getLength() == 1 && children.item(0).getNodeType() == Node.TEXT_NODE) {
	            System.out.print(children.item(0).getNodeValue());
	            writer.print(children.item(0).getNodeValue());
	        } else {
	            System.out.println();
	            writer.println();
	            for (int i = 0; i < children.getLength(); i++) {
	                printNode(children.item(i), indent + 1, writer);
	            }
	            System.out.print(getIndentString(indent));
	            writer.print(getIndentString(indent));
	        }
	        System.out.println("</" + nodeName + ">");
	        writer.println("</" + nodeName + ">");
	    }
	}

	private static String getIndentString(int indent) {
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < indent; i++) {
	        sb.append("  ");
	    }
	    return sb.toString();
	}
}
