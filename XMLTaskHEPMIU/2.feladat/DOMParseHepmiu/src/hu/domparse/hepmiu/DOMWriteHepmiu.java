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
				"Hungary","D�l-Alf�ld","k�zepes", "er�s", "gyenge");
		addFoparancsnok(document, rootElement, "4545", "1", "Nagy J�zsef", "vez�rezredes", "56", "30");
		addTisztek(document, rootElement, "2323", "4545", "Kir�ly Zolt�n", "sz�zados", "42", "22");
		addTisztek(document, rootElement, "2336", "4545", "Nagy Zsolt", "ezredes", "48", "28");
		addTisztek(document, rootElement, "9836", "4545", "Szab� Gyula", "�rnagy", "50", "30");
		addKatonak(document, rootElement, "7874", "9836", "56", "Szab� Krist�f", "tizedes", "20", "1");
		addKatonak(document, rootElement, "4921", "9836", "56", "T�th Bendeg�z", "hadnagy", "28", "10");
		addKatonak(document, rootElement, "7123", "9836", "56", "Kov�cs Istv�n", "tizedes", "23", "3");
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

	private static void addHadsereg(Document document, Element rootElement, String hadseregID, String l�tszam,
			String orszagNev, String pozicio, String utok�pessseg, String tamadas, String vedelekezes) {
		Element hadsereg = document.createElement("Hadsereg");
		hadsereg.setAttribute("hadseregID", hadseregID);

		Element letszamElement = createElement(document, "l�tsz�m", l�tszam);
		hadsereg.appendChild(letszamElement);

		Element orszagNevElement = createElement(document, "orsz�gN�v", orszagNev);
		hadsereg.appendChild(orszagNevElement);

		Element pozicioElement = createElement(document, "poz�ci�", pozicio);
		hadsereg.appendChild(pozicioElement);

		Element fejletts�gElement = document.createElement("fejletts�g");

		Element utok�pess�gElement = createElement(document, "�t�k�pess�g", utok�pessseg);
		fejletts�gElement.appendChild(utok�pess�gElement);

		Element tamadasElement = createElement(document, "t�mad�s", tamadas);
		fejletts�gElement.appendChild(tamadasElement);

		Element vedelekezesElement = createElement(document, "v�dekez�s", vedelekezes);
		fejletts�gElement.appendChild(vedelekezesElement);

		hadsereg.appendChild(fejletts�gElement);

		rootElement.appendChild(hadsereg);
	}

	private static void addFoparancsnok(Document document, Element rootElement, String fsorszam, String iranyitja,
			String nev, String rang, String eletkor, String szolgIdo) {
		Element foparancsnok = document.createElement("F�parancsnok");
		foparancsnok.setAttribute("F_sorsz�m", fsorszam);
		foparancsnok.setAttribute("ir�ny�tja", iranyitja);

		Element nevElement = createElement(document, "n�v", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "�letkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgId�", szolgIdo);

		foparancsnok.appendChild(nevElement);
		foparancsnok.appendChild(rangElement);
		foparancsnok.appendChild(eletkorElement);
		foparancsnok.appendChild(szolgIdoElement);

		rootElement.appendChild(foparancsnok);
	}

	private static void addTisztek(Document document, Element rootElement, String tsorszam, String fparancsnok,
			String nev, String rang, String eletkor, String szolgIdo) {
		Element tisztek = document.createElement("Tisztek");
		tisztek.setAttribute("T_sorsz�m", tsorszam);
		tisztek.setAttribute("F_parancsnok", fparancsnok);

		Element nevElement = createElement(document, "n�v", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "�letkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgId�", szolgIdo);

		tisztek.appendChild(nevElement);
		tisztek.appendChild(rangElement);
		tisztek.appendChild(eletkorElement);
		tisztek.appendChild(szolgIdoElement);

		rootElement.appendChild(tisztek);
	}

	private static void addKatonak(Document document, Element rootElement, String ksorszam, String tparancsnok,
			String szolgHelye, String nev, String rang, String eletkor, String szolgIdo) {
		Element katonak = document.createElement("Katon�k");
		katonak.setAttribute("K_sorsz�m", ksorszam);
		katonak.setAttribute("T_parancsnok", tparancsnok);
		katonak.setAttribute("szolgHelye", szolgHelye);

		Element nevElement = createElement(document, "n�v", nev);
		Element rangElement = createElement(document, "rang", rang);
		Element eletkorElement = createElement(document, "�letkor", eletkor);
		Element szolgIdoElement = createElement(document, "szolgId�", szolgIdo);

		katonak.appendChild(nevElement);
		katonak.appendChild(rangElement);
		katonak.appendChild(eletkorElement);
		katonak.appendChild(szolgIdoElement);

		rootElement.appendChild(katonak);
	}

	private static void addSzarazfoldierok(Document document, Element rootElement, String szE_ID, String letszam,
			String szfoldiJarmuvekSzama) {
		Element szarazfoldierok = document.createElement("Sz�razf�ldi_er�k");
		szarazfoldierok.setAttribute("SzE_ID", szE_ID);

		Element letszamElement = createElement(document, "l�tsz�m", letszam);
		Element szfoldiJarmuvekSzamaElement = createElement(document, "Szf�ldiJ�rm�vekSz�ma", szfoldiJarmuvekSzama);

		szarazfoldierok.appendChild(letszamElement);
		szarazfoldierok.appendChild(szfoldiJarmuvekSzamaElement);

		rootElement.appendChild(szarazfoldierok);
	}

	private static void addTengereszet(Document document, Element rootElement, String teng_ID, String letszam,
			String hajokSzama) {
		Element tengereszet = document.createElement("Tenger�szet");
		tengereszet.setAttribute("Teng_ID", teng_ID);

		Element letszamElement = createElement(document, "l�tsz�m", letszam);
		Element hajokSzamaElement = createElement(document, "Haj�kSz�ma", hajokSzama);

		tengereszet.appendChild(letszamElement);
		tengereszet.appendChild(hajokSzamaElement);

		rootElement.appendChild(tengereszet);
	}

	private static void addLegiero(Document document, Element rootElement, String leg_ID, String letszam,
			String repulokSzama) {
		Element legiero = document.createElement("L�gier�");
		legiero.setAttribute("Leg_ID", leg_ID);

		Element letszamElement = createElement(document, "l�tsz�m", letszam);
		Element repulokSzamaElement = createElement(document, "Rep�l�kSz�ma", repulokSzama);

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
