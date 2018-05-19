#-*- coding: utf-8 -*-
import cv2
import time
import json
import os
import threading
import urllib
import urllib2
#this is for guesture recognition
# 您需要先注册一个App，并将得到的API key和API secret写在这里。
# You need to register your App first, and enter you API key/secret.
API_KEY = "u8T328HHbUVgZucADi2KvWcEwJOychLM"
API_SECRET = "mfQlsKBmbbTd0UCrkrY8ccr021UCbZs4"

face_one = '/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo2.jpg'
return_gesture = '1'

#国际版的服务器地址
#the server of international version
api_url = 'https://api-cn.faceplusplus.com/humanbodypp/beta/gesture'

# Import system libraries and define helper functions
# 导入系统库并定义辅助函数
from pprint import pformat

# First import the API class from the SDK
# 首先，导入SDK中的API类
from facepp import API, File


#创建一个API对象，如果你是国际版用户，代码为：api = API(API_KEY, API_SECRET, srv=api_server_international)
#Create a API object, if you are an international user,code: api = API(API_KEY, API_SECRET, srv=api_server_international)

cap = cv2.VideoCapture(0)
time.sleep(2)
def fun_timer():
    _i = 1
    ret, frame = cap.read()
    cv2.imwrite('/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo2.jpg', frame, [int(cv2.IMWRITE_JPEG_QUALITY), 40])

# detect image
    content = {'api_key' : API_KEY, 'api_secret' : API_SECRET, 'image_file' : face_one, 'return_gesture' : return_gesture}
    
    file_name = "/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result2.json"
    with open(file_name,'w') as show:
        json.dump(res,show)
    print _i

    """
    for item in res['faces'][0]['landmark']:
        y = res['faces'][0]['landmark'][item]['x'] 
        x = res['faces'][0]['landmark'][item]['y'] 
        frame[x, y] = [0, 0, 255]
        frame[x + 1, y] = [0, 0, 255]
        frame[x, y + 1] = [0, 0, 255]
        frame[x - 1, y] = [0, 0, 255]
        frame[x, y - 1] = [0, 0, 255]
    """
    global timer
    timer = threading.Timer(0.01, fun_timer)
    timer.start()
    #cv2.destroyAllWindows()
    #cv2.imshow("capture", frame)
    #cv2.waitKey(1)

timer = threading.Timer(0.1,fun_timer)
timer.start()
# 将得到的FaceToken存进Faceset里面
# save FaceToken in Faceset
#api.faceset.addface(outer_id='test', face_tokens=Face.itervalues())

# 删除无用的人脸库
# delect faceset because it is no longer needed
#api.faceset.delete(outer_id='test', check_empty=0)

# 恭喜！您已经完成了本教程，可以继续阅读我们的API文档并利用Face++ API开始写您自
# 己的App了！
# 旅途愉快 :)
# Congratulations! You have finished this tutorial, and you can continue
# reading our API document and start writing your own App using Face++ API!
# Enjoy :)
