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
package com.qualogy.qafe.presentation.handler;

import java.util.HashMap;
import java.util.Map;

import com.qualogy.qafe.bind.business.action.BusinessAction;
import com.qualogy.qafe.bind.core.application.ApplicationContext;
import com.qualogy.qafe.core.datastore.DataIdentifier;
import com.qualogy.qafe.core.datastore.DataStore;
import com.qualogy.qafe.core.errorhandling.ExternalException;
import com.qualogy.qafe.presentation.BusinessActionItemDataObject;

/**
 * 
 * Handler for Business Action Event Items
 *
 */
public class BusinessActionRefHandler {

    public final Map<String, Object> execute(final BusinessActionItemDataObject businessActionItemDataObject,
            final ApplicationContext context, final DataIdentifier dataId) {

        final BusinessAction businessAction =
            context.getApplicationMapping().getBusinessTier()
                .getBusinessAction(businessActionItemDataObject.getBusinessActionId());

        Map<String, Object> outputValues = new HashMap<String, Object>();

        try {
            storeInputValuesInDataStore(businessActionItemDataObject, dataId);

            context.getBusinessManager().manage(context, dataId, businessAction);
            
            outputValues = collectOutputVariables(businessActionItemDataObject, dataId);
            

        } catch (final ExternalException e) {
            e.printStackTrace();
        }

        return outputValues;
    }

    private Map<String, Object> collectOutputVariables(final BusinessActionItemDataObject businessActionItemDataObject,
            final DataIdentifier dataId) {
        Map<String, Object> outputValues = new HashMap<String, Object>();
        for(String key : businessActionItemDataObject.getOutputVariables()) {
            Object value = DataStore.getValue(dataId, key);
            outputValues.put(key, value);
        }
        
        //This is to make sure the inout values can be used in the rest of the event.
        for(String key : businessActionItemDataObject.getInputValues().keySet()) {
            Object value = DataStore.getValue(dataId, key);
            outputValues.put(key, value);
        }
        
        return outputValues;
    }

    private void storeInputValuesInDataStore(final BusinessActionItemDataObject businessActionItemDataObject,
            final DataIdentifier dataId) {
        final Map<String, Object> inpuValues = businessActionItemDataObject.getInputValues();
        for (String key : businessActionItemDataObject.getInputValues().keySet()) {
            DataStore.store(dataId, key, inpuValues.get(key));
        }
    }

}
