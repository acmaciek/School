JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
        ExamTester.java \
        Answer.java \
        Question.java \
        Exam.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
