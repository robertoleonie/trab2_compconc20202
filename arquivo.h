#include<stdbool.h>
#define TAM_MEMORIA 60

typedef struct {
	int valor;
	int idSensor;
	int idLeitura = 0;
} Elemento;

typedef struct {
	int inicio;
	int final;
	Elemento *elemento[TAM_MEMORIA];
} Fila;

void iniciaLeitura(int id);

void terminaLeitura(int id);

void iniciaEscrita(int id);

void terminaEscrita(int id);

int *sensores(int indice);

void *atuadores(int temperatura);

void inicia_fila(Fila* fila);

bool enfileirar(Fila* fila, Elemento* elemento);

Elemento* desenfileirar(Fila* fila);

Elemento* primeiro(Fila* fila);
