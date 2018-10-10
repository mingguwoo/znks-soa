package com.sh.znks.service.impl.job;

import com.sh.znks.dao.UserDao;
import com.sh.znks.service.job.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询礼物过期时间已到的记录置为无效
 * 每小时执行一次
 * Created by wuminggu on 2018/9/27.
 */
public class ExpiryJobServiceImpl implements JobService {
    private final static Logger log = LoggerFactory.getLogger(ExpiryJobServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void execute() {
        try{
            userDao.updateExpirySignRecord();
        } catch (Exception e) {
            log.error("L27_ExpiryJobServiceImpl e:", e);
            return;
        }
    }
}
