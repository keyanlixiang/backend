package com.example.keyan.tongxun;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class WebSocketMapUtil {

    //在线的连接
    private static ConcurrentMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    //全部的连接
    private static ConcurrentMap<String, Session> AllsessionMap=new ConcurrentHashMap<>();

    /*
     *
     * 加入连接用户map
     * */
    public static void put (String key, Session session){
        sessionMap.put(key, session);
        AllsessionMap.put(key,session);
    }

    /*
     *
     * 获取连接
     * */
    public static Session get (String key){
        return sessionMap.get(key);
    }

    /*
     *
     * 移除连接
     * */
    public static void remove (String key){
        sessionMap.remove(key);
    }

    /*
     *
     * 获取map所有值
     * */
    public static Collection getAllValues (){
        Collection values = sessionMap.values();
        return values;
    }

    /*
     *
     * 获取map所有的key
     * */
    public static List<String> getAllKey(){
        List<String> keyList = new ArrayList<>();
        for (String key : AllsessionMap.keySet()){
            keyList.add(key);
        }
        return keyList;
    }

    public static List<String> getOnlineKey(){
        List<String> keyList = new ArrayList<>();
        for (String key : sessionMap.keySet()){
            keyList.add(key);
        }
        return keyList;
    }
}


