package com.test.process_one;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

	public static void main(String argv[]) {

		  try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		POSTaggerExample posHashMap= new POSTaggerExample();
//			ArrayList<ArrayList> tokenPosArray = posArrayList.postagging();
			HashMap<Integer,HashMap<String,String>> newSentenceList = posHashMap.postagging() ;
			
			
			for (HashMap.Entry<Integer,HashMap<String,String>> entry:newSentenceList.entrySet()){
				int sid = entry.getKey();
				
				HashMap<String,String> wordList = entry.getValue();
				System.out.println("Sentence id: " + sid + "\n---------------");
				for(HashMap.Entry<String,String> entry1:wordList.entrySet()){
					System.out.println(entry1.getKey() +"	"+ entry1.getValue());
				}
			}
			
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("D");
			doc.appendChild(rootElement);

			Element staff = doc.createElement("S");
			rootElement.appendChild(staff);
			
			Attr attr = doc.createAttribute("id");
			attr.setValue("1");
			staff.setAttributeNode(attr);
		
			Element firstname = doc.createElement("W");
			firstname.appendChild(doc.createTextNode("word"));
			staff.appendChild(firstname);
			staff.setAttribute("id", "1");

			

			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.xml"));


			transformer.transform(source, result);

			System.out.println("File saved successfully!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		}
}
