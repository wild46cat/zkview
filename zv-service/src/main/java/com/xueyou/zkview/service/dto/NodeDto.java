package com.xueyou.zkview.service.dto;

/**
 * Created by wuxueyou on 2017/9/17.
 */
public class NodeDto {
    String nodePath;
    String nodeValue;

    public NodeDto() {
    }

    public NodeDto(String nodePath, String nodeValue) {
        this.nodePath = nodePath;
        this.nodeValue = nodeValue;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }
}
