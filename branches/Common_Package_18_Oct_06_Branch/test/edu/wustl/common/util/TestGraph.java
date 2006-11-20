/**
 * 
 */

package edu.wustl.common.util;

import java.util.ArrayList;
import java.util.List;

import edu.wustl.common.querysuite.exceptions.CyclicException;
import junit.framework.TestCase;

/**
 * @author prafull_kadam
 * Test Case class for the Graph class.
 */
public class TestGraph extends TestCase
{

	Graph<Integer, String> graph;
	final Integer one = new Integer(1);
	final Integer two = new Integer(2);
	final Integer three = new Integer(3);
	final Integer four = new Integer(4);
	final Integer five = new Integer(5);
	final Integer six = new Integer(6);

	final String oneTwo = "12";
	final String oneThree = "13";
	final String ThreeFour = "34";
	final String fourFive = "45";
	final String sixFive = "65";
	final String fiveFour = "54";

	/**
	 * @see junit.framework.TestCase#setUp()
	 * creates Graph as:
	 * 
	 * 1---> 2
	 *  \--> 3 --> 4
	 */
	@Override
	protected void setUp() throws Exception
	{
		graph = new Graph<Integer, String>();
		graph.putEdge(one, two, oneTwo);
		graph.putEdge(one, three, oneThree);
		graph.putEdge(three, four, ThreeFour);
		super.setUp();
	}

	/**
	 * by Adding edge between 4 & 1 shold throw CyclicException
	 *
	 */
	public void testPutEdge1()
	{
		String fourOne = "41";
		try
		{
			graph.putEdge(four, one, fourOne);
			assertTrue("Expected Cyclic Exception !!!", false);
		}
		catch (CyclicException e)
		{
		}
	}

	/**
	 * by Adding edge between 4 & 5 shold not throw CyclicException
	 *
	 */
	public void testPutEdge2()
	{
		try
		{
			String edge = graph.putEdge(four, five, fourFive);
			assertNull("Expected null value from putEdge method since edge does not Exist!!!", edge);
			edge = graph.getEdge(four, five);
			assertEquals("Unequal edge got from Graph!!!", edge, fourFive);
		}
		catch (CyclicException e)
		{
			assertTrue("Unexpected Cyclic Exception !!!", false);
		}
	}

	/**
	 * The existing graph is connected graph.
	 *
	 */
	public void testIsConnected1()
	{
		assertTrue("Expected true value from isConnected method!!!", graph.isConnected());
	}

	/**
	 * Adding edge 5 to 6 in the existing graph will result into disconnected graph.
	 *
	 */
	public void testIsConnected2()
	{
		try
		{
			graph.putEdge(six, five, sixFive);
			assertFalse("Expected false value from isConnected method!!!", graph.isConnected());
		}
		catch (CyclicException e)
		{
			assertTrue("Unexpected Cyclic Exception !!!", false);
		}
	}

	/**
	 * Adding edge 5 to 4 in the existing graph will not result into disconnected graph.
	 * 
	 * 1---> 2
	 *  \--> 3 --> 4 <-- 5
	 */
	public void testIsConnected3()
	{
		try
		{
			graph.putEdge(five, four, fiveFour);
			assertTrue("Expected true value from isConnected method!!!", graph.isConnected());
		}
		catch (CyclicException e)
		{
			assertTrue("Unexpected Cyclic Exception !!!", false);
		}
	}

	/**
	 * To test UnreachablNode list methos for graph.
	 * In the actual graph only vertex "1" is unreachable.
	 * After adding edge in 5 -> 4, UnreachableNodeList will contain 1 & 5 
	 */
	public void testGetUnreachableList()
	{
		List<Integer> actualList = new ArrayList<Integer>();
		actualList.add(one);
		assertEquals("incorrect unreachableNodeList!!!", graph.getUnreachableNodeList(), actualList);

		try
		{
			actualList.add(five);
			graph.putEdge(five, four, fiveFour);
			assertEquals("incorrect unreachableNodeList!!!", graph.getUnreachableNodeList(),
					actualList);
		}
		catch (CyclicException e)
		{
			assertTrue("Unexpected Cyclic Exception !!!", false);
		}
	}
}
