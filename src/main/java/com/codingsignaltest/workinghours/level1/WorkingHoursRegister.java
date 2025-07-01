package com.codingsignaltest.workinghours.level1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkingHoursRegister {
    private final Map<String,Worker> workers = new HashMap<>();
    public List<String> processQueries(String[] queries){
        List<String> result = new ArrayList<>();
        for(String query : queries){
            String[] parts = query.split(" ");
            String command = parts[0];

            switch (command){
                case "ADD_WORKER" :
                    result.add(addWorker(parts[1],parts[2],parts[3]));
                    break;
                case "REGISTER" :
                    result.add(registers(parts[1],Integer.parseInt(parts[2])));
                    break;
                case "GET" :
                    result.add(getTime(parts[1]));
                    break;
                default:
                    result.add("invalid_request");
            }

        }
        return result;
    }

    private String getTime(String part) {
        Worker worker = workers.get(part);
        if(worker == null) return "";
        return String.valueOf(worker.getTotaltime());
    }

    private String registers(String part, int part1) {
        Worker worker = workers.get(part);
        if(worker == null) return "invalid request";
        worker.register(part1);
        return "registered";
    }

    private String addWorker(String part, String part1, String part2) {
        if(workers.containsKey(part) ) return "false";

        workers.put(part, new Worker(part1,part2));
        return "true";
    }

    public static void main(String[] args) {

            WorkingHoursRegister system = new WorkingHoursRegister();
            String[] queries = {
                    "ADD_WORKER John Developer 5000",
                    "ADD_WORKER John Developer 5000",
                    "REGISTER John 5",
                    "REGISTER John 20",
                    "GET John",
                    "REGISTER John 30",
                    "REGISTER John 50",
                    "REGISTER John 63",
                    "GET John",
                    "GET Alice",
                    "REGISTER Alice 10"
            };

            List<String> output = system.processQueries(queries);
            System.out.println(output);
    }
}
