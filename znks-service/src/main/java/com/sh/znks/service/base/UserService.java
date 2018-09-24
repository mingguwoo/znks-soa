package com.sh.znks.service.base;

import com.sh.znks.common.result.ResultResponse;
import com.sh.znks.domain.register.User;
import com.sh.znks.domain.user.ExpertUser;
import com.sh.znks.domain.user.GeneralUser;
import com.sh.znks.domain.user.WxUser;

/**
 * Created by wuminggu on 2018/5/9.
 */
public interface UserService {
    /**
     * ��ȡ��¼̬token
     * @param authorizationCode
     * @return
     */
    ResultResponse getxAuthorizationGenLoginInfo(String authorizationCode);

    /**
     * ΢����Ȩ���ȡ�û���Ϣע�ᵽDB��
     * @param user
     * @return
     */
    ResultResponse registerWxUser(WxUser user);

    /**
     * �˳���¼(΢��)
     * @return
     */
    ResultResponse wxLogoutGen();

    /**
     * �û�ǩ��
     * @param unionId
     * @return
     */
    ResultResponse signIn(String unionId);
}
