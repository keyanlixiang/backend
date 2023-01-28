package com.example.keyan.tongxun;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WebSocketRoomMapUtil {

    //在线的连接
    private static ConcurrentMap<String, Session> RoomSessionMap = new ConcurrentHashMap<>();

    //全部的连接
    private static ConcurrentMap<String, Session> AllRoomSessionMap =new ConcurrentHashMap<>();

    /*
     *
     * 加入连接用户map
     * */
    public static void put (String key, Session session){
        RoomSessionMap.put(key, session);
        AllRoomSessionMap.put(key,session);
    }

    /*
     *
     * 获取连接
     * */
    public static Session get (String key){
        return RoomSessionMap.get(key);
    }

    /*
     *
     * 移除连接
     * */
    public static void remove (String key){
        RoomSessionMap.remove(key);
    }

    /*
     *
     * 获取map所有值
     * */
    public static Collection getAllValues (){
        Collection values = RoomSessionMap.values();
        return values;
    }

    /*
     *
     * 获取map所有的key
     * */
    public static List<String> getAllKey(){
        List<String> keyList = new ArrayList<>();
        for (String key : AllRoomSessionMap.keySet()){
            keyList.add(key);
        }
        return keyList;
    }

    public static List<String> getOnlineKey(){
        List<String> keyList = new ArrayList<>();
        for (String key : RoomSessionMap.keySet()){
            keyList.add(key);
        }
        return keyList;
    }
}
