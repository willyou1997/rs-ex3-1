package EX7;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;
import org.junit.Test;

public class test {
	boundary b = new boundary();
	control c = new control();
	entity e = new entity();
	@Before 
	public void test1() throws IOException
	{
		b.Input();
		c.showAdjacencyMatrix();
	}
	@Test
	public void test2() throws IOException {
		String a = c.querybirdgeWords();
		assertEquals("No birdge words from \"time\" to \"by\"!\n",a);
	}

	@Test
	public void test3() throws IOException {
		String a = c.querybirdgeWords();
		assertEquals("The birdge words from \"important\" to \"trends\" are: development ",a);
		
	}


	@Test
	public void test4() throws IOException {
		String a = c.querybirdgeWords();
		assertEquals("The birdge words from \"the\" to \"of\" are: format study meaning ",a);
		
	}


	@Test
	public void test5() throws IOException {
		String a = c.querybirdgeWords();
		assertEquals("No \"first\" or \"second\" in graph!\n",a);
		
	}

}
