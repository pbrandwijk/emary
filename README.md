# Emary
This is a command line application that allows you to execute an XSLT transformation on an XML and have the result output in canonical form.

## Building the java jar file
To build the project you need the JDK version 1.8 or higher and the Gradle build tool on your path.

After you have downloaded the source code into a folder named `emary`, run the following command to build the stand alone jar file:
```
$ cd emary
$ gradle clean fatJar
```
You can then find the jar file in `emary\build\libs\emary.jar`.

## Usage
To you the tool you need to execute the jar file and provide the XML, XSLT and output path as arguments.
```
$ java -jar emary.jar [xml file] [stylesheet] [output path]
```
Logging messages will be printed to the console. The output file can be found on the path you provided or `./canonical.xml` if you did not provide and output path.