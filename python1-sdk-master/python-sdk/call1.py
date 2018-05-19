import numpy as np
import math
import cv2
import time
import face_recognition

from pprint import pformat

cap = cv2.VideoCapture(0)
while True:
    ret, frame = cap.read()
    sframe = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)
    face_locations = face_recognition.face_locations(sframe)
    face_landmarks_list = face_recognition.face_landmarks(sframe)
    file_name = "/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result1.txt"
    
    if len(face_locations) > 0:
            top, right, bottom, left = face_locations[0]
            with open(file_name,'w') as show:
                show.write(str(top)+" "+str(right)+" "+str(bottom)+" "+str(left))
            print top,right,bottom,left
    #print face_landmarks_list
