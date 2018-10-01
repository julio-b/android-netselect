JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		TFTopsisNetSelect.java \
		InterValFN.java \
		Network.java \
		TrapezoidalFN.java \
		TestAlg.java

MAIN = TestAlg

default: classes

classes: $(CLASSES:.java=.class)

run: classes
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
