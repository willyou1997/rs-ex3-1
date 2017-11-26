package EX7;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.*;



import org.junit.Test;

public class test_w {
	boundary b = new boundary();
	control c = new control();
	entity e = new entity();
	@Before
	public void test1() throws FileNotFoundException, IOException
	{
		b.Input();
		c.showAdjacencyMatrix();
		
	}
	@Test
	public void test2() throws FileNotFoundException, IOException
	{
		String s = c.calcShortestPath();
		assertEquals("\"time\"->\"word\" ,the cheapest path is:time --> servitization --> becomes --> one --> of --> this --> word\n" + 
				"weight:6",s);
	}
	
	@Test
	public void test3() throws FileNotFoundException, IOException
	{
			String s = c.calcShortestPath();
		assertEquals("There is no path between \"this\" and \"study\".",s);
	}



	@Test
	public void test4() throws FileNotFoundException, IOException
	{
		String s = c.calcShortestPath();
		assertEquals("\"the\"->\"word\" ,the cheapest path is:the --> format --> of --> this --> word\n" + 
				"weight:4",s);
	}



	@Test
	public void test5() throws FileNotFoundException, IOException
	{
		String s = c.calcShortestPath();
		assertEquals("\"in\"->\"big\" ,the cheapest path is:in --> the --> big\n"+"weight:2",s);
	}

}

