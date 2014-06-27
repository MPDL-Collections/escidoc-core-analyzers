/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at license/ESCIDOC.LICENSE
 * or http://www.escidoc.de/license.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at license/ESCIDOC.LICENSE.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2006-2007 Fachinformationszentrum Karlsruhe Gesellschaft
 * für wissenschaftlich-technische Information mbH and Max-Planck-
 * Gesellschaft zur Förderung der Wissenschaft e.V.  
 * All rights reserved.  Use is subject to license terms.
 */

package de.escidoc.sb.common.lucene.analyzer;

import java.io.Reader;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.analysis.ASCIIFoldingFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.snowball.SnowballFilter;

/**
 * Analyzer for all escidoc lucene indices.
 * 
 * @author MIH
 * @sb
 */
public class EscidocAnalyzer extends Analyzer {
	
    private String language = null;

    private HashMap<String, HashMap> supportedLanguages =
        new HashMap<String, HashMap>();
    
    private int indexMode = 0;
    
    private TokenStream resultReindex = null;

    private static Log log = LogFactory.getLog(EscidocAnalyzer.class);

    /**
     * initializes the analyzer.
     * 
     * @sb
     */
    public EscidocAnalyzer() {
        if (log.isDebugEnabled()) {
            log.debug("Initializing EscidocAnalyzer");
        }

        // initialize Analyzers for languages
        supportedLanguages = Constants.SUPPORTED_ANALYZING_LANGUAGES;

    }

    /**
     * Constructs a token stream. -Whitespace tokenization -Language-dependant
     * stemming with SnowballFilter
     * 
     * @param fieldName
     *            name of the Lucene Indexfield.
     * @param reader
     *            reader with field-value
     * 
     * @return TokenStream tokenStream
     * 
     * @sb
     */
    @Override
    public TokenStream tokenStream(final String fieldName, final Reader reader) {
        if (log.isDebugEnabled()) {
            log.debug("tokenizing with EscidocAnalyzer");
        }

        if (this.indexMode == 0) {
        	return tokenStreamStandard(fieldName, reader);
        } else  {
        	return tokenStreamReindex(fieldName, reader);
        }


    }

    private TokenStream tokenStreamReindex(String fieldName, Reader reader) {
        // Tokenize with ClassicTokenizer
        resultReindex = new XmlWhitespaceTokenizer(reader);

        // apply filters
        // remove junk
        resultReindex = new JunkFilter(resultReindex);
        
        // make lowercase
        resultReindex = new LowerCaseFilter(Constants.LUCENE_VERSION, resultReindex);
        
        // convert non-ascii-chars to ascii (eg french e to ascii)
        resultReindex = new ASCIIFoldingFilter(resultReindex);

        // Do further stop-word removal,
        // stemming + normalization.
        if (language == null || language.equals("")) {
            language = "all";
        }
        // remove stop words
        resultReindex =
            new StopFilter(Constants.LUCENE_VERSION, resultReindex,
            					StopFilter.makeStopSet(
            							Constants.LUCENE_VERSION, 
            							((String[]) (supportedLanguages.get(language)).get("stopwords")), 
            							true));       
                    
        if (language != null 
                && supportedLanguages.get(language) != null
                && (String) (supportedLanguages.get(language))
                .get("snowballType") != null) {
            // stem
            resultReindex =
                new SnowballFilter(resultReindex,
                    (String) (supportedLanguages.get(language))
                        .get("snowballType"));
        }

        return resultReindex;
	}

	private TokenStream tokenStreamStandard(String fieldName, Reader reader) {
        // Tokenize with ClassicTokenizer
        TokenStream result = new XmlWhitespaceTokenizer(reader);

        // apply filters
        // remove junk
        result = new JunkFilter(result);
        
        // make lowercase
        result = new LowerCaseFilter(Constants.LUCENE_VERSION, result);
        
        // convert non-ascii-chars to ascii (eg french e to ascii)
        result = new ASCIIFoldingFilter(result);

        // Do further stop-word removal,
        // stemming + normalization.
        if (language == null || language.equals("")) {
            language = "all";
        }
        // remove stop words
        result =
            new StopFilter(Constants.LUCENE_VERSION, result,
            					StopFilter.makeStopSet(
            							Constants.LUCENE_VERSION, 
            							((String[]) (supportedLanguages.get(language)).get("stopwords")), 
            							true));       
                    
        if (language != null 
                && supportedLanguages.get(language) != null
                && (String) (supportedLanguages.get(language))
                .get("snowballType") != null) {
            // stem
            result =
                new SnowballFilter(result,
                    (String) (supportedLanguages.get(language))
                        .get("snowballType"));
        }

        return result;
	}

	/**
     * @return Returns the language.
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language
     *            The language to set.
     */
    public void setLanguage(final String language) {
        this.language = language;
    }
    
    /**
     * Overwrite the default positionIncrementGap of 0.
     * This is done to avoid phrase-searches across field-boundaries.
     * 
     * @param fieldName
     *            The name of the field.
     *            
     * @return int positionIncrementGap
     */
    public int getPositionIncrementGap(String fieldName) {
        return 1;
    }
    
    public void setIndexMode(int mode) {	
		this.indexMode = mode;
		
		log.info("indexMode of EscidocAnalyzer " + this + " set to " + indexMode );
	}
    
    public int getIndexMode() {
    	return this.indexMode;
    }
    
}