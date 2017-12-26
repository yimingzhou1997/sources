package com.lxit.dao.impl;

import com.lxit.dao.MediaDao;

public class DaoFactory {
    private static DaoFactory daoFactory = new DaoFactory();
  //�������ģʽ, ˽�й���,�����ṩ��ȡ�����Ķ����Ψһ�ӿ�,
    private DaoFactory(){
        
    }
    
    public static DaoFactory getInstance(){
        return daoFactory;
    }
    
    public static MediaDao getMediaDao(){
        return new MediaDaoImpl();
    }
}
