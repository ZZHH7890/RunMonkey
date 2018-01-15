# RunMonkey
monkey
这是一个java调用monkey并且分析日志的程序！！
1. eclipse
2. jdk 1.8
3. 配置文件config.properties，有日志和monkey command
##############################################
monkeyLogFile = C:\\Users\\Administrator\\monkeylog\\monkey.log
AnalyzeLogFile = C:\\Users\\Administrator\\monkeylog\\analyze.log
command = adb shell monkey -p com.geometry --ignore-crashes --ignore-timeouts --ignore-security-exceptions --ignore-native-crashes --monitor-native-crashes --throttle 100 -s 5 -v -v -v 1000

4. main函数类  RunTest.java

5. Monkey命令运行类 Monkey.java

6. monkey运行结果保存在C:\\Users\\Administrator\\monkeylog\\monkey.log

7. monkey运行结果分析保存在C:\\Users\\Administrator\\monkeylog\\analyze.log

8. monkey运行的命令见配置config.properties

9. Got IOException performing flipjava.io.IOException: write failed: EINVAL (Invalid argument) 这个异常为monkey自己的问题可以忽略
https://www.cnblogs.com/miniren/p/4858788.html

10. android SDK安装好
C:\Users\Administrator>adb version
Android Debug Bridge version 1.0.39
Version 0.0.1-4500957
Installed as D:\Program Files\android-sdk-windows\platform-tools\adb.exe

11. usb 连接好安卓手机
C:\Users\Administrator>adb devices
List of devices attached
R8V7N16115005018        device


