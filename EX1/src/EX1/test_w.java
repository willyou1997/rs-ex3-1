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
		assertEquals("\"services\"->\"study\" ,the cheapest path is:services --> in --> the --> study\n" + 
				"weight:3",s);
	}

}
