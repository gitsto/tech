package filesystem.iteration1.injection;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
	Map<String, IFormat> formats = new HashMap<String, IFormat>();
	public IFormat getFormat(String format) {
		return formats.get(format);
	}
}
