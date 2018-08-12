set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_45\bin\"
set LIBPATH=.\jogl-winlib
set JARPATH=.\jogl-jar

set CLASSPATH=%CLASSPATH%;%JARPATH%\gluegen-rt.jar;%JARPATH%\gluegen-rt-natives-windows-i586.jar;%JARPATH%\jogl-all.jar;%JARPATH%\jogl-all-natives-windows-i586.jar;

javac *.java
java -Djava.library.path=%LIBPATH% CgMain
