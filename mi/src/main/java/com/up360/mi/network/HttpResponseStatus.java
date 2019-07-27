package com.up360.mi.network;

public interface HttpResponseStatus {
    /**
     * 接口请求成功
     */
    int OK = 1;
    /**
     * json解析异常成功
     */
    int ERR_JSON = 601;
    /**
     * 默认错误号，表示服务器返回了error标签
     * 但是客户端不关心此error的内容，此时使用默认错误号
     */
    int ERR_DEFAULT = 0;

    /**
     * 网络连接失败、超时，http请求无响应
     */
    int ERR_RESPONSE = 100;


    /**
     * 服务器返回了error标签："<error>用户Token过期</error>"
     */
    int ERR_TOKEN_OVERDUE = 301;

    /*后台的返回码有0，1，2，3 其中0表示失败，1表示成功，2表示sessionkey无效，100表示退出*/
    int FAIL = 0;

    int SESSION_KEY_FAIL = 2;

    //请求次数超出
    int NUMBER_BEYOND = 4;

    //失败有跳转页面
    int ERR_INTENT_PAGE = 5;

    int SERVER_RESULT_6 = 6;

    int MSG_NOT_SHOW = 99;

}
