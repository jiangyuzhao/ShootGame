# java_project
# 配置方法
ShootGame 5.zip里面是整个的java项目，解压后，把org.json.jar导入到项目中。<br>
python1-sdk-master里面是调用人脸识别和手势识别API的代码。人脸识别采用的是opencv的API，<br>
大家需要在本机上配置好opencv的环境。手势识别采用的是Face++的API，调用过程已经写好。<br>
运行python2 /python1-sdk-master/python-sdk/call4.py可以得到手势识别的结果，结果会写在/python1-sdk-master/python-sdk/result2.json里<br>
运行python2 /python1-sdk-master/python-sdk/call1.py可以得到人脸检测的结果，结果会写在/python1-sdk-master/python-sdk/result1.txt里<br>
result2.json里存储的是json字符串表示的手势信息，包括各种手势的概率。详情参见https://console.faceplusplus.com.cn/documents/10065649,
里面包括手势识别的文档，以及https://console.faceplusplus.com.cn/documents/10065685 这里面包括能够识别的手势的定义。<br>
result1.txt里面存放的4个数是检测出的人脸框的top,right,bottom,left,也即上，右，下，左的位置。当然，在以后的改进中，咱们可以得到更多的信息。<br>
同时大家注意，更改ShootGame项目里Demo.java文件里的File file = new File("/Users/qinyuxuan/Desktop/python1-sdk-master/python-sdk/result1.txt")为本机的位置。<br>
整个过程的思路是这样的：先运行call1.py和call4.py，得到人脸检测和手势识别的结果，然后把结果写到文件中，设置一个定时器，定时器每隔10毫秒读取文件，根据文件中人脸的位置和是否有手势，哪种手势可能性最大，来更新游戏的状态。<br>
