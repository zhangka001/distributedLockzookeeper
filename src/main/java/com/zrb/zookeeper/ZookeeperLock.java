package com.zrb.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangruibiao on 17/5/23.
 */
public class ZookeeperLock {
    public static void main(String[] args) throws Exception{
//        ZooKeeper zk = new ZooKeeper("172.16.179.132:2181", 60000, new Watcher() {
//            // 监控所有被触发的事件
//            public void process(WatchedEvent event) {
//                System.out.println("监控所有被触发的事件:EVENT:" + event.getType());
//            }
//        });
        String ZKServers = "172.16.179.132:2181";



        for (int i = 0 ; i < 5 ; i++){
            new Thread(){
                @Override
                public void run(){
                    ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());
                    String rootPath = "/root";
                    SimpleDistributedLockMutex simpleDistributedLockMutex = new SimpleDistributedLockMutex(zkClient,rootPath);
                    try {
                        boolean acquire = simpleDistributedLockMutex.acquire(10000, TimeUnit.SECONDS);
                        try {
                            Thread.sleep(5000);
                            simpleDistributedLockMutex.release();

                        }catch (Exception e){}

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }.start();
        }

//        ZkClient zkClient = new ZkClient(ZKServers,10000,10000,new SerializableSerializer());
//        String rootPath = "/root";
//        SimpleDistributedLockMutex simpleDistributedLockMutex = new SimpleDistributedLockMutex(zkClient,rootPath);
//        try {
//            boolean acquire = simpleDistributedLockMutex.acquire(10000, TimeUnit.SECONDS);
//            simpleDistributedLockMutex.release();
//            System.out.println(acquire);
//            boolean acquire1 = simpleDistributedLockMutex.acquire(10000, TimeUnit.SECONDS);
//            simpleDistributedLockMutex.release();
//            System.out.println(acquire1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }


    }
}
