#322607177
#zeiraid

compile: bin
	javac -d bin -cp ./biuoop*.jar src/*.java

run: compile
	java -cp biuoop-1.4.jar:bin Ass3

checkstyle:
	java -jar ../../../checkstyle-5.7-all.jar -c ../../../biuoop.xml src/*.java

bin:
	mkdir bin

clean:
	rm -rf bin
