import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteDrive {

	public static void main(String[] args) {
		
		Map<String, String> mapa = new HashMap<String,String>();
		Map<String, String> mapaAux = new HashMap<String,String>();
		List<String> strings = new ArrayList<>();
		mapa.put("1", "um");
		mapa.put("2", "dois");
		mapa.put("3", "tres");
		mapaAux.put("1", "um");
		mapaAux.put("2", "dois");
		mapaAux.put("3", "dois");
		
		mapa.putAll(mapaAux);
		
		List<Map<String, String>> l = new ArrayList<>();
		l.add(mapa);
		l.add(mapaAux);
		
		Collections.sort(strings);
		System.out.println(mapa);
	}
}
