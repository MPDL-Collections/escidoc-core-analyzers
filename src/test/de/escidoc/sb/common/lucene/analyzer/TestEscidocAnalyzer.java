package test.de.escidoc.sb.common.lucene.analyzer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.junit.Test;

import de.escidoc.sb.common.lucene.analyzer.EscidocAnalyzer;

public class TestEscidocAnalyzer {

	static String[] terms = {

		"Pb-207/Pb-204",
		" Nb/La",
		"nature of Sgr A*'s near-infrared",
		"1,3-Bicyclo[1.1.1]pentanediyl",
		"Meier, Hans",
		"Xaver Müller",
		"with other right"
		
	
	};
	
	@Test
	public void test() throws IOException {
		EscidocAnalyzer analyzer = new EscidocAnalyzer();
		analyzer.setLanguage("all");
		TokenStream result;
		
		result = analyzer.tokenStream("field", new StringReader("abc/cfg"));
		List<String> tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("abc/cfg"));
		tokens.clear();
		
		result = analyzer.tokenStream("field", new StringReader("abc,def"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("abc"));
		assertTrue(tokens.get(1).equals("def"));
		tokens.clear();
		
		result = analyzer.tokenStream("field", new StringReader("2011-01-28T15:14:30.925Z"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("2011-01-28t15:14:30.925z"));
		tokens.clear();
		
		result = analyzer.tokenStream("field", new StringReader("hdl:11858/00-001Z-0000-0023-453D-4"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("hdl:11858/00-001z-0000-0023-453d-4"));
		
		result = analyzer.tokenStream("field", new StringReader("http://pubman.mpdl.mpg.de/pubman"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("http://pubman.mpdl.mpg.de/pubman"));
		
		result = analyzer.tokenStream("field", new StringReader("http://purl.org/eprint/type/Book"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("http://purl.org/eprint/type/book"));
		
		result = analyzer.tokenStream("field", new StringReader("S5 0014+813"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("s5"));
		assertTrue(tokens.get(1).equals("0014+813"));
		
		result = analyzer.tokenStream("field", new StringReader("Black Hole in Sgr A(*)"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 4);
		assertTrue(tokens.get(0).equals("black"));
		assertTrue(tokens.get(1).equals("hole"));
		assertTrue(tokens.get(2).equals("sgr"));
		assertTrue(tokens.get(3).equals("a(*"));
		
		result = analyzer.tokenStream("field", new StringReader("abc_cdf"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("abc_cdf"));
		
		result = analyzer.tokenStream("field", new StringReader("(001)-oriented MgO"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("001)-oriented"));
		assertTrue(tokens.get(1).equals("mgo"));
		
		result = analyzer.tokenStream("field", new StringReader("(001)-oriented TbBaCo2O6-α"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("001)-oriented"));
		assertTrue(tokens.get(1).equals("tbbaco2o6-α"));
		
		result = analyzer.tokenStream("field", new StringReader("galaxy IRAS 00182−7112"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 3);
		assertTrue(tokens.get(0).equals("galaxy"));
		assertTrue(tokens.get(1).equals("iras"));
		assertTrue(tokens.get(2).equals("00182−7112"));
		
		result = analyzer.tokenStream("field", new StringReader("Techniken für 0,01-mm3-Auflösung"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 4);
		assertTrue(tokens.get(0).equals("techniken"));
		assertTrue(tokens.get(1).equals("fur"));
		assertTrue(tokens.get(2).equals("0"));
		assertTrue(tokens.get(3).equals("01-mm3-auflosung"));
		
		result = analyzer.tokenStream("field", new StringReader("01-P003 The role of miRNAs"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 3);
		assertTrue(tokens.get(0).equals("01-p003"));
		assertTrue(tokens.get(1).equals("role"));
		assertTrue(tokens.get(2).equals("mirnas"));
		
		result = analyzer.tokenStream("field", new StringReader("supernova remnant 1E 0102.2-7219"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 4);
		assertTrue(tokens.get(0).equals("supernova"));
		assertTrue(tokens.get(1).equals("remnant"));
		assertTrue(tokens.get(2).equals("1e"));
		assertTrue(tokens.get(3).equals("0102.2-7219"));
		
		result = analyzer.tokenStream("field", new StringReader("quasar SDSS J172206.03+565451.6"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 3);
		assertTrue(tokens.get(0).equals("quasar"));
		assertTrue(tokens.get(1).equals("sdss"));
		assertTrue(tokens.get(2).equals("j172206.03+565451.6"));
		
		result = analyzer.tokenStream("field", new StringReader("0[110] transformation"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("0[110]"));
		assertTrue(tokens.get(1).equals("transformation"));
		
		result = analyzer.tokenStream("field", new StringReader("pi-Congested poly(paraphenylene) from 2,2',6,6'-tetraphenyl-1,1'-biphenyl units"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 9);
		assertTrue(tokens.get(0).equals("pi-congested"));
		assertTrue(tokens.get(1).equals("poly(paraphenylene"));
		assertTrue(tokens.get(2).equals("from"));
		assertTrue(tokens.get(3).equals("2"));
		assertTrue(tokens.get(4).equals("2"));
		assertTrue(tokens.get(5).equals("6"));
		assertTrue(tokens.get(6).equals("6'-tetraphenyl-1"));
		assertTrue(tokens.get(7).equals("1'-biphenyl"));
		assertTrue(tokens.get(8).equals("units"));
		
		result = analyzer.tokenStream("field", new StringReader("1,5-Dimethoxy-9,10-di(phenylethynyl)anthracene fluorophore"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 4);
		assertTrue(tokens.get(0).equals("1"));
		assertTrue(tokens.get(1).equals("5-dimethoxy-9"));
		assertTrue(tokens.get(2).equals("10-di(phenylethynyl)anthracene"));
		assertTrue(tokens.get(3).equals("fluorophore"));
		
		result = analyzer.tokenStream("field", new StringReader("Pb-207/Pb-204"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("pb-207/pb-204"));
		
		result = analyzer.tokenStream("field", new StringReader("Nb/La"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 1);
		assertTrue(tokens.get(0).equals("nb/la"));
		
		result = analyzer.tokenStream("field", new StringReader("Sgr A*'s near-infrared"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 3);
		assertTrue(tokens.get(0).equals("sgr"));
		assertTrue(tokens.get(1).equals("a*'s"));
		assertTrue(tokens.get(2).equals("near-infrared"));
		
		result = analyzer.tokenStream("field", new StringReader("1,3-Bicyclo[1.1.1]pentanediyl"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("1"));
		assertTrue(tokens.get(1).equals("3-bicyclo[1.1.1]pentanediyl"));
		
		result = analyzer.tokenStream("field", new StringReader("Meier, Hans"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("meier"));
		assertTrue(tokens.get(1).equals("hans"));

		result = analyzer.tokenStream("field", new StringReader("Xaver Müller"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("xaver"));
		assertTrue(tokens.get(1).equals("muller"));
		
		result = analyzer.tokenStream("field", new StringReader("with other right"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("other"));
		assertTrue(tokens.get(1).equals("right"));
		
		result = analyzer.tokenStream("field", new StringReader("God and Right"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 2);
		assertTrue(tokens.get(0).equals("god"));
		assertTrue(tokens.get(1).equals("right"));
		
		result = analyzer.tokenStream("field", new StringReader("we didn't use the same brain twice"));
		tokens = doTokenizing(result);
		assertTrue(tokens.size() == 6);
		assertTrue(tokens.get(0).equals("we"));
		assertTrue(tokens.get(1).equals("didn't"));
		assertTrue(tokens.get(2).equals("use"));
		assertTrue(tokens.get(3).equals("same"));
		assertTrue(tokens.get(4).equals("brain"));
		assertTrue(tokens.get(5).equals("twice"));		
	}
	
	private List<String> doTokenizing(TokenStream ts) throws IOException {
		
		List<String> tokens = new ArrayList<String>();
		TermAttribute termAtt = ts.addAttribute(TermAttribute.class);
		try {
			ts.reset();

			while (ts.incrementToken()) {
				String s = termAtt.term();
				tokens.add(s);
				System.out.println("token: " + s);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		ts.clearAttributes();
		ts.close();
		
		return tokens;
	}

}
