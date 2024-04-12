public class Ex2Palindromo {
	public static boolean palindromo(String s) {
		boolean ehPalindromo = true;
		int i = 0;
		int j = s.length()-1;//contadores recebem as posicoes opostas da palavra
		while(i < j && ehPalindromo) {//caso o contador i passe o j significa que a palavra eh um palindromo
			if(s.charAt(i) != s.charAt(j)) {
				ehPalindromo = false;
			}
			i++;
			j--;
		}//fim while
		return ehPalindromo;
	}//fim palindromo ()

	public static void ehPalindromo(String s) {
		if(palindromo(s)) {
			MyIO.println("SIM");
		}//fim if
		else {
			MyIO.println("NAO");
		}//fim else
	}//fim ehPalindromo()

	public static void main (String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;
  		//leitura da entrada padrao
		do {
			entrada[nEntrada] = MyIO.readLine();
	        }while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//nao considerar a ultima linha("FIM")
		
		for(int i = 0;i < nEntrada;i++) {
			ehPalindromo(entrada[i]);
		}//resposta booleana para cada palavra
	}//fim main
}//fim class
