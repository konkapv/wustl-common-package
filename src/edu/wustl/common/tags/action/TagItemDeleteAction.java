
package edu.wustl.common.tags.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import edu.wustl.common.tags.factory.TagBizlogicFactory;
import edu.wustl.common.util.global.Constants;

public class TagItemDeleteAction extends Action
{

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		String entityTagItem = (String) request.getParameter(Constants.ENTITY_TAGITEM);
		String tagItemIdentifier = (String) request.getParameter(Constants.TAGITEMID_STRING);

		long tagItemId = Long.parseLong(tagItemIdentifier);

		TagBizlogicFactory.getBizLogicInstance(entityTagItem).deleteTagItem(entityTagItem,
				tagItemId);
		return null;
	}
}