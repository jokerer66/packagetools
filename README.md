# packagetools
...
1.执行packagetool.sql创建数据库
2.配置电脑环境变量：JAVA_HOME，GRADLE_HOME,ANDROID_HOME,MAEN_HOME（建议使用tomcat7与jdk7 进行https下载通信，笔者尝试使用更高版本的jdk与tomcat版本，均无法成功）
3.根据使用的打包服务器，创建相应域名的app.keystore和app.pem，进行https链接的下载，由于每个机器的域名不同，需要用户自行制作，制作方法？
4.根据提供的server.xml配置tomcat，要点？