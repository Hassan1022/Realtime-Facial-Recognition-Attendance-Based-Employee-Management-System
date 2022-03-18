# Realtime-Facial-Recognition-Attendance-Based-Employee-Management-System
# This is a Desktop application that has been built using Java and Python. As the name suggests, the application allows employees to record their attendance based on their live CCTV feed from a distance and also facilitates with the tradtitonal management system features.

# Project Title: Real Time Facial Recognition Attendance and Employee Management System.

# Project Description: My project “Real Time Facial Recognition Attendance and Employee 
# Management System ” is a Java and Python Based Desktop Application.

## The entire project has been divided into 3 significant portions, which are:
## 1) Employee Management System- Front End (Java)
## 2) Employee Management System- Back end (Java)
## 3) Real Time Facial Recognition Attendance System- Back end (Python)
## *** The entire project has been developed based on “MVC Design Pattern” and the back end
## Java portion has been developed using “Dao Pattern”.

# 1) Front End:
## The front end of the Project is done using “Java Fx” which uses “FXML” which is an
## “XML-based language” that provides the structure for building a user interface
## separate from the application logic. Since , the project has been based on MVC
## or Model View Controller design pattern. The front end is completely separated
## from the Back end. The UI interacts with the user and pass the instructions
## received from the user to the controller which then communicates with the back
## end portion of the code

# 2) Backend (Java):
## The management system has been mostly written in Java. Here, the application
## communicates with the mysql database through the JDBC API and performs all
## the database manipulations related to the project which is kept completely
## seperate from the UI as mentioned above. Apart from the Mvc Design pattern
## the whole Model portion of the project is built by following the “Dao Pattern.”

# 3) Backend(Python):
## This is the final segment of the project. The whole “Real Time Facial Recognition Attendance
## System” is implemented here. Here, python was used. A short summary how this portion
## works is that in the first phase of this segment, the python code deals with databasemanipulations of its own separate from the java database manipulations where it basically
## retrieves the employee info and image from the “Employee” table and in the second phase of
## this segment, face detection of people is done and facial recognition is done from the
## employee information retrieved in the first phase by feeding to a machine learning model
## (Deep Metric Learning in this case) which then after training is used to identify people infront
## of the camera and stores the attendance data in the “Attendance” table of the Database.

# Project Features:
## Implemented features:
## 1) Login System
## 2) Separate admin and general user privileges.
## 3) Using MVC Design pattern and Dao Pattern.
## 4) Inserting employee data.
## 5) Updating existing employees data.
## 6) Deleting any specific employees data.
## 7) Retrieving any employees data.
## 8) Creating new departments.
## 9) Updating existing department information.
## 10) Retrieving data about any department.
## 11) Inserting new project data.
## 12) Updating existing project data.
## 13) Retrieving all the past and ongoing project data of each employee.
## 14) Inserting project information about each employee.
## 15) Updating project data of an employee.
## 16) Retrieve attendance information for each employee. Showing how many days
## an employee is present in the month.
## 17) Visualizing the project/work history that is “performance of” every employee in
## a graph.
## 18) Accessing the webcam/camera to take face data of the employee
## in front of the camera.
## 19) Using "HOG" or "Histogram of Oriented Gradients" algorithm for face detection
## of the employee in front of the webcam/camera.
## 20) Using "Deep Metric Learning " to do facial recognition of each employee from
## their stored data.
## 21) For each person in front of the camera checks whether that person exist in the
## employee database and if so, then insert attendance for that employee with the
## current time and date into the database.
## 22) For the same person coming in front of the camera twice on the same day,
## attendance would not be given more than once.
## 23) Visualizing Employees Data in charts(Attendance and Performance History).

# Future Enhancements:

## Following enhancement can be added to the current system which will improve the system:
## 1)Adding an secured internal chat application feature using “Java Networking” to the
## current project with which employees can interact among themselves.
## 2) Using Data analysis of employees past project records and attendance history to find
## the best workers and also employees suitable for promotions.
