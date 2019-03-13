compile: bin
	javac -d bin -cp ./biuoop*.jar src/*.java

run2:
	java -cp ./biuoop*.jar:./bin AbstractArtDrawing

run3:
	java -cp ./biuoop*.jar:./bin BouncingBallAnimation

bin:
	mkdir bin

checkstyle:
	java -jar ../../../checkstyle-5.7-all.jar -c ../../../biuoop.xml src/*.java

clean:
	rm -rf bin