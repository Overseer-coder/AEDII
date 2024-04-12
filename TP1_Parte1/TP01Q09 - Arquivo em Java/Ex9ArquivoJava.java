import java.io.*;
public class Ex9ArquivoJava {
	public static void main(String[] args) {
		//String linhas = MyIO.readLine();
		RandomAccessFile raf = null;
		int i = 0;

		try {
			raf = new RandomAccessFile("pub.in","rw");
			raf.seek(0);
			raf.readUTF();
			raf.getFilePointer();


			raf.seek(80);
			MyIO.println(raf.readUTF());
			raf.getFilePointer();
			i++;
		}
		catch(IOException ioException) {
			ioException.printStackTrace();
		}	
	}
}
