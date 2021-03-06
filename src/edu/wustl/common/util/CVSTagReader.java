/*L
 * Copyright Washington University in St. Louis, SemanticBits, Persistent Systems, Krishagni.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/wustl-common-package/LICENSE.txt for details.
 */

/*
 * Created on Jun 29, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package edu.wustl.common.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import edu.wustl.common.util.logger.Logger;


/**
 * @author kapil_kaveeshwar
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CVSTagReader
{
	/**
     * Reads the file and returns the CVS tag from comment section.
     * File pattern is 
     *
		 /* $Name: 1.1 $
		 * $Log: CVSTagReader.java,v $
		 * Revision 1.1  2006/07/04 10:50:37  Kapil
		 * Read the tag information from a file.
		 *
		 * Revision 1.1  2006/06/28 14:02:17  Kapil
		 * Test cvs keywords
		 * /	
	 * @param file: File in whcih tag information is available
	 * @return parse and retrun the CVS tag form whcih check-out is done. 
	 * Returns null if any IOError occures or if tag can not be parsed.
     **/
    public String readTag(String file)
    {
    	try
		{
	    	BufferedReader reader = new BufferedReader(new FileReader(file));
	    	String line = "";
	    	while((line=reader.readLine())!=null)
	    	{
	    		if(line.indexOf("$Name:")!=-1)
	    		{
	    			String tag = parseTag(line);
	    			Logger.out.debug("tag "+tag);
	    			return tag;
	    		}
	    	}
		}
    	catch(IOException exp)
		{
    		//Any IOException is an invalid condition; therefore tag is null.
    		Logger.out.error(exp.getMessage(), exp);
		}
    	return null;
    }
    
    /**
     * @param str one line string that contains CVS tag
     * @return Returns the CVS tag parse from the following pattern.
     * Line pattern: * $Name: 1.1 $
     * Example1: * $Name: 1.1 $
     * Example2: * $Name: 1.1 $
     * */
    private String parseTag(String str)
    {
    	try
		{
    		//Tokenizer with default delimiter
	    	StringTokenizer tok = new StringTokenizer(str);
	    	
	    	//Ignore first token: "*:"
	    	String firstTok = tok.nextToken();
	    	if(firstTok.equals("*"))
	    	{
	    		//Ignore 2nd token: "$Name:"
	    		String secondTok = tok.nextToken();
	    		if(secondTok.equals("$Name:"))
	    		{
	    	    	//Third token is the tag
	    	    	return tok.nextToken();
	    		}
	    	}
		}
    	catch(Exception exp)
		{
    		//Any exception to the pattern is invalid condition therefore tag is null.
    		Logger.out.error(exp.getMessage(), exp);
		}
    	return null;
    }
}