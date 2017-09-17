package com.xueyou.zkview.core.zkUtils;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuxueyou on 2017/9/6.
 */
public class XyZkTools {
    public static List<String> res = new ArrayList<>();

    public static List<String> getNode(CuratorZkClientBridge curatorZkClientBridge, String parentNode) {
        if (parentNode.equals("/")) {
            res.clear();
        }
        try {
            List<String> tmpList = curatorZkClientBridge.getChildren(parentNode, false);
            for (String tmp : tmpList) {
                String childNode = parentNode.equals("/") ? parentNode + tmp : parentNode + "/" + tmp;
                res.add(childNode);
                getNode(curatorZkClientBridge, childNode);
            }
            return res;
        } catch (KeeperException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readNode(CuratorZkClientBridge curatorZkClientBridge, String nodePath) throws Exception {
        byte[] res = curatorZkClientBridge.readData(nodePath, new Stat(), false);
        return new String(res);
    }


    public static void writeNode(CuratorZkClientBridge curatorZkClientBridge, String nodePath, String value) throws Exception {
        //查看版本
        Stat stat = new Stat();
        curatorZkClientBridge.readData(nodePath, stat, false);
        curatorZkClientBridge.writeData(nodePath, value.getBytes(), stat.getVersion());
    }


}
