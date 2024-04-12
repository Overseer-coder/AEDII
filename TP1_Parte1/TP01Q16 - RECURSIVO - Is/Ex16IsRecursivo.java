public class Ex16IsRecursivo {
	public static boolean onlyVogais(String s) {
		return onlyVogais(s,0);
	}//fim onlyVogais

	public static boolean onlyVogais(String s,int i) {
		char min;
		boolean onlyVogais = true;
		if(i >= s.length())onlyVogais = true;

		if(i < s.length() && onlyVogais) {
			min = Character.toLowerCase(s.charAt(i));//cada char eh convertido para minusculo para evitar erros
			if(min== 'a'||min=='e'||min=='i'||min=='o'||min== 'u') {
				onlyVogais = onlyVogais(s,++i);
			}else {
				onlyVogais = false ;
			}//fim else
		}//fim if
		return onlyVogais;
	}//fim onlyVogais

	public static boolean onlyConsoantes(String s) {
		return onlyConsoantes(s,0);
	}//fim onlyConsoantes

	public static boolean onlyConsoantes(String s,int i) {
		char min;
		boolean onlyConsoantes = true;
		if(i >= s.length())onlyConsoantes = true;

		if(i < s.length() && onlyConsoantes) {
			min = Character.toLowerCase(s.charAt(i));//cada char eh convertido para minusculo para evitar erros
			if((min=='a'||min=='e'||min=='i'||min=='o'||min=='u')||(s.charAt(i)>=0||s.charAt(i)<=9)) {
				onlyConsoantes = false ;
			}else {
				onlyConsoantes = onlyConsoantes(s,++i);
			}//fim else
		}//fim if
		return onlyConsoantes;
	}//fim onlyConsoantes

	public static boolean sohInt(String s) {
		return sohInt(s,0);
	}//fim sohInt

	public static boolean sohInt(String s,int i) {
		char min;
		boolean sohInt = true;
		
		if(i >= s.length())sohInt = true;

		if(i < s.length() && sohInt) {
			min = Character.toLowerCase(s.charAt(i));//cada char eh convertido para minusculo para evitar erros
			if((min >= 'a' && min <= 'z')||(s.charAt(i) == '.' || s.charAt(i) == ',')) {
				sohInt = false ;
			}else {
				sohInt = sohInt(s,++i);
			}//fim else
		}//fim if
		return sohInt;
	}//fim sohInt

	public static boolean sohDouble(String s) {
		boolean sohDouble = false;
		if(sohDouble(s,0) <= 1) {//caso retorne 1 ou 0 significa que nao ha letras e ha 1'.' ou ',' ou nenhuma,entao eh um real
			sohDouble = true;
		}
		return sohDouble;
	}//fim sohDouble

	public static int sohDouble(String s,int i) {
		char min;
		int j = 0;
		
		if(i >= s.length())j=0;

		if(i < s.length()) {
			min = Character.toLowerCase(s.charAt(i));//cada char eh convertido para minusculo para evitar erros
			if((min >= 'a' && min <= 'z')||(s.charAt(i) == '.' ||  s.charAt(i) == ',')) {
				j = 1 + sohDouble(s,++i);
			}else {
				j = sohDouble(s,++i);
			}//fim else
		}//fim if
		return j;
	}//fim sohDouble

	public static void main(String[] args) {
		String[] entrada = new String[1000];
		int nEntrada = 0;

		do {//leitura da entrada padrao	
			entrada[nEntrada] = MyIO.readLine();
		}while(entrada[nEntrada++].equals("FIM") == false);
		nEntrada--;//ultima linha nao sera considerada

		for(int i = 0;i < nEntrada;i++) {//para cada string os metodos propostos serao acionados
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
