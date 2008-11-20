package edu.wustl.common.util.global;

/**
 * This file is to store all Text constants.
 * @author ravi_kumar
 *
 */
public class TextConstants
{
	/**
	 * constants for line seperator.
	 */
	public static final String LINE_SEPARATOR = System.getProperty(("line.separator"));
	/**
	 * constants for two consecutive new line character.
	 */
	public static final String TWO_LINE_BREAK = "\n\n";
	/**
	 * Constant for new line character.
	 */
	public static final String LINE_BREAK = "\n";
	/**
	 * Constant for tab character.
	 */
	public static final String TAB = "\t";
	/**
	 * Constant for Empty String.
	 */
	public static final String EMPTY_STRING = "";
	/**
	 * Constant for zero.
	 */
	public static final String STR_ZERO = "0";
	/**
	 * XML file name to initialize title table mappting.
	 */
	public static final String TITLI_TABLE_MAPPING_FILE="titli-table-mapping.xml";
	/**
	 * XML file name to initialize Privileges Map.
	 */
	public static final String PERMSN_MAP_DET_FILE="PermissionMapDetails.xml";

	public static final String ERROR_KEY_FOR_TABLE = "simpleQuery.object.required";
	public static final String ERROR_KEY_FOR_FIELD = "simpleQuery.attribute.required";
}