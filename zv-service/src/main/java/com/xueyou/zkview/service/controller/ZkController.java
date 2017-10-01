package com.xueyou.zkview.service.controller;

import com.xueyou.zkview.core.zkUtils.CreateClient;
import com.xueyou.zkview.core.zkUtils.CuratorZkClientBridge;
import com.xueyou.zkview.core.zkUtils.XyZkTools;
import com.xueyou.zkview.service.cache.CuratorZKFrameworkCache;
import com.xueyou.zkview.service.constant.Constant;
import com.xueyou.zkview.service.dto.NodeDto;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private CuratorFramework curatorFramework;

    @Autowired
    private CuratorZKFrameworkCache curatorZKFrameworkCache;

    @RequestMapping("/connect")
    public Map<String, Object> connect(String connectionString) {
        try {
//            if (Constant.curatorFramework != null) {
//                Constant.curatorFramework.close();
//            }
            if (curatorZKFrameworkCache.getCuratorFrameworkMap().get(connectionString) == null) {
                curatorFramework = CreateClient.createSimple(connectionString);
                curatorFramework.start();
                curatorZKFrameworkCache.getCuratorFrameworkMap().put(connectionString, curatorFramework);
            } else {
                curatorFramework = curatorZKFrameworkCache.getCuratorFrameworkMap().get(connectionString);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("connectionString not validate", e);
        } finally {
        }
        curatorZkClientBridge = new CuratorZkClientBridge(curatorFramework);
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

    @RequestMapping("/getByNodeName")
    public String getByNodeName(String name) {
        try {
            return XyZkTools.readNode(curatorZkClientBridge, name);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping(value = "/setNodeValue", method = {RequestMethod.POST})
    public boolean setNodeValue(@RequestBody NodeDto nodeDto) {
        try {
            XyZkTools.writeNode(curatorZkClientBridge, nodeDto.getNodePath(), nodeDto.getNodeValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "/createNode", method = {RequestMethod.POST})
    public boolean createNode(@RequestBody NodeDto nodeDto) {
        try {
            XyZkTools.createNode(curatorZkClientBridge, nodeDto.getNodePath(), nodeDto.getNodeValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @RequestMapping(value = "/deleteNode", method = {RequestMethod.POST})
    public boolean deleteNode(@RequestBody NodeDto nodeDto) {
        try {
            XyZkTools.deleteNode(curatorZkClientBridge, nodeDto.getNodePath());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public CuratorZkClientBridge getCuratorZkClientBridge() {
        return curatorZkClientBridge;
    }

    public void setCuratorZkClientBridge(CuratorZkClientBridge curatorZkClientBridge) {
        this.curatorZkClientBridge = curatorZkClientBridge;
    }

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public void setCuratorFramework(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    public CuratorZKFrameworkCache getCuratorZKFrameworkCache() {
        return curatorZKFrameworkCache;
    }

    public void setCuratorZKFrameworkCache(CuratorZKFrameworkCache curatorZKFrameworkCache) {
        this.curatorZKFrameworkCache = curatorZKFrameworkCache;
    }
}
