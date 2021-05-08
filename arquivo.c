#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>
#include "arquivo.h"

#define MIN_TEMPERATURA 25
#define MAX_TEMPERATURA 40

Fila fila[TAM_MEMORIA];
Elemento* elemento;

int leitores=0; //contador de threads lendo

int escritores=0; //contador de threads escrevendo



pthread_mutex_t lock;

pthread_cond_t cond_leit, cond_escr;

void iniciaLeitura(int id){
	pthread_mutex_lock(&lock);
	printf("Thread %d quer ler.\n", id);
	while(escritores > 0) {
		printf("Thread leitora %d bloqueou.\n", id);
		pthread_cond_wait(&cond_leit, &lock);
		printf("Thread leitora %d desbloqueou.\n", id);
	}
	leitores++;
	pthread_mutex_unlock(&lock);
}

void terminaLeitura(int id){
	pthread_mutex_lock(&lock);
	printf("Thread %d terminou de ler.\n", id);
	leitores--;
	if(leitores == 0) pthread_cond_signal(&cond_escr);
	pthread_mutex_unlock(&lock);
}

void iniciaEscrita(int id){
	pthread_mutex_lock(&lock);
	printf("Thread %d quer escrever.\n", id);
	while((leitores > 0) || (escritores > 0)){
		printf("Thread escritora %d bloqueou.\n", id);
		pthread_cond_wait(&cond_escr, &lock);
		printf("Thread escritora %d desbloqueou.\n", id);
	}
	escritores++;
	pthread_mutex_unlock(&lock);
}

void terminaEscrita(int id){
	pthread_mutex_lock(&lock);
	printf("Thread %d terminou de escrever.\n", id);
	escritores--;
	pthread_cond_signal(&cond_escr);
	pthread_cond_broadcast(&cond_leit);	
	pthread_mutex_unlock(&lock);
}

void *atuadores(void *arg){
	int *id = (int *) arg;
	int leituraVermelho = 5;
	int leituraAmarelo = 15;
	
	while (1) {
		iniciaLeitura(*id);
		printf("Atuador %d esta lendo a temperatura.\n", *id);
		while(leituraVermelho > 0) {
			if(fila[*id].elemento.valor > 35) printf("ALERTA VERMELHO NO ATUADOR %d!!!\n")


		
		terminaLeitura(*id);
		sleep(2);
	}
	free(arg);
	pthread_exit();
}	

void *sensores(void *arg){
	int *id = (int *) arg;
	int valorLido;
	while (1){
		iniciaEscrita(*id);
		printf("Sensor %d esta registrando a temperatura.\n", *id);
		srand(time(NULL));
        valorLido = (rand() % (MAX_TEMPERATURA - MIN_TEMPERATURA + 1)) + MIN_TEMPERATURA;
        if(valorLido > 30) elemento.valor = valorLido;
        elemento.idSensor = *id;
        elemento.idLeitura++;
        enfileirar(fila, elemento);
		terminaEscrita(*id);
		sleep(1);
	}
	free(arg);
	pthread_exit(NULL);
}

void inicia_fila(Fila* fila){
	fila.inicio = 0;
	fila.final = -1;
}

void enfileirar(Fila* fila, Elemento* elemento){
	fila.final++;
	fila.elemento[fila.final] = elemento;
}

Elemento* desenfileirar(Fila* fila){
	Elemento *elemento = fila.elemento[fila.inicio];
	fila.inicio++;
	return elemento;
}

Elemento* primeiro(Fila* fila){
	return fila.elemento[fila.inicio];
}
