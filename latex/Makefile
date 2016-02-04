CFLAGS =
TARGET = main.tex
CC = pdflatex
RUBBISH = main.aux main.log preambule.aux

all : $(TARGET)
	$(CC) $^

clean:
	rm main.pdf

mrproper:
	rm $(RUBBISH)
