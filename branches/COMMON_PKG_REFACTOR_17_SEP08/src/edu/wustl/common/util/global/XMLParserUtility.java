package edu.wustl.common.util.global;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.wustl.common.util.logger.Logger;

/**
 * This class has utility methods to parse xml file.
 * @author ravi_kumar
 *
 */
public class XMLParserUtility
{

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(XMLParserUtility.class);

	/**
	 * This method return role name from xml file.
	 * @param element String- privilege Element
	 * @param elementName -Element name for which value has to be return
	 * @return String Role name
	 */
	public static String getElementValue(Element element,String elementName)
	{
		String roleName;
		NodeList roleNmElmntLst = element.getElementsByTagName(elementName);
		Element roleNmElmnt = (Element) roleNmElmntLst.item(0);
		NodeList roleNm = roleNmElmnt.getChildNodes();
		roleName=((Node) roleNm.item(0)).getNodeValue();
		return roleName;
	}
	/**
	 * This method returns the Document object for xml file.
	 * @param fileName File name.
	 * @return Document xml document.
	 */
	public static Document getDocument(String fileName)
	{
		Document doc=null;
		try
		{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			doc=documentBuilder.parse(file);
		}
		catch (ParserConfigurationException exception)
		{
			logger.error("DocumentBuilder cannot be created",exception);
		}
		catch (SAXException exception)
		{
			logger.error("Can not parse the xml file:"+fileName,exception);
		}
		catch (IOException exception)
		{
			logger.error("Can not parse the xml file:"+fileName,exception);
		}
		return doc;
	}
}
