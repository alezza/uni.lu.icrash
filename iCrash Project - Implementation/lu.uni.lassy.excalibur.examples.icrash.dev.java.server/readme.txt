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
This Java project contains the SERVER side implementation of the iCrash case study
specified according to the Messir methodology and using the Excalibur tool.

http://hera.uni.lu:8090/confluence/pages/viewpage.action?title=Excalibur&spaceKey=EXCALIBUR




Prerequisites
--------------------------
1. Install MySql Server.
1.a Download from http://dev.mysql.com/downloads/

1.b Follow the installation instructions as explained at
http://dev.mysql.com/doc/refman/5.7/en/installing.html


2. Open a terminal and execute, and execute the following commands:
2.1 cd  [PATH_TO_THE_ICRASH_SERVER_JAVA_PROJECT]/scripts
2.2 mysql -u root -p  < icrash_db_create.sql

Note: to be sure that mysql service is running and it's on your PATH.



How To Install The Project
--------------------------
1. Import the SERVER iCrash Java Development project into your workspace. 
This can be done by checking out the project from the following SVN's repository address:

https://doomsday.uni.lux/svn/teaching-excalibur/casestudies/iCrash/dev/distributed/lu.uni.lassy.excalibur.examples.icrash.dev.java.server


2. Build the project (if option Project -> Build Automatically has not been selected) 





How To Execute The SERVER Project?
----------------------------------
A) Launching the server
The package lu.uni.lassy.excalibur.examples.icrash.dev.java.main
contains the LaunchServer class, which is in charge of starting the iCrash server.
Once, the server has been started, it's ready to receive requests coming from the client side application. 

To execute this you should:

1. Open the class LaunchServer contained in the package 
lu.uni.lassy.excalibur.examples.icrash.dev.java.main

2. Right-click on the class and then select Run as -> Java Application


B) Running simple test cases
The package lu.uni.lassy.excalibur.examples.icrash.dev.java.testcases
contains several simple examples meant to allow the user to test:
b.1. if the database has been properly set-up: class TestCase_db_alive
b.2. if the server has been launched: class TestCase_server_alive
b.3. insert/select/delete SQL statements on the Alerts table of the icrash DB.  
b.4. insert/select/delete SQL statements on the Crises table of the icrash DB.
b.5. insert/select/delete SQL statements on the Humans table of the icrash DB.


To execute each of these tests you should:

1. Open the class respective to the test of interest contained in the the package 
lu.uni.lassy.excalibur.examples.icrash.dev.java.testcases

2. Right-click on the class and then select Run as -> Java Application




 
