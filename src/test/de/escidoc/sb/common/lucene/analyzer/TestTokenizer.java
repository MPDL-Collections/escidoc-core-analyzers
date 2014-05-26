package test.de.escidoc.sb.common.lucene.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.LetterTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceTokenizer;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.junit.Ignore;
import org.junit.Test;

import de.escidoc.sb.common.lucene.analyzer.Constants;
import de.escidoc.sb.common.lucene.analyzer.XmlWhitespaceTokenizer;


public class TestTokenizer {
	
	static String[] terms = {
		"bitwise.",
		"12.5",
		"12,2",
		"abc/cfg", 
		"abc,cfg", 
		"hdl:11858/00-001Z-0000-0023-453D-4",
		"http://pubman.mpdl.mpg.de/pubman",
		"http://purl.org/eprint/type/Book",
		"http://www.loc.gov/loc.terms/relators/AUT",
		"S5 0014+813",
		"Black Hole in Sgr A(*)",
		"abc_cdf",
		"(001)-oriented MgO",
		"(001)-oriented TbBaCo2O6-α",
		"galaxy IRAS 00182−7112",
		"supernova remnant 1E 0102.2-7219",
		"Techniken für 0,01-mm3-Auflösung",
		"01-P003 The role of miRNAs",
		"Erratum to Physical Review B 79, 020506(R) (2009)",
		"radio-loud narrow-line quasar SDSS J172206.03+565451.6",
		"0[110] transformation",
		"pi-Congested poly(paraphenylene) from 2,2',6,6'-tetraphenyl-1,1'-biphenyl units",
		"1,5-Dimethoxy-9,10-di(phenylethynyl)anthracene fluorophore",
		"Pb-207/Pb-204",
		" Nb/La",
		"nature of Sgr A*'s near-infrared",
		"1,3-Bicyclo[1.1.1]pentanediyl",
		"<framework.admin.username>roland</framework.admin.username>",
		"separator1!separator2?separator3",
	};

	@Test
	public void testTokenizers() throws IOException {
		
		for (int i = 0; i < terms.length; i++) {
			
			System.out.println("tokenizing: " + terms[i]);			
			System.out.println("\ntokenizing with ClassicTokenizer");
			doTokenizing(new ClassicTokenizer(Constants.LUCENE_VERSION, new StringReader(terms[i])));	
			System.out.println("end ClassicTokenizer ***********************\n ");	
			
			System.out.println("tokenizing: " + terms[i]);
			System.out.println("\ntokenizing with StandardTokenizer");
			doTokenizing(new StandardTokenizer(Constants.LUCENE_VERSION, new StringReader(terms[i])));	
			System.out.println("end StandardTokenizer ***********************\n ");	
			
			System.out.println("tokenizing: " + terms[i]);
			System.out.println("\ntokenizing with WhitespaceTokenizer");
			doTokenizing(new WhitespaceTokenizer(Constants.LUCENE_VERSION, new StringReader(terms[i])));	
			System.out.println("end WhitespaceTokenizer ***********************\n ");	
			
			System.out.println("tokenizing: " + terms[i]);
			System.out.println("\ntokenizing with XmlWhitespaceTokenizer");
			doTokenizing(new XmlWhitespaceTokenizer(new StringReader(terms[i])));	
			System.out.println("end XmlWhitespaceTokenizer ***********************\n ");	
			
			/*System.out.println("tokenizing: " + terms[i]);
			System.out.println("\ntokenizing with LetterTokenizer");
			doTokenizing(new LetterTokenizer(Constants.LUCENE_VERSION, new StringReader(terms[i])));	
			System.out.println("end LetterTokenizer ***********************\n ");	*/
		}
		System.out.println("end  ***********************\n ");	
	}

	private void doTokenizing(TokenStream ts) throws IOException {
		CharTermAttribute termAtt = ts.addAttribute(CharTermAttribute.class);
		try {
			ts.reset();

			while (ts.incrementToken()) {
				String s = termAtt.toString();
				System.out.println("token: " + s);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ts.clearAttributes();
		ts.close();
	}
	
	@Test
	@Ignore
	public void testStandardTokenizer() throws IOException {
		System.out.println("\ntokenizing with StandardTokenizer");
		for (int i = 0; i < terms.length; i++) {
			StringReader reader = new StringReader(terms[i]);
			System.out.println("tokenizing: " + terms[i]);

			// Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
			TokenStream ts = new StandardTokenizer(Constants.LUCENE_VERSION, reader);

			// TokenStream ts = analyzer.tokenStream("myfield", reader);

			doTokenizing(ts);			
		}
		System.out.println("end StandardTokenizer ***********************\n ");	
	}
	
	@Test
	@Ignore
	public void testLetterTokenizer() throws IOException {
		System.out.println("\ntokenizing with LetterTokenizer");
		for (int i = 0; i < terms.length; i++) {
			StringReader reader = new StringReader(terms[i]);
			System.out.println("tokenizing: " + terms[i]);

			TokenStream ts = new LetterTokenizer(Constants.LUCENE_VERSION, reader);

			doTokenizing(ts);			
		}
		System.out.println("end LetterTokenizer ***********************\n ");	
	}
	
	@Test
	@Ignore
	public void testWhitespaceTokenizer() throws IOException {
		System.out.println("\ntokenizing with WhitespaceTokenizer");
		for (int i = 0; i < terms.length; i++) {
			StringReader reader = new StringReader(terms[i]);
			System.out.println("tokenizing: " + terms[i]);

			TokenStream ts = new WhitespaceTokenizer(Constants.LUCENE_VERSION, reader);

			doTokenizing(ts);			
		}
		System.out.println("end WhitespaceTokenizer ***********************\n ");	
	}

}
