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

_OBJ = dictionary.o
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))

_SOL = day01.o
SOL = $(patsubst %,$(ODIR)/%,$(_OBJ))


$(ODIR)/%.o: $(LIBDIR)/%.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

day02: $(OBJ)
	$(CC) -o $@ $(SOLDIR)/$@.c $^ $(CFLAGS) $(LIBS)

dictionary_test01: $(OBJ)
	$(CC) -o $@ $(TESTDIR)/$@.c $^ $(CFLAGS) $(LIBS)

.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ core $(IDIR)/*~
