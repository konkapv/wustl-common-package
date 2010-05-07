
package edu.wustl.common.datahandler;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ravindra_jain
 * @version 1.0
 * @created 21-Apr-2009 6:57:50 PM
 */
public class DataHandlerFactory
{

	/*private HandlerTypeEnum fileType;
	private String fileName;
	private int bufferSize;*/

	/**
	 *
	 * @param fileType
	 * @param fileName
	 * @throws ClassNotFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static AbstractDataHandler getDataHandler(HandlerTypeEnum handlerType,
			DataHandlerParameter parameters, String fileName) throws ClassNotFoundException,
			IllegalArgumentException, InstantiationException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException
	{
		AbstractDataHandler dataHandler = null;
		Integer rowCount = null;
		String delimiter = null;
		String sheetName = null;
		List<String> sheetNames = null;

		Map<ParametersEnum, Object> parametersMap = parameters.getParametersMap();

		if (parametersMap.get(ParametersEnum.BUFFERSIZE) != null)
		{
			rowCount = Integer.valueOf(parametersMap.get(ParametersEnum.BUFFERSIZE).toString());
		}
		if (parametersMap.get(ParametersEnum.DELIMITER) != null)
		{
			delimiter = parametersMap.get(ParametersEnum.DELIMITER).toString();
		}
		if (parametersMap.get(ParametersEnum.SHEET_NAME) != null)
		{
			sheetName = parametersMap.get(ParametersEnum.SHEET_NAME).toString();
		}
		if (parametersMap.get(ParametersEnum.SHEET_NAMES) != null)
		{
			sheetNames = (ArrayList<String>)parametersMap.get(ParametersEnum.SHEET_NAMES);
		}


		switch (handlerType)
		{
			case CSV :
				dataHandler = new CSVDataHandler(fileName, rowCount, delimiter);
				break;
			case TEXT :
				dataHandler = new TextDataHandler(fileName, rowCount);
				break;

			case EXCELSHEET :
				dataHandler = new ExcelsheetDataHandler(fileName,sheetName);
				break;
			case EXCELSHEET_FOR_LARGE_DATA :
				dataHandler = new ExcelsheetForLargeDataHandler(fileName,sheetNames);
				break;

		}
		return dataHandler;
	}
}