package com.sensia.tools.client.swetools.editors.sensorml.renderer.editor.panels.sml;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Panel;
import com.sensia.relaxNG.RNGElement;
import com.sensia.relaxNG.RNGTag;
import com.sensia.tools.client.swetools.editors.sensorml.panels.AbstractPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.IPanel;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.editor.panels.element.EditSectionElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.editor.panels.element.EditSubSectionElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.viewer.panels.root.ViewRootHeaderPanel;
import com.sensia.tools.client.swetools.editors.sensorml.renderer.viewer.panels.root.ViewRootPanel;
import com.sensia.tools.client.swetools.editors.sensorml.utils.Utils;

/**
 * 
 * Handle  a link pointing to a new viewer using the current xlink ref.
 * attributes:
 * - name
 * - xlink:title
 * - xlink:role
 * - xlink:href
 *
 */
public class SMLEditComponentPanel extends EditSectionElementPanel{

	public SMLEditComponentPanel(RNGElement tag) {
		super(tag,null);
		container.addStyleName("component");
	}
	
	
	@Override
	protected void addInnerElement(IPanel<? extends RNGTag> element) {
		if(element.getName().equals("name")) {
			labelPanel.clear();
			labelPanel.add(element.getPanel());
			labelPanel.setVisible(true);
		} else {
			super.addInnerElement(element);
		}
	}
	
	@Override
	protected AbstractPanel<RNGElement> newInstance() {
		// TODO Auto-generated method stub
		return null;
	}
}
