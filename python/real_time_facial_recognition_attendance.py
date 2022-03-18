import mysql.connector
import face_recognition
from datetime import datetime
import cv2
import numpy as np
import io
from PIL import Image

# The secret behind this facial recognition system is a technique called
# "Deep metric learning"

# Get a reference to webcam #0 (the default one)
video_capture = cv2.VideoCapture(0)


## initializing the list that will store  the employee id, employee photo and their labels 
## after retrieving from the database
employee_id=[] 
known_faces=[]
face_encodings=[]

# connecting to the database
connection= mysql.connector.connect(user='root', password='16101289',
                              host='localhost',
                              database='employee_management_system')

# creating the cursor object for query execution
cursor=connection.cursor()

## function for retieving data from database
def retrieveData():
    query=('select employeeId, firstName, employeePhoto from Employees')    
    cursor.execute(query)    
    result_set=cursor.fetchall()
    for every_row in result_set:
        # filling the identity lists
        # separting all the employee id's 
        
        # image retrieval
        data=io.BytesIO(every_row[2])
        
        #image=Image.open(data)
        ##img = array(image)
        image1=face_recognition.load_image_file(data, mode='RGB')
        


        # storing employee id 
        employee_id.append(every_row[0])    
        
        # seperating the id and name of employees to use as label during facial recognition
        known_faces.append(str(every_row[0]) + '_' + every_row[1] )
        
        # storing the 128 d face encodings of each employee's image 
        face_encodings.append(face_recognition.face_encodings(image1)[0])
    
## function for inserting attendance data into the database
def insert_attendance(e_id, date, time):
    query=("""INSERT INTO Attendance (employeeId, date, attendance, time) VALUES (%s, %s, %s, %s)""")
    data_attendance=(e_id, date, 1, time)
    
    # executing the query
    # for duplicate insertion return to the line from where the method 
    # is called without inserting anything i.e this is used to avoid giving multiple attendance
    # for the same person
    try:
        cursor.execute(query, data_attendance)
        connection.commit()
    except mysql.connector.IntegrityError as err:
        return
    
    
# retieving all the face data of the employees by calling the first function defined above 
retrieveData()

# Initialize some variables
face_locations = []
new_face_encodings = []
face_names = []
process_this_frame = True

while True:
    # Grab a single frame of video
    ret, frame = video_capture.read()

    # Resize frame of video to 1/4 size for faster face recognition processing
    small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)

    # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
    rgb_small_frame = small_frame[:, :, ::-1]

    # Only process every other frame of video to save time
    if process_this_frame:
        # Find all the faces and face encodings in the current frame of video
        face_locations = face_recognition.face_locations(rgb_small_frame)
        new_face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)
        # print(face_encodings[0])
        #print(len(face_encodings))
        #break
        face_names = []
        for face_encoding in new_face_encodings:
            # See if the face is a match for the known face(s)
            matches = face_recognition.compare_faces(face_encodings, face_encoding)
            name = "Unknown"
            ##print(matches)
            #break
            # # If a match was found in known_face_encodings, just use the first one.
            # if True in matches:
            #     first_match_index = matches.index(True)
            #     name = known_face_names[first_match_index]

            # Or instead, use the known face with the smallest distance to the new face
            face_distances = face_recognition.face_distance(face_encodings, face_encoding)
            best_match_index = np.argmin(face_distances)
            if matches[best_match_index]:
                name = known_faces[best_match_index]
                # if a match is found and if the attendance is not given for that day
                # then insertion is done otherwise insertion is not done
                if name not in face_names:
                    
                    # since the person's face infront of the camera matches with an employee's
                    # photo we use the method below to give the attendance and 
                    # also pass the time and date of the attendance at the same time
                    insert_attendance(employee_id[best_match_index], 
                    datetime.date(datetime.now()), datetime.time(datetime.now()))
                
                    
            # appending the names that are present
            face_names.append(name)
            
            
                
    
    process_this_frame = not process_this_frame


    # Display the results
    for (top, right, bottom, left), name in zip(face_locations, face_names):
        # Scale back up face locations since the frame we detected in was scaled to 1/4 size
        top *= 4
        right *= 4
        bottom *= 4
        left *= 4

        # Draw a box around the face
        cv2.rectangle(frame, (left, top), (right, bottom), (0, 0, 255), 2)

        # Draw a label with a name below the face
        cv2.rectangle(frame, (left, bottom - 35), (right, bottom), (0, 0, 255), cv2.FILLED)
        font = cv2.FONT_HERSHEY_DUPLEX
        cv2.putText(frame, name, (left + 6, bottom - 6), font, 1.0, (255, 255, 255), 1)

    # Display the resulting image
    cv2.imshow('Employee Management System- Real Time Facial Recognition Attendance', frame)

    # When " " will be pressed then get out of the while loop and program will end
    if cv2.waitKey(1) & 0xFF == ord(' '):
        break

# Release handle to the webcam
video_capture.release()
cv2.destroyAllWindows()


# closing the sql connection and cursor objects
cursor.close()
connection.close()
cv2.close()


