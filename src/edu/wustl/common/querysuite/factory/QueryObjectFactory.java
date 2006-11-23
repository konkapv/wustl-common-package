
package edu.wustl.common.querysuite.factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.common.dynamicextensions.domain.BooleanAttributeTypeInformation;
import edu.common.dynamicextensions.domain.DateAttributeTypeInformation;
import edu.common.dynamicextensions.domain.DoubleAttributeTypeInformation;
import edu.common.dynamicextensions.domain.IntegerAttributeTypeInformation;
import edu.common.dynamicextensions.domain.LongAttributeTypeInformation;
import edu.common.dynamicextensions.domaininterface.AttributeInterface;
import edu.common.dynamicextensions.domaininterface.AttributeTypeInformationInterface;
import edu.common.dynamicextensions.domaininterface.EntityInterface;
import edu.wustl.common.querysuite.category.ICategory;
import edu.wustl.common.querysuite.queryobject.DataType;
import edu.wustl.common.querysuite.queryobject.IAttribute;
import edu.wustl.common.querysuite.queryobject.IClass;
import edu.wustl.common.querysuite.queryobject.ICondition;
import edu.wustl.common.querysuite.queryobject.IConstraints;
import edu.wustl.common.querysuite.queryobject.IExpression;
import edu.wustl.common.querysuite.queryobject.IExpressionId;
import edu.wustl.common.querysuite.queryobject.IFunctionalClass;
import edu.wustl.common.querysuite.queryobject.IInterModelAssociation;
import edu.wustl.common.querysuite.queryobject.IIntraModelAssociation;
import edu.wustl.common.querysuite.queryobject.ILogicalConnector;
import edu.wustl.common.querysuite.queryobject.IQuery;
import edu.wustl.common.querysuite.queryobject.IRule;
import edu.wustl.common.querysuite.queryobject.LogicalOperator;
import edu.wustl.common.querysuite.queryobject.RelationalOperator;
import edu.wustl.common.querysuite.queryobject.impl.Attribute;
import edu.wustl.common.querysuite.queryobject.impl.Condition;
import edu.wustl.common.querysuite.queryobject.impl.Constraints;
import edu.wustl.common.querysuite.queryobject.impl.ExpressionId;
import edu.wustl.common.querysuite.queryobject.impl.FunctionalClass;
import edu.wustl.common.querysuite.queryobject.impl.InterModelAssociation;
import edu.wustl.common.querysuite.queryobject.impl.IntraModelAssociation;
import edu.wustl.common.querysuite.queryobject.impl.LogicalConnector;
import edu.wustl.common.querysuite.queryobject.impl.Query;
import edu.wustl.common.querysuite.queryobject.impl.Rule;

/**
 * factory to create the query objects, query engine etc...
 * @version 1.0
 * @updated 11-Oct-2006 02:57:23 PM
 */
public class QueryObjectFactory
{

	public QueryObjectFactory()
	{

	}

	public void finalize() throws Throwable
	{

	}

	public static ILogicalConnector createLogicalConnector(LogicalOperator logicalOperator,
			int nestingNumber)
	{
		return new LogicalConnector(logicalOperator, nestingNumber);
	}

	public static IAttribute createAttribute(DataType dataType, IClass umlCLass,
			String attributeName)
	{
		return new Attribute(dataType, umlCLass, attributeName);
	}

	public static IAttribute createAttribute()
	{
		return new Attribute(null, null, null);
	}

	public static IClass createClass()
	{
		return createClass(null, null, null, false);
	}

	public static IClass createClass(String fullQualifiedName, List<IAttribute> attributes,
			ICategory category, boolean isVisible)
	{
		return new edu.wustl.common.querysuite.queryobject.impl.Class(fullQualifiedName,
				attributes, category, isVisible);
	}

	public static ICondition createCondition(IAttribute attribute,
			RelationalOperator relationalOperator, List<String> values)
	{
		return new Condition(attribute, relationalOperator, values);
	}

	public static ICondition createCondition()
	{
		return new Condition(null, null, null);
	}

	//	public static IExpression createExpression(IFunctionalClass functionalClass,
	//			List<IExpressionOperand> expressionOperands, List<ILogicalConnector> logicalConnectors,
	//			IExpressionId expressionId)
	//	{
	//		return new Expression(functionalClass, expressionOperands, logicalConnectors, expressionId);
	//	}
	//
	//	public static IExpression createExpression(IFunctionalClass functionalClass)
	//	{
	//		return new Expression(functionalClass, null, null, null);
	//	}

	public static IExpressionId createExpressionId(int id)
	{
		return new ExpressionId(id);
	}

