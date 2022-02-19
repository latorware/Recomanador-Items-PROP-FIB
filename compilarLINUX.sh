echo Realitzant operacions previes a la compilacio...
cp -r ./FONTS ./EXE
cd ./EXE
echo Compilant el programa...
javac -d ./executables -sourcepath ./FONTS ./FONTS/domini/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
javac -d ./executables -sourcepath ./FONTS ./FONTS/utils/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
javac -d ./executables -sourcepath ./FONTS ./FONTS/persistencia/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
javac -d ./executables -sourcepath ./FONTS ./FONTS/domini/controladors/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
javac -d ./executables -sourcepath ./FONTS ./FONTS/presentacio/*.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
javac -d ./executables -sourcepath ./FONTS ./FONTS/Main.java -Xlint:-serial -Xlint:-unchecked -encoding utf8
echo Realitzant operacions previes a la creacio del jar...
cp ./MANIFEST.MF ./executables
cp -r ./FONTS/presentacio/icons ./executables/presentacio
cd ./executables
echo Creant executable jar...
jar -cmf MANIFEST.MF run.jar domini/* persistencia/* presentacio/* utils/* domini/controladors/* Main.class Main\$1.class
echo Realitzant operacions posteriors a la creacio de lexecutable...
cd ../
rm -r ./FONTS
cp ./executables/run.jar ./
rm -r ./executables	
echo EXECUTABLE CREAT