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
package com.qualogy.qafe.mgwt.server.ui;

import com.qualogy.qafe.bind.presentation.component.View;


public abstract class GwtUIRenderer {

	
	private View view = null;
	
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Constructor for the DojoUIGenerator.
	 * @param ui an instance of the ApplicationMapping description object
	 */
	public GwtUIRenderer(View ui) {
		this.view = ui;
	}
	
	/**
	 * Render the RootPanel and its contents
	 * @return the RootPanel and all contenst
	 */
	public void render() {
		
	}
}