#-------------------------------------------------------------------------------
# Copyright (c) 2014-2015 University of Luxembourg.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
# 
# Contributors:
#     Alfredo Capozucca - initial API and implementation
#-------------------------------------------------------------------------------

iCrash Java Distributed Development 
===================================

What Is This?
-------------
This Java project contains the CLIENT side implementation of the iCrash case study
specified according to the Messir methodology and using the Excalibur tool.

http://hera.uni.lu:8090/confluence/pages/viewpage.action?title=Excalibur&spaceKey=EXCALIBUR



Prerequisites
--------------------------
1. Install the iCrash Java SERVER side implementation.
2. Launch the iCrash server.



How To Install The Project
--------------------------
1. Import the CLIENT iCrash Java Development project into your workspace. 
This can be done by checking out the project from the following SVN's repository address:

https://doomsday.uni.lux/svn/teaching-excalibur/casestudies/iCrash/dev/distributed/lu.uni.lassy.excalibur.examples.icrash.dev.java.client


2. Build the project (if option Project -> Build Automatically has not been selected) 





How To Execute The Project?
--------------------------
A) JUnit-like mode
This mode allows you to write java tests aimed at simulating particular scenarios of interest. 

The package lu.uni.lassy.excalibur.examples.icrash.dev.client.java.testcases
contains some examples aimed at letting the user execute some interesting scenarios.
 
a.1) to test whether the server is reachable: TestCase_client_serverIsAlive
a.2) to test whether the system administrator can be logged: TestCase_client_login
a.3) to perform the scenario described in the iCrash Specification document in its seciton Test Model: TestCase_client_instance01
 
To execute any of these scenarios you should:

1. Open the class that implements the scenario of interest contained in the package 
lu.uni.lassy.excalibur.examples.icrash.dev.client.java.testcases

2. Right-click on the class and then select Run as -> Java Application

The results of such execution are to be shown in the console.



B) GUI mode
This execution mode gives the user a Control Simulation Panel from where he can monitor the system and its environment.
The GUI also allows the user to behave as any of the system-related actors such that he can perform any of the 
respective actor-related operations. Thus, the user may decide on the fly the scenario to be simulate.
To launch the GUI mode you should:

1.Open the class Main contained in the package 
lu.uni.lassy.excalibur.examples.icrash.dev.client.java.gui

2. Right-click on the class and then select Run as -> Java Application


Note: GUI mode limitations
The controller part associated to the GUI is not yet fully implemented. Thus just few system operations can be
executed. However, the system operations that have been implemented convey the technical information to easily 
achieve the implementation of the remaining part of the GUI.





Admin credentials?
--------------------------
There exists a built-in user called "admin". Its credentials are:
user name: icrashadmin
password: 7WXC1359
 
This user allows to create the other system users who are known as "Coordinators".


 
