/**
 * This simple classloader supports a dynamically exensible classpath.
 * As usual it first delegates the loading task to its parent classloader.
 * It only loads classes that are not present in its parent classpath. 
 * 
 * @author  F. Horn
 */

package classLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipException;

public class DynamicClassLoader extends ClassLoader {
	@SuppressWarnings("unchecked")
	List classPathElements = new ArrayList();
	
	public DynamicClassLoader(ClassLoader arg0, String aCPath[]) {
		super(arg0);
		if (aCPath != null) {
			for (int i = 0; i < aCPath.length; i++) {
				addClasspath(aCPath[i]);
			}
		}
	}
	
	public DynamicClassLoader(String aCPath[]) {
		this(null, aCPath);
	}
	
	@SuppressWarnings("unchecked")
	public void addClasspath(String aPath) {
		String norm = normalize(aPath);		
		int beginIndex = 0;
		int endIndex = norm.indexOf(File.pathSeparator);
		// Walk through the classpath
		while (true) {
			String element = "";
			if (endIndex == -1) {
				element = norm.substring(beginIndex);
			} else {
				element = norm.substring(beginIndex, endIndex);
			}
			classPathElements.add(element);
			if (endIndex == -1) {
				break;
			}
			beginIndex = endIndex + 1;			
			endIndex = norm.indexOf(File.pathSeparator, beginIndex);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Class findClass(String name) throws ClassNotFoundException {
		byte[] b;
		try {
			b = loadClassData(name);
			if (b != null) {
				return defineClass(name, b, 0, b.length);
			}
		} catch (ZipException e) {
			throw new ClassNotFoundException(name);
		} catch (IOException e) {
			throw new ClassNotFoundException(name);
		}
		throw new ClassNotFoundException(name);
	}
	
	private byte[] loadClassData(String aName) throws ZipException, IOException {
		String name = aName.replace('.', '/') + ".class";
		byte buf[] = null;

		for (int i = 0; i < classPathElements.size(); i++) {
			buf = openResource(name, (String) classPathElements.get(i));
			if (buf != null) {
				break;
			}
		}
		return buf;
	}
	
	private byte[] openResource(String name, String path) throws ZipException, IOException {
		byte buf[] = null;
		String lPath = path.toLowerCase();
		if (lPath.endsWith(".zip") || lPath.endsWith(".jar")) {
			buf = openResourceFromJar(name, path);
		} else {
			String fullName = path;
			if (!path.endsWith("\\") && !path.endsWith("/")) {
				fullName += "/";
			}
			fullName += name;
			java.io.File f = new java.io.File(fullName);
			if (f.exists() && f.isFile()) {
				java.io.FileInputStream fi = new java.io.FileInputStream(f);
				long length = f.length();
				buf = new byte[(int) length];
				fi.read(buf);
				fi.close();
			}
		}
		return buf;
	}
	
	private byte[] openResourceFromJar(String name, String jarFile) throws ZipException, IOException {
		byte buf[] = null;
		java.io.File f = new java.io.File(jarFile);
		java.util.zip.ZipFile zip = null;
		
		if (f.exists() && f.isFile()) {
			zip = new java.util.zip.ZipFile(f);
			java.util.zip.ZipEntry entry = zip.getEntry(name);
			
			if (entry != null) {
				java.io.InputStream in = zip.getInputStream(entry);
				int len = (int) entry.getSize();
				buf = new byte[len];
				in.read(buf, 0, len);
				in.close();
			}
		}
		if (zip != null) {
			zip.close();
		}
		return buf;
	}
	
	private String normalize(String aElem) {
		if ('\\' != File.separatorChar) {
			return aElem.replace('\\', File.separatorChar);
		}
		if ('/'!= File.separatorChar) {
			return aElem.replace('/', File.separatorChar);
		}
		return aElem;
	}
}