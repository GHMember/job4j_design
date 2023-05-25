package ru.job4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
        try(var scanner = new Scanner(new BufferedReader(new FileReader("./data/cities.xml")))
                .useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                String str = scanner.next();
                if (str.contains("<item")) {
                    list.add(str.trim());
                }
            }
            Map<String, Integer> duplicates = new HashMap<>();
            for (String str : list) {
                duplicates.put(str, Collections.frequency(list, str));
            }
            /* Выводим на печать дублирующие строки с количеством их повторов */
//            duplicates.entrySet().stream().filter(map -> map.getValue() > 1).
//                    forEach(map -> System.out.printf("Строка \"%s\", количество повторений - %d%n",
//                            map.getKey(), map.getValue()));

            List<String> buildingInfo = new ArrayList<>();
            for (String str : list){
                String[] substrs = str.split("\"");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < substrs.length; i++) {
                    if (substrs[i].contains("city") || substrs[i].contains("floor")) {
                        sb.append(substrs[i + 1]).append(" ");
                    }
                }
                buildingInfo.add(sb.toString().trim());
            }
            Collections.sort(buildingInfo);
            Set<String> cities = new HashSet<>();
            for (String str : buildingInfo) {
                String city = str.substring(0,str.length()-1);
                cities.add(city);
            }
            cities = cities.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
            for (String city : cities) {
                System.out.println(city);
                List<String> floors = new ArrayList<>();
                for (String str : buildingInfo) {
                    if (str.contains(city)) {
                        String floor = str.substring(str.length()-1);
                        floors.add(floor);
                    }
                }
                List<String> floorList = List.of("1", "2", "3", "4", "5");
                Map<Object, Long> countBuildings = floors.stream().collect(Collectors.groupingBy(c -> c , Collectors.counting()));
                /* Выводим на печать города с количеством 1, 2, 3, 4 и 5 этажных зданий */
                for (String s : floorList) {
                    if (countBuildings.containsKey(s)) {
                        System.out.printf("количество %s-этажных зданий - %d%n", s, countBuildings.get(s));
                    }
                    else {
                        System.out.printf("количество %s-этажных зданий - %d%n", s, 0);
                    }
                }
                System.out.println();
            }
        }
    }
}
