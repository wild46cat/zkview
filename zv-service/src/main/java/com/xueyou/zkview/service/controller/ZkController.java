package com.xueyou.zkview.service.controller;

import com.xueyou.zkview.core.zkUtils.CreateClient;
import com.xueyou.zkview.core.zkUtils.CuratorZkClientBridge;
import com.xueyou.zkview.core.zkUtils.XyZkTools;
import com.xueyou.zkview.service.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxueyou on 2017/9/6.
 */
@RestController
@RequestMapping("/zk")
public class ZkController {
    Logger logger = LoggerFactory.getLogger(ZkController.class);
    private CuratorZkClientBridge curatorZkClientBridge;

    @RequestMapping("/connect")
    public Map<String, Object> connect(String connectionString) {
        try {
            if (Constant.curatorFramework != null) {
                Constant.curatorFramework.close();
            }
            Constant.curatorFramework = CreateClient.createSimple(connectionString);
            Constant.curatorFramework.start();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("connectionString not validate", e);
        } finally {
        }
        curatorZkClientBridge = new CuratorZkClientBridge(Constant.curatorFramework);
        String servers = curatorZkClientBridge.getServers();
        logger.info("connectionServer -->" + servers);
        Map<String, Object> res = new HashMap();
        res.put("flag", connectionString.equals(servers));
        res.put("nodes", getAll());
        return res;
    }


    @RequestMapping("/getAll")
    public List<String> getAll() {
        XyZkTools.getNode(curatorZkClientBridge, "/");
        List<String> resList = XyZkTools.res;
        return resList;
    }
}
