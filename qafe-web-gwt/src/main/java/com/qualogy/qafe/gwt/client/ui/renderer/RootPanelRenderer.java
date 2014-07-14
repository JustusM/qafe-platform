/**
 * Copyright 2008-2014 Qualogy Solutions B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qualogy.qafe.gwt.client.ui.renderer;

import com.google.gwt.user.client.ui.UIObject;
import com.qualogy.qafe.gwt.client.vo.ui.ComponentGVO;
import com.qualogy.qafe.gwt.client.vo.ui.RootPanelGVO;

public class RootPanelRenderer extends PanelRenderer {

	public UIObject render(ComponentGVO component, String uuid,String parent, String context) {
		String title = "";
		if (component instanceof RootPanelGVO){
			title =((RootPanelGVO)component).getTitle();
			((RootPanelGVO)component).setTitle("");
		}
		
		UIObject uiObject = super.render(component, uuid,parent, context);
		
		if (component instanceof RootPanelGVO){
			((RootPanelGVO)component).setTitle(title);
			
		}
		
	
//		UIObject panelWithMenuAndToolbar = addMenuAndToolbar(uiObject,
//				component, uuid,parent);
//
//		Widget w = (Widget) panelWithMenuAndToolbar;
//		return w;
		return uiObject;
	}


	
	
	

}