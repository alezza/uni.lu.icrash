# uni.lu.icrash
iCrash Implementation Project:

1. Launching the servers (Redundancy req.)
- Run “LaunchServer.java” as a Java Application - run both the LaunchServer and the Backup instances

2. Launching the “TestCase_db_alerts_location” will require the database to be clear 
(dump and recreate the database)

sudo ./mysqldump --user=root --password=………… -A > ~/Desktop/db1.sql
sudo /usr/local/mysql/bin/mysql -u root -p < icrash_db_create.sql

