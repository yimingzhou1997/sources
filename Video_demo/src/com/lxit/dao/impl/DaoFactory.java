package com.lxit.dao.impl;

import com.lxit.dao.MediaDao;

public class DaoFactory {
    private static DaoFactory daoFactory = new DaoFactory();
  //单例设计模式, 私有构造,对外提供获取创建的对象的唯一接口,
    private DaoFactory(){
        
    }
    
    public static DaoFactory getInstance(){
        return daoFactory;
    }
    
    public static MediaDao getMediaDao(){
        return new MediaDaoImpl();
    }
}
