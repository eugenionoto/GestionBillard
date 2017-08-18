del *.class
del *.jar
javac *.java
jar cmf Manifest.mf GestionBillard.jar *.class *.java Billard.gif settings.spk tables.dat billardPerso.gif billardPerso2.gif shuffle.gif total.tot
