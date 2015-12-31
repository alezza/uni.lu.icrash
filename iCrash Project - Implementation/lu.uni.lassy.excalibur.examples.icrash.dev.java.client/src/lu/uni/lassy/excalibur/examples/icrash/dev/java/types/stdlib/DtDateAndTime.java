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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib;

import java.io.Serializable;
import java.util.Calendar;
import java.util.TimeZone;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;

import org.apache.log4j.Logger;


public class DtDateAndTime implements Serializable {

		private static final long serialVersionUID = 227L;

		public DtDate date;
		public DtTime time; 


		public DtDateAndTime(DtDate aDate, DtTime aTime) {
			date = aDate;
			time = aTime;
		}
		
		
		public void show(){
			Logger log = Log4JUtils.getInstance().getLogger();
			
			int d = date.day.value.value;
			int m = date.month.value.value;
			int y = date.year.value.value;
			
			int h = time.hour.value.value;
			int min = time.minute.value.value;
			int sec = time.second.value.value;
			
			//log.info("System state's clock is: "+y+":"+m+":"+d+"-"+h+":"+min+":"+sec);
			log.info(y+":"+m+":"+d+"-"+h+":"+min+":"+sec);
		
		}


		public PtInteger toSecondsQty(){
			
			Logger log = Log4JUtils.getInstance().getLogger();
			//Note: java's epoch is UTC 1/1/1970 
			
			int d = date.day.value.value;
			int m = date.month.value.value;
			int y = date.year.value.value;
			
			int h = time.hour.value.value;
			int min = time.minute.value.value;
			int sec = time.second.value.value;
			
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			calendar.clear();
			calendar.set(y, m, d, h, min, sec);
			double secondsSinceEpoch = calendar.getTimeInMillis() / 1000L; 
			log.debug("toSecondsQty[double]="+ secondsSinceEpoch);

			int res = (int)secondsSinceEpoch;
			log.debug("toSecondsQty[int]="+ res);
			
			return new PtInteger(res);
		
		}
}
