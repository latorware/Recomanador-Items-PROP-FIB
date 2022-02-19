@echo Realitzant operacions previes a la compilacio...
@mkdir EXE\FONTS
@xcopy .\FONTS .\EXE\FONTS /s /e /Q
@cd .\EXE
@echo Compilant el programa...
@javac -d ./executables -sourcepath ./FONTS ./FONTS/domini/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@javac -d ./executables -sourcepath ./FONTS ./FONTS/utils/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@javac -d ./executables -sourcepath ./FONTS ./FONTS/persistencia/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@javac -d ./executables -sourcepath ./FONTS ./FONTS/domini/controladors/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@javac -d ./executables -sourcepath ./FONTS ./FONTS/presentacio/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@javac -d ./executables -sourcepath ./FONTS ./FONTS/Main.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
@echo Realitzant operacions previes a la creacio del jar...
@copy .\MANIFEST.MF .\executables
@mkdir executables\presentacio\icons
@xcopy .\FONTS\presentacio\icons .\executables\presentacio\icons /s /e /Q
@cd .\executables
@echo Creant executable jar...
@jar -cmf .\MANIFEST.MF run.jar .\domini\* .\persistencia\* .\presentacio\* .\utils\* .\domini\controladors\* .\Main$1.class .\Main.class
@echo Realitzant operacions posteriors a la creacio de lexecutable...
@cd ..\
@rmdir .\FONTS /s /Q
@copy .\executables\run.jar .\
@rmdir .\executables /s /Q
@echo EXECUTABLE CREAT