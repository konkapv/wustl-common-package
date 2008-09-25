
package edu.wustl.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.global.Variables;
import edu.wustl.common.util.logger.Logger;

/**
 * 
 *<p>Title: </p>
 *<p>Description:  </p>
 *<p>Copyright: (c) Washington University, School of Medicine 2005</p>
 *<p>Company: Washington University, School of Medicine, St. Louis.</p>
 *@author Aarti Sharma
 *@version 1.0
 */
public class CommonExceptionHandler extends ExceptionHandler
{

	private static org.apache.log4j.Logger logger = Logger.getLogger(CommonExceptionHandler.class);
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ExceptionHandler#execute(java.lang.Exception, org.apache.struts.config.ExceptionConfig, org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */	
	public ActionForward execute(Exception exception, ExceptionConfig exConfig, ActionMapping mapping,
			ActionForm formInstance, HttpServletRequest request, HttpServletResponse response)
			throws ServletException
	{
		String errorMessage = getErrorMsg(exception);
		logger.error(errorMessage, exception);
		/** Modified by amit_doshi
		*  code reviewer abhijit_naik 
		*/
		request.getSession().setAttribute(
				Constants.ERROR_DETAIL,
				"Unhandled Exception occured in " + Variables.applicationName + " : "
						+ exception.getMessage());
		return super.execute(exception, exConfig, mapping, formInstance, request, response);

	}

	/**
	* @param exception the Exception.
	* @return the string of the error message.
	*/
	public String getErrorMsg(Exception exception)
	{
		StringBuffer msg = new StringBuffer("Exception was NULL");

		if (exception != null)
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintWriter printWriter = new PrintWriter(baos, true);
			exception.printStackTrace(printWriter);
			msg.append("Unhandled Exception occured in caTISSUE Core \nMessage: ").append(exception.getMessage())
				.append("\nStackTrace: ").append(baos.toString());
		}

		return msg.toString();

	}

}
