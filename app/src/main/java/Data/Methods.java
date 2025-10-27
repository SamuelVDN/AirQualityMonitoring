package Data;

import java.util.*;


public class Methods {

    public ArrayList<String> FillList(Map<String, List<String>> Data){
        ArrayList<String> Final = new ArrayList<>();

        // percorre as chaves do Hash para pegar o nome do estado)
        for (String key : Data.keySet()) {
            Final.add(key);
        }
        // ordena alfabeticamente os estados
        Collections.sort(Final, String.CASE_INSENSITIVE_ORDER);

        return Final;
    }

    public void ParseInfo(){

    }
}
