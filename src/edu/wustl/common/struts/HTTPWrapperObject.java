/**
 * <p>Title: HTTPWrapperObject Class>
 * <p>Description:	This class provides a wrapper object which constitutes
 * an object of AbstractDomainObject & operation that is to be performed.</p>
 * Copyright:    Copyright (c) year
 * Company: Washington University, School of Medicine, St. Louis.
 * @author Aniruddha Phadnis
 * @version 1.00
 * Created on Dec 20, 2005
 */

package edu.wustl.common.struts;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

//Gautam_COMMON_TEMP_FIX
//import edu.wustl.catissuecore.actionForm.ActionFormFactory;
//import edu.wustl.catissuecore.actionForm.LoginForm;
//import edu.wustl.catissuecore.domain.User;

import edu.wustl.common.util.global.Constants;
import edu.wustl.common.actionForm.AbstractActionForm;

public class HTTPWrapperObject implements Serializable
{
    private static final long serialVersionUID = -4958330782397508598L;
    private ActionForm formBean;
	private String operation;
	
	public HTTPWrapperObject(){}
	
	public HTTPWrapperObject(Object domainObject,String operation) throws Exception
	{
	    //creating instance of LoginForm for LOGIN operation
	    //Gautam_COMMON_TEMP_FIX
/*	    if(operation.equals(Constants.LOGIN))
		{
			User user = (User)domainObject;
			LoginForm loginForm = new LoginForm();
			loginForm.setLoginName(user.getLoginName());
			loginForm.setPassword(user.getPassword());
			formBean = loginForm;
		}
	    //assigning formBean value as null for LOGOUT operation
	    else if(operation.equals(Constants.LOGOUT))
	    {
	        formBean= null;
	    }
	    //creating instance of Form-Bean according to type of the domainObject using ActionFormFactory
		else
		{
		    AbstractActionForm abstractForm = ActionFormFactory.getFormBean(domainObject,operation);
		    abstractForm.setOperation(operation);				
			abstractForm.setAllVal(domainObject);
			formBean = abstractForm;
		}
*/		
		this.operation = operation;
	}
	
	/**
	 * Returns FormBean object
	 */
	public ActionForm getForm()
	{
	    return formBean; 
	}
	
	/**
	 * Returns Operation value
	 */
	public String getOperation()
	{
	    return this.operation;
	}
}