IDIR =../include
CC=gcc
CFLAGS=-I$(IDIR) -Wall

ODIR=obj
LDIR =../src
LIBDIR =../lib
SOLDIR =../sol
TESTDIR =../test

LIBS=

_DEPS = dictionary.h
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

_OBJ = dictionary.o intMatrix2D.o
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))

_SOL = day01.o
SOL = $(patsubst %,$(ODIR)/%,$(_OBJ))


$(ODIR)/%.o: $(LIBDIR)/%.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)


day01: $(OBJ)
	$(CC) -o $@.exe $(SOLDIR)/$@.c $^ $(CFLAGS) $(LIBS)

day02: $(OBJ)
	$(CC) -o $@.exe $(SOLDIR)/$@.c $^ $(CFLAGS) $(LIBS)

day03: $(OBJ)
	$(CC) -o $@.exe $(SOLDIR)/$@.c $^ $(CFLAGS) $(LIBS)


dictionary_test01: $(OBJ)
	$(CC) -o $@.exe $(TESTDIR)/$@.c $^ $(CFLAGS) $(LIBS)

intMatrix2D_test01: $(OBJ)
	$(CC) -o $@.exe $(TESTDIR)/$@.c $^ $(CFLAGS) $(LIBS)


.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ *.exe core $(IDIR)/*~
