package edu.wustl.common.transformer;

import edu.wustl.common.actionForm.AbstractActionForm;
import edu.wustl.common.actionForm.IValueObject;
import edu.wustl.common.domain.AbstractDomainObject;
import edu.wustl.common.exception.AssignDataException;
import edu.wustl.common.exception.BizLogicException;
import edu.wustl.common.factory.AbstractFactoryConfig;
import edu.wustl.common.factory.ITransformerFactory;
import edu.wustl.common.util.logger.Logger;

public class TransformerUtil {

	private static final Logger LOGGER = Logger.getCommonLogger(TransformerUtil.class);
	
	public static <T> T transform(IValueObject form, AbstractDomainObject abstractDomain) throws AssignDataException 
	{
		try
		{
			T result = null;
			ITransformerFactory transformerFactory = AbstractFactoryConfig.getInstance().getTransformFactory();
			AbstractActionForm abstractForm = (AbstractActionForm) form;
			int formId = abstractForm.getFormId();
			Transformer<T> transformer = transformerFactory.getTransformer(formId);
			if (transformer == null) 
			{
				throw new RuntimeException("No transformer registered for " + formId);
			}
			return transformer.transform(abstractDomain, form);
		}
		catch (BizLogicException exp) 
		{
			LOGGER.error(exp.getMessage(), exp);
			throw new AssignDataException(null, exp,"Exception occureed during Transformer-"+exp.getMessage());
		}
	}
}
