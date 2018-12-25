package com.taireum;

import java.util.HashMap;
import java.util.Objects;

public class NodeInfo {
    String enode;
    String enr;
    String id;
    String ip;
    String listenAddr;
    String name;
    HashMap<String, String> ports;
    HashMap<String, Object> protocols;
    public NodeInfo(){

    }
    public NodeInfo(String enode, String enr, String id, String ip, String listenAddr, String name, HashMap<String, String> ports, HashMap<String, Object> protocols) {
        this.enode = enode;
        this.enr = enr;
        this.id = id;
        this.ip = ip;
        this.listenAddr = listenAddr;
        this.name = name;
        this.ports = ports;
        this.protocols = protocols;
    }

    public String getEnode() {
        return enode;
    }

    public void setEnode(String enode) {
        this.enode = enode;
    }

    public String getEnr() {
        return enr;
    }

    public void setEnr(String enr) {
        this.enr = enr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getListenAddr() {
        return listenAddr;
    }

    public void setListenAddr(String listenAddr) {
        this.listenAddr = listenAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, String> getPorts() {
        return ports;
    }

    public void setPorts(HashMap<String, String> ports) {
        this.ports = ports;
    }

    public HashMap<String, Object> getProtocols() {
        return protocols;
    }

    public void setProtocols(HashMap<String, Object> protocols) {
        this.protocols = protocols;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeInfo nodeInfo = (NodeInfo) o;
        return Objects.equals(enode, nodeInfo.enode) &&
                Objects.equals(enr, nodeInfo.enr) &&
                Objects.equals(id, nodeInfo.id) &&
                Objects.equals(ip, nodeInfo.ip) &&
                Objects.equals(listenAddr, nodeInfo.listenAddr) &&
                Objects.equals(name, nodeInfo.name) &&
                Objects.equals(ports, nodeInfo.ports) &&
                Objects.equals(protocols, nodeInfo.protocols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enode, enr, id, ip, listenAddr, name, ports, protocols);
    }

    @Override
    public String toString() {
        return "NodeInfo{" +
                "enode='" + enode + '\'' +
                ", enr='" + enr + '\'' +
                ", id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", listenAddr='" + listenAddr + '\'' +
                ", name='" + name + '\'' +
                ", ports=" + ports +
                ", protocols=" + protocols +
                '}';
    }
}
