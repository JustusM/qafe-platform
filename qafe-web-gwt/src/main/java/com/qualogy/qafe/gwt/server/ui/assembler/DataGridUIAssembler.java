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
package com.qualogy.qafe.gwt.server.ui.assembler;

import java.util.Iterator;

import com.qualogy.qafe.bind.core.application.ApplicationContext;
import com.qualogy.qafe.bind.domain.ApplicationMapping;
import com.qualogy.qafe.bind.presentation.component.Component;
import com.qualogy.qafe.bind.presentation.component.DataGrid;
import com.qualogy.qafe.bind.presentation.component.DataGridColumn;
import com.qualogy.qafe.bind.presentation.component.Window;
import com.qualogy.qafe.gwt.client.vo.ui.ComponentGVO;
import com.qualogy.qafe.gwt.client.vo.ui.DataGridColumnGVO;
import com.qualogy.qafe.gwt.client.vo.ui.DataGridGVO;
import com.qualogy.qafe.gwt.client.vo.ui.HiddenGVO;
import com.qualogy.qafe.gwt.client.vo.ui.ImageGVO;
import com.qualogy.qafe.gwt.client.vo.ui.PanelGVO;
import com.qualogy.qafe.gwt.server.helper.UIAssemblerHelper;
import com.qualogy.qafe.web.util.SessionContainer;

public class DataGridUIAssembler implements UIAssembler {

	public DataGridUIAssembler() {
	}

    // CHECKSTYLE.OFF: CyclomaticComplexity
	public ComponentGVO convert(Component object, Window currentWindow,ApplicationMapping applicationMapping, ApplicationContext context, SessionContainer ss) {
		ComponentGVO vo = null;
		if (object != null) {
			if (object instanceof DataGrid) {
				int totalWidthOfColumns = 0;
				DataGrid dataGrid = (DataGrid)object;
				DataGridGVO voTemp  = new DataGridGVO();
				UIAssemblerHelper.copyFields(dataGrid, currentWindow,voTemp,applicationMapping, context, ss);

				voTemp.setMaxRows(dataGrid.getMaxRows());
				voTemp.setPageSize(dataGrid.getPageSize());
				voTemp.setPageScroll(dataGrid.getPageScroll());
				voTemp.setMinRows(dataGrid.getMinRows());
				voTemp.setDelete(dataGrid.getDelete());
				voTemp.setAdd(dataGrid.getAdd());
				voTemp.setExport(dataGrid.getExport());
				voTemp.setExportFormats(dataGrid.getExportFormats());
				voTemp.setImportEnabled(dataGrid.getImportEnabled());
				voTemp.setMultipleSelect(dataGrid.getMultipleSelect());
				voTemp.setSelectFirstRow(dataGrid.getSelectFirstRow());
				voTemp.setSave(dataGrid.getSave());
				voTemp.setCancel(dataGrid.getCancel());
				voTemp.setRefresh(dataGrid.getRefresh());
				voTemp.setOverflowGroup(dataGrid.getOverflowGroup());

				if(dataGrid.getRowColors() != null){
					voTemp.setRowColors((dataGrid.getRowColors().trim()).split(","));
				}

				if (dataGrid.hasOverflow()){
					voTemp.setOverflow((PanelGVO) ComponentUIAssembler.convert(dataGrid.getOverflow(), currentWindow,applicationMapping,context, ss));
				}

				if (dataGrid.getColumns()!=null){
					Iterator<DataGridColumn> itr = dataGrid.getColumns().iterator();
					DataGridColumnGVO[] columns =  new DataGridColumnGVO[dataGrid.getColumns().size()];

					int index=0;
					while (itr.hasNext()){
						 columns[index]= (DataGridColumnGVO)ComponentUIAssembler.convert((Component)itr.next(),currentWindow,applicationMapping,context, ss);
						 columns[index].setContainerName(dataGrid.getId());
						 if (!dataGrid.getEditable()) {
							 // When the parent is not editable,
							 // all its children should not be editable also
							 columns[index].setEditable(dataGrid.getEditable());
						 }
						 if (columns[index].getWidth() != null){
							 totalWidthOfColumns += Integer.parseInt(columns[index].getWidth());
						 }
						 index++;
					}
					voTemp.setColumns(columns);
				}

				// http://livedocs.adobe.com/flex/3/langref/mx/controls/DataGrid.html
				if(voTemp.getWidth() == null) {
					if(totalWidthOfColumns > 0){
						voTemp.setWidth(totalWidthOfColumns+"px");
					} else {
						voTemp.setWidth("300px");
					}
				}


				// Since the datagrid is inspired by the Flex Datagrid, we apply the same rules.
				if (voTemp.getHeight()==null || voTemp.getHeight().trim().length()==0){
					voTemp.setHeight("140px");
				}

				if (dataGrid.getDelete().booleanValue() || dataGrid.getAdd().booleanValue() || dataGrid.getEditable().booleanValue()){
					if (dataGrid.getSave()){
						voTemp.setSaveComponent(createImage(dataGrid,"save","images/save.png","Save changes",dataGrid.getId()));
					}
					if (dataGrid.getRefresh()){
						voTemp.setRefreshComponent(createImage(dataGrid,"refresh","images/refresh.png","Refresh Data",dataGrid.getId()));
					}
					if (dataGrid.getCancel()){
						voTemp.setCancelComponent(createImage(dataGrid,"cancel","images/undo.png","Undo changes",dataGrid.getId()));
					}
				}

				if (dataGrid.getDelete().booleanValue()){
					voTemp.setDeleteComponent(createImage(dataGrid,"delete","images/delete.png","Delete record(s)",dataGrid.getId()));
				}

				if (dataGrid.getAdd().booleanValue()){
					voTemp.setAddComponent(createImage(dataGrid,"add","images/add.gif","Add a new record",dataGrid.getId()));
				}

				if (dataGrid.getExport().booleanValue()){
					voTemp.setExportComponent(createImage(dataGrid,"export","images/export.gif","Export",dataGrid.getId()));
				}

				if (dataGrid.getPageSize()!=null && dataGrid.getPageSize().intValue()>0){
					voTemp.setPageSizeComponent(createHiddenComponent(dataGrid,"pagesize",dataGrid.getPageSize()));
					voTemp.setPageSize(dataGrid.getPageSize());
					voTemp.setOffSetComponent(createHiddenComponent(dataGrid,"offset","0"));
				}

				vo = voTemp;
			}
		}
		return vo;
	}
    // CHECKSTYLE.OFF: CyclomaticComplexity

	private ComponentGVO createImage(DataGrid dataGrid, String id, String imageUrl, String description, String parent) {
		ImageGVO imageGVO = new ImageGVO();
		imageGVO.setParent(parent);
		imageGVO.setDescription(description);
		imageGVO.setImageLocation(imageUrl);
		imageGVO.setId(dataGrid.getId()+"."+id);
		return imageGVO;

	}

	private ComponentGVO createHiddenComponent(DataGrid dataGrid, String id,Object value) {
		HiddenGVO hiddenGVO = new HiddenGVO();
		hiddenGVO.setId(dataGrid.getId()+"."+id);
		hiddenGVO.setValue(value.toString());
		return hiddenGVO;
	}

	public String getStaticStyleName() {
		return "datagrid";
	}
}
