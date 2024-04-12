import java.io.*;
import java.nio.charset.*;
public class Copa {
	public static void main (String[] args) {
		ArvoreBinaria AB = new ArvoreBinaria();
		int comparacoes = 0;
		long inicio = System.currentTimeMillis();
		for (int linha = MyIO.readInt(); linha != 0; linha = MyIO.readInt()) {
            		Time.leitura(linha, AB);
        	}
		AB.mostrarPre();
		for (String line = MyIO.readLine();line.equals("FIM") == false;line = MyIO.readLine()) {
			AB.pesquisar(line);
		}
		//AB.mostrarPre();
		//Ranking.mostrar();
		long fim = System.currentTimeMillis();

		Arq.openWrite("matrı́cula arvoreBinaria.txt");
		Arq.print("Matricula - 594451 \tInicio - "+inicio+"\tFim - "+fim+"\tComparacoes - "+comparacoes);
		Arq.close();
		
	}//fim main
}//fim class principal

class Time {
	protected static Time[] team = new Time[1000];
	protected static int c = 0;
	private String pais;
	private int nPontos;
	private int nJogos;
	private int wins;
	private int loses;
	private int draws;
	private int gp;
	private int gc;
	private int sg;

	Time() {
		this.setPais("");
		this.setPontos(0);
		this.setJogos(0);
		this.setWins(0);
		this.setLoses(0);
		this.setDraws(0);
		this.setGP(0);
		this.setGC(0);
		this.setSG(0);
	}//fim construtor

	Time(String pais,int nPontos,int nJogos,int wins,int loses,int draws,int gp,int gc,int sg) {
		this.setPais(pais);
		this.setPontos(nPontos);
		this.setJogos(nJogos);
		this.setWins(wins);
		this.setLoses(loses);
		this.setDraws(draws);
		this.setGP(gp);
		this.setGC(gc);
		this.setSG(sg);
	}//fim construtor

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public void setPontos(int nPontos) {
		this.nPontos += nPontos;
	}

	public void setJogos(int nJogos) {
		this.nJogos += nJogos;
	}

	public void setWins(int wins) {
		this.wins += wins;
	}

	public void setLoses(int loses) {
		this.loses += loses;
	}

	public void setDraws(int draws) {
		this.draws += draws;
	}

	public void setGP(int gp) {
		this.gp += gp;
	}

	public void setGC(int gc) {
		this.gc += gc;
	}

	public void setSG(int sg) {
		this.sg += sg;
	}

	public String getPais() {
		return this.pais;
	}

	public int getPontos() {
		return this.nPontos;
	}

	public int getJogos() {
		return this.nJogos;
	}

	public int getWins() {
		return this.wins;
	}

	public int getLoses() {
		return this.loses;
	}

	public int getDraws() {
		return this.draws;
	}
	
	public int getGP() {
		return this.gp;
	}

	public int getGC() {
		return this.gc;
	}

	public int getSG() {
		return this.sg;
	}

	public void inserir(Time t) {
		team[c] = t;
		c++;
	}

	public Time clone() {
		Time clone = new Time();

		clone.setPais(this.getPais());
		clone.setPontos(this.getPontos());
		clone.setJogos(this.getJogos());
		clone.setWins(this.getWins());
		clone.setLoses(this.getLoses());
		clone.setDraws(this.getDraws());
		clone.setGP(this.getGP());
		clone.setGC(this.getGC());
		clone.setSG(this.getSG());

		return clone;
	}//fim clone

