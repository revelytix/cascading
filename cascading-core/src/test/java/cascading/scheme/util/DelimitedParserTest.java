package cascading.scheme.util;

import java.util.regex.Pattern;
import junit.framework.TestCase;
import org.junit.Test;

public class DelimitedParserTest extends TestCase{

	@Test
	public void testCreateSplitWithQuote() {
		Pattern regex = DelimitedParser.createSplitPatternFor(",", "\"");
		String []results = DelimitedParser.createSplit("abc,def,ghi", regex, 0);
		assertEquals("abc", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}
	
	@Test
	public void testCreateSplitWithQuotesInData() {
		Pattern regex = DelimitedParser.createSplitPatternFor(",", "\"");
		String []results = DelimitedParser.createSplit("\"abc\",def,\"ghi\"", regex, 0);
		assertEquals("\"abc\"", results[0]);
		assertEquals("def", results[1]);
		assertEquals("\"ghi\"", results[2]);
	}
	
	@Test
	public void testCreateSplitWithQuotesAndCommasInData() {
		Pattern regex = DelimitedParser.createSplitPatternFor(",", "\"");
		String []results = DelimitedParser.createSplit("\"a,b,c\",def,\"g,h,i\"", regex, 0);
		assertEquals("\"a,b,c\"", results[0]);
		assertEquals("def", results[1]);
		assertEquals("\"g,h,i\"", results[2]);
	}
	
	@Test
	public void testCreateSplitEscapedQuotes() {
		Pattern regex = DelimitedParser.createSplitPatternFor(",", "\"");
		String []results = DelimitedParser.createSplit("\"a\"\"b\"\"c\",def,\"ghi\"", regex, 0);
		assertEquals("\"a\"\"b\"\"c\"", results[0]);
		assertEquals("def", results[1]);
		assertEquals("\"ghi\"", results[2]);
	}
	
	@Test
	public void testCreateSplitNoQuotes() {
		Pattern regex = DelimitedParser.createSplitPatternFor(",", "");
		String []results = DelimitedParser.createSplit("abc,def,ghi", regex, 0);
		assertEquals("abc", results[0]);
		assertEquals("def", results[1]);
		assertEquals("ghi", results[2]);
	}

}
