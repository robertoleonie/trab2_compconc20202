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
