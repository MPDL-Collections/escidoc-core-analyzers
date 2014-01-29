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

/**
 * Class holds all Stopwords that have to get filtered in 
 * language-independant index in a String-Array.
 * 
 * @author MIH
 * @sb
 */
public class AllStopwords {
	
	public static final String[] ALL_STOP_WORDS = { 
		// german
			"einer", "eine", "eines", "einem", "einen", "der", "die", "das", "dass",
			"daß", "du", "er", "sie", "es", "was", "wer", "wie", "wir", "und",
			"oder", "ohne", "mit", "am", "im", "in", "aus", "auf", "ist",
			"sein", "war", "wird", "ihr", "ihre", "ihres", "als", "für", "von",
			"mit", "dich", "dir", "mich", "mir", "mein", "sein", "kein",
			"durch",
			"wegen",
			"wird",

			// english
			"a", "an", "and", "are", "as", "at", "be", "but", "by", "for",
			"if", "in", "into", "is", "it", "no", "not", "of", "on", "or",
			"such", "that", "the", "their", "then", "there", "these", "they",
			"this", "to", "was", "will", "with" };

}
