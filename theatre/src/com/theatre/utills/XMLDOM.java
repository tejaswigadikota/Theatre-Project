package com.theatre.utills;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XMLDOM {

	private static String defaultFilename= "./data/theatre.xml";
	private String filename;
	private Document doc;
	
	public XMLDOM() throws Exception {
		this(defaultFilename);
	}
	public XMLDOM(String filename) throws Exception{
		this.filename=filename;
		this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
	}
	
	public Document getDoc() throws Exception{
		return doc;
	}
	
	public NodeList query(String query) throws Exception{
		
		XPathExpression expr = XPathFactory.newInstance().newXPath().compile(query);  // "//Type[@type_id=\"4218\"]"
		NodeList nl = (NodeList) expr.evaluate(this.doc, XPathConstants.NODESET);
		return nl;
	}
	
	public void writeDoc() throws Exception{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(this.filename));
		transformer.transform(source, result);
	}
}
