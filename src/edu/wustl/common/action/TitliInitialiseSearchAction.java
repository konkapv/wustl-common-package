/**
 *
 */

package edu.wustl.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import titli.model.Titli;
import titli.model.TitliException;
import edu.wustl.common.util.TitliTableMapper;
import edu.wustl.common.util.global.Constants;
import edu.wustl.common.util.logger.Logger;

/**
 * Initialize Titli Search.
 *
 * @author Juber Patel
 *
 */
public class TitliInitialiseSearchAction extends Action
{

	/**
	 * logger Logger - Generic logger.
	 */
	private static org.apache.log4j.Logger logger = Logger.getLogger(TitliInitialiseSearchAction.class);

	/**
	 * @param mapping the mapping
	 * @param form the action form
	 * @param request the request
	 * @param response the response
	 * @return success,in case of success of getting Instance.
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	{
		logger.info("in execute method");
		try
		{
			Titli.getInstance();
			TitliTableMapper.getInstance();
		}
		catch (TitliException e)
		{
			logger.error("TitliException in InitialiseTitliSearchAction : " + e.getMessage(), e);
		}
		return mapping.findForward(Constants.SUCCESS);
	}
}