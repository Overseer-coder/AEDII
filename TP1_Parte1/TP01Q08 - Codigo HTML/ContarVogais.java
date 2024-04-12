import java.util.*;
import java.net.*;
import java.io.*;
class ContarVogais {
   public static String getHtml(String endereco){
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try {
            //MyIO.setCharset("UTF-8");

         //endereco = endereco.replace("%","/");
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {//contar ocorrencias dentro while
             resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }

      try {
         is.close();
      } catch (IOException ioe) {
          //tratar erro
      }

      return resp;
   }

   public static void main(String[] args) throws IOException{
      //MyIO.setCharset("UTF-8");
        MyIO.setCharset("UTF-8");

       String html;
       String vogais = "aeiou";
       int array[] = new int[5]; 
       int c1=0,c2=0,c3=0,c4=0,c5=0;
                InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String nome = br.readLine();
		String endereco = br.readLine();
		//System.out.println(endereco);
 //      InputStream is = System.in;   
    //   InputStreamReader isr = new InputStreamReader(is);
      // BufferedReader br = new BufferedReader(isr);
      // String
      // nome = br.readLine();
      // String endereco = br.readLine();
       html = getHtml(endereco);
       while(!nome.equals("FIM") && !endereco.equals("FIM")){
          
          for(int i=0;i<html.length();i++){
            switch(html.charAt(i)) {
                    case 'a':
                   c1++;
                   array[0]= c1;
                    break;
                    
                    case 'e':
                   c2++;
                   array[1]= c2;
                    break;
                    
                    case 'i':
                   c3++;
                   array[2]= c3;
                    break;
                    
                    case 'o':
                   c4++;
                   array[3]= c4;
                    break;
                    
                    case 'u':
                   c5++;
                   array[4]= c5;
                    break;
       }
    }
        for(int j=0;j<vogais.length();j++){
        MyIO.print(vogais.charAt(j)+ "("+array[j]+") " );
        }
        MyIO.print(nome);
        
        nome = br.readLine();
        endereco = br.readLine();
       
        
     }//fim while
      //leitura da entrada padrão
      
       
          /*int c1=0,c2=0,c3=0,c4=0,c5=0;
    for(int i=0;i<entrada.length();i++){
        switch (entrada.charAt(i)) {
            case 'a':
            c1++;
            break;
            
            case 'e':
            c2++;
            break;
            
            case 'i':
            c3++;
            break;
            
            case 'o':
            c4++;
            break;
            
            case 'u':
            c5++;
            break;
            
            
        }//fim switch
    }
         for(int i=0;i<numEntrada;i++){
           MyIO.println(getHtml(entrada[i]));
      
    }
      //System.out.println("A quantida de letras a("+c1+") e("+c2+") i("+c3+") o("+c4+") u("+c5+")");
    */  
   }

}
