#include <stdio.h>
#include <string.h>
#define cSize 1000
bool ePalindromo(char c[],int i,int j) {
	bool ehPalindromo = true;

	if(i > j)ehPalindromo = true;	
	
	if(i < j) {
		if(c[i] != c[j]) {
			ehPalindromo = false;
		}else {
			ehPalindromo = ePalindromo(c,++i,--j);
		}//fim else
	}//fim if
	return ehPalindromo;
}//fim ePalindromo

void ehPalindromo(char c[]) {
	if(ePalindromo(c,0,strlen(c)-2)) {
		printf("SIM\n");
	}else {
		printf("NAO\n");
	}//fim else
}//fim ehPalindromo

int main(void) {
	char entrada[cSize];

	fgets(entrada,cSize,stdin);//leitura antes de entrar no laco while para a ultima linha nao ser considerada
	while(strcmp(entrada,"FIM\n") != 0) {
		ehPalindromo(entrada);
		fgets(entrada,cSize,stdin);//leitura ao final para ao ler a ultima linha,a mesma nao entrar no metodo pois saira da repeticao
	}//fim while

	return 0;
}//fim main