	//	public static IExpressionList createExpressionList(List<IExpression> expressions)
	//	{
	////		return new ExpressionList(expressions);
	//        return null;
	//	}

	public static IConstraints createConstraints()
	{
		return new Constraints();
	}

	public static IFunctionalClass createFunctionalClass()
	{
		return new FunctionalClass();
	}

	public static IFunctionalClass createFunctionalClass(List<IAttribute> attributes,
			ICategory category)
	{
		return new FunctionalClass(attributes, category);
	}

	public static IRule createRule(List<ICondition> conditions, IExpression containingExpression)
	{
		return new Rule(conditions, containingExpression);
	}

	public static IRule createRule()
	{
		return new Rule(null,null);
	}

	public static IIntraModelAssociation createIntraModelAssociation(IClass leftClass,
			IClass rightClass, String roleName, String revereseRoleName, boolean bidirectional)
	{
		return new IntraModelAssociation(leftClass, rightClass, roleName, revereseRoleName,
				bidirectional);
	}

	public static IInterModelAssociation createInterModelAssociation(IAttribute sourceAttribute,
			IAttribute targetAttribute)
	{
		return new InterModelAssociation(sourceAttribute, targetAttribute);
	}

	public static IQuery createQuery()
	{
		return new Query();
	}
    
    /**
     * Creates the query Class object from dynamic extension's Entity object.
     * The attributes for the class are not set and need to be set explicitly.
     * @param entity The entity object.
     * @return the Class object from Entity object.
     */
    public static IClass createClass(EntityInterface entity)
    {
        IClass classObj = createClass();
//        classObj.setAttributes(createAttributes((List)entity.getAttributeCollection()));
        classObj.setFullyQualifiedName(entity.getName());
        
        return classObj;
    }
    
    /**
     * Creates & returns the list of query Attributes from the dynamic extension Attribute objects.  
     * @param attributes The dynamic extension Attribute objects.
     * @return the list of query Attributes from the dynamic extension Attribute objects.
     */
    public static List<IAttribute> createAttributes(List<AttributeInterface> attributes)
    {
        List<IAttribute> queryAttributes = new ArrayList<IAttribute>();
        Iterator iterator = attributes.iterator();
        
        while (iterator.hasNext())
        {
            AttributeInterface attribute = (AttributeInterface) iterator.next();
            IAttribute queryAttribute = createAttribute();
            queryAttribute.setAttributeName(attribute.getName());
            queryAttribute.setDataType(getAttributeDataType(attribute.getAttributeTypeInformation()));
            IClass iclass = createClass(attribute.getEntity());
            iclass.setAttributes(queryAttributes);
            
            queryAttribute.setUMLClass(iclass);
            queryAttributes.add(queryAttribute);
        }
        
        return queryAttributes;
    }
    
    /**
     * Creates & returns the query Attribute object from the dynamic extension Attribute object.  
     * @param attribute The dybnamic extension object.
     * @return the query Attribute object from the dynamic extension Attribute object.
     */
    public static IAttribute createAttribute(AttributeInterface attribute)
    {
        IAttribute queryAttribute = createAttribute();
        queryAttribute.setAttributeName(attribute.getName());
        queryAttribute.setDataType(getAttributeDataType(attribute.getAttributeTypeInformation()));
        IClass iclass = createClass(attribute.getEntity());
        iclass.setAttributes(createAttributes((List<AttributeInterface>)attribute.getEntity().getAttributeCollection()));
        
        return queryAttribute;
    }
    
    /**
     * Returns the datatype of attribute depending on the AttributeTypeInformation of the attribute. 
     * @param attributeTypeInformation The attribute type information.
     * @return the datatype of attribute depending on the AttributeTypeInformation of the attribute.
     */
    private static DataType getAttributeDataType(AttributeTypeInformationInterface attributeTypeInformation)
    {
        DataType dataType = DataType.String;
        if (attributeTypeInformation instanceof LongAttributeTypeInformation)
        {
            dataType = DataType.Long;
        }
        else if (attributeTypeInformation instanceof DateAttributeTypeInformation)
        {
            dataType = DataType.Date;
        }
        else if (attributeTypeInformation instanceof BooleanAttributeTypeInformation)
        {
            dataType = DataType.Boolean;
        }
        else if (attributeTypeInformation instanceof DoubleAttributeTypeInformation)
        {
            dataType = DataType.Double;
        }
        else if (attributeTypeInformation instanceof IntegerAttributeTypeInformation)
        {
            dataType = DataType.Integer;
        }
        
        return dataType;
    }
}