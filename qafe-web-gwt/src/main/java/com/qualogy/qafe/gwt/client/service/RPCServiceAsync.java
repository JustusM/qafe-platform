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
package com.qualogy.qafe.gwt.client.service;

import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.qualogy.qafe.gwt.client.vo.data.EventDataGVO;
import com.qualogy.qafe.gwt.client.vo.data.EventItemDataGVO;
import com.qualogy.qafe.gwt.client.vo.functions.BuiltInFunctionGVO;
import com.qualogy.qafe.gwt.client.vo.functions.DataContainerGVO;

public interface RPCServiceAsync extends RemoteService {
	void getUISFromApplicationContext(Map<String,String> parameters,AsyncCallback<?> callback);

	void getUIFromApplicationContext(AsyncCallback<?> callback);

	void reload(Map<String,String> parameters,AsyncCallback<?> callback);
	
	void activate(Map<String,String> parameters, AsyncCallback<?> callback);

	void removeUI(String uuid, AsyncCallback<?> callback);

	/**
	 * 
	 * @param xml
	 * @param callback
	 */
	void getUI(String xml, AsyncCallback<?> callback);

	void getUIVO(String xml, AsyncCallback<?> callback);

	void executeEvent(EventDataGVO eventData, AsyncCallback<?> callback);

	void getUIByUUID(String uuid, AsyncCallback<?> callback);

	void getXMLUIByUUID(String uuid, AsyncCallback<?> callback);
	
	//void getXMLUIByWebserviceCall(String uuid, AsyncCallback<?> callback);

	
	void getMDIState(Map<String, String> parameters, AsyncCallback<?> callback);

	void removeFileFromLocalStore(String appUUID, String windowId, String uploadUUID, AsyncCallback<?> callback);
	
	void getDataForDatagridFromUpload(String uuid,AsyncCallback<?> callback);

	void prepareForExport(List<DataContainerGVO> list, String exportCode, String header,boolean autoGeneratedColumns,AsyncCallback<?> callback);
	
//	void notify(String to,String message,String body, AsyncCallback<?> callback);
	
	void notify(String message,String body, AsyncCallback<?> callback);
	
	void removeWindowsEventData(String eventId, String windowId, AsyncCallback<?> callback);
	
	void generateTypedCSS(String rendererType, String applicationId, AsyncCallback<?> callback);

	void removeGloballyStoredData(String windowSession, String applicationId, AsyncCallback<?> callback);

    void executeEventItem(EventItemDataGVO eventItem, AsyncCallback<?> callback);
}