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
package com.qualogy.qafe.mgwt.server.ui.assembler;

import com.qualogy.qafe.bind.core.application.ApplicationContext;
import com.qualogy.qafe.bind.domain.ApplicationMapping;
import com.qualogy.qafe.bind.presentation.component.Component;
import com.qualogy.qafe.bind.presentation.component.Stack;
import com.qualogy.qafe.bind.presentation.component.Window;
import com.qualogy.qafe.mgwt.client.vo.ui.ComponentGVO;
import com.qualogy.qafe.mgwt.client.vo.ui.StackGVO;
import com.qualogy.qafe.mgwt.server.helper.UIAssemblerHelper;
import com.qualogy.qafe.web.util.SessionContainer;

public class StackUIAssembler implements UIAssembler {

	public StackUIAssembler() {
	}

	public ComponentGVO convert(Component object, Window currentWindow,ApplicationMapping applicationMapping, ApplicationContext context, SessionContainer ss) {
		ComponentGVO vo = null;
		if (object != null) {
			if (object instanceof Stack) {
				Stack stack = (Stack)object;
				StackGVO voTemp  = new StackGVO();
				UIAssemblerHelper.copyFields(stack, currentWindow,voTemp,applicationMapping, context, ss);		
				
				voTemp.setTitle(stack.getDisplayname());
				voTemp.setComponent(ComponentUIAssembler.convert(stack.getComponent(), currentWindow,applicationMapping,context, ss));
				
				
				vo =voTemp;
			}
		}
		return vo;
	}

	public String getStaticStyleName() {
		return "stack"; 
	}
}
