package com.xueyou.zkview.core.leaderSeletion;

import com.xueyou.zkview.core.zkUtils.CreateClient;
import org.apache.curator.framework.CuratorFramework;


public class LeaderSelectorExample {
    private static final String CONNECTIONSTRING = "localhost:2181,localhost:2182,localhost:2183";
    private static final String PATH = "/examples/leader";
    private static final String NAME1 = "c1";
    private static final String NAME2 = "c2";

    public static void main(String[] args) throws Exception {
        System.out.println("begin ");
        CuratorFramework framework = CreateClient.createSimple(CONNECTIONSTRING);
        framework.start();
        ExampleClient exampleClient = new ExampleClient(framework, PATH, NAME1);
        exampleClient.start();
        ExampleClient exampleClient2 = new ExampleClient(framework, PATH, NAME2);
        exampleClient2.start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
