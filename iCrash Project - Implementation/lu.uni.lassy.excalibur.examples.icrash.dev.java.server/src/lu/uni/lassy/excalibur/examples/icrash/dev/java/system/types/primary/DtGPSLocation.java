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
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.VincentyDistanceCalculator;

import static java.lang.Double.isNaN;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.asin;
import static java.lang.Math.atan;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;
import static java.lang.Math.round;
import java.text.NumberFormat;

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
		//first method to compute distance between two points - Haversine formula
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

		
		/*******************
		 * Taken from:
		 * http://svn.codehaus.org/geotracing/research/gis/src/calc/GISCalcAlt.java
		 * 
		 ******************/
		//second method to compute distance between two points - Law of Cosines
		static public double distance3(double lat1, double lon1, double lat2, double lon2) {

			// convert to radians
			lat1 = Math.toRadians(lat1);
			lat2 = Math.toRadians(lat2);
			lon1 = Math.toRadians(lon1);
			lon2 = Math.toRadians(lon2);

			// do the spherical trig calculation
			double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2) +
					Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

			// convert back to degrees
			angle = Math.toDegrees(angle);

			// each degree on a great circle of Earth is 69.1105 miles
			return 69.1105 * angle;

		}
		
		public DtGPSLocation(DtLatitude aLatitude,DtLongitude aLongitude){
			latitude = aLatitude;
			longitude = aLongitude;
		}
		
		
		public void is(){
			//TODO
		}
		
		public PtBoolean isNearTo(DtLatitude aLatitude,DtLongitude aLongitude){
	
			double lat1,lat2,long1,long2,dist1, dist2,dist3;
			
			lat1 = latitude.value.getValue();
			lat2 = aLatitude.value.getValue();
			long1 = longitude.value.getValue();
			long2 = aLongitude.value.getValue();
			
			
			dist1 = distanceBetweenTwoLocationsInKm(lat1,long1,lat2,long2);
			dist2 = VincentyDistanceCalculator.getDistance(lat1,long1,lat2,long2); //third method for distance computation - the Vincenty formula
			dist3 = distance3(lat1,long1,lat2,long2);
			
			double dist = chooseBestDistance(dist1, dist2, dist3);
			//compare best distance * 1000 to the threshold of 100 m to determine whether it is close to an existing crisis or if a new crisis has to be created for the new location 
			if(dist*1000 < 100)
				return new PtBoolean(true);
			else
				return new PtBoolean(false);
		}
		//choose best distance out of the three with the help of a weighted average
		private double chooseBestDistance(double dist1, double dist2, double dist3) {
			
			double dist = 0;
			double w1,w2,w3;
			
			w1 = 1; 
			w2 = 1;
			w3 = 0.5; //a weight of 1/2 for the Law of cosines 
			
			dist = (w1*dist1 + w2*dist2 + w3*dist3)/2.5;
			
			System.out.println("dist: " + dist);

			return dist;
		}


}
