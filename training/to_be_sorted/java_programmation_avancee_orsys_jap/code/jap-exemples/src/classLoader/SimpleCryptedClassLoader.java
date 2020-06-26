package classLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class SimpleCryptedClassLoader extends ClassLoader {
	private String baseDir;
	private byte decalage;

	public SimpleCryptedClassLoader(String bD, byte dec, ClassLoader parent) { super(parent);  baseDir = bD; decalage = dec; }
	public SimpleCryptedClassLoader(String bD, byte dec) { baseDir = bD; decalage = dec; }


	@SuppressWarnings("unchecked")
	@Override
	protected Class<?> findClass(String name) {
		String fileName=name.replace('.', File.separatorChar) + ".class";
		byte[ ] buf = new byte[5000];

		try {
			//lecture du contenu du fichier .class
			File file =new File(baseDir + File.separator + fileName);
			InputStream is=new FileInputStream(file);
			int len = is.read(buf);

			//transformation du contenu du fichier .class
			for (int i = 0; i < len; i ++) buf[i] -= decalage;

			Class cl=defineClass(name,buf,0,len);
			return cl;
		} catch(Exception exc) { return null; }
	}
}
