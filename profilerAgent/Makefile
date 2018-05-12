PROFILER_JAR=ProfilerAgent.jar
ASYNC_PROFILER_JAR=async-profiler.jar
JAVA_HOME=/usr/lib/jvm/java-1.11.0-openjdk-amd64
INCLUDES=-I$(JAVA_HOME)/include
JAVAC=$(JAVA_HOME)/bin/javac
JAR=$(JAVA_HOME)/bin/jar
INCLUDES +=  -I$(JAVA_HOME)/include/linux

.PHONY: $(PROFILER_JAR)

$(PROFILER_JAR): profilerAgent/*.java
	mkdir -p classes
	$(JAVAC) -cp $(ASYNC_PROFILER_JAR) -source 6 -target 6 -d classes $^
	$(JAR) cvfm $@ manifest.mf -C classes . $(ASYNC_PROFILER_JAR)
	rm -rf classes
