import java.util.Base64;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter; 

/**
 * Klasse bietet eine Implementation des XStream Converter Interfaces und einige Hilfsmethoden
 * @author Lars Hagemann
 */
public class StringConverter implements Converter {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(String.class);
	}

	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		writer.startNode("string64");
		writer.setValue(toBase64((String)value));
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		reader.moveDown();
		String str = fromBase64(reader.getValue());
		reader.moveUp();
		return str;
	}
	
	/**
	 * Hilfsfunktion um Base64 Strings zu erzeugen
	 * @param other normaler String
	 * @return gibt den Eingabe String als Base64 zurueck
	 */
	public String toBase64(String other) {
		return Base64.getEncoder().encodeToString(other.getBytes());
	}
	
	/**
	 * Hilfsfunktion um aus Base64 Strings normale Strings zu erzeugen
	 * @param base64 Ein Base64 enkodierter String
	 * @return gibt den Eingabestring dekodiert zurueck
	 */
	public String fromBase64(String base64) {
		return new String(Base64.getDecoder().decode(base64));
	}
	
}
