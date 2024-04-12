import java.io.*;
import java.nio.charset.*;
public class Copa {
	public static void main (String[] args) {
		Pilha pilha = new Pilha(1000);
		for(int linha = MyIO.readInt();linha > 0;linha = MyIO.readInt()) {
			Partida.leitura(linha,pilha);
		}//leitura dos primeiros anos
		
		int n = MyIO.readInt();//inteiro para contar quantas leituras serao feitas
		
		for(int j = 0;j < n;j++) {
			Partida p = new Partida();//objeto para adicionar a pilha
			String comando = MyIO.readString();//comando a ser feito		
			if(comando.charAt(0) == 'I') {
				Partida pAux = new Partida();//auxiliar para receber o objeto da leitura
				pAux = p.leitura(MyIO.readInt(),MyIO.readInt());
				pilha.inserir(pAux);//insercao na pilha
			}else  if(comando.charAt(0) == 'R') {
					p = pilha.remover();//remocao da pilha
					MyIO.println("(R) "+p.getCopa()+" - "+p.getEtapa()+" - "+p.getTime1()+"  x "+p.getTime2());
				}//fim if
			else {
				MyIO.println("ERRO : "+comando);
				System.exit(1);
			}//fim else
			
		}//fim for
		pilha.mostrar();
			
	}//fim main
}//fim class principal

class Partida {
	private int copa;
	private int placar1;
	private int placar2;
	private int dia;
	private int mes;
	private String etapa;
	private String time1;
	private String time2;
	private String local;

	Partida() {		
		this.setCopa(0);
		this.setPlacar1(0);
		this.setPlacar2(0);
		this.setDia(0);
		this.setMes(0);
		this.setEtapa("");
		this.setTime1("");
		this.setTime2("");
		this.setLocal("");
	}//fim construtor
	
	Partida(int copa,int placar1,int placar2,int dia,int mes,String etapa,String time1,String time2,String local) {
		this.setCopa(copa);
		this.setPlacar1(placar1);
		this.setPlacar2(placar2);
		this.setDia(dia);
		this.setMes(mes);
		this.setEtapa(etapa);
		this.setTime1(time1);
		this.setTime2(time2);
		this.setLocal(local);
	}//fim construtor

	public void setCopa (int copa) {
		this.copa = copa;
	}//fim setCopa
	public void setPlacar1 (int placar1) {
		this.placar1 = placar1;
	}//fim sePlacar1
	public void setPlacar2 (int placar2) {
		this.placar2 = placar2;
	}//fim setPlacar2
	public void setDia (int dia) {
		this.dia = dia;
	}//fim setDia
	public void setMes (int mes) {
		this.mes = mes;
	}//fim setMes
	public void setEtapa (String etapa) {
		this.etapa = etapa;
	}//fim setEtapa
	public void setTime1 (String time1) {
		this.time1 = time1;
	}//fim setTime1
	public void setTime2 (String time2) {
		this.time2 = time2;
	}//fim setTime2
	public void setLocal  (String local) {
		this.local = local;
	}//fim setLocal
	public int getCopa () {
		return this.copa;
	}//fim getCopa
	public int getPlacar1 () {
		return this.placar1;
	}//fim getPlacar1
	public int getPlacar2 () {
		return this.placar2;
	}//fim getPlacar2
	public int getDia () {
		return this.dia;
	}//fim getDia
	public int getMes () {
		return this.mes;
	}//fim getMes
	public String getEtapa () {
		return this.etapa;
	}//fim getEtapa
	public String getTime1 () {
		return this.time1;
	}//fim getTime1
	public String getTime2 () {
		return this.time2;
	}//fim getTime2
	public String getLocal () {
		return this.local;
	}//fim getLocal
	
	public Partida clone() {
		Partida clone = new Partida();

		clone.setCopa(this.getCopa());//a partida corrente sera atribuida ao clone
		clone.setPlacar1(this.getPlacar1());
		clone.setPlacar2(this.getPlacar2());
		clone.setDia(this.getDia());
		clone.setMes(this.getMes());
		clone.setEtapa(this.getEtapa());
		clone.setTime1(this.getTime1());
		clone.setTime2(this.getTime2());
		clone.setLocal(this.getLocal());

		return clone;
	}//fim clone

