/*
 * Created on Jul 19, 2004
 *
 */

package edu.wustl.common.util.global;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import edu.wustl.common.util.logger.Logger;

/**
 * This class is used to retrieve values of keys from the ApplicationResources.properties file.
 * @author kapil_kaveeshwar
 */
public class ApplicationProperties
{
	/**
	 * Generic resource bundle.
	 */
	private static ResourceBundle bundle;

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(ApplicationProperties.class);

	/**
	 * Load the resource bundle file.
	 * @param baseName Resource Bundle file name.
	 */
	public static void initBundle(String baseName)
	{
		bundle = ResourceBundle.getBundle(baseName);
	}

	/**
	 * @param theKey key in a application property file
	 * @return the value of key.
	 */
	public static String getValue(String theKey)
	{
		String val = TextConstants.EMPTY_STRING;
		if (bundle == null)
		{
			logger.fatal("resource bundle is null cannot return value for key " + theKey);
		}
		else
		{
			val = bundle.getString(theKey);
		}
		return val;
	}

	/**
	 * This method should be used when you want to
	 * customize error message with multiple replacement parameters.
	 *
	 * @param theKey - error key
	 * @param placeHolders - replacement Strings
	 * @return - complete error message
	 */
	public static String getValue(String theKey, List<String> placeHolders)
	{
		String msg = TextConstants.EMPTY_STRING;
		if (bundle == null)
		{
			logger.fatal("resource bundle is null cannot return value for key " + theKey);
		}
		else
		{
			msg = bundle.getString(theKey);
			StringBuffer message = new StringBuffer(msg);
			for (int i = 0; i < placeHolders.size(); i++)
			{
				message.replace(message.indexOf("{"),
						message.indexOf("}") + 1,placeHolders.get(i));
			}
			msg=message.toString();
		}
		return msg;
	}

	/**
	 * This method should be used when you want to customize error message with single replacement parameter.
	 * @param theKey - error key
	 * @param placeHolder - replacement Strings
	 * @return - complete error message
	 */
	public static String getValue(String theKey, String placeHolder)
	{
		List<String> temp = new ArrayList<String>();
		temp.add(placeHolder);
		return getValue(theKey, temp);
	}

}