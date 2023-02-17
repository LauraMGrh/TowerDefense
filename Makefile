build: clean
	cp -r src bin
	javac bin/*/*.java
	rm bin/*/*.java

clean:
	rm -r bin | true

run:
	java -cp bin MainPackage/Main
