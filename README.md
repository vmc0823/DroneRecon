# DroneRecon
1.	SUMMARY

This project will simulate communicating with a drone that is surveying land for information (part 1) and then provide a portal for drone data storage in a database as well as reviewing summarized data results (part 2).  

The imagined target of the Drone Recon data collection is your choice.  Suggestions include:
-	Agriculture, Forest, Wildlife, Search and Rescue, or Military.

This project will involve the following:
-	JSP			- Servlets
-	REST Web services	- JSON
-	HTML			- Databases

2.	DETAILS

Part 1 – DRONE Communication: Data collection and guidance

a.	Zip file provided… Files provided in zip file:
i.	dronerecon/drone_launch.jsp (needs code added by you)
ii.	dronerecon/drone_sim.jsp (needs code added by you)
iii.	dronerecon/js/dronesim.js
iv.	dronerecon/js/jquery.min.js
v.	dronerecon/js/webservice_client.js

b.	The “dronerecon” folder should be placed in the “webapps” folder in your Tomcat installation.
i.	IMPORTANT Mac users tip:
1.	Make sure you give read/write access to everyone to the “webapps” folder! (not just yourself)
a.	This is because Tomcat is ran under different system user and web service calls from our drone recon web page in Safari back to our servlet in Tomcat will be denied otherwise and you only get a generic not found issue.

c.	Fill in the drone_sim.jsp and drone_launch.jsp template code that is provided.
i.	Simply look for places where to adjust code marked with ########.

d.	DroneDataService.java
i.	This is a servlet that provides a RESTful web service to the drone_sim.jsp page.
ii.	The service does all the following:
1.	Receives last grid tile data from drone and sends data to Drone Recon Portal web service for DB storage (Sending to Drone Recon Portal is in part 2).
2.	Based on previous tile passed in and grid specs passed in, it returns to the drone that called the next tile to fly over and which direction the drone should be going all in a JSON structured string.
iii.	The template file for this is provided.
iv.	This should be done in a class library project and then compiled into a JAR file and put in the “WEB-INF/lib” folder in your web app.  
v.	IMPORTANT: Every time you deploy new/updated jar file to Tomcat, Tomcat needs to be restarted.

e.	IMPORTANT:  SCREENSHOT of colored grid:  
i.	Get a screenshot of the web page in the browser after drone has ran through it and colored the squares.


Part 2 – DRONE Data Portal: Web front end and DB 

a.	In this section, you are creating another web app similar to Part 1, but this app will have a DB file in it and will provide a web service for writing data.

b.	Zip file provided… Files provided in zip file:
a.	dronereconportal/db/dronedata.sqlite
b.	Rest of files are the standard web app structure and the servlet and DB JARs are in lib folder.
c.	PUT THE “dronereconportal” folder in your “webapps” folder in Tomcat.
c.	All the following Java files can be put in the same project.
a.	All files will be in package com.dronerecon.ws
b.	Java files provided:
i.	DBManager.java (no additions for you to make)
ii.	PortalDBService.java (There are CODE ADDITIONS you need to make – look for #######)
c.	Create a new Java file:
i.	AreaGridTile.java
ii.	Just 6 instance variables (IMPORTANT: Make all of these "public"):
1.	areaID – String type
2.	x, y, r, g – All int types
3.	timestamp – String type
d.	A JAR of all these files should be created and placed in dronereconportal/WEB-INF/lib
d.	Call the PortalDBService from the DroneDataService.java file from Part 1.
a.	Do this using the same code example used in the weather web service lab.
b.	The code should be in the doGet method just after Step 1 where you are getting the values from the request object.
c.	Remember, you also have to get two more parameters from the request object that you weren’t getting before: “r” and “g” (these are already there for you to get).
d.	You are adding the values retrieved from the request to fill into the URL:
i.	The URL would look something like this:
http://127.0.0.1:8080/dronereconportal/PortalDBService?area_id=1&tilex=1&tiley=1&r=1&g=1
e.	Lastly, create two JSP files in dronereconportal folder:
a.	areasearch.jsp:  It has a text box input for area ID and a submit button that submits to a second JSP.
b.	arearesults.jsp: 
i.	Step 1:  Be sure to import the required classes you need with this type of syntax as we’ve done in earlier labs:
<%@ page import="com.dronerecon.ws.AreaGridTile" %>
Hint: You also need to import DBManager and the ArrayList type (in java.util)
ii.	Step 2:  Get incoming area id from other JSP.
iii.	Step 3:  Create an instance of DBManager.
iv.	Step 4:  Adjust DB location on DBManager object with similar line of code I provided in PortalDBService.java:
For Windows Users:
oDBManager.DBLocation = System.getProperty("catalina.base") +              "\\webapps\\dronereconportal\\db\\" + oDBManager.DBLocation;
				For Mac Users: 
oDBManager.DBLocation = System.getProperty("catalina.base") +              "/webapps/dronereconportal/db/" + oDBManager.DBLocation;


i.	Step 5:  Call readAreaGridTiles and pass in area id.
ii.	Step 6:  You get back an ArrayList<AreaGridTile> type.
iii.	Step 7:  Loop through and print out to the screen…
1.	The x,y values of the record with the highest r value.
2.	The x,y values of the record with the highest g value.

