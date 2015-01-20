package test.de.escidoc.sb.common.lucene.analyzer;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;
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
	
	@Test
	public void testNormailzer() {
		final String input = "Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ";
		System.out.println("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ " +
		    Normalizer.normalize(input.toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
		
		System.out.println("Čadík " + 
			    Normalizer.normalize("Čadík".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("本 " +  
			    Normalizer.normalize("本".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("登録 " + 
			    Normalizer.normalize("登録".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
	}
	

}
