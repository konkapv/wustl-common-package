/**
 * TODO Correct all the mock object. All parameters of the query are not set
 * properly. Null pointer exception will occur.
 */
package edu.wustl.common.querysuite.queryobject;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import edu.common.dynamicextensions.domain.Attribute;
import edu.common.dynamicextensions.domain.Entity;
import edu.common.dynamicextensions.domaininterface.AttributeInterface;
import edu.common.dynamicextensions.domaininterface.EntityInterface;
import edu.wustl.common.hibernate.HibernateDatabaseOperations;
import edu.wustl.common.querysuite.QueryGeneratorMock;
import edu.wustl.common.querysuite.queryobject.impl.Condition;
import edu.wustl.common.querysuite.queryobject.impl.Connector;
import edu.wustl.common.querysuite.queryobject.impl.Expression;
import edu.wustl.common.querysuite.queryobject.impl.ExpressionId;
import edu.wustl.common.querysuite.queryobject.impl.Query;
import edu.wustl.common.querysuite.queryobject.impl.QueryEntity;
import edu.wustl.common.querysuite.queryobject.impl.Rule;

/**
 * 
 * @author chetan_patil
 * @created Aug 8, 2007
 */
public class QueryObjectTestCase extends TestCase {

    static {
        // TODO
        // Logger.configure();// To avoid null pointer Exception for code
        // calling logger statements.
    }

