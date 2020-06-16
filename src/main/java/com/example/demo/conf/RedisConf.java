//package com.example.demo.conf;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * @Author mintaoyu
// * Date on 2020-06-05  13:40
// */
//@Configuration
//public class RedisConf {
//
//    @Value("${spring.redis.cluster.nodes}")
//    private String clusterNodes;
//
//
//    @Bean
//    public JedisCluster getJedisCluster(){
//        //分割集群节点
//        String[] cNodes = clusterNodes.split(",");
//        //创建set集合对象
//        Set<HostAndPort> nodes =new HashSet<>();
//        for (String node:cNodes) {
//            //127.0.0.1:7001
//            String[] hp = node.split(":");
//            nodes.add(new HostAndPort(hp[0],Integer.parseInt(hp[1])));
//        }
//        int connectionTimeout = 15000;
//        int soTimeout = 15000;
//        int maxAttempts = 3;
//        String password = "135799";
//// Jedis连接池配置
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(200);
//        config.setMaxIdle(50);
//        config.setMinIdle(8);//设置最小空闲数
//        config.setMaxWaitMillis(10000);
//        config.setTestOnBorrow(true);
//        config.setTestOnReturn(true);
////Idle时进行连接扫描
//        config.setTestWhileIdle(true);
////表示idle object evitor两次扫描之间要sleep的毫秒数
//        config.setTimeBetweenEvictionRunsMillis(30000);
////表示idle object evitor每次扫描的最多的对象数
//        config.setNumTestsPerEvictionRun(10);
////表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
//        config.setMinEvictableIdleTimeMillis(60000);
//
//
//
//        //创建Redis集群对象
//        return new JedisCluster(nodes,connectionTimeout,soTimeout,maxAttempts,password,config);
//    }
//
//}
