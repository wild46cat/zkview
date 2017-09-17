package com.xueyou.zkview.service.cache;

import com.xueyou.zkview.core.zkUtils.CuratorZkClientBridge;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxueyou on 2017/9/17.
 */
@Component
public class CuratorZKFrameworkCache {
    private Map<String, CuratorFramework> curatorFrameworkMap;

    @PostConstruct
    public void init() {
        curatorFrameworkMap = new HashMap<>();
    }

    public Map<String, CuratorFramework> getCuratorFrameworkMap() {
        return curatorFrameworkMap;
    }

    public void setCuratorFrameworkMap(Map<String, CuratorFramework> curatorFrameworkMap) {
        this.curatorFrameworkMap = curatorFrameworkMap;
    }
}
