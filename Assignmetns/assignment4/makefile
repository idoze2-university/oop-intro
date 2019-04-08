compile: bin
	javac -d bin -cp ./biuoop*.jar src/*.java

run: compile
	java -cp ./biuoop*.jar:bin Test1

bin:
	mkdir bin

clean:
	rm -rf bin
