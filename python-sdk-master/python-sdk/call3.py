# -*- coding: utf-8 -*-
import cv2
import urllib2
import urllib
import time
import json
import os
import threading

http_url='https://api-cn.faceplusplus.com/humanbodypp/beta/gesture'
key = "u8T328HHbUVgZucADi2KvWcEwJOychLM"
secret = "mfQlsKBmbbTd0UCrkrY8ccr021UCbZs4"
filepath = r"/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo2.jpg"
boundary = '----------%s' % hex(int(time.time() * 1000))
data = []
data.append('--%s' % boundary)
data.append('Content-Disposition: form-data; name="%s"\r\n' % 'api_key')
data.append(key)
data.append('--%s' % boundary)
data.append('Content-Disposition: form-data; name="%s"\r\n' % 'api_secret')
data.append(secret)
data.append('--%s' % boundary)
fr=open(filepath,'rb')
data.append('Content-Disposition: form-data; name="%s"; filename=" "' % 'image_file')
data.append('Content-Type: %s\r\n' % 'application/octet-stream')
data.append(fr.read())
fr.close()
data.append('--%s' % boundary)
data.append('Content-Disposition: form-data; name="%s"\r\n' % 'return_gesture')
data.append('1')
data.append('--%s--\r\n' % boundary)

http_body='\r\n'.join(data)

cap = cv2.VideoCapture(0)
time.sleep(2)
def fun_timer():
    ret, frame = cap.read()
    cv2.imwrite('/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo2.jpg', frame, [int(cv2.IMWRITE_JPEG_QUALITY), 40])
    
    #build http request
    req=urllib2.Request(http_url)   
    #header
    req.add_header('Content-Type', 'multipart/form-data; boundary=%s' % boundary)
    req.add_data(http_body)
    try:
            #req.add_header('Referer','http://remotserver.com/')
            #post data to server
            resp = urllib2.urlopen(req, timeout=5)
            #get response
            qrcont=resp.read()
            file_name = "/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result2.json"
            with open(file_name,'w') as show:
                json.dump(qrcont,show)
            print qrcont

    except urllib2.HTTPError as e:
        print e.read()

    global timer
    timer = threading.Timer(0.5, fun_timer)
    timer.start()

timer = threading.Timer(0.5,fun_timer)
timer.start()

            
    
