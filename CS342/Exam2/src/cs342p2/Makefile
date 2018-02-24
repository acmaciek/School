JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
		$(JC) $(JFLAGS) $*.java

CLASSES = \
	Question.java \
	Answer.java \
	Exam.java \
        ExamTester.java \
	MCAnswer.java \
	MCSAAnswer.java \
	SAAnswer.java \
	MCQuestion.java \
	MCSAQuestion.java \
	SAQuestion.java 
        #Exam.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