	public static void leitura (int ano,ArvoreBinaria AB) {
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
		String team1 = "", team2 = "";
		int pos1 = 0, pos2 = 0;
		int aux1 = 0, aux2 = 0;
		int placar1 = 0, placar2 = 0;
		String au = "";
		String[] placar;
		for(int j = 0;j < infos.length;j+=6) {
		 	
		 if(infos[j].equals("GRUPO A")||infos[j].equals("GRUPO B")||infos[j].equals("GRUPO C")||infos[j].equals("GRUPO D")||
		    infos[j].equals("SEMIFINAL")||infos[j].equals("FINAL")||infos[j].equals("OITAVAS-DE-FINAL")||
		    infos[j].equals("QUARTAS-DE-FINAL")||infos[j].equals("Disp. 3°. Lugar")||infos[j].equals("GRUPO E")||
		    infos[j].equals("GRUPO F")||infos[j].equals("GRUPO G")||infos[j].equals("GRUPO H")||infos[j].equals("Disp. 3°. Lugar ")||
                    infos[j].equals("SEGUNDA FASE - GRUPO 1")||infos[j].equals("SEGUNDA FASE - GRUPO 2")||
		    infos[j].equals("SEGUNDA FASE - GRUPO 3")||infos[j].equals("SEGUNDA FASE - GRUPO 4")||infos[j].equals("FASE FINAL")){
			j++;
		}//fim if
			
			team1 = infos[j+2];
			team1 = confere(team1);//metodo para trocar nome dos paises de acordo com o enunciado
			//placar1 = spPlacar1(infos[j+3]);
			//placar2 = spPlacar2(infos[j+3]);
			placar = infos[j+3].split("x");//linha dos placares e colocada em um vetor de duas posicoes
			placar1 = Integer.parseInt(placar[0].trim());//as posicoes sao direcionadas aos respectivos placares com trim para 
                        placar2 = Integer.parseInt(placar[1].trim());//retirar os espacos que ha
			team2 = infos[j+4];
			team2 = confere(team2);//metodo para trocar nome dos paises de acordo com o enunciado
	
		pos1=existe(team1,team);//metodo feito por Bertoni e modificado por Pedro Rodrigues
                if(pos1==-1){
		    aux1 = pos1;//caso entre no if aux ficara -1 para um mesmo pais nao ser inserido repetido
		    pos1=c;
                    team[pos1] = new Time();
                    team[pos1].setPais(team1);
                    c++;
                    }
                //fim if
                pos2 = existe(team2,team);
                if(pos2==-1){
			aux2 = pos2;//caso entre no if aux ficara -1 para um mesmo pais nao ser inserido repetido
                	pos2 = c;
                	team[pos2] = new Time();
                	team[pos2].setPais(team2);
                c++;
                
                }//fim if
                team[pos1].setJogos(1);
                team[pos2].setJogos(1);
                
                team[pos1].setGP(placar1);
                team[pos2].setGP(placar2);
                
                team[pos1].setGC(placar2);
                team[pos2].setGC(placar1);
                
                team[pos1].setSG(placar1-placar2);
                team[pos2].setSG(placar2-placar1);
                
                if(placar1>placar2){
                    team[pos1].setPontos(3);
                    team[pos1].setWins(1);
                    team[pos2].setLoses(1);
                
                }else if(placar1==placar2){
               		 team[pos1].setPontos(1);
                	 team[pos2].setPontos(1);
                	 team[pos1].setDraws(1);
                	 team[pos2].setDraws(1);
                }else{
                	team[pos2].setPontos(3);
                	team[pos2].setWins(1);
                	team[pos1].setLoses(1);
                }//fim else
                  
		if(aux1 == -1){//se aux for -1 o time sera repetido entao nao eh necessario inserir
			AB.inserir(team[pos1]);//insercao 
			aux1 = 0;//aux recebe 0 para verificar se ha outros paises repetidos
		}//fim if
		
		if(aux2 == -1){//se aux for -1 o time sera repetido entao nao eh necessario inserir
			AB.inserir(team[pos2]);//insercao 
	 		aux2 = 0;//aux recebe 0 para verificar se ha outros paises repetidos
		}//fim if
            			
	     }//fim for
		
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

	public static String confere(String time) {
		if(time.equals("Alemanha Ocidental"))time = "Alemanha";
		if(time.equals("União Soviética"))time = "Rússia";
		if(time.equals("Tchecoslováquia"))time = "República Tcheca";
		if(time.equals("Iugoslávia") || time.equals("Sérvia e Montenegro"))time = "Sérvia";

		return time;
	}//fim confere

	public static int existe(String time,Time[] t){
      		int existe = -1;//caso o time nao exista no Ranking ele sera adicionado
      		for(int i=0;i<c;i++){
         		if(time.equals(t[i].getPais())){
          			existe = i;
       	  		}//fim if
     		}//fim for
     		return existe;
   	}//fim existe
}//fim Time

class No {
    public Time elemento; 
    public No esq, dir;  

    public No(Time elemento) {
        this(elemento, null, null);
    }//fim construtor

    public No(Time elemento, No esq, No dir) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }//fim construtor
}//fim No

