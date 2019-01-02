# Labb 1 – Embedded SQL i Java
Uppgift
Med materialet från föreläsningen om embedded SQL i Java som utgångspunkt gör följande:

1. Skapa databasen i MySQL (hämta skriptet från Canvas).
Skriptet kan köras med kommandot mysql (som beskrivs i kompendiet MySQL Essentials)
Alternativt kan man använda Access-versionen av databasen (hämtas från Canvas)

2. Hämta exempelprogrammet, kompilera och kör för att testa att allt fungerar. Tänk på att mysql-driver-jar-filen måste finnas tillgänglig när man kör programmet och använder MySQL. På samma sätt måste UCanAccess-drivern finnas tillgänglig om man använder Access. (Man kan även välja att köra via ODBC med Java 7 eller tidigare och då måste man skapa ett ODBC-alias).
Kompilera gör man med kommandot javac, t ex javac MyProg.java
Exekvera gör man med kommandot java, t ex java MyProg
eller om man vill ta med drivern för MySQL:
java -cp .;C:\MySQL\mysql-connector-java-8.0.12.jar MyProg
förutsatt att jar-filen finns just där. Hittar man inte jar-filen, kan man alltid ladda ner den från databashanteringssystemets webbplats eller från Canvas.
Om man vill köra med UCanAccess-drivern får man inkludera jar-filerna ucanaccess- 4.0.4.jar, commons-lang-2.6.jar, commons-logging-1.1.3.jar, hsqldb.jar, jackcess-2.1.11.jar, och ucanload.jar i classpathen. Eller om alla finns i en folder kan man enkelt ange *:
java -cp .;C:\UCanAccess\* MyProg
Ändra programmet så att det istället gör följande:
a. Visar alla bilmärken.
b. Visar alla bilar (regnr, märke och färg) som ägs av någon som bor i en viss stad.
Användaren anger en stad.
c. Ändrar en bils färg. Användaren ska ange bilens regnr och den nya färgen.

3. Kompilera och kör programmet. Kontrollera att programmet fungerar korrekt.
a. Kontrollera att programmet kan utföra alla tre uppgifter (a, b och c)!
b. Kontrollera att programmet tar emot rätt input (för b och c)!
c. Kontrollera att programmet ger rätt output (för a, b och c)!
d. Kontrollera att SQL-satserna gör exakt det som behövs, inte mer, inte mindre!
e. Kontrollera att parametrarna hanteras korrekt!
f. Kontrollera att variabler och metoder har bra namn!
g. En möjlighet är att hitta en annan grupp/student och granska varandras
program. Detta är dock frivilligt.

4. Kör programmet och visa/förklara koden för en av handledarna för att få godkänt på
labben.
