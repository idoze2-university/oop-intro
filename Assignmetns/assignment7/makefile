#322607177
#zeiraid

compile: bin
	find src -name *.java > sources.txt
	javac -d bin -cp ./biuoop-1.4.jar:./resources/ @sources.txt
	rm sources.txt

jar: compile
	jar cfm ass7game.jar Manifest.mf -C bin .

run: jar
	java -jar ass7game.jar level_sets.txt

bin:
	mkdir bin

clean:
	rm -rf bin ass7game.jar db
