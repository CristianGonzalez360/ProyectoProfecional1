package repositories;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesServiceImpl {

	private String propertiesFileName;

	public PropertiesServiceImpl(String propertyFileName) {
		this.propertiesFileName = propertyFileName;
	}

	public void update(Map<String, String> values) throws IOException {
		Properties properties = new Properties();
		properties.clear();
		values.forEach((k, v) -> {
			properties.setProperty(k, v);
		});
		OutputStream output = new FileOutputStream(propertiesFileName);
		properties.store(output, null);
		output.close();
	}

	public Map<String, String> read() throws IOException {
		FileInputStream inputStream = new FileInputStream(propertiesFileName);
		Properties properties = new Properties();
		properties.load(inputStream);
		Map<String, String> ret = new HashMap<String, String>();
		properties.forEach((k, v) -> {
			ret.put((String) k, (String) v);
		});
		inputStream.close();
		return ret;
	}
}
