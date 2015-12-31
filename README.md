# uni.lu.icrash
iCrash Implementation Project:

1. Launching the servers (Redundancy req.)
- Run “LaunchServer.java” as a Java Application - run both the LaunchServer and the Backup instances

2. Launching the “TestCase_db_alerts_location” will require the database to be clear 
(I have tried to modify the code to delete rows from the database before populating them again with the new data, but I ran into many errors and due to the lack of time, I decided to leave the code as it is. A simple trick to dump and recreate the database takes less time and it works better.)

sudo ./mysqldump --user=root --password=………… -A > ~/Desktop/db1.sql
sudo /usr/local/mysql/bin/mysql -u root -p < icrash_db_create.sql

