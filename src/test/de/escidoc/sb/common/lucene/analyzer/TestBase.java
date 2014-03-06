package test.de.escidoc.sb.common.lucene.analyzer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;

public class TestBase {

	public TestBase() {
		super();
	}

	protected List<String> doTokenizing(TokenStream ts) throws IOException {
		
		List<String> tokens = new ArrayList<String>();
		OffsetAttribute offsetAttribute = ts.addAttribute(OffsetAttribute.class);
		CharTermAttribute charTermAttribute = ts.addAttribute(CharTermAttribute.class);
		
		try {
			ts.reset();
	
			while (ts.incrementToken()) {
				String s = charTermAttribute.toString();
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