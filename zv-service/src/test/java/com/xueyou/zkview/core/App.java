package com.xueyou.zkview.core;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxueyou on 2017/9/5.
 */
public class App {
//    public static String connectionString = "192.168.0.99:2181,192.168.0.99:2182,192.168.0.99:2183";
//    public static List<String> res = new ArrayList<>();
//
//    public static void main(String[] args) {
//        System.out.println("Hello World!");
//        CuratorFramework curatorFramework = CreateClient.createSimple(connectionString);
//        curatorFramework.start();
//        //doSomething to zookeeper
//        CuratorZkClientBridge curatorZkClientBridge = new CuratorZkClientBridge(curatorFramework);
//        System.out.println(getNode(curatorZkClientBridge, "/"));
//        curatorFramework.close();
//    }
//
//    public static List<String> getNode(CuratorZkClientBridge curatorZkClientBridge, String parentNode) {
//        try {
//            List<String> tmpList = curatorZkClientBridge.getChildren(parentNode, false);
//            for (String tmp : tmpList) {
//                String childNode = parentNode.equals("/") ? parentNode + tmp : parentNode + "/" + tmp;
//                res.add(childNode);
//                getNode(curatorZkClientBridge, childNode);
//            }
//            return res;
//        } catch (KeeperException e) {
//            e.printStackTrace();
//            return null;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

}
