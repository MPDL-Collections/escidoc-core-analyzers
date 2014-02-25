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
import java.util.regex.Pattern;

import org.apache.lucene.analysis.CharTokenizer;
import org.apache.lucene.util.Version;

/**
 * @author mih
 * 
 * Class tokenizes Strings at Whitespace and at <,>,&,.,,,;,!,?.
 * But doesnt tokenize xs:date and xs:decimal
 * 
 */
public class XmlWhitespaceTokenizer extends CharTokenizer {

	private StringBuffer lastToken = new StringBuffer("");
    private static Pattern dateOrDecimalPattern = Pattern.compile(
    			"[0-9]*?-[0-9]*?-[0-9]*?[0-9TtZz\\+\\:\\.\\-]*|[+-]?[0-9\\.]*");

    /**
     * Constructor with Reader.
     * 
     * @param in
     *            Reader
     * 
     */
    public XmlWhitespaceTokenizer(final Reader in) {
        super(in);
    }

    /**
     * Return false if character is Whitespace or <,>,&,.,,,;,!,?.
     * \u003c <
     * \u003e >
     * \u0026 &
     * \u002c ,
     * \u003b ;
     * \u0021 !
     * \u003f ?
     * 
     * @param c
     *            character
     * @return true if whitespace-character, else false.
     * 
     */
    @Override
    protected boolean isTokenChar(final char c) {
    	
        if (Character.isWhitespace(c) || c == '\u003c' || c == '\u003e'
            || c == '\u0026' || c == '\u002c'
            || c == '\u003b' || c == '\u0021' || c == '\u003f') {
        	
        	lastToken = new StringBuffer("");
            return false;
        }
        lastToken.append(c);
        return true;
    }
}
