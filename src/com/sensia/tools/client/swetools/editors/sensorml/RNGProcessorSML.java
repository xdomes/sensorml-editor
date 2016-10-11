/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are Copyright (C) 2016 DHAINAUT.
 All Rights Reserved.
 
 Contributor(s): 
    Mathieu DHAINAUT <mathieu.dhainaut@gmail.com>
 
 ******************************* END LICENSE BLOCK ***************************/

package com.sensia.tools.client.swetools.editors.sensorml;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.sensia.gwt.relaxNG.RNGParser;
import com.sensia.gwt.relaxNG.RNGParserCallback;
import com.sensia.gwt.relaxNG.XMLSensorMLParser;
import com.sensia.relaxNG.RNGGrammar;
import com.sensia.relaxNG.RNGTag;
import com.sensia.relaxNG.RNGTagList;
import com.sensia.tools.client.swetools.editors.sensorml.controller.IObserver;
import com.sensia.tools.client.swetools.editors.sensorml.panels.widgets.ISensorWidget;
import com.sensia.tools.client.swetools.editors.sensorml.panels.widgets.ISensorWidget.MODE;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.RNGRendererSML;

/**
 * The Class RNGProcessorSML is in charge of parsing a document.
 * The processor can parse a document from local storage (file) or from an url.
 */
public class RNGProcessorSML {

	/** The observers. */
	private List<IObserver> observers;
	
	/** The loaded grammar. */
	private RNGGrammar loadedGrammar;
	
	/** The mode. */
	private MODE mode = MODE.VIEW;
	
	private int rootMinLevel = 1;
	
	/**
	 * Instantiates a new RNG processor sml.
	 */
	public RNGProcessorSML(){
		this.observers = new ArrayList<IObserver>();
	}
	
	/**
	 * Parses the document from an url.
	 *
	 * @param url the url
	 */
	public void parse(final String url) {
		//if the document is a RNG instance
		if(url.toLowerCase().endsWith(".rng")) {
			final RNGParser parser = new RNGParser();
			parser.parse(url, new RNGParserCallback() {
				@Override
				public void onParseDone(final RNGGrammar grammar) {
					parseRNG(grammar);
				}
			});
		} else if(url.toLowerCase().endsWith(".xml")) {
			//if the document is a XML instance
			//transform XML document into RNG profile
			final XMLSensorMLParser parser = new XMLSensorMLParser();
			parser.parse(url, new RNGParserCallback() {
				
				@Override
				public void onParseDone(final RNGGrammar grammar) {
					parseRNG(grammar);
				}
			});
		}
	}

	/**
	 * Parses the document from a local file.
	 *
	 * @param fileName the file name
	 * @param xmlContent the xml content
	 * @throws Exception the exception
	 */
	public void parse(final String fileName,final String xmlContent) throws Exception {
		//transform XML document into RNG profile
		final XMLSensorMLParser parser = new XMLSensorMLParser();
		final RNGGrammar grammar = parser.parse(fileName, xmlContent);
		parseRNG(grammar);
	}
	
	public void parseString(final String xmlContent) {
		//transform XML document into RNG profile
		final XMLSensorMLParser parser = new XMLSensorMLParser();
		RNGGrammar grammar;
		try {
			grammar = parser.parse("", xmlContent);
			parseRNG(grammar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Parses the rng grammar.
	 *
	 * @param grammar the grammar
	 */
	private void parseRNG(final RNGGrammar grammar) {
		setLoadedGrammar(grammar);
		RNGRendererSML renderer = new RNGRendererSML();
		renderer.setRootMinLevel(rootMinLevel);
		renderer.setObservers(observers);
		renderer.visit(grammar);
		ISensorWidget root = renderer.getRoot();
		
		for(final IObserver observer : observers) {
			observer.parseDone(root);
		}
		
		root.switchMode(mode);
	}
	
	/**
	 * Parses only a subset of the global document
	 * @param url
	 */
	public ISensorWidget parseRNG(RNGTag tag) {
		RNGRendererSML renderer = new RNGRendererSML();
		renderer.setRootMinLevel(rootMinLevel);
		renderer.visit(tag);
		
		return renderer.getRoot();
	}
	
	/**
	 * Gets the loaded grammar.
	 *
	 * @return the loaded grammar
	 */
	public RNGGrammar getLoadedGrammar() {
		return loadedGrammar;
	}

	/**
	 * Sets the loaded grammar.
	 *
	 * @param loadedGrammar the new loaded grammar
	 */
	public void setLoadedGrammar(RNGGrammar loadedGrammar) {
		this.loadedGrammar = loadedGrammar;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public MODE getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public void setMode(MODE mode) {
		this.mode = mode;
	}
	
	public void addObserver(IObserver observer) {
		this.observers.add(observer);
	}
	
	public void setRootMinLevel(int rootMinLevel) {
		this.rootMinLevel = rootMinLevel;
	}
}
