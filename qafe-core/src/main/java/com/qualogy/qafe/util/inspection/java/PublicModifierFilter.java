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
package com.qualogy.qafe.util.inspection.java;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;

public class PublicModifierFilter implements ModifierFilter {

	public boolean filter(Modifier modifier) {
		return isPublic(modifier.getAccess());
	}
	
	public static List<MethodDescriptor> filter(List<MethodDescriptor> methodDescriptors) {
		List<MethodDescriptor> publicMethodDescriptors = new ArrayList<MethodDescriptor>();
		for (MethodDescriptor methodDescriptor:methodDescriptors) {
			if (isPublic(methodDescriptor)) {
				publicMethodDescriptors.add(methodDescriptor);
			}
		}
		return publicMethodDescriptors;
	}
	
	public static boolean isPublic(Modifier modifier) {
		return ((modifier.getAccess() & Opcodes.ACC_PUBLIC) == 1);
	}
	
	public static boolean isPublic(int access) {
		return ((access & Opcodes.ACC_PUBLIC) == 1);
	}
}
