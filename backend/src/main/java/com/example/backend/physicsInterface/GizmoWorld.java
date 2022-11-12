package com.example.backend.physicsInterface;

import java.util.List;

/**
 * 地图管理实现的接口
 * add 在地图上添加一个成员
 * delete删除一个成员
 * getAll返回所有成员
 * get根据位置获得一个成员
 */
public interface GizmoWorld {
    void add(GizmoObject e);
    void delete(Integer id);
    List<GizmoObject> getAll();
    GizmoObject get(Integer id);
}
