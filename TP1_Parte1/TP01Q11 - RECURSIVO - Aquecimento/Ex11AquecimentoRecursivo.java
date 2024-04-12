public class Ex11AquecimentoRecursivo {
	public static int contMaiusculo(String s) {
		return contMaiusculo(s,0);
	}//fim contMaiusculo

	public static int contMaiusculo(String s,int i) {
		int contador=0;
		if(i > s.length()) contador = 0;
		
		if(i < s.length()) {		
			if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				contador = 1 + contMaiusculo(s,++i);//caso o char for maiusculo o recursivo sera chamado acrescentando 1
			}else {
				contador = 0 + contMaiusculo(s,++i);//caso contrario o recursivo sera chamado acrscentando 0
			}//fim else
		}//fim if	
		return contador;
	}//fim contMaiusculo

	public static void main (String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;

		do {//leitura da entrada padrao
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//ultima linha nao sera contada

		for(int i = 0;i < nEntrada;i++) {//para cada string o metodo sera chamado
			MyIO.println(contMaiusculo(entrada[i]));
		}//fim for
	}//fim main
}//fim class		