class ArvoreBinaria extends Time {
    private No raiz; 

    public ArvoreBinaria() {
        raiz = null;
    }//fim construtor

    public boolean pesquisar(String x) {
	System.out.print(x+" raiz ");
	//System.out.println();
        return pesquisar(x, raiz);
    }

    private boolean pesquisar(String x, No i) {
      boolean resp=true;
        if (i == null) {
	 System.out.print("NÃO\n");
         resp = false;
 
      } else if (x == i.elemento.getPais()) {
	 System.out.print("SIM\n");
         resp = true;
 	/*
      } else if (x.charAt(0) < i.elemento.getPais().charAt(0) || x.charAt(1) < i.elemento.getPais().charAt(1)) {
	 System.out.print("esq ");
         resp = pesquisar(x, i.esq);
 
      } else if (x.charAt(0) > i.elemento.getPais().charAt(0) || x.charAt(1) > i.elemento.getPais().charAt(1)) {
	 System.out.print("dir ");
         resp = pesquisar(x, i.dir);
      } else {
	 //resp = pesquisar(x,ordena(x,i.elemento.getPais(),i));
      }
	*/
	}else if(ordenaBoolean(x,i.elemento.getPais())) {
		System.out.print("dir ");
		resp = pesquisar(x,i.dir);
	    }else {
		System.out.print("esq ");
		resp = pesquisar(x,i.esq);
	    }
	
      return resp;
    }//fim pesquisar

    public void mostrarPre() {
        System.out.print("[ ");
        mostrarPre(raiz);
        System.out.println("]");
    }

    private void mostrarPre(No i) {
        if (i != null) {
            System.out.print(i.elemento.getPontos()+"("+i.elemento.getPais() +") "); 
            mostrarPre(i.esq); 
            mostrarPre(i.dir); 
        }//fim if
    }//fim mostrarPre

    public void inserir(Time x) {//throws Exception {
        raiz = inserir(x, raiz);
    }

    private No inserir(Time x, No i) {//throws Exception {
        if (i == null) {
         i = new No(x);
 
      } //else if (x.getPais().charAt(0) < i.elemento.getPais().charAt(0) || x.getPais().charAt(1) < i.elemento.getPais().charAt(1)) {
         //i.esq = inserir(x, i.esq);
 
      //} else if (x.getPais().charAt(0) > i.elemento.getPais().charAt(0) || x.getPais().charAt(1) > i.elemento.getPais().charAt(1)) {
         //i.dir = inserir(x, i.dir);
 
      //} 
      else if(ordenaBoolean(x.getPais(),i.elemento.getPais())) {
		i.dir = inserir(x,i.dir);
	    }else {
		i.esq = inserir(x,i.esq);
	    }
        return i;
    }//fim inserir

	public static boolean ordenaBoolean(String procurado,String corrente) {
		boolean resp = false;
		int comparacoes = 0;
		for(int i = 0;i < 3 && resp == false;i++) {
			if(procurado.charAt(i) != corrente.charAt(i)) {
				if(procurado.charAt(i) > corrente.charAt(i)) {
					resp = true;
				}//fim else
			}//fim if		
		}//fim for
		return resp;
	}//fim ordena
 
    public void remover(Time x) {//throws Exception {
        raiz = remover(x, raiz);
    }

    private No remover(Time x, No i) {//throws Exception {
 
        if (i == null) {
         //throw new Exception("Erro ao remover!");
 
      }  else if (x.getPais().charAt(0) < i.elemento.getPais().charAt(0)) {
         i.esq = remover(x, i.esq);
 
      } else if (x.getPais().charAt(0) > i.elemento.getPais().charAt(0)) {
         i.dir = remover(x, i.dir);
 
      } else if (i.dir == null) {
         i = i.esq;
 
      // Sem no a esquerda.
      } else if (i.esq == null) {
         i = i.dir;
 
      // No a esquerda e no a direita.
      } else {
         i.esq = antecessor(i, i.esq);
        }
 
        return i;
    }//fim remover

    private No antecessor(No i, No j) {
        if (j.dir != null) {
            j.dir = antecessor(i, j.dir);
        } else {
            i.elemento = j.elemento; 
            j = j.esq; 
        }//fim else
        return j;
    }//fim antecessor
}//fim arvoreBinaria
	




























































	
