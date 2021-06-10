@echo off
echo.
echo [信息] 运行Web工程。
echo.

cd %~dp0
cd ../personas-admin/target

set JAVA_OPTS=-Xms256m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -jar %JAVA_OPTS% personas-admin.jar

cd bin
pause