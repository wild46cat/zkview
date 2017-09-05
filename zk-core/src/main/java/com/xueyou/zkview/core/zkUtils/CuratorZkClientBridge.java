package com.xueyou.zkview.core.zkUtils;

/**
 * Created by wuxueyou on 2017/8/26.
 */

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CuratorZkClientBridge {
    private final CuratorFramework curator;
    private final AtomicReference<CuratorListener> listener = new AtomicReference<CuratorListener>(null);

    /**
     * @param curator Curator instance to bridge
     */
    public CuratorZkClientBridge(CuratorFramework curator)
    {
        this.curator = curator;
    }

    /**
     * Return the client
     *
     * @return client
     */
    public CuratorFramework getCurator()
    {
        return curator;
    }

    public void connect(final Watcher watcher)
    {
        if ( watcher != null )
        {
            CuratorListener     localListener = new CuratorListener()
            {
                public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
                {
                    if ( event.getWatchedEvent() != null )
                    {
                        watcher.process(event.getWatchedEvent());
                    }
                }
            };
            curator.getCuratorListenable().addListener(localListener);
            listener.set(localListener);

            try
            {
                BackgroundCallback callback = new BackgroundCallback()
                {
                    public void processResult(CuratorFramework client, CuratorEvent event) throws Exception
                    {
                        WatchedEvent        fakeEvent = new WatchedEvent(Watcher.Event.EventType.None, curator.getZookeeperClient().isConnected() ? Watcher.Event.KeeperState.SyncConnected : Watcher.Event.KeeperState.Disconnected, null);
                        watcher.process(fakeEvent);
                    }
                };
                curator.checkExists().inBackground(callback).forPath("/foo");
            }
            catch ( Exception e )
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void close() throws InterruptedException
    {
        // NOTE: the curator instance is NOT closed here

        CuratorListener localListener = listener.getAndSet(null);
        if ( localListener != null )
        {
            curator.getCuratorListenable().removeListener(localListener);
        }
    }

    public String create(String path, byte[] data, CreateMode mode) throws KeeperException, InterruptedException
    {
        try
        {
            return curator.create().withMode(mode).forPath(path, data);
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return null;    // will never execute
    }

    public void delete(String path) throws InterruptedException, KeeperException
    {
        try
        {
            curator.delete().forPath(path);
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
    }

    public boolean exists(String path, boolean watch) throws KeeperException, InterruptedException
    {
        try
        {
            return watch ? (curator.checkExists().watched().forPath(path) != null) : (curator.checkExists().forPath(path) != null);
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return false;   // will never execute
    }

    public List<String> getChildren(String path, boolean watch) throws KeeperException, InterruptedException
    {
        try
        {
            return watch ? curator.getChildren().watched().forPath(path) : curator.getChildren().forPath(path);
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return null;   // will never execute
    }

    public byte[] readData(String path, Stat stat, boolean watch) throws KeeperException, InterruptedException
    {
        try
        {
            if ( stat != null )
            {
                return watch ? curator.getData().storingStatIn(stat).watched().forPath(path) : curator.getData().storingStatIn(stat).forPath(path);
            }
            else
            {
                return watch ? curator.getData().watched().forPath(path) : curator.getData().forPath(path);
            }
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return null;   // will never execute
    }

    public void writeData(String path, byte[] data, int expectedVersion) throws KeeperException, InterruptedException
    {
        writeDataReturnStat(path, data, expectedVersion);
    }

    public Stat writeDataReturnStat(String path, byte[] data, int expectedVersion) throws KeeperException, InterruptedException {
        try
        {
            curator.setData().withVersion(expectedVersion).forPath(path, data);
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return null; // will never execute
    }

    public ZooKeeper.States getZookeeperState()
    {
        try
        {
            return curator.getZookeeperClient().getZooKeeper().getState();
        }
        catch ( Exception e )
        {
            throw new RuntimeException(e);
        }
    }

    public long getCreateTime(String path) throws KeeperException, InterruptedException
    {
        try
        {
            Stat            stat = curator.checkExists().forPath(path);
            return (stat != null) ? stat.getCtime() : 0;
        }
        catch ( Exception e )
        {
            adjustException(e);
        }
        return 0;
    }

    public String getServers()
    {
        return curator.getZookeeperClient().getCurrentConnectionString();
    }

    private void adjustException(Exception e) throws KeeperException, InterruptedException
    {
        if ( e instanceof KeeperException )
        {
            throw (KeeperException)e;
        }

        if ( e instanceof InterruptedException )
        {
            throw (InterruptedException)e;
        }

        throw new RuntimeException(e);
    }

}
