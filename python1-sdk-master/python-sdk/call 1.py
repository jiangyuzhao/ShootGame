#-*- coding: utf-8 -*-
import cv2
import time
import json
import os
import threading
# 您需要先注册一个App，并将得到的API key和API secret写在这里。
# You need to register your App first, and enter you API key/secret.
API_KEY = "u8T328HHbUVgZucADi2KvWcEwJOychLM"
API_SECRET = "mfQlsKBmbbTd0UCrkrY8ccr021UCbZs4"

face_one = '/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo1.jpg'


#国际版的服务器地址
#the server of international version
api_server_international = 'https://api-us.faceplusplus.com/facepp/v3/'

# Import system libraries and define helper functions
# 导入系统库并定义辅助函数
from pprint import pformat


def print_result(hit, result):
    def encode(obj):
        if type(obj) is unicode:
            return obj.encode('utf-8')
        if type(obj) is dict:
            return {encode(v): encode(k) for (v, k) in obj.iteritems()}
        if type(obj) is list:
            return [encode(i) for i in obj]
        return obj
    print hit
    result = encode(result)
    print '\n'.join("  " + i for i in pformat(result, width=75).split('\n'))


# First import the API class from the SDK
# 首先，导入SDK中的API类
from facepp import API, File


#创建一个API对象，如果你是国际版用户，代码为：api = API(API_KEY, API_SECRET, srv=api_server_international)
#Create a API object, if you are an international user,code: api = API(API_KEY, API_SECRET, srv=api_server_international)
api = API(API_KEY, API_SECRET)
cap = cv2.VideoCapture(0)
time.sleep(2)
_i = 0
def fun_timer():   
    ret, frame = cap.read()
    cv2.imwrite('/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/demo1.jpg', frame, [int(cv2.IMWRITE_JPEG_QUALITY), 40])

# detect image
    Face = {}
    res = api.detect(image_file=File(face_one), return_landmark=2, return_attributes='headpose,gender,age')
    print_result("person_one", res['time_used'])
    res_str = json.dumps(res,encoding="UTF-8", ensure_ascii=False)
    #file_name = "/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result" + str( _i) + ".json"
    file_name = "/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result.json"
    with open(file_name,'w') as show:
        json.dump(res,show)
 
    
    for item in res['faces'][0]['landmark']:
        y = res['faces'][0]['landmark'][item]['x'] 
        x = res['faces'][0]['landmark'][item]['y'] 
        frame[x, y] = [0, 0, 255]
        frame[x + 1, y] = [0, 0, 255]
        frame[x, y + 1] = [0, 0, 255]
        frame[x - 1, y] = [0, 0, 255]
        frame[x, y - 1] = [0, 0, 255]

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
api.faceset.addface(outer_id='test', face_tokens=Face.itervalues())

# 删除无用的人脸库
# delect faceset because it is no longer needed
api.faceset.delete(outer_id='test', check_empty=0)

# 恭喜！您已经完成了本教程，可以继续阅读我们的API文档并利用Face++ API开始写您自
# 己的App了！
# 旅途愉快 :)
# Congratulations! You have finished this tutorial, and you can continue
# reading our API document and start writing your own App using Face++ API!
# Enjoy :)
