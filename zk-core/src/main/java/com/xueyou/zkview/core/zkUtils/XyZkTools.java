package com.xueyou.zkview.core.zkUtils;

import org.apache.zookeeper.KeeperException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wuxueyou on 2017/9/6.
 */
public class XyZkTools {
    public static List<String> res = new ArrayList<>();

    public static List<String> getNode(CuratorZkClientBridge curatorZkClientBridge, String parentNode) {
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
}
