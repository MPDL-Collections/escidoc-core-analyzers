package test.de.escidoc.sb.common.lucene.analyzer;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.StringReader;
import java.text.Normalizer;
import java.util.List;

import org.apache.lucene.analysis.ASCIIFoldingFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.commons.lang3.StringUtils;
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

		System.out.println("_________________________________");
		System.out.println("\nNormalizer.normalize \n");
		
		System.out.println("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ " +  "xxx " +
		    Normalizer.normalize("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ ".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
		
		System.out.println("Čadík " +  "xxx " + 
			    Normalizer.normalize("Čadík".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("本 " +  "xxx " +  
			    Normalizer.normalize("本".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("登録 " +  "xxx " + 
			    Normalizer.normalize("登録".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("Δ-stepping " +  "xxx " +
			    Normalizer.normalize("Δ-stepping".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("Αναζητώντας το μεγάλο επικό ποιητή " +  "xxx " +
			    Normalizer.normalize("Αναζητώντας το μεγάλο επικό ποιητή".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("§-1.1 " +  "xxx " + 
			    Normalizer.normalize("§-1.1".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("Über dän Wölken " +  "xxx " + 
			    Normalizer.normalize("Über dän Wölken".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 
		
		System.out.println("Kılıç " +  "xxx " + 
			    Normalizer.normalize("Kılıç".toLowerCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")); 

		System.out.println("_________________________________");
		System.out.println("\nUsing StringUtils.stripAccents \n");
	
		System.out.println("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ " +  "xxx " +
			    StringUtils.stripAccents("Tĥïŝ ĩš â fůňķŷ Šťŕĭńġ ".toLowerCase()));
			
		System.out.println("Čadík " +  "xxx " + 
				StringUtils.stripAccents("Čadík ".toLowerCase()));
		
		System.out.println("本 " +  "xxx " +  
				StringUtils.stripAccents("本  ".toLowerCase())); 
		
		System.out.println("登録 " +  "xxx " + 
				StringUtils.stripAccents("登録  ".toLowerCase())); 
		
		System.out.println("Δ-stepping " +  "xxx " +
				StringUtils.stripAccents("Δ-stepping ".toLowerCase())); 
		
		System.out.println("Αναζητώντας το μεγάλο επικό ποιητή " +  "xxx " +
				StringUtils.stripAccents("Αναζητώντας το μεγάλο επικό ποιητή  ".toLowerCase()));
		
		System.out.println("§-1.1 " +  "xxx " +
				StringUtils.stripAccents("§-1.1  ".toLowerCase()));
		
		System.out.println("Über dän Wölken " +  "xxx " +
				StringUtils.stripAccents("Über dän Wölken  ".toLowerCase()));
		
		System.out.println("Kılıç " +  "xxx " + 
				StringUtils.stripAccents("Kılıç".toLowerCase())); 
			
	}

}