	public static void leitura (int ano,Pilha pilha) {
		String aux = "";//auxiliar para a leitura do BufferReader	
		String html = "";//String para a atribuicao final da String lida
		boolean leituraValida = false;
		StringBuffer sb = new StringBuffer();//StringBuffer gera uma String que podera ser modificada		

		try {
		     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/tmp/copa/"+ano+".html"),"ISO-8859-1"));
			aux = br.readLine();
			while(!(aux.contains("<tr bgcolor=\"#000000"))) {//apos essa tag nao eh necessario mais a leitura pois todos os dados
								        //relevantes foram lidos
	      
				if(aux.contains("<table")) leituraValida = true;//boolean mudara para true para a leitura      
				                                                                         
				if(leituraValida)sb.append(aux + "\n");//caso a leitura tenho chegado na tag table sera lido ate o fechamento

				aux = br.readLine();
			}//fim while
			br.close();
		}//fim try
		catch(IOException ioException) {
			ioException.printStackTrace();
		}//fim catch
		html = sb.toString();//retorna os dados da sequencia para a variavel html
		String removidas = removeTags(html);
		/*
		 *Set Geral
                 */
		String[] infos = removidas.split("\n");//cada posicao do vetor ha uma informao da partida
		//Partida[] pAux = new Partida[1000];
		String etapa = "";//auxiliar para pegar a etapa de cada copa
		int i = 0;
		for(int j = 0;j < infos.length;j+=6) {
		 Partida p = new Partida();
                 p.setCopa(ano);	
		 if(infos[j].equals("GRUPO A")||infos[j].equals("GRUPO B")||infos[j].equals("GRUPO C")||infos[j].equals("GRUPO D")||
		    infos[j].equals("SEMIFINAL")||infos[j].equals("FINAL")||infos[j].equals("OITAVAS-DE-FINAL")||
		    infos[j].equals("QUARTAS-DE-FINAL")||infos[j].equals("Disp. 3°. Lugar")||infos[j].equals("GRUPO E")||
		    infos[j].equals("GRUPO F")||infos[j].equals("GRUPO G")||infos[j].equals("GRUPO H")||infos[j].equals("Disp. 3°. Lugar ")||
                    infos[j].equals("SEGUNDA FASE - GRUPO 1")||infos[j].equals("SEGUNDA FASE - GRUPO 2")||
		    infos[j].equals("SEGUNDA FASE - GRUPO 3")||infos[j].equals("SEGUNDA FASE - GRUPO 4")||infos[j].equals("FASE FINAL")){
			//p.setEtapa(infos[j]);
			etapa = infos[j];
			j++;
		}//fim if
			p.setEtapa(etapa);
			p.setDia(Integer.parseInt(infos[j]));
			p.setMes(Integer.parseInt(infos[j+1]));
			p.setTime1(infos[j+2]);
			p.separaPlacares(infos[j+3]);
			p.setTime2(infos[j+4]);
			p.setLocal(infos[j+5]);
			//pAux[i] = p;
			pilha.inserir(p);
	     }//fim for
	}//fim leitura

	public static Partida leitura (int ano,int n) {
		String aux = "";//auxiliar para a leitura do BufferReader	
		String html = "";//String para a atribuicao final da String lida
		boolean leituraValida = false;
		StringBuffer sb = new StringBuffer();//StringBuffer gera uma String que podera ser modificada		

		try {
		     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/tmp/copa/"+ano+".html"),"ISO-8859-1"));
			aux = br.readLine();
			while(!(aux.contains("<tr bgcolor=\"#000000"))) {//apos essa tag nao eh necessario mais a leitura pois todos os dados
								        //relevantes foram lidos
	      
				if(aux.contains("<table")) leituraValida = true;//boolean mudara para true para a leitura      
				                                                                         
				if(leituraValida)sb.append(aux + "\n");//caso a leitura tenho chegado na tag table sera lido ate o fechamento

				aux = br.readLine();
			}//fim while
			br.close();
		}//fim try
		catch(IOException ioException) {
			ioException.printStackTrace();
		}//fim catch
		html = sb.toString();//retorna os dados da sequencia para a variavel html
		String removidas = removeTags(html);
		/*
		 *Set Geral
                 */
		String[] infos = removidas.split("\n");//cada posicao do vetor ha uma informao da partida
		Partida[] pAux = new Partida[1000];
		int i = 1;
		String etapa = "";//auxiliar para pega a etapa de cada copa
		for(int j = 0;j < infos.length;j+=6) {
		 Partida p = new Partida();
		 p.setCopa(ano);	
		 if(infos[j].equals("GRUPO A")||infos[j].equals("GRUPO B")||infos[j].equals("GRUPO C")||infos[j].equals("GRUPO D")||
		    infos[j].equals("SEMIFINAL")||infos[j].equals("FINAL")||infos[j].equals("OITAVAS-DE-FINAL")||
		    infos[j].equals("QUARTAS-DE-FINAL")||infos[j].equals("Disp. 3°. Lugar")||infos[j].equals("GRUPO E")||
		    infos[j].equals("GRUPO F")||infos[j].equals("GRUPO G")||infos[j].equals("GRUPO H")||infos[j].equals("Disp. 3°. Lugar ")||
                    infos[j].equals("SEGUNDA FASE - GRUPO 1")||infos[j].equals("SEGUNDA FASE - GRUPO 2")||
		    infos[j].equals("SEGUNDA FASE - GRUPO 3")||infos[j].equals("SEGUNDA FASE - GRUPO 4")||infos[j].equals("FASE FINAL")){
			//p.setEtapa(infos[j]);
			etapa = infos[j];
			j++;
		}//fim if
			p.setEtapa(etapa);
			p.setDia(Integer.parseInt(infos[j]));
			p.setMes(Integer.parseInt(infos[j+1]));
			p.setTime1(infos[j+2]);
			p.separaPlacares(infos[j+3]);
			p.setTime2(infos[j+4]);
			p.setLocal(infos[j+5]);
			//pAux[i] = p;
			if(i == n) {
				//pilha.inserir(p);
				pAux[i] = p;
			}
			i++;
	     }//fim for
		return pAux[n];
	}//fim leitura

