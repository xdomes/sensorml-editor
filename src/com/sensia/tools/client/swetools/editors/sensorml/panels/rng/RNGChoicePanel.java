package com.sensia.tools.client.swetools.editors.sensorml.panels.rng;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.sensia.relaxNG.RNGChoice;
import com.sensia.relaxNG.RNGTag;
import com.sensia.relaxNG.RNGTagVisitor;
import com.sensia.tools.client.swetools.editors.sensorml.panels.AbstractPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.IPanel;
import com.sensia.tools.client.swetools.editors.sensorml.utils.Utils;

public class RNGChoicePanel extends AbstractPanel<RNGChoice>{

	private ListBox choices;
	private Panel paternContainer;
	
	public RNGChoicePanel(final RNGChoice tag, final RNGTagVisitor visitor) {
		super(tag);
		paternContainer = new VerticalPanel();
		
		choices = new ListBox();
		container.add(choices);
		container.add(paternContainer);
		
		choices.addItem("");
		
		// add children name into the choice and
		List<RNGTag> children = tag.getItems();
		if(children != null){
			for(RNGTag child : children) {
				String label = Utils.findLabel(child);
				choices.addItem(label);
			}
		}
		
		if(getTag().getSelectedIndex() != -1) {
			choices.setSelectedIndex(getTag().getSelectedIndex()+1);
		}
		
		choices.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				paternContainer.clear();
				if(choices.getSelectedIndex() == 0) {
					RNGChoicePanel.this.getTag().setSelectedIndex(-1);
				} else {
					RNGChoicePanel.this.getTag().setSelectedIndex(choices.getSelectedIndex()-1);
					tag.getSelectedPattern().accept(visitor);
				}
			}
		});
	}
	
	@Override
	public String getName() {
		return "";
	}

	@Override
	protected void addInnerElement(IPanel<? extends RNGTag> element) {
		paternContainer.add(element.getPanel());
	}

	@Override
	protected AbstractPanel<RNGChoice> newInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}