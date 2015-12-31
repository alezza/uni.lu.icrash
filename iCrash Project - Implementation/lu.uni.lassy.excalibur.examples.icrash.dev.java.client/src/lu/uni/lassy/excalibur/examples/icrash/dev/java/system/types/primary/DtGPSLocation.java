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

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class DtGPSLocation implements Serializable {

		private static final long serialVersionUID = 227L;

		public DtLatitude latitude;
		public DtLongitude longitude;
		

		/*******************
		 * Taken from:
		 * http://stackoverflow.com/questions/120283/how-can-i-measure-distance-and-create-a-bounding-box-based-on-two-latitudelongi
		 * 
		 ******************/ 
		
		
		
		static Double distanceBetweenTwoLocationsInKm(Double latitudeOne, Double longitudeOne, 
														Double latitudeTwo, Double longitudeTwo) {
	        if (latitudeOne == null || latitudeTwo == null || longitudeOne == null || longitudeTwo == null) {
	            return null;
	        }

	        Double earthRadius = 6371.0;
	        Double diffBetweenLatitudeRadians = Math.toRadians(latitudeTwo - latitudeOne);
	        Double diffBetweenLongitudeRadians = Math.toRadians(longitudeTwo - longitudeOne);
	        Double latitudeOneInRadians = Math.toRadians(latitudeOne);
	        Double latitudeTwoInRadians = Math.toRadians(latitudeTwo);
	        Double a = Math.sin(diffBetweenLatitudeRadians / 2) * Math.sin(diffBetweenLatitudeRadians / 2) + Math.cos(latitudeOneInRadians) * Math.cos(latitudeTwoInRadians) * Math.sin(diffBetweenLongitudeRadians / 2)
	                * Math.sin(diffBetweenLongitudeRadians / 2);
	        Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	        return (earthRadius * c);
    }



		
		
		public DtGPSLocation(DtLatitude aLatitude,DtLongitude aLongitude){
			latitude = aLatitude;
			longitude = aLongitude;
		}
		
		
		public void is(){
			//TODO
		}
		
		public PtBoolean isNearTo(DtLatitude aLatitude,DtLongitude aLongitude){
	
			double lat1,lat2,long1,long2,dist;
			
			lat1 = latitude.value.getValue();
			lat2 = aLatitude.value.getValue();
			long1 = longitude.value.getValue();
			long2 = aLongitude.value.getValue();
	
			dist = distanceBetweenTwoLocationsInKm(lat1,long1,lat2,long2);
	
			if(dist*1000 < 100)
				return new PtBoolean(true);
			else
				return new PtBoolean(false);
		}


}