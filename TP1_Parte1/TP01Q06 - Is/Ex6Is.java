public class Ex6Is {
	public static boolean onlyVogais(String s) {
		int i = 0;
		int j = 0;
		boolean onlyVogais = false;
		char min;
		while(i < s.length()) {
			min = Character.toLowerCase(s.charAt(i));/*a cada repeticao o caractere sera convertido para minusculo para haver a 
															comparacao
                                                                  ***********************************************************************/
			if(min=='a'||min=='e'||min=='i'||min=='o'||min=='u') {
				j++;//caso seja uma vogal,um contador sera incrementado para uma final decisao
			}//fim if
			i++;
		}//fim while

		if(j>=s.length()) {//se o contador for maior ou igual ao tamanho da palavra,quer dizer que ela so possui vogais
			onlyVogais = true;
		}//fim if
		return onlyVogais;
	}//fim onlyVogais

	public static boolean onlyConsoantes(String s) {
		int i = 0;
		int j = 0;
		boolean onlyConsoantes = false;
		char min;
		while(i < s.length()) {
			min = Character.toLowerCase(s.charAt(i));//o caratere sera convertido para minusculo para a comparacao
			if(min=='a'||min=='e'||min=='i'||min=='o'||min=='u') {
				j++;//caso a letra seja igual a uma vogal ela nao sera uma palavra apenas de consoantes
			}//fim if
			if(s.charAt(i)>=0||s.charAt(i)<=9) {
				j++;//caso a palavra possua numeros,ela nao sera uma palavras apenas de consoantes
			}//fim if
			i++;
		}//fim while

		if(j == 0) {//caso o contador seja 0 quer dizer que nenhum dos Ifs foi "utilizado",entao a palavra sera toda de consoantes
			onlyConsoantes = true;
		}//fim if
		return onlyConsoantes;
	}//fim onlyConsoantes

	public static boolean sohInt(String s) {
		int i = 0;
		int j = 0;
		boolean sohInt = false;
		char min;
		while(i < s.length()) {
			min = Character.toLowerCase(s.charAt(i));//caracter sera convertido para minusculo para comparacao
			if(min >= 'a'&& min <= 'z') {
				j++;//caso a posicao seja um letra ela nao sera apenas um inteiro
			}//fim if
			if(s.charAt(i) == '.'||s.charAt(i) == ',') {
				j++;//restricao do '.' e da ',' pois numeros inteiros nao as possuem
			}//fim if
			i++;
		}//fim while
		if(j == 0) {//caso o contador seja 0 quer dizer que nenhum dos If foi "utilizado",entao ha apenas inteiros
			sohInt = true;
		}//fim if
		return sohInt;
	}//fim sohInt

	public static boolean sohDouble(String s) {
		int i = 0;
		int j = 0;
		char min;
		int contaVirgulaPonto = 0;
		boolean sohDouble = false; 

		while(i < s.length()) {
			min = Character.toLowerCase(s.charAt(i));//caractere sera convertido para minusculo para comparacao
	 		if(min >= 'a'&& min <= 'z') {
				j++;//caso a posicao seja uma palavra ela nao sera apenas um real
			}//fim if
			if(s.charAt(i) == '.' || s.charAt(i) == ',') {
				contaVirgulaPonto++;/*quando houver '.' ou ',' adicionara um ao contador,pois um double nao possui mais de
						      1 ponto ou 1 virgula						                     ***********************************************************************************/				
			}//fim if
			i++;
		}//fim while
		

		if(j == 0 && contaVirgulaPonto <=1) {/*se o j for 0 quer dizer que o if nao foi utilizado entao ele pode ser apenas um 
							real,porem,se contaVirgulaPonto for maior do que 1,o numero possui mais de uma
							virgula ou ponto,no caso,ele nao sera real
						      ***********************************************************************************/
			sohDouble = true;
		}//fim if
		return sohDouble;
	}//fim sohDouble

	public static void main (String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;

		do {//leitura da entrada padrao
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//desconsiderar a ultima linha(FIM)

		for(int i = 0;i < nEntrada;i++) {//cada palavra eh submetida a todos os 4 metodos propostos
			if(onlyVogais(entrada[i])) {
				MyIO.print("SIM ");
			}else {
				MyIO.print("NAO ");
			}
			if(onlyConsoantes(entrada[i])) {
				MyIO.print("SIM ");
			}else {
				MyIO.print("NAO ");
			}
			if(sohInt(entrada[i])) {
				MyIO.print("SIM ");	
			}else {
				MyIO.print("NAO ");
			}
			if(sohDouble(entrada[i])){ 
				MyIO.print("SIM");
			}else {
				MyIO.print("NAO");
			}
			MyIO.println(" ");
		}//fim for
	}//fim main
}//fim class






