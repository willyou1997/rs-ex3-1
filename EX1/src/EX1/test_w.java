package EX1;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.*;



import org.junit.Test;

public class test_w {
	EX1 t = new EX1();
	@Before
	public void test1() throws FileNotFoundException, IOException
	{
		t.Input();
		
	}
	@Test
	public void test2() throws FileNotFoundException, IOException
	{
		String s = t.calcShortestPath();
<<<<<<< HEAD
		assertEquals("\"services\"->\"study\" ,the cheapest path is:services --> in --> the --> study\n" + 
				"weight:3",s);
	}

}
=======
		assertEquals("There is no path between \"this\" and \"study\".",s);
	}

}

/*public class test_w {
	EX1 t = new EX1();
	@Before
	public void test1() throws FileNotFoundException, IOException
	{
		t.Input();
		
	}
	@Test
	public void test2() throws FileNotFoundException, IOException
	{
		String s = t.calcShortestPath();
		assertEquals("\"time\"->\"word\" ,the cheapest path is:time --> servitization --> becomes --> one --> of --> this --> word\n" + 
				"weight:6",s);
	}

}*/

/*public class test_w {
	EX1 t = new EX1();
	@Before
	public void test1() throws FileNotFoundException, IOException
	{
		t.Input();
		
	}
	@Test
	public void test2() throws FileNotFoundException, IOException
	{
		String s = t.calcShortestPath();
		assertEquals("\"the\"->\"word\" ,the cheapest path is:the --> format --> of --> this --> word\n" + 
				"weight:4",s);
	}

}*/

/*public class test_w {
	EX1 t = new EX1();
	@Before
	public void test1() throws FileNotFoundException, IOException
	{
		t.Input();
		
	}
	@Test
	public void test2() throws FileNotFoundException, IOException
	{
		String s = t.calcShortestPath();
		assertEquals("\"in\"->\"big\" ,the cheapest path is:in --> the --> big\n"+"weight:2",s);
	}

}*/
>>>>>>> lab6w
