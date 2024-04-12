public class Ex13CiframentoRecursivo {
	public static String doCifra(String s) {
		return doCifra(s,0);
	}//fim doCifra

	public static String doCifra(String s,int i) {
		String aux = "";

		if(i >= s.length()-1)aux = "";//caso base

		if(i < s.length()) aux += (char)(s.charAt(i)+3) + doCifra(s,++i);
	
		return aux;
	}//fim doCifra

	public static void main (String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;

		do {//leitura da entrada padrao
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;

		for(int i = 0;i < nEntrada;i++) {//para cada string o metodo sera acionado
			MyIO.println(doCifra(entrada[i]));
		}//fim for
	}//fim main
}//fim class
