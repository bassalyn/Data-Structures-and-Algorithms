mymatrix: main.o matrix.o bstree.o datatype.o
	gcc -o mymatrix main.o matrix.o bstree.o datatype.o
main.o: matrix.c matrix.h
	gcc -c main.c
matrix.o: matrix.c matrix.h bstree.h
	gcc -c matrix.c
bstree.o: bstree.c bstree.h datatype.h
	gcc -c bstree.c
datatype.o: datatype.c datatype.h
	gcc -c datatype.c
clean:
	rm -f main *.o core mymatrix