    /**
     * To test the saving of Condition object.
     */
    public void testSaveCondition() {
        Condition condition = new Condition();
        condition.setRelationalOperator(RelationalOperator.Between);
        condition.addValue("1");
        condition.addValue("100");
        AttributeInterface attribute = new Attribute();
        attribute.setId(1L);
        condition.setAttribute(attribute);

        // Saving Condition object
        try {
            new HibernateDatabaseOperations<Condition>().insert(condition);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test the saving of Rule having two Condition object.
     */
    public void testSaveRuleWith2Condition() {
        // Condition 1
        Condition condition1 = new Condition();
        condition1.setRelationalOperator(RelationalOperator.Between);
        condition1.addValue("1");
        condition1.addValue("10");
        AttributeInterface attribute1 = new Attribute();
        attribute1.setId(1L);
        condition1.setAttribute(attribute1);

        // Condition 2
        Condition condition2 = new Condition();
        condition2.setRelationalOperator(RelationalOperator.Between);
        condition2.addValue("2");
        condition2.addValue("20");
        AttributeInterface attribute2 = new Attribute();
        attribute2.setId(2L);
        condition2.setAttribute(attribute2);

        // Rule
        Rule rule = new Rule();
        rule.addCondition(condition1);
        rule.addCondition(condition2);
        rule.setContainingExpression(null);

        // Saving Rule object
        try {
            new HibernateDatabaseOperations<Rule>().insert(rule);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test the saving of Expression with one Rule object.
     */
    public void testSaveExpressionWith1Rule() {
        // Condition 1
        Condition condition1 = new Condition();
        condition1.setRelationalOperator(RelationalOperator.Between);
        condition1.addValue("1");
        condition1.addValue("10");
        AttributeInterface attribute1 = new Attribute();
        attribute1.setId(1L);
        condition1.setAttribute(attribute1);

        // Condition 2
        Condition condition2 = new Condition();
        condition2.setRelationalOperator(RelationalOperator.Between);
        condition2.addValue("2");
        condition2.addValue("20");
        AttributeInterface attribute2 = new Attribute();
        attribute2.setId(2L);
        condition2.setAttribute(attribute2);

        // Rule 1
        Rule rule1 = new Rule();
        rule1.addCondition(condition1);
        rule1.addCondition(condition2);

        // Forming Expression operand list
        List<IExpressionOperand> expressionOperandList1 = new ArrayList<IExpressionOperand>();
        expressionOperandList1.add(rule1);

        // Expression
        Expression expression1 = new Expression();
        rule1.setContainingExpression(expression1);

        expression1.setExpressionOperands(expressionOperandList1);
        expression1.setExpressionId(new ExpressionId(0));
        expression1.setInView(true);
        expression1.setVisible(true);

        // Saving Expression object
        try {
            new HibernateDatabaseOperations<Expression>().insert(expression1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test saving of Expression having two operands, one a Rule and other a
     * ExpressionId connected by LogicalConnector
     */
    public void testSaveExpressionWith2Operands() {
        // Condition 1
        Condition condition1 = new Condition();
        condition1.setRelationalOperator(RelationalOperator.Between);
        condition1.addValue("1");
        condition1.addValue("10");
        AttributeInterface attribute1 = new Attribute();
        attribute1.setId(1L);
        condition1.setAttribute(attribute1);

        // Condition 2
        Condition condition2 = new Condition();
        condition2.setRelationalOperator(RelationalOperator.Between);
        condition2.addValue("2");
        condition2.addValue("20");
        AttributeInterface attribute2 = new Attribute();
        attribute2.setId(2L);
        condition2.setAttribute(attribute2);

        // Rule 1
        Rule rule1 = new Rule();
        rule1.addCondition(condition1);
        rule1.addCondition(condition2);

        // Forming Expression1 operand list
        List<IExpressionOperand> expressionOperandList1 = new ArrayList<IExpressionOperand>();
        expressionOperandList1.add(rule1);
        expressionOperandList1.add(new ExpressionId(1));

        // LogicalOperator between two operands of Expression2
        IConnector<LogicalOperator> logicalConnector1 = new Connector<LogicalOperator>();
        logicalConnector1.setOperator(LogicalOperator.And);

        List<IConnector<LogicalOperator>> logicalConnectors = new ArrayList<IConnector<LogicalOperator>>();
        logicalConnectors.add(logicalConnector1);

        // Expression 1
        Expression expression1 = new Expression();
        rule1.setContainingExpression(expression1);

        expression1.setExpressionOperands(expressionOperandList1);
        expression1.setLogicalConnectors(logicalConnectors);
        expression1.setExpressionId(new ExpressionId(1));
        expression1.setInView(true);
        expression1.setVisible(true);

        // Saving Expression2
        try {
            new HibernateDatabaseOperations<Expression>().insert(expression1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test saving of Expression having two operands, one a Rule and other a
     * ExpressionId connected by LogicalConnector and one ConstraintEntity
     */
    public void testSaveExpressionWith2OperandsAndConstraintEntity() {
        // Condition 1
        Condition condition1 = new Condition();
        condition1.setRelationalOperator(RelationalOperator.Between);
        condition1.addValue("1");
        condition1.addValue("10");
        AttributeInterface attribute1 = new Attribute();
        attribute1.setId(1L);
        condition1.setAttribute(attribute1);

        // Condition 2
        Condition condition2 = new Condition();
        condition2.setRelationalOperator(RelationalOperator.Between);
        condition2.addValue("2");
        condition2.addValue("20");
        AttributeInterface attribute2 = new Attribute();
        attribute2.setId(2L);
        condition2.setAttribute(attribute2);

        // Rule 1
        Rule rule1 = new Rule();
        rule1.addCondition(condition1);
        rule1.addCondition(condition2);

        // Forming Expression1 operand list
        List<IExpressionOperand> expressionOperandList1 = new ArrayList<IExpressionOperand>();
        expressionOperandList1.add(rule1);
        expressionOperandList1.add(new ExpressionId(1));

        // LogicalOperator between two operands of Expression2
        IConnector<LogicalOperator> logicalConnector1 = new Connector<LogicalOperator>();
        logicalConnector1.setOperator(LogicalOperator.And);

        List<IConnector<LogicalOperator>> logicalConnectors = new ArrayList<IConnector<LogicalOperator>>();
        logicalConnectors.add(logicalConnector1);

        // ConstraintEntity
        EntityInterface entityInterface = new Entity();
        entityInterface.setId(0L);
        QueryEntity queryEntity = new QueryEntity(entityInterface);

        // Expression 1
        Expression expression1 = new Expression();
        rule1.setContainingExpression(expression1);

        expression1.setExpressionOperands(expressionOperandList1);
        expression1.setLogicalConnectors(logicalConnectors);
        expression1.setQueryEntity(queryEntity);
        expression1.setExpressionId(new ExpressionId(2));
        expression1.setInView(true);
        expression1.setVisible(true);

        // Saving Expression2
        try {
            new HibernateDatabaseOperations<Expression>().insert(expression1);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test saving of Expression having a ConstraintEntity object and two
     * Rules that are connected by LogicalConnector.
     */
    public void testSaveExpressionWith2RulesAndConstraintEntity() {
        // Condition 1
        Condition condition1 = new Condition();
        condition1.setRelationalOperator(RelationalOperator.Between);
        condition1.addValue("1");
        condition1.addValue("10");
        AttributeInterface attribute1 = new Attribute();
        attribute1.setId(1L);
        condition1.setAttribute(attribute1);

        // Condition 2
        Condition condition2 = new Condition();
        condition2.setRelationalOperator(RelationalOperator.Between);
        condition2.addValue("2");
        condition2.addValue("20");
        AttributeInterface attribute2 = new Attribute();
        attribute2.setId(2L);
        condition2.setAttribute(attribute2);

        // Rule 1
        Rule rule1 = new Rule();
        rule1.addCondition(condition1);
        rule1.addCondition(condition2);

        // Condition 3
        Condition condition3 = new Condition();
        condition3.setRelationalOperator(RelationalOperator.Between);
        condition3.addValue("3");
        condition3.addValue("30");
        AttributeInterface attribute3 = new Attribute();
        attribute3.setId(3L);
        condition3.setAttribute(attribute3);

        // Condition 4
        Condition condition4 = new Condition();
        condition4.setRelationalOperator(RelationalOperator.Between);
        condition4.addValue("4");
        condition4.addValue("40");
        AttributeInterface attribute4 = new Attribute();
        attribute4.setId(4L);
        condition4.setAttribute(attribute4);

        // Rule 2
        Rule rule2 = new Rule();
        rule2.addCondition(condition3);
        rule2.addCondition(condition4);

        // Forming Expression2 operand list
        List<IExpressionOperand> expressionOperandList = new ArrayList<IExpressionOperand>();
        expressionOperandList.add(rule1);
        expressionOperandList.add(rule2);

        // LogicalOperator between two operands of Expression2
        IConnector<LogicalOperator> logicalConnector = new Connector<LogicalOperator>();
        logicalConnector.setOperator(LogicalOperator.And);

        List<IConnector<LogicalOperator>> logicalConnectors = new ArrayList<IConnector<LogicalOperator>>();
        logicalConnectors.add(logicalConnector);

        // ConstraintEntity
        EntityInterface entityInterface = new Entity();
        entityInterface.setId(1L);
        QueryEntity constraintEntity = new QueryEntity(entityInterface);

        // Expression2
        Expression expression = new Expression();
        rule1.setContainingExpression(expression);
        rule2.setContainingExpression(expression);

        expression.setExpressionOperands(expressionOperandList);
        expression.setLogicalConnectors(logicalConnectors);
        expression.setExpressionId(new ExpressionId(3));
        expression.setQueryEntity(constraintEntity);
        expression.setInView(true);
        expression.setVisible(true);

        // Saving Expression2
        try {
            new HibernateDatabaseOperations<Expression>().insert(expression);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test saving of Expression having a Constraint.
     */
    public void testSaveConstraint() {
        IQuery query = QueryGeneratorMock.createSpecimenBioHazardQuery1();
        IConstraints constraint = query.getConstraints();

        try {
            new HibernateDatabaseOperations<IConstraints>().insert(constraint);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To test saving of Query
     */
    public void testRetrieveQuery() {
        try {
            IQuery query = (IQuery) (new HibernateDatabaseOperations<IQuery>().retrieve(Query.class.getName(), "id", 1L))
                    .get(0);
            assertTrue(query != null);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * To save parameterized query
     */
    public void testSaveParameterizedQuery() {
        IParameterizedQuery parameterizedQuery = QueryGeneratorMock.createSpecimenBioHazardQuery4();

        try {
            new HibernateDatabaseOperations<IParameterizedQuery>().insert(parameterizedQuery);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}