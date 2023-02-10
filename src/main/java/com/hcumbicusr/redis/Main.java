package com.hcumbicusr.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.UUID;

public class Main {
    private static  final String REDIS_HOST = "localhost";
    private static  final String REDIS_LIST = "lista-ejemplo";
    public static  void main(String[] args) {
        if (args.length != 1) { printUsage(); return; }
        if (args[0].equals("producer")) producer();
        else if (args[0].equals("consumer")) consumer();
        else printUsage();
    }

    private static void producer() {
        Jedis jedis = new Jedis( REDIS_HOST );

        while (true) {
            String newElement = UUID.randomUUID().toString();
            jedis.rpush( REDIS_LIST, newElement );
            System.out.println("\033[H\033[2J");
            System.out.flush();
            System.out.println("REDIS Productor");
            System.out.println("==========================");
            System.out.println("");
            System.out.println("Total de elementos: "+jedis.llen( REDIS_LIST ));
            System.out.println("Elemento "+jedis.lrange( REDIS_LIST, 0, -1 ));

            try{
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void consumer() {
        Jedis jedis = new Jedis( REDIS_HOST );
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("REDIS Consumer");
        System.out.println("==========================");
        System.out.println("");

        while (true) {
            List<String> element = jedis.blpop(0, REDIS_LIST);
            System.out.println("Elemento leído: "+element);
            System.out.println(" ("+jedis.llen( REDIS_LIST )+" Pendientes)");
            try {
                Thread.sleep(1500);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static void printUsage() {
        System.out.println("USAGE: java -jar Redis.jar <producer|consumer>");
    }



}
