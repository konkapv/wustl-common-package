
package edu.wustl.common.exceptionformatter;

import java.sql.Connection;
import java.text.MessageFormat;

import edu.wustl.common.util.dbmanager.HibernateMetaData;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.logger.Logger;

/**
 * Description: Object Not Found Formatter.
 *
 */
public class ObjectNotFoundFormatter implements ExceptionFormatter
{

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(ObjectNotFoundFormatter.class);

	/**
	 * This method format Message.
	 * @param objExcp Exception
	 * @param args arguments
	 * @return formatted Error Message.
	 */
	public String formatMessage(Exception objExcp, Object[] args)
	{
		// TODO Auto-generated method stub
		String formattedErrMsg = null;
		String temp1 = "exists: ";
		String temp2 = "class: ";
		String temp3 = "given ";
		int startIndex = -1;
		int endIndex = -1;
		int tempIndex = -1;
		Connection connection = getConnection(args);
		try
		{
			// get Message from exception object
			String message = objExcp.getMessage();

			// get column name from message
			startIndex = message.indexOf(temp1) - 1;
			tempIndex = message.indexOf(temp3) + temp3.length();
			String columnName = message.substring(tempIndex, startIndex);
			logger.debug(columnName + "--" + columnName.length());

			// get column value on from message for which object was not found.
			startIndex = message.indexOf(temp1) + temp1.length();
			endIndex = message.indexOf(",", startIndex);
			String value = message.substring(startIndex, endIndex);
			logger.debug(value + "  " + value.length());

			// get class name from message
			startIndex = message.indexOf(temp2) + temp2.length();
			String className = message.substring(startIndex);
			logger.debug(className + "--" + className.length());
			Class classObj = Class.forName(className);
			// get table name from class
			String displayName = ExceptionFormatterFactory.getDisplayName(HibernateMetaData
					.getTableName(classObj), connection);

			Object[] arguments = new Object[]{displayName, columnName, value};

			formattedErrMsg = MessageFormat.format(Constants.OBJECT_NOT_FOUND_ERROR, arguments);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			formattedErrMsg = Constants.GENERIC_DATABASE_ERROR;
		}
		return formattedErrMsg;
	}

	/**
	 * get Connection.
	 * @param args arguments
	 * @return Connection
	 */
	private Connection getConnection(Object[] args)
	{
		Connection connection = null;
		if (args[1] == null)
		{
			logger.debug("Error Message: Connection object not given");
		}
		else
		{
			connection = (Connection) args[1];
		}
		return connection;
	}

	/**
	 * @param args arguments.
	 */
	public static void main(String[] args)
	{
		String formattedErrMsg = null;
		String temp1 = "exists: ";
		String temp2 = "class: ";
		String temp3 = "given ";
		int startIndex = -1;
		int endIndex = -1;
		int tempIndex = -1;
		try
		{
			// get Message from exception object
			String message = "No row with the given identifier exists: 123," +
					" of class: edu.wustl.catissuecore.domain.StorageContainer";

			// get column name from message
			startIndex = message.indexOf(temp1) - 1;
			tempIndex = message.indexOf(temp3) + temp3.length();
			String columnName = message.substring(tempIndex, startIndex);

			// get column value on from message for which object was not found.
			startIndex = message.indexOf(temp1) + temp1.length();
			endIndex = message.indexOf(",", startIndex);
			String value = message.substring(startIndex, endIndex);

			// get class name from message
			startIndex = message.indexOf(temp2) + temp2.length();
			// get table name from class
			String displayName = "dispname";

			Object[] arguments = new Object[]{displayName, columnName, value};

			formattedErrMsg = MessageFormat.format(Constants.OBJECT_NOT_FOUND_ERROR, arguments);
			logger.debug(formattedErrMsg);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage(), e);
			formattedErrMsg = Constants.GENERIC_DATABASE_ERROR;
		}

	}

}
