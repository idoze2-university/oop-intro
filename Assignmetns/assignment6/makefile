#322607177
#zeiraid

compile: bin
	find src -name *.java > sources.txt
	javac -d bin -cp ./biuoop*.jar @sources.txt
	rm sources.txt

jar: compile
	jar cfm ass5game.jar Manifest.mf -C bin .

run: jar
	java -jar ass5game.jar

bin:
	mkdir bin

clean:
	rm -rf bin ass5game.jar
