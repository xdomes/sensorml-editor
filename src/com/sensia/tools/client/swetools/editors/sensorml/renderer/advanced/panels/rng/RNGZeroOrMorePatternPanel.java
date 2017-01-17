package com.sensia.tools.client.swetools.editors.sensorml.renderer.advanced.panels.rng;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sensia.relaxNG.RNGTag;
import com.sensia.relaxNG.RNGZeroOrMore;
import com.sensia.tools.client.swetools.editors.sensorml.panels.AbstractPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.IPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.IRefreshHandler;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.editor.panels.element.EditSectionElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.editor.panels.element.EditSimpleElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.utils.Utils;

public class RNGZeroOrMorePatternPanel extends AbstractPanel<RNGZeroOrMore>{

	private Panel patternContainer;
	private HTML removeButton;
	
	public RNGZeroOrMorePatternPanel(final RNGZeroOrMore tag,final int indexPattern,final IRefreshHandler refreshHandler) {
		super(tag,refreshHandler);
		
		patternContainer = new VerticalPanel();
		patternContainer.addStyleName("rng-zeroormore-pattern");
		
		final String label = Utils.findLabel(tag);
		
		removeButton = new HTML();
		Panel hPanel = new HorizontalPanel();
		hPanel.add(removeButton);
		hPanel.add(new HTML(Utils.toNiceLabel(label)));
		
		container.add(hPanel);
		container.add(patternContainer);
		
		removeButton.addStyleName("rng-optional-select-remove");
		
		removeButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				// remove pattern from pattern instances
				tag.getPatternInstances().remove(indexPattern);
				
				if(refreshHandler != null) {
					refreshHandler.refresh();
				}
			}
		});
		
		patternContainer.addStyleName("rng-optional-pattern");
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void addInnerElement(IPanel<? extends RNGTag> element) {
		if(element instanceof EditSectionElementPanel) {
			
			element.getPanel().removeStyleName("disclosure-noborder");
			element.getPanel().addStyleName("section-panel disclosure-border");
			container.clear();
			Panel hPanel = new HorizontalPanel();
			hPanel.add(removeButton);
			hPanel.add(element.getPanel());
			
			hPanel.addStyleName("rng-disclosure");
			container.add(hPanel);
		} else if(element instanceof EditSimpleElementPanel){
			// inline the simple edit panel
			container.clear();
			container = new HorizontalPanel();
			//container.addStyleName("rng-optional-pattern");
			container.add(removeButton);
			container.add(patternContainer);
			
			// remove default style because of new inline configuration
			patternContainer.removeStyleName("rng-optional-pattern");
			patternContainer.addStyleName("rng-optional-pattern-single");
			patternContainer.add(element.getPanel());
		} else {
			patternContainer.add(element.getPanel());
		}
	}

	@Override
	protected AbstractPanel<RNGZeroOrMore> newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
