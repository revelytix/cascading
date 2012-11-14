package cascading.scheme.util;

import junit.framework.TestCase;
import org.junit.Test;

import cascading.tuple.Fields;

public class DelimitedParserTest extends TestCase{

	private DelimitedParser parserWithQuote = new DelimitedParser(",", "\"", null, true, true, true, Fields.ALL, Fields.ALL);
	private DelimitedParser parserNoQuote = new DelimitedParser(",", null, null, true, true, true, Fields.ALL, Fields.ALL);
	
	@Test
	public void testCreateSplitWithQuote() {
		String []results = parserWithQuote.createSplit("abc,def,ghi", 0);
		assertEquals("abc", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}
	
	@Test
	public void testCreateSplitWithQuotesInData() {
		String []results = parserWithQuote.createSplit("\"abc\",def,\"ghi\"", 0);
		assertEquals("abc", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}
	
	@Test
	public void testCreateSplitWithQuotesAndCommasInData() {
		String []results = parserWithQuote.createSplit("\"a,b,c\",def,\"g,h,i\"", 0);
		assertEquals("a,b,c", results[0]);
		assertEquals("def", results[1]);
		assertEquals("g,h,i", results[2]);
	}
	
	@Test
	public void testCreateSplitEscapedQuotes() {
		String []results = parserWithQuote.createSplit("\"a\\\"b\\\"c\",def,\"ghi\"", 0);
		assertEquals("a\"b\"c", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}
	
	@Test
	public void testCreateSplitNoQuotes() {
		String []results = parserNoQuote.createSplit("abc,def,ghi", 0);
		assertEquals("abc", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}

}
