# Emary
This is a command line application that allows you to execute an XSLT transformation on an XML and have the result
output in canonical form.

## Getting started
The easiest way to get started with Emary is to download the latest distribution from the
[Releases](https://github.com/pbrandwijk/emary/releases) page.
1. Make sure you also have Java 8 or higher installed on your system.
2. Download the latest release of Emary.
3. Unzip the contents to a location on your hard drive.
4. Add the folder `emary-2.0.0/bin` to your PATH.

## Usage
Emary is a command line application, so to use it you must open up a terminal. Also, you need an XML file to work with,
which we here assume is called `input.xml`. You also need a style sheet to transform the XML, which we'll assume here is
called `stylesheet.xsl`. Lastly, you need an output path where to write to. Here we'll use `output.xml`.

Now you can use the following command to perform the transformation.
```console
$ emary -f input.xml -s stylesheet.xsl -o output.xml
```

With the following command, you can print a help message.
```console
$ emary --help
```

## Building Emary as a standalone jar file
To build the project you need the JDK version 1.8. For this build a Gradle wrapper is used that will automatically download the
correct version of Gradle.

After you have downloaded the source code into a folder named `emary`, run the following command to build the standalone
jar file:
```console
$ cd emary
$ gradlew clean fatJar
```
You can then find the jar file in `emary\build\libs\emary.jar`. Using the standalone jar file is similar to command above, only
 you need to use the java command to start it.
```console
$ java -jar emary.jar -f input.xml -s stylesheet.xsl -o output.xml
```
