/**
 *<p>Copyright: (c) Washington University, School of Medicine 2006.</p>
 *<p>Company: Washington University, School of Medicine, St. Louis.</p>
 *<p>ClassName: edu.wustl.cab2b.client.ui.main.ParseXMLFile </p> 
 */

package edu.wustl.common.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.wustl.cab2b.common.errorcodes.ErrorCodeConstants;
import edu.wustl.cab2b.common.exception.CheckedException;
import edu.wustl.common.util.global.TextConstants;

/**
 * This class is used for parsing the XML file and put the parsed elements to HashMaps 
 * @author Kaushal Kumar
 * @version 1.0
 */
public class ParseXMLFile
{

	private static ParseXMLFile instance = null;

	/**
	 * XML file (to be parsed) with complete path.
	 */
	/*
		private final String xmlFileName = "conf/dynamicUI.xml";*/

	/**
	 * Variables declared by Pratibha
	 */
	/**
	 * non enum data type to contition map
	 */
	private Map<String, ArrayList<String>> nedtCondMap = new HashMap<String, ArrayList<String>>();
	/**
	 * non enum data type to component map
	 */
	private Map<String, String> nedtCompMap = new HashMap<String, String>();
	
	/**
	 * enum data type to condition map
	 */
	private Map<String, ArrayList<String>> edtCondMap = new HashMap<String, ArrayList<String>>();
	/**
	 * enum data type to component map
	 */
	private Map<String, String> edtompMap = new HashMap<String, String>();

	/**
	 * Constructor for parsing the XML file. 
	 *
	 */
	protected ParseXMLFile(String path) throws CheckedException
	{
		Document doc = parseFile(path);
		Node root = doc.getDocumentElement();
		readDynamicUIComponents(root);
	}

	public static ParseXMLFile getInstance(String path) throws CheckedException
	{
		if (instance == null)
		{
			instance = new ParseXMLFile(path);
		}
		return instance;
	}

	/**
	 * This method return the Element value of any particular node
	 * @param elem
	 * @return string
	 */
	private String getElementValue(Node elem)
	{
		Node child;
		String eleValue=TextConstants.EMPTY_STRING;
		if (elem != null)
		{
			if (elem.hasChildNodes())
			{
				for (child = elem.getFirstChild(); child != null; child = child.getNextSibling())
				{
					if (child.getNodeType() == Node.TEXT_NODE)
					{
						eleValue=child.getNodeValue();
					}
				}
			}
		}
		return eleValue;
	}

	/**
	 * Method to read dynamic UI component details from xml and populate UI accordingly
	 * @param node root node to parse
	 */
	private void readDynamicUIComponents(Node node)
	{

		// This node list will return two childrens 
		// 1. non-enumerated
		// 2. enumerated
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++)
		{
			Node child = children.item(i);
			// Check if node is non-enumberated
			if (child.getNodeType() == Node.ELEMENT_NODE)
			{
				if (child.getNodeName().equalsIgnoreCase("non-enumerated"))
				{
					// Get all its children nodes and store corresponding details into the appropriate data-strutures
					NodeList dataTypeNodes = child.getChildNodes();
					readDataTypeDetailsForAllNode(dataTypeNodes, nedtCondMap,
							nedtCompMap);
				}
				else
				{
					//	Get all its children nodes and store corresponding details into the appropriate data-strutures
					NodeList dataTypeNodes = child.getChildNodes();
					readDataTypeDetailsForAllNode(dataTypeNodes, edtCondMap,
							edtompMap);
				}
			}
		}
	}

	/**
	 * Method to get condition list and component associated with it
	 * @param dataTypeNodes
	 * @param dataTypeToCondMap
	 * @param dataTypeToCompMap
	 */
	private void readDataTypeDetailsForAllNode(NodeList dataTypeNodes,
			Map<String, ArrayList<String>> dataTypeToCondMap,
			Map<String, String> dataTypeToCompMap)
	{
		for (int i = 0; i < dataTypeNodes.getLength(); i++)
		{
			Node dataTypeNode = dataTypeNodes.item(i);
			if (dataTypeNode.getNodeType() == Node.ELEMENT_NODE)
			{
				// Node name like String, number, date, boolean
				String nodeType = dataTypeNode.getNodeName();
				// Get all the nodes under nodeType
				NodeList childNodes = dataTypeNode.getChildNodes();
				for (int childCnt = 0; childCnt < childNodes.getLength(); childCnt++)
				{
					// Read the condition node and populate the condition list
					Node node = childNodes.item(childCnt);
					if (node.getNodeType() == Node.ELEMENT_NODE)
					{
						if (node.getNodeName().equalsIgnoreCase("conditions"))
						{
							ArrayList<String> conditionList = new ArrayList<String>();
							NodeList nodeConditions = node.getChildNodes();
							for (int conditionCnt = 0; conditionCnt < nodeConditions.getLength(); conditionCnt++)
							{
								Node conditionNode = nodeConditions.item(conditionCnt);
								if (conditionNode.getNodeType() == Node.ELEMENT_NODE)
								{
									NodeList displayNodes = conditionNode.getChildNodes();
									for (int j = 0; j < displayNodes.getLength(); j++)
									{
										if (displayNodes.item(j).getNodeType() == Node.ELEMENT_NODE)
										{
											conditionList
													.add(getElementValue(displayNodes.item(j)));
											break;
										}
									}
								}
							}
							dataTypeToCondMap.put(nodeType, conditionList);
						}
						else if (node.getNodeName().equalsIgnoreCase("components"))// Read component details
						{
							dataTypeToCompMap.put(nodeType, getElementValue(node));
						}
					}
				}
			}
		}
	}

	/**
	 * This method parses the nodes of XML file and returns the Document object
	 * @param fileName
	 * @return doc
	 */
	private Document parseFile(String fileName) throws CheckedException
	{
		DocumentBuilder docBuilder;
		Document doc = null;
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		docBuilderFactory.setIgnoringElementContentWhitespace(true);
		try 
		{
			docBuilderFactory.setIgnoringComments(true);
			docBuilderFactory.setIgnoringElementContentWhitespace(true);
			docBuilder = docBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			throw new CheckedException(e.getMessage(), e, ErrorCodeConstants.UN_XXXX); // TODO put proper error code.
		}
		File sourceFile = new File(fileName);
		try
		{
			InputStream inputStream = ParseXMLFile.class.getClassLoader().getResourceAsStream(
					fileName);
			if (inputStream == null)
			{
				doc = docBuilder.parse(sourceFile);
			}
			else
			{
				doc = docBuilder.parse(inputStream);
			}
		}
		catch (SAXException e)
		{
			throw new CheckedException(e.getMessage(), e, ErrorCodeConstants.IO_0003);
		}
		catch (IOException e)
		{
			throw new CheckedException(e.getMessage(), e, ErrorCodeConstants.IO_0001);
		}
		return doc;
	}

	public String getNonEnumClassName(String dataTypeString)
	{
		return nedtCompMap.get(dataTypeString);
	}

	public String getEnumClassName(String dataTypeString)
	{
		return edtompMap.get(dataTypeString);
	}

	public List<String> getEnumConditionList(String dataTypeString)
	{
		return edtCondMap.get(dataTypeString);
	}

	public List<String> getNonEnumConditionList(String dataTypeString)
	{
		return nedtCondMap.get(dataTypeString);
	}

}