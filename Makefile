### VARIABLES ###

JC = javac
JCFLAGS = -encoding UTF-8 -implicit:none

JVM = java
JVMFLAGS = 

### REGLES ESSENTIELLES ###

Main.class : Main.java Menu.class
	${JC} ${JCFLAGS} Main.java

Menu.class : Menu.java SetGame.class
	${JC} ${JCFLAGS} Menu.java

SetGame.class : SetGame.java Game.class
	${JC} ${JCFLAGS} SetGame.java

Game.class : Game.java Grid.class
	${JC} ${JCFLAGS} Game.java

Grid.class : Grid.java Case.class
	${JC} ${JCFLAGS} Grid.java

Case.class : Case.java
	${JC} ${JCFLAGS} Case.java

### REGLES OPTIONNELLES ###

run : Main.class
	${JVM} ${JVMFLAGS} Main

clean :
	-rm -f *.class

mrproper : clean Main.class

### BUTS FACTICES ###

.PHONY : run clean mrproper

### FIN ###