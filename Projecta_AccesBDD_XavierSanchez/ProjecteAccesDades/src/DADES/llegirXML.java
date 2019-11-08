package DADES;

import MODEL.LineaComanda;
import MODEL.Comanda;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class llegirXML {
	ArrayList<Comanda> Comanda = new ArrayList<Comanda>();
	String idclient;
	String codi;
	int unitat;
	int test;
	char status;
	float preuUnitari;
	char statusc;

	public llegirXML() {
//a
		try {
			File inputFile = new File("comanda.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("article");
			System.out.println("Client :" + doc.getDocumentElement().getAttribute("idclient"));
			idclient = doc.getDocumentElement().getAttribute("idclient");
			statusc = doc.getDocumentElement().getAttribute("status").charAt(0);
			System.out.println("----------------------------");
			test = 1;
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("\nElement  :" + nNode.getNodeName());
				ArrayList<LineaComanda> liniaComanda = new ArrayList<LineaComanda>();

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					System.out
							.println("Codi article: " + eElement.getElementsByTagName("codi").item(0).getTextContent());
					codi = eElement.getElementsByTagName("codi").item(0).getTextContent();

					status = eElement.getElementsByTagName("status").item(0).getTextContent().charAt(0);

					System.out
							.println("Unitats : " + eElement.getElementsByTagName("unitats").item(0).getTextContent());
					unitat = Integer.parseInt(eElement.getElementsByTagName("unitats").item(0).getTextContent());

					preuUnitari = Float
							.parseFloat(eElement.getElementsByTagName("preuUnitat").item(0).getTextContent());

					liniaComanda.add(new LineaComanda(temp + 1/2, 1, codi, unitat, preuUnitari, status));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public char getStatus() {
		return status;
	}

	public ArrayList<LineaComanda> getLinies() {
		return liniaComanda;
	}

	public String getIdClient() {
		return idclient;
	}

	public char getStatusc() {
		return statusc;
	}

	@Override

	public String toString() {
		return "llegirXML [idclient=" + idclient + ", codi=" + codi + ", unitats=" + unitat + ", unitat=" + unitat
				+ ", test=" + test + ", getIdClient()=" + getIdClient() + "]";
	}

}
