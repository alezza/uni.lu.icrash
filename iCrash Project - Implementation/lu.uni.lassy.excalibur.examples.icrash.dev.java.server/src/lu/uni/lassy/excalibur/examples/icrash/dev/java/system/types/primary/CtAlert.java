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

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActCoordinator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class CtAlert implements Serializable {

	private static final long serialVersionUID = 227L;

	public DtAlertID id;
	public EtAlertStatus status;
	public DtGPSLocation location;
	public DtDateAndTime instant;
	public DtComment comment;

	public PtBoolean init(DtAlertID aId, EtAlertStatus aStatus,
			DtGPSLocation aLocation, DtDateAndTime aInstant, DtComment aComment) {
			
		id = aId;
		status = aStatus;
		location = aLocation;
		instant = aInstant;
		comment = aComment;
		
		return new PtBoolean(true);
	}

	public PtBoolean isSentToCoordinator(ActCoordinator aActCoordinator) {
		aActCoordinator.ieSendAnAlert(this);
		return new PtBoolean(true);
	}

}
