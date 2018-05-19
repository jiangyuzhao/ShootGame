import cv2
c = cv2.VideoCapture(0)  
while 1:  
    ret, image = c.read()  
    cv2.imshow("Origin", image) 
    cv2.waitKey(1) 
