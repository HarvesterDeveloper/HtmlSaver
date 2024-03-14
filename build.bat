mkdir bin
javac -sourcepath src -d bin .\src\com\harvestdev\htmlsaver\Application.java
jar -cmf manifest.txt HtmlSaver.jar -C bin .