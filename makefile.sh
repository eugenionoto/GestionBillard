rm *.class
rm *.jar
javac *.java
jar cmf Manifest.mf GestionBillard.jar *.class *.java Billard.gif settings.spk tables.dat billardPerso.gif billardPerso2.gif shuffle.GIF total.tot

