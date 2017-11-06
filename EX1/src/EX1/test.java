package EX1;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.*;
import org.junit.Test;

/*public class test {
	EX1 abc = new EX1();
	@Before 
	public void test1() throws IOException
	{
		abc.Input();
	}
	@Test
	public void test2() throws IOException {
		String a = abc.querybirdgeWords();
		assertEquals("No birdge words from \"time\" to \"by\"!\n",a);
	}

}*/

/*public class test {
	EX1 abc = new EX1();
	@Before 
	public void test1() throws IOException
	{
		abc.Input();
	}
	@Test
	public void test2() throws IOException {
		String a = abc.querybirdgeWords();
		assertEquals("The birdge words from \"important\" to \"trends\" are: development ",a);
		
	}

}*/




public class test {
	EX1 abc = new EX1();
	@Before 
	public void test1() throws IOException
	{
		abc.Input();
	}
	@Test
	public void test2() throws IOException {
		String a = abc.querybirdgeWords();
		assertEquals("The birdge words from \"the\" to \"of\" are: format study meaning ",a);
		
	}

}

/*public class test {
	EX1 abc = new EX1();
	@Before 
	public void test1() throws IOException
	{
		abc.Input();
	}
	@Test
	public void test2() throws IOException {
		String a = abc.querybirdgeWords();
		assertEquals("No \"first\" or \"second\" in graph!\n",a);
		
	}

}*/
