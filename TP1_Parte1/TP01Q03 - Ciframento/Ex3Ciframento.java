public class Ex3Ciframento {
	public static String fazCifra(String c) {
		int i = 0,texto = c.length();
		String aux = " ";//auxiliar para onde a palavra criptografada sera atribuida
		for(i=0;i<texto;i++) {
			aux += (char)(c.charAt(i)+3);//cada letra eh mudada em 3 posicoes
		}//fim for
		return aux;
	}//fim fazCifra()

	public static void main (String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;
			
		do {//leitura da entrada padrao
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//desconsidera ultima linha("FIM)

		for(int i = 0;i < nEntrada;i++) {//para cada entrada eh feita a cifra
			MyIO.println(fazCifra(entrada[i]));
		}//fim for
	}//fim main
}//fim class	
		
