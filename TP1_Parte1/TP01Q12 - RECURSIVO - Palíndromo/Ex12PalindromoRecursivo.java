public class Ex12PalindromoRecursivo {
	public static boolean ehPalindromo(String s) {
		return ehPalindromo(s,0,s.length()-1);
	}//fim ehPalindromo

	public static boolean ehPalindromo(String s,int i,int j) {
		boolean ehPalindromo=true;

		if(i >= j)ehPalindromo = true;//caso base

		if(i < s.length()) {
			if(s.charAt(i) != s.charAt(j)) {
				ehPalindromo = false;
			}else {
				ehPalindromo = ehPalindromo(s,++i,--j);
			}//fim else
		}//fim if
		return ehPalindromo;
	}//fim ehPalindromo

	public static void main(String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;

		do {//leitura da entrada padrao
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//ultima linha nao sera considerada

		for(int i = 0;i < nEntrada;i++) {//para cada string o metodo sera acionado
			if(ehPalindromo(entrada[i])) {
				MyIO.println("SIM");
			}else {
				MyIO.println("NAO");
			}//fim else
		}//fim for
	}//fim main
}//fim class
