/*
 * Created on Aug 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package edu.wustl.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import edu.common.dynamicextensions.domaininterface.AttributeInterface;
import edu.wustl.common.beans.NameValueBean;
import edu.wustl.common.beans.SessionDataBean;
import edu.wustl.common.dao.DAOFactory;
import edu.wustl.common.dao.JDBCDAO;
import edu.wustl.common.querysuite.security.utility.CsmCacheManager;
import edu.wustl.common.tree.TreeNodeImpl;
import edu.wustl.common.util.dbManager.DAOException;
import edu.wustl.common.util.dbManager.HibernateMetaData;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.global.TextConstants;
import edu.wustl.common.util.global.Variables;
import edu.wustl.common.util.logger.Logger;

/**
 * @author kapil_kaveeshwar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Utility
{

	/**
	 * logger -Generic Logger
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(Utility.class);
	/**
	 * Parses the string format of date in the given format and returns the Data object.
	 * @param date the string containing date.
	 * @param pattern the pattern in which the date is present.
	 * @return the string format of date in the given format and returns the Data object.
	 * @throws ParseException
	 */
	public static Date parseDate(String date, String pattern) throws ParseException
	{
		Date dateObj=null;
		if (date != null && !date.trim().equals(TextConstants.EMPTY_STRING))
		{
			try
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				dateObj = dateFormat.parse(date);
			}
			catch (ParseException e)
			{
				if (pattern == null || TextConstants.EMPTY_STRING.equals(pattern))
				{
					throw new ParseException( new StringBuffer("Date '")
					.append(date).append("' is not in format of ")
					.append(Constants.DATE_PATTERN_MM_DD_YYYY).toString(), 0);
				}
			}
		}
		return dateObj;
	}

	/**
	 * Parses the string format of date in the given format and returns the Data object.
	 * @param date the string containing date.
	 * @param pattern the pattern in which the date is present.
	 * @return the string format of date in the given format and returns the Data object.
	 * @throws ParseException
	 */
	public static Date parseDate(String date) throws ParseException
	{
		String pattern = datePattern(date);

		return parseDate(date, pattern);
	}

	public static String datePattern(String strDate)
	{
		String datePattern = "";
		String dtSep = "";
		boolean result = true;
		try
		{
			Pattern regExp = Pattern.compile("[0-9]{2}-[0-9]{2}-[0-9]{4}", Pattern.CASE_INSENSITIVE);
			Matcher mat = regExp.matcher(strDate);
			result = mat.matches();

			if (result)
			{
				dtSep = Constants.DATE_SEPARATOR;
				datePattern = "MM" + dtSep + "dd" + dtSep + "yyyy";
			}

			// check for  / separator
			if (!result)
			{
				regExp = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}", Pattern.CASE_INSENSITIVE);
				mat = regExp.matcher(strDate);
				result = mat.matches();
				//System.out.println("is Valid Date Pattern : / : "+result);
				if (result)
				{
					dtSep = Constants.DATE_SEPARATOR_SLASH;
					datePattern = "MM" + dtSep + "dd" + dtSep + "yyyy";
				}
			}

			if (!result)
			{
				regExp = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}", Pattern.CASE_INSENSITIVE);
				mat = regExp.matcher(strDate);
				result = mat.matches();

				if (result)
				{
					dtSep = Constants.DATE_SEPARATOR;
					datePattern = "yyyy" + dtSep + "mm" + dtSep + "dd";
				}
			}

			// check for  / separator
			if (!result)
			{
				regExp = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}", Pattern.CASE_INSENSITIVE);
				mat = regExp.matcher(strDate);
				result = mat.matches();
				if (result)
				{
					dtSep = Constants.DATE_SEPARATOR_SLASH;
					datePattern = "yyyy" + dtSep + "mm" + dtSep + "dd";
				}
			}
		}
		catch (Exception exp)
		{
			logger.error("No pattern for date:" +strDate,exp);
		}
		/*if(dtSep.trim().length()>0)
		    datePattern = "MM"+dtSep+"dd"+dtSep+"yyyy";*/
		/*else
		{
		    try
		    {
		        Pattern re = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}", Pattern.CASE_INSENSITIVE);
		        Matcher  mat =re.matcher(strDate); 
		        result = mat.matches();
		        
		        if(result)
		            dtSep  = Constants.DATE_SEPARATOR; 
		        
		        // check for  / separator
		        if(!result)
		        {
		            re = Pattern.compile("[0-9]{4}/[0-9]{2}/[0-9]{2}", Pattern.CASE_INSENSITIVE);
		            mat =re.matcher(strDate); 
		            result = mat.matches();
		            //System.out.println("is Valid Date Pattern : / : "+result);
		            if(result)
		                dtSep  = Constants.DATE_SEPARATOR_SLASH; 
			}
		}
		catch(Exception exp)
		{
			logger.error("Utility.datePattern() : exp : " + exp);
		}
		if(dtSep.trim().length()>0)
		        datePattern = "yyyy"+dtSep+"mm"+dtSep+"dd";
		}*/
		return datePattern;
	}

	public static String createAccessorMethodName(String attr, boolean isSetter)
	{
		String firstChar = attr.substring(0, 1);
		String str = "get";
		if (isSetter)
		{
			str = "set";
		}
		return str + firstChar.toUpperCase() + attr.substring(1);
	}

	public static Object getValueFor(Object obj, String attrName) throws Exception
	{
		//Create the getter method of attribute
		String methodName = Utility.createAccessorMethodName(attrName, false);
		Class objClass = obj.getClass();
		Method method = objClass.getMethod(methodName, new Class[0]);

		return method.invoke(obj, new Object[0]);
	}

	/**
	 * Start: Change for API Search   --- Jitendra 06/10/2006
	 * In Case of Api Search, previoulsy it was failing since there was default 
	 * class level initialization 
	 * on domain object. For example in ParticipantMedicalIdentifier object, it was initialized as 
	 * protected Site site= new Site(); So we removed default class level initialization on domain object.
	 * Hence getValueFor() method was returning null. So write new method SetValueFor() which will 
	 * instantiate new Object and set it in parent object.
	 * @param obj
	 * @param attrName
	 * @return
	 * @throws Exception
	 */
	public static Object setValueFor(Object obj, String attrName, Object attrValue)
			throws Exception
	{

		//create the setter method for the attribute.
		String methodName = Utility.createAccessorMethodName(attrName, true);
		Method method = findMethod(obj.getClass(), methodName);
		Object object=attrValue;
		if (object == null)
		{
			object = method.getParameterTypes()[0].newInstance();
		}
		Object objArr[] = {object};
		//set the newInstance to the setter nethod of parent obj
		method.invoke(obj, objArr);
		return object;

	}

	private static Method findMethod(Class objClass, String methodName) throws Exception
	{
		Method []methods = objClass.getMethods();
		Method method=null;
		for (int i = 0; i < methods.length; i++)
		{
			if (methods[i].getName().equals(methodName))
			{
				method= methods[i];
				break;
			}
		}
		return method;
	}

	public static Object getValueFor(Object obj, Method method) 
				throws IllegalAccessException, InvocationTargetException
	{
		return method.invoke(obj, new Object[0]);

	}

	/**
	 * @param objectIds
	 * @return
	 */
	public static String getArrayString(Object[] objectIds)
	{
		StringBuffer arrayStr = new StringBuffer();
		for (int i = 0; i < objectIds.length; i++)
		{
			arrayStr.append(objectIds[i].toString()).append(',');
		}
		return arrayStr.toString();
	}

	/**
	 * @param fullClassName Full qualified name
	 * @return
	 */
	public static Class getClassObject(String fullClassName)
	{
		Class className = null;
		try
		{
			className = Class.forName(fullClassName);
		}
		catch (ClassNotFoundException classNotExcp)
		{
			logger.warn("Didn't find any class as "+fullClassName,classNotExcp);
		}

		return className;
	}

	/**
	 * Changes the format of the string compatible to Grid Format,
	 * removing escape characters and special characters from the string
	 * @param obj - Unformatted obj to be printed in Grid Format
	 * @return obj - Foratted obj to print in Grid Format
	 */
	public static Object toGridFormat(Object obj)
	{
		Object retObj=obj;
		if (obj instanceof String)
		{
			StringBuffer tokenedString = new StringBuffer();
			StringTokenizer tokenString = new StringTokenizer((String) obj, "\n\r\f");
			while (tokenString.hasMoreTokens())
			{
				tokenedString.append(tokenString.nextToken()).append(' ');
			}
			String gridFormattedStr = new String(tokenedString);
			retObj = gridFormattedStr.replaceAll("\"", "\\\\\"");
		}

		return retObj;
	}

	public static boolean isNull(Object obj)
	{
		boolean isNull=false;
		if (obj == null)
		{
			isNull= true;
		}
		return isNull;
	}

	/**
	 * Instantiates and returns the object of the class name passed.
	 * @param className The class name whose object is to be instantiated.
	 * @return the object of the class name passed.
	 */
	public static Object getObject(String className)
	{
		Object object = null;

		try
		{
			Class classObject = Utility.getClassObject(className);
			object = classObject.newInstance();
		}
		catch (InstantiationException instExp)
		{
		}
		catch (IllegalAccessException illAccExp)
		{
		}

		return object;
	}

	public static Object[] addElement(Object[] array, Object obj)
	{
		Object newObjectArr[] = new Object[array.length + 1];

		if (array instanceof String[])
		{
			newObjectArr = new String[array.length + 1];
		}

		for (int i = 0; i < array.length; i++)
		{
			newObjectArr[i] = array[i];
		}
		newObjectArr[newObjectArr.length - 1] = obj;
		return newObjectArr;
	}

	public static String parseAttributeName(String methodName) throws Exception
	{
		String attributeName = "";
		int index = methodName.indexOf("get");
		if (index != -1)
		{
			attributeName = methodName.substring(index + "get".length());
		}

		String firstChar = (attributeName.charAt(0) + "").toLowerCase();
		attributeName = firstChar + attributeName.substring(1);

		logger.debug("attributeName <" + attributeName + ">");

		return attributeName;
	}

	/**
	 * @param list
	 * @return
	 */
	public static List removeNull(List list)
	{
		List nullFreeList = new ArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			if (list.get(i) != null)
			{
				nullFreeList.add(list.get(i));
			}
		}
		return nullFreeList;
	}

	/**
	 * Parses the fully qualified classname and returns only the classname.
	 * @param qualifiedName The fully qualified classname. 
	 * @return The classname.
	 */
	public static String parseClassName(String qualifiedName)
	{
		try
		{
			return qualifiedName.substring(qualifiedName.lastIndexOf('.') + 1);
		}
		catch (Exception e)
		{
			return qualifiedName;
		}
	}

	/**
	 * Constants that will appear in HQL for retreiving Attributes of the Collection data type.
	 */
	private static final String ELEMENTS = "elements";

	/**
	 * To Create the attribute name for HQL select part.
	 * If the  selectColumnName is in format "elements(<attributeName>)" then it will return String
	 * as *  "elments(<className>.<AttributeName>)" 
	 * else it will return String in format "<className>.<AttributeName>"
	 * @param className The className
	 * @param selectColumnName The select column name passed to form HQL. either in format
	 *  "elements(<attributeName>)" or "<AttributeName>"
	 * @return The Select column name for the HQL.
	 */
	public static String createAttributeNameForHQL(String className, String selectColumnName)
	{
		String attribute;
		// Check whether the select Column start with "elements" & ends with ")" or not
		if (isColumnNameContainsElements(selectColumnName))
		{
			int startIndex = selectColumnName.indexOf('(') + 1;
			attribute = selectColumnName.substring(0, startIndex) + className + "."
					+ selectColumnName.substring(startIndex);
		}
		else
		{
			attribute = className + "." + selectColumnName;
		}
		return attribute;
	}

	/**
	 * Check whether the select Column start with "elements" & ends with ")" or not
	 * @param columnName The columnName
	 * @return true if the select Column start with "elements" & ends with ")" or not
	 */
	public static boolean isColumnNameContainsElements(String columnName)
	{
		String colName=columnName.toLowerCase().trim();
		return colName.startsWith(ELEMENTS) && colName.endsWith(")");
	}

	/**
	 * Returns name of FormBean specified in struts-config.xml for passed Object of FormBean
	 * @param obj - FormBean object 
	 * @return String - name of FormBean object
	 */
	public static String getFormBeanName(Object obj)
	{
		String objClassName = obj.getClass().toString();
		objClassName = objClassName.substring((objClassName.lastIndexOf('.') + 1),(objClassName.length()));
		String classNamefstChar = objClassName.substring(0, 1);
		String formBeanName = classNamefstChar.toLowerCase()
				+ objClassName.substring(1, (objClassName.length()));
		return formBeanName;
	}

	/**
	 * Parses the Date in given format and returns the string representation.
	 * @param date the Date to be parsed.
	 * @param pattern the pattern of the date.
	 * @return
	 */
	public static String parseDateToString(Date date, String pattern)
	{
		String dateStr = TextConstants.EMPTY_STRING;
		//TODO Check for null
		if (date != null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateStr = dateFormat.format(date);
		}
		return dateStr;
	}

	public static String toString(Object obj)
	{
		if (obj == null)
		{
			return "";
		}

		return obj.toString();
	}

	public static String[] getTime(Date date)
	{
		String[] time = new String[2];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		time[0] = Integer.toString(cal.get(Calendar.HOUR_OF_DAY));
		time[1] = Integer.toString(cal.get(Calendar.MINUTE));
		return time;
	}

	public static Long[] toLongArray(Collection collection)
	{
		logger.debug(collection.toArray().getClass().getName());

		Long obj[] = new Long[collection.size()];

		int index = 0;
		Iterator iterator = collection.iterator();
		while (iterator.hasNext())
		{
			obj[index] = (Long) iterator.next();
			logger.debug("obj[index] " + obj[index].getClass().getName());
			index++;
		}
		return obj;
	}

	public static int toInt(Object obj)
	{
		int value = 0;
		if (obj != null)
		{
			String objVal = String.valueOf(obj);
			if (objVal.length() > 0)
			{
				Integer intObj = Integer.parseInt(objVal);
				value = intObj.intValue();
			}
		}
		return value;
	}

	public static long toLong(Object obj)
	{
		long value = 0;
		if (obj != null)
		{
			String objVal = String.valueOf(obj);
			if (objVal.length() > 0)
			{
				Long intObj = Long.parseLong(objVal);
				value = intObj.longValue();
			}
		}
		return value;

	}

	public static double toDouble(Object obj)
	{
		double value = 0;
		if (obj == null)
		{
			return value;
		}
		else
		{
			Double dblObj = (Double) obj;
			value = dblObj.doubleValue();
			return value;
		}
	}

	/**
	 * checking whether key's value is persisted or not
	 *
	 */
	public static boolean isPersistedValue(Map map, String key)
	{
		Object obj = map.get(key);
		String val = null;
		if (obj != null)
		{
			val = obj.toString();
		}
		if ((val != null && !(val.equals("0"))) && !(val.equals("")))
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	//	Mandar 17-Apr-06 Bugid : 1667 :- URL is incomplete displays /.
	/**
	 * @param requestURL URL generated from the request.
	 * Sets the application URL in the Variables class after generating it in proper format.
	 */
	public static void setApplicationURL(String requestURL)
	{
		logger.debug("17-Apr-06 : requestURL : " + requestURL);
		// Mandar : 17-Apr-06 : 1667 : caTissuecore Application URL is displayed as "/"
		String ourUrl = "";
		try
		{
			URL aURL = new URL(requestURL);
			ourUrl = aURL.getProtocol() + "://" + aURL.getAuthority() + aURL.getPath();
			ourUrl = ourUrl.substring(0, ourUrl.lastIndexOf('/'));
			logger.debug("Application URL Generated : " + ourUrl);
		}
		catch (MalformedURLException urlExp)
		{
			logger.error(urlExp.getMessage(), urlExp);
		}
		if (Variables.catissueURL != null && Variables.catissueURL.trim().length() == 0)
		{
			Variables.catissueURL = ourUrl;
			logger.debug("Application URL set: " + Variables.catissueURL);
		}
	}//setApplicationURL()

	/**
	 * @param selectedMenuID Menu that is clicked
	 * @param currentMenuID Menu that is being checked
	 * @param normalMenuClass style class for normal menu
	 * @param selectedMenuClass style class for selected menu 
	 * @param menuHoverClass  style class for hover effect
	 * @return The String generated for the TD tag. Creates the selected menu or normal menu.
	 */
	public static String setSelectedMenuItem(int selectedMenuID, int currentMenuID,
			String normalMenuClass, String selectedMenuClass, String menuHoverClass)
	{
		String returnStr = "";
		if (selectedMenuID == currentMenuID)
		{
			returnStr = "<td class=\"" + selectedMenuClass
					+ "\" onmouseover=\"changeMenuStyle(this,\'" + selectedMenuClass
					+ "\')\" onmouseout=\"changeMenuStyle(this,\'" + selectedMenuClass + "\')\">";
		}
		else
		{
			returnStr = "<td class=\"" + normalMenuClass
					+ "\" onmouseover=\"changeMenuStyle(this,\'" + menuHoverClass
					+ "\')\" onmouseout=\"changeMenuStyle(this,\'" + normalMenuClass + "\')\">";
		}

		return returnStr;
	}

	/**
	 * @param str String to be converted to Proper case.
	 * @return The String in Proper case.
	 */
	public static String initCap(String str)
	{
		String retStr = "";
		if (str != null && str.trim().length() > 0)
		{
			String firstCharacter = str.substring(0, 1);
			String otherData = str.substring(1);
			retStr = firstCharacter.toUpperCase() + otherData.toLowerCase();
		}
		else
		{
			logger.debug("Utility.initCap : - String provided is either empty or null" + str);
		}

		return retStr;
	}

	/**
	 * This method is used in JSP pages to get the width of columns for the html fields. 
	 * It acts as a wrapper for the HibernateMetaData getColumnWidth() method.
	 * @param className Class name of the field
	 * @param attributeName Attribute name of the field.
	 * @return Length of the column. 
	 * @see HibernateMetaData.getColumnWidth()  
	 */
	public static String getColumnWidth(Class className, String attributeName)
	{
		String columnLength = toString(Integer.valueOf((HibernateMetaData.getColumnWidth(className,
				attributeName))));
		logger.debug(className.getName() + " : " + attributeName + " : " + columnLength);
		return columnLength;
	}

	//Mandar 17-Apr-06 Bugid : 1667 : end

	/**
	 * To sort the Tree nodes based on the comparators overidden by the TreeNodeImpl object.
	 * @param nodes reference to the Vector containing object of class implementing TreeNodeImpl class.
	 */
	public static void sortTreeVector(Vector nodes)
	{
		Collections.sort(nodes);
		for (int i = 0; i < nodes.size(); i++)
		{
			TreeNodeImpl child = (TreeNodeImpl) nodes.get(i);
			sortTreeVector(child.getChildNodes());
		}
	}

	/**
	 * Remove special characters and white space from a string.Added for Bug#5142 
	 * @param str string.
	 * @return String after removing special characters.
	 */
	public static String removeSpecialCharactersFromString(String str)
	{
		String regexExpression = "[\\p{Punct}\\s]";
		return str.replaceAll(regexExpression, "");
	}

	/**
	 * Returns the label for objects name. It compares ascii value of each char for lower or upper case and 
	 * then forms a capitalized lebel. 
	 * eg firstName is converted to First Name
	 * @param objectName name of the attribute
	 * @return capitalized label
	 */
	public static String getDisplayLabel(String objectName)
	{
		StringBuffer attrLabel = new StringBuffer();
		boolean isPrevLetLCase = false;
		int len = objectName.length();
		for (int i = 0; i < len; i++)
		{
			char attrChar = objectName.charAt(i);
			int asciiValue = attrChar;
			if (asciiValue >= 65 && asciiValue <= 90)
			{
				if (i == 0)
				{
					attrLabel.append(attrChar);
				}
				else
				{
					attrLabel.append(' ').append(attrChar);
				}
				for (int k = i + 1; k < len; k++)
				{
					attrChar = objectName.charAt(k);
					asciiValue = attrChar;
					if (asciiValue >= 65 && asciiValue <= 90)
					{
						if (isPrevLetLCase)
						{
							attrLabel.append(' ').append(attrChar);
							isPrevLetLCase = false;
						}
						else
						{
							attrLabel.append(attrChar);
						}
						i++;
					}
					else
					{
						isPrevLetLCase = true;
						attrLabel.append(attrChar);
						i++;
					}
				}
			}
			else
			{
				if (i == 0)
				{
					int capitalAsciiValue = asciiValue - 32;
					attrLabel.append(capitalAsciiValue);
				}
				else
				{
					attrLabel.append(attrChar);
				}
			}
		}
		return attrLabel.toString();
	}

	/**
	 * Forms display name for attribute as className : attribute name
	 * @param attribute AttributeInterface
	 * @return columnDisplayName
	 */
	public static String getDisplayNameForColumn(AttributeInterface attribute)
	{
		String columnDisplayName = "";
		String className = parseClassName(attribute.getEntity().getName());
		className = getDisplayLabel(className);
		String attributeLabel = getDisplayLabel(attribute.getName());
		columnDisplayName = className + " : " + attributeLabel;
		return columnDisplayName;
	}

	private static String pattern = "MM-dd-yyyy";

	public static int getMonth(String date)
	{
		int month = 0;

		month = getCalendar(date, pattern).get(Calendar.MONTH);
		month = month + 1;
		return month;
	}

	public static int getDay(String date)
	{
		int day = 0;
		day = getCalendar(date, pattern).get(Calendar.DAY_OF_MONTH);
		return day;
	}

	public static int getYear(String date)
	{
		int year = 0;
		year = getCalendar(date, pattern).get(Calendar.YEAR);
		return year;
	}

	private static Calendar getCalendar(String date, String pattern)
	{
		try
		{
			SimpleDateFormat dateformat = new SimpleDateFormat(pattern);
			Date givenDate = dateformat.parse(date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(givenDate);
			return calendar;
		}
		catch (Exception e)
		{
			logger.error(e);
			Calendar calendar = Calendar.getInstance();
			return calendar;
		}
	}

	//	public static void main(String[] args) {
	//	
	//		System.out.println(getDisplayLabelForUnderscore("Read Only"));
	//	}
	public static String getDisplayLabelForUnderscore(String objectName)
	{
		/*if(objectName.equals("PHI_ACCESS"))
		{
			return ApplicationProperties.getValue(objectName);
		}*/

		String attrLabel = "";
		int len = objectName.length();
		for (int i = 0; i < len; i++)
		{
			char attrChar = objectName.charAt(i);
			int asciiValueCurrent = attrChar;

			if (asciiValueCurrent >= 65 && asciiValueCurrent <= 90)
			{
				if (i == 0 || objectName.charAt(i - 1) == 95 || objectName.charAt(i - 1) == 32)
				{
					attrLabel = attrLabel + "" + attrChar;
				}
				else
				{
					asciiValueCurrent = asciiValueCurrent + 32;
					attrLabel = attrLabel + "" + (char) asciiValueCurrent;
				}
			}
			else if (asciiValueCurrent == 95)
			{
				if (i == 0 || i == len - 1)
				{
					continue;
				}
				else
				{
					attrLabel = attrLabel + " ";
				}
			}
			else
			{
				attrLabel = attrLabel + "" + attrChar;
			}
		}
		return attrLabel;
	}

	/**
	 * For MSR changes
	 */

	public static void initializePrivilegesMap()
	{
		Map<String, String> privDetMap = Variables.privilegeDetailsMap;
		Map<String, List<NameValueBean>> privGroupMap = Variables.privilegeGroupingMap;

		try
		{
			InputStream inputXmlFile = Utility.class.getClassLoader().getResourceAsStream(
					"PermissionMapDetails.xml");

			if (inputXmlFile != null)
			{
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = builder.parse(inputXmlFile);

				Element root = null;
				root = doc.getDocumentElement();

				if (root == null)
				{
					throw new Exception("file can not be read");
				}

				NodeList nodeList = root.getElementsByTagName("PrivilegeMapping");

				int length = nodeList.getLength();

				for (int counter = 0; counter < length; counter++)
				{
					Element element = (Element) (nodeList.item(counter));
					String key = new String(element.getAttribute("key"));
					String value = new String(element.getAttribute("value"));

					privDetMap.put(key, value);
				}

				NodeList nodeList1 = root.getElementsByTagName("siteMapping");
				int length1 = nodeList1.getLength();
				String siteListKey = "SITE";
				List<NameValueBean> sitePrivList = new ArrayList<NameValueBean>();

				for (int counter = 0; counter < length1; counter++)
				{
					Element element = (Element) (nodeList1.item(counter));
					NameValueBean nmv = new NameValueBean(new String(element.getAttribute("name")),
							new String(element.getAttribute("id")));
					sitePrivList.add(nmv);
				}

				privGroupMap.put(siteListKey, sitePrivList);

				NodeList nodeList2 = root.getElementsByTagName("collectionProtocolMapping");
				int length2 = nodeList2.getLength();
				String cpListKey = "CP";
				List<NameValueBean> cpPrivilegesList = new ArrayList<NameValueBean>();

				for (int counter = 0; counter < length2; counter++)
				{
					Element element = (Element) (nodeList2.item(counter));
					NameValueBean nmv = new NameValueBean(new String(element.getAttribute("name")),
							new String(element.getAttribute("id")));
					cpPrivilegesList.add(nmv);
				}

				privGroupMap.put(cpListKey, cpPrivilegesList);

				NodeList nodeList3 = root.getElementsByTagName("scientistMapping");
				int length3 = nodeList3.getLength();
				String scientistListKey = "SCIENTIST";
				List<NameValueBean> sintstPrivList = new ArrayList<NameValueBean>();

				for (int counter = 0; counter < length3; counter++)
				{
					Element element = (Element) (nodeList3.item(counter));
					NameValueBean nmv = new NameValueBean(new String(element.getAttribute("name")),
							new String(element.getAttribute("id")));
					sintstPrivList.add(nmv);
				}

				privGroupMap.put(scientistListKey, sintstPrivList);

				NodeList nodeList4 = root.getElementsByTagName("globalMapping");
				int length4 = nodeList4.getLength();
				String globalListKey = "GLOBAL";
				List<NameValueBean> globalPrivList = new ArrayList<NameValueBean>();

				for (int counter = 0; counter < length4; counter++)
				{
					Element element = (Element) (nodeList4.item(counter));
					NameValueBean nmv = new NameValueBean(new String(element.getAttribute("name")),
							new String(element.getAttribute("id")));
					globalPrivList.add(nmv);
				}

				privGroupMap.put(globalListKey, globalPrivList);
			}
		}
		catch (ParserConfigurationException excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		catch (SAXException excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		catch (IOException excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
	}

	/**
	 * For MSR changes
	 */
	public static List getAllPrivileges()
	{
		List<NameValueBean> allPrivileges = new ArrayList<NameValueBean>();

		List<NameValueBean> list1 = Variables.privilegeGroupingMap.get("SITE");
		List<NameValueBean> list2 = Variables.privilegeGroupingMap.get("CP");
		List<NameValueBean> list3 = Variables.privilegeGroupingMap.get("SCIENTIST");
		List<NameValueBean> list4 = Variables.privilegeGroupingMap.get("GLOBAL");

		for (NameValueBean nmv : list1)
		{
			allPrivileges.add(nmv);
		}

		for (NameValueBean nmv : list2)
		{
			allPrivileges.add(nmv);
		}

		for (NameValueBean nmv : list3)
		{
			allPrivileges.add(nmv);
		}

		for (NameValueBean nmv : list4)
		{
			allPrivileges.add(nmv);
		}

		return allPrivileges;
	}

	public static List getCPIdsList(String objName, Long identifier,
			SessionDataBean sessionDataBean, List cpIdsList)
	{
		if (objName != null && !objName.equalsIgnoreCase(Variables.mainProtocolObject))
		{
			String cpQuery = CsmCacheManager.getQueryStringForCP(objName, Integer.valueOf(identifier
					.toString()));
			JDBCDAO jdbcDao = (JDBCDAO) DAOFactory.getInstance().getDAO(Constants.JDBC_DAO);
			try
			{
				jdbcDao.openSession(sessionDataBean);

				List<List> list = null;
				list = jdbcDao.executeQuery(cpQuery, sessionDataBean, false, null);
				if (list != null && !list.isEmpty())
				{
					for (List list1 : list)
					{
						cpIdsList.add(Long.valueOf(list1.get(0).toString()));
					}
				}
			}
			catch (Exception e)
			{
				return null;
			}
			finally
			{
				try
				{
					jdbcDao.closeSession();
				}
				catch (DAOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else
		{
			cpIdsList.add(identifier);
		}
		return cpIdsList;
	}

	/**
	 * This method returns records per page from session
	 * @param session HttpSession
	 * @return no of records per page has been extracted.
	 */
	public static int getRecordsPerPage(HttpSession session)
	{
		int recordsPerPage;
		String recPerPageSessVal = (String) session
				.getAttribute(Constants.RESULTS_PER_PAGE);
		if (recPerPageSessVal == null)
		{
			recordsPerPage = Integer.parseInt(XMLPropertyHandler
					.getValue(Constants.RECORDS_PER_PAGE_PROPERTY_NAME));
			session.setAttribute(Constants.RESULTS_PER_PAGE, recordsPerPage + "");
		}
		else
		{
			recordsPerPage = Integer.parseInt(recPerPageSessVal);
		}
		return recordsPerPage;
	}

}