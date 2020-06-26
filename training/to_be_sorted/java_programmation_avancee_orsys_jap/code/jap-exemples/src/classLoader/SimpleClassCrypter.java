package classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class SimpleClassCrypter {
	public static void main(String[] args) {
		if (args == null || args.length < 4) {
			System.out.println("exige 4 args : repertoire_racine_in nom_classe decalage repertoire_racine_out");
			return;
		}
		
		String baseDirIn = args[0];
		String fileName=args[1].replace('.', File.separatorChar) + ".class";
		byte decalage = (byte) Integer.parseInt(args[2]);
		String baseDirOut = args[3];
		
		try{
			byte[] buf = new byte[5000];
			//lecture du contenu du fichier .class
			File fileIn =new File(baseDirIn + File.separator + fileName);
			InputStream is=new FileInputStream(fileIn);
			int len = is.read(buf);
			is.close();

			//transformation du contenu du fichier .class
			for (int i = 0; i < len; i ++) buf[i] += decalage;
			
			File fileOut =new File(baseDirOut + File.separator + fileName);
			System.out.println("fichier out :" + fileOut.toString());
			fileOut.createNewFile();
			OutputStream os=new FileOutputStream(fileOut);
			os.write(buf, 0, len);
			os.close();
		} catch(Exception exc) {
			System.out.println("probleme dans le cryptage");
			exc.printStackTrace();
		}
	}
}
