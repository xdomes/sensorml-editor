package com.sensia.tools.client.swetools.editors.sensorml.panels.sml.edit;

import com.sensia.relaxNG.RNGElement;
import com.sensia.relaxNG.RNGTag;
import com.sensia.tools.client.swetools.editors.sensorml.panels.AbstractPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.IPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.base.element.edit.EditElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.base.element.edit.EditSubSectionElementPanel;
import com.sensia.tools.client.swetools.editors.sensorml.panels.line.EditAbstractGenericLinePanel;

public class SMLEditCapabilityPanel extends EditSubSectionElementPanel{

	public SMLEditCapabilityPanel(RNGElement element) {
		super(element);
	}

	@Override
	protected AbstractPanel<RNGElement> newInstance() {
		return null;
	}
}