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
import com.qualogy.qafe.bind.presentation.component.Image;
import com.qualogy.qafe.bind.presentation.component.Window;
import com.qualogy.qafe.mgwt.client.vo.ui.ComponentGVO;
import com.qualogy.qafe.mgwt.client.vo.ui.ImageGVO;
import com.qualogy.qafe.mgwt.server.helper.UIAssemblerHelper;
import com.qualogy.qafe.web.util.SessionContainer;

public class ImageUIAssembler implements UIAssembler {

	public ImageUIAssembler() {
	}

	public ComponentGVO convert(Component object,Window currentWindow,ApplicationMapping applicationMapping, ApplicationContext context, SessionContainer ss) {
		ComponentGVO vo = null;
		if (object != null) {
			if (object instanceof Image) {
				Image image = (Image)object;
				ImageGVO voTemp  = new ImageGVO();
				UIAssemblerHelper.copyFields(image, currentWindow,voTemp,applicationMapping, context, ss);				
				voTemp.setDescription(image.getDescription());
				voTemp.setImageLocation(image.getImageLocation());
				vo =voTemp;
			}
		}
		return vo;
	}

	public String getStaticStyleName() {
		return "image";
	}
}
