package Data;

import java.util.*;

public class StoredState {
    Map<String, List<String>> states;

    public Map<String, List<String>> StartStateData() {

        states = new HashMap<>();

        //presta atenção no . ou , na hora de mandar as cordenadas porque esses pequenin dão problema
        states.put("Acre", Arrays.asList("-9.2762","-71.4253"));
        states.put("Alagoas", Arrays.asList("-9.7103","-35.8965"));
        states.put("Amapá", Arrays.asList("2.0235","-50.7823"));
        states.put("Amazonas", Arrays.asList("-3.1019","-60.0253"));
        states.put("Bahia", Arrays.asList("-12.9756","-38.4923"));
        states.put("Ceará", Arrays.asList("-3.7172","-38.5421"));
        states.put("Distrito Federal", Arrays.asList("-15.7797","-47.9297"));
        states.put("Espírito Santo", Arrays.asList("-20.3194","-40.3378"));
        states.put("Goiás", Arrays.asList("-16.6786","-49.2539"));
        states.put("Maranhão", Arrays.asList("-2.5797","-44.3028"));
        states.put("Mato Grosso", Arrays.asList("-15.2567","-56.7333"));
        states.put("Mato Grosso do Sul", Arrays.asList("-20.4428","-54.6464"));
        states.put("Minas Gerais", Arrays.asList("-19.9129","-43.9409"));
        states.put("Pará", Arrays.asList("-1.4558","-48.5044"));
        states.put("Paraíba", Arrays.asList("-7.1152","-34.8631"));
        states.put("Paraná", Arrays.asList("-25.4278","-49.2731"));
        states.put("Pernambuco", Arrays.asList("-8.0539","-34.8811"));
        states.put("Piauí", Arrays.asList("-5.0892","-42.8019"));
        states.put("Rio de Janeiro", Arrays.asList("-22.9064", "-43.1822"));
        states.put("Rio Grande do Norte", Arrays.asList("-5.7951","-35.2094"));
        states.put("Rio Grande do Sul", Arrays.asList("-30.0328","-51.2302"));
        states.put("Rondônia", Arrays.asList("-8.7619","-63.9039"));
        states.put("Roraima", Arrays.asList("-2.8197","-60.6733"));
        states.put("Santa Catarina", Arrays.asList("-27.5967","-48.5492"));
        states.put("Sao Paulo", Arrays.asList("-23.5505", "-46.6333"));
        states.put("Sergipe", Arrays.asList("-10.9111","-37.0717"));
        states.put("Tocantins", Arrays.asList("-7.3294","-47.4164"));

        return states;

    }

    //Salva vidas
    public List<String> getStateData(String stateName) {
        return states.getOrDefault(stateName, null);
    }
}