	public static String removeTags(String html) {
		StringBuffer sb = new StringBuffer();//StringBuffer para gerar uma String que podera ser modificada
		boolean chegouNaTag = false;		
		for(int i = 0;i < html.length();i++) {
			if(html.charAt(i) == '>' && (((html.charAt(i+1)>='A'&&html.charAt(i+1)<='Z')||(html.charAt(i+1)>='0'&&
				html.charAt(i+1)<='9')||html.charAt(i+1)=='Á')||(html.charAt(i+1)==' '&&html.charAt(i+2)=='B'))) 
														chegouNaTag = true;
			//caso a posicao seja '>' e a proxima for
				//uma letra ou numero o boolean sera true para a concatenacao OBS:'Á' por causa da Áustria e ' ' + 'B' pois ha
														//um erro no html
			
			if(html.charAt(i) == '<')chegouNaTag = false;//caso na posicao haja '<' outra tag inicia,entao nao ha leitura

			if(chegouNaTag)sb.append(html.charAt(i+1));//concatenacao de posicoes validas
		}//fim for(i) 
		for(int j=0;j<sb.length();j++) {//laco for para substituir as ocorrencias de '<' que sobraram por '\n' 
			if(sb.charAt(j) == '<'||sb.charAt(j) == '/')sb.setCharAt(j,'\n');						
		}//fim for(j)
		return sb.toString();
	}//fim removeTags

	public void separaPlacares(String placar) {
		String aux = "";
		if((placar.charAt(0) >= '0' && placar.charAt(0) <= '9')&&(placar.charAt(1) >= '0'&&placar.charAt(1) <= '9')) {
			aux += placar.charAt(0);// caso haja um placar com mais de dois digitos os dois serao transformados com uma posicao a
			aux += placar.charAt(1);//mais
			setPlacar1(Integer.parseInt(aux));
			aux = "";
			aux+= placar.charAt(5);
			setPlacar2(Integer.parseInt(aux));
		}else { 
			aux += placar.charAt(0);//placar1 esta na primeira posicao
			setPlacar1(Integer.parseInt(aux));//setPlacar1()
			aux = "";//aux recebe "" novamente para obter o segundo placar
			aux += placar.charAt(4);//placar2 esta na ultima posicao
			setPlacar2(Integer.parseInt(aux));//setPlacar2()
		}//fim else
	}//fim separaPlacares
	
	public void imprime(){//String html){	
		MyIO.println("[COPA "+getCopa()+"] - "+getEtapa()+" - "+getDia()+"/"+getMes()+" - "+getTime1()+" ("+getPlacar1()+") x "+
				   "("+getPlacar2()+") "+getTime2()+"  - "+getLocal().trim());
	}//fim imprime
}//fim class Partida

/*
 * pilha estatica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
class Pilha {
   private Partida[] array;
   private int topo;
 
 
   /**
    * Construtor da classe.
    */
   public Pilha () {
      this(0);
   }
 
 
   /**
    * Construtor da classe.
    * @param tamanho Tamanho da pilha.
    */
   public Pilha (int tamanho){
      array = new Partida[tamanho];
      topo = 0;
   }
 
 
   /**
    * Insere um elemento na ultima posicao da pilha.
    * @param x int elemento a ser inserido.
    * @throws Exception Se a pilha estiver cheia.
    */
   public void inserir(Partida x) {//throws Exception {
 
      //validar insercao
      //if(topo >= array.length){
        // throw new Exception("Erro ao inserir!");
      //}
 
      array[topo] = x;
      topo++;
   }
 
 
   /**
    * Remove um elemento da ultima posicao da pilha.
    * @return resp int elemento a ser removido.
    * @throws Exception Se a pilha estiver vazia.
    */
   public Partida remover() { //throws Exception{
 
      //validar remocao
      //if (topo == 0) {
        // throw new Exception("Erro ao remover!");
     // }
 
      return array[--topo];
   }
 
 
   /**
    * Mostra os array separados por espacos.
    */
   public void mostrar (){
      for(int i = 0;i < topo;i++) {
		array[i].imprime();
      }
   }
 
 
   /**
    * Retorna um boolean indicando se a pilha esta vazia
    * @return boolean indicando se a pilha esta vazia
    */
   public boolean isVazia() {
      return topo == 0; 
   }
}

























































	
