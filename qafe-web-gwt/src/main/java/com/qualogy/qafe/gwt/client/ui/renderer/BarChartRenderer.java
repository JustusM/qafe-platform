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
import com.objetdirect.tatami.client.charting.BarPlot;
import com.qualogy.qafe.gwt.client.component.QChart2D;
import com.qualogy.qafe.gwt.client.vo.ui.BarChartGVO;
import com.qualogy.qafe.gwt.client.vo.ui.ComponentGVO;

public class BarChartRenderer implements GWTUIRenderer {

	public UIObject render(ComponentGVO component, String uuid, String parent, String context) {
		QChart2D uiObject = null;
		String height = "250px";
		String width = "250px";
		if(component != null){
			if (component instanceof BarChartGVO) {
				BarChartGVO gvo = (BarChartGVO)component;
				if(gvo.getHeight()!=null){
					height = gvo.getHeight();
				}
				if(gvo.getWidth()!=null){
					width = gvo.getWidth();
				}
				uiObject = new QChart2D(height,width);
				uiObject.setCategoryAxis(gvo.getCategoryAxis());
				uiObject.setChartItems(gvo.getChartItems());
				uiObject.setLinearAxis(gvo.getLinearAxis());
				uiObject.setLegend(gvo.getLegend());
				
				
				BarPlot barPlot = new BarPlot(BarPlot.PLOT_TYPE_BARS_CLUSTERED);
				barPlot.setGap(10); 
				
				uiObject.addPlot(barPlot);
				RendererHelper.fillIn(component, uiObject, uuid, parent, context);
			}
		}
		return uiObject;
	}

}
