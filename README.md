# korszeru_beadando
korszeru adatbazis beadando Tolgyesi Daniel (WX5HV8)

Felhasznált technológiák:
  JAVA 8
  MAVEN
  VAADIN 14

Programhoz szükségesek:
1. Java 8 https://www.oracle.com/java/technologies/javase-jdk8-downloads.html
2. Maven https://maven.apache.org/download.cgi

Couchbase Docker elindítása - a propertyknél definiált kapcsolattal: localhost - Administrator - password - port:8091
Program elindítása: mvn spring-boot:run
Program elérése Böngészőből: localhost:8080

Első beöltésnél mivel nem buildelt verzió és debugban lesz elindítva lehetséges hiba üzenet:
"A felépített kapcsolatot az állomás szoftvere megszakította", ettől a program fut rendesen
