package test.de.escidoc.sb.common.lucene.analyzer;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.apache.lucene.analysis.ASCIIFoldingFilter;
import org.apache.lucene.analysis.TokenStream;
import org.junit.Test;

import de.escidoc.sb.common.lucene.analyzer.XmlWhitespaceTokenizer;

public class TestASCIIFoldingFilter extends TestBase {

	@Test
	public void test() throws IOException {
		TokenStream result = new XmlWhitespaceTokenizer(new StringReader("Über"));
		result = new ASCIIFoldingFilter(result);
		
		List<String> r = doTokenizing(result);
		
		assertTrue(r.get(0).equals("Uber"));
	}

}
