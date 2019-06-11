#322607177
#zeiraid

compile: bin
	find src -name *.java > sources.txt
	javac -d bin -cp ./biuoop*.jar @sources.txt
	rm sources.txt

jar: compile
	jar cfm ass6game.jar Manifest.mf -C bin .

run: jar
	java -jar ass6game.jar 1 2 3 4

bin:
	mkdir bin

clean:
	rm -rf bin ass6game.jar
