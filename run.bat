@echo off
REM --------
CHCP 65001
SET kst_java=
SET kst_java=C:\Program Files\Java\jdk-14.0.1\bin
SET classpath=.;.\driver\mysql-connector-java-5.0.3-bin.jar
REM --------
cls
if (%1)==()    GOTO :BAD
if (%1)==(-i)  GOTO :INSTALL
if (%1)==(-h)  GOTO :AIDE_EXEC
if (%1)==(-ce) GOTO :COMPILE_EXEC

REM ----------------------------------------------------
:BAD
echo PARAMETRE A UTILISER :
echo %0 -i          pour installer l'environnement java
echo %0 -h          aide a execution
echo %0 -ce Src     pour compiler puis executer un source Src.java
GOTO :FIN 

REM ----------------------------------------------------
:AIDE_EXEC
echo SYNTAXE A CONNAITRE
echo ... 1/ javac Src.java 
echo ... 2/ java  Src 
echo ... 
echo ... ou 1+2 avec ce raccourci ..... %0 -ce Src    
echo ... ou astuce CLI ................ "cls && java Src"
echo ...
echo ... BUG ? si 1/ vous affiche "commande inconnue" 
echo ... - Dans %0.bat , il faut actualiser la ligne 4 :
echo ...   SET kst_java=c:\Program Files\Java\jdk1.8.0_144\bin
echo ... - Reperez le dossier exact d'install de votre SDK java
echo ... - Collez le precisement apres le =  (pas de \ final svp) 
echo ... - Sauvegardez
echo ... 
GOTO :FIN

REM ----------------------------------------------------
:COMPILE_EXEC
cls
IF EXIST "%2.java"     GOTO :COMPILE_EXEC_OK

:COMPILE_EXEC_BAD
IF "%2"==""     echo ... ERREUR. param2 absent 
echo ... ERREUR. fichier %2.java introuvable
GOTO :FIN

:COMPILE_EXEC_OK
echo ------- 1/ compilation de %2.java
javac %2.java 
echo ...
echo ------- 2/ execution du %2.class
PAUSE ctrl-C pour abandonner
java %2
GOTO :FIN

REM ---------------------------------------------------- personnaliser ici
:INSTALL
IF EXIST "%kst_java%\javac.exe"  GOTO :INSTALL_OK
echo ... ERREUR. JAVA non localise sur ce PC
GOTO :FIN

:INSTALL_OK
prompt JAVA: 
path=%path%;%kst_java%;
GOTO :FIN

REM ----------------------------------------------------
:FIN
echo ...
 

