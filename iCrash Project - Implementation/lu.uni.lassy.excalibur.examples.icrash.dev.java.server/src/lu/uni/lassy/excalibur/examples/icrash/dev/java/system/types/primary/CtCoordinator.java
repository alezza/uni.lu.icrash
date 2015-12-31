/*******************************************************************************
 * Copyright (c) 2014 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class CtCoordinator extends CtAuthenticated {

	private static final long serialVersionUID = 227L;

	public DtCoordinatorID id;
	
	
	public PtBoolean init(DtCoordinatorID aId,DtLogin aLogin,DtPassword aPwd){
			id = aId;
			login = aLogin;
			pwd = aPwd;
			return new PtBoolean(true); 
		}
		
		
		

}
