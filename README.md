# korszeru_beadando
korszeru adatbazis beadando Tolgyesi Daniel (WX5HV8)<br>

Felhasznált technológiák:<br>
  JAVA 8<br>
  MAVEN<br>
  VAADIN 14<br>
<br>
Programhoz szükségesek:<br>
1. Java 8 https://www.oracle.com/java/technologies/javase-jdk8-downloads.html<br>
2. Maven https://maven.apache.org/download.cgi<br>
<br>
Couchbase Docker elindítása - a propertyknél definiált kapcsolattal: localhost - Administrator - password - port:8091<br>
Program elindítása: mvn spring-boot:run<br>
Program elérése Böngészőből: localhost:8080<br>
<br>
Első beöltésnél mivel nem buildelt verzió és debugban lesz elindítva lehetséges hiba üzenet:<br>
"A felépített kapcsolatot az állomás szoftvere megszakította", ettől a program fut rendesen<br>
