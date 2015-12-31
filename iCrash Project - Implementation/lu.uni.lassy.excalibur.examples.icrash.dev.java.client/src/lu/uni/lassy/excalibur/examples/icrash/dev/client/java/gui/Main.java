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
package lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
	{
       try {
       	    AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("MainPanel.fxml"));
            Scene scene = new Scene(page,1010,680);
            primaryStage.setScene(scene);
            primaryStage.setTitle("iCrash Control Simulation Panel");
            primaryStage.show();
       
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
      
     public static void main(String[] args) {
		Application.launch(Main.class, (java.lang.String[])null);
	}
     
    
}
