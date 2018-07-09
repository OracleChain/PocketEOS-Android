package com.oraclechain.pocketeos.base;

/**
 * Created by pocketEos on 2017/11/23.
 */

public class BaseUrl {
    /**
     * 生产环境服务器地址
     */
   /* public final static String HTTP_PACKETADDRESS = "http://59.110.162.106:9999/api_oc_redpacket/";
    public final static String HTTP_ADDRESS = "http://59.110.162.106:9999/api_oc_personal/v1.0.0/";
    public final static String HTTP_CHAIN_VOTE_ADDRESS = "http://59.110.162.106:8080/voteoraclechain/";
    //    public final static String HTTP_CHAIN_ADDRESS = "http://192.168.3.205:8888/v1/chain/";
    public final static String HTTP_CHAIN_ADDRESS = "http://59.110.162.106:8080/api_oc_blockchain-v1.3.0/";
    public final static String HTTP_ANSWER_ADDRESS = "http://59.110.162.106:8080/";
    public final static String HTTP_CANDY_ADDRESS = "http://59.110.162.106:9999/api_oc_pe_candy_system/";*/
    /**
     * 服务器地址
     */
   /* public final static String HTTP_PACKETADDRESS = "http://192.168.3.185:8089/api_oc_redpacket/";
    public final static String HTTP_ADDRESS = "http://47.105.99.78/api_oc_personal/v1.0.0/";
    public final static String HTTP_CHAIN_VOTE_ADDRESS = "http://47.105.99.78/voteoraclechain/";
    public final static String HTTP_CHAIN_ADDRESS = "http://47.105.99.78/api_oc_blockchain-v1.3.0/";
    public final static String HTTP_ANSWER_ADDRESS = "http://47.105.99.78/";
    public final static String HTTP_CANDY_ADDRESS = "http://47.105.99.78/api_oc_pe_candy_system/";*/
    /**
     * 正式环境服务器地址
     */
    public final static String HTTP_PACKETADDRESS = "http://192.168.3.185:8089/api_oc_redpacket/";
    public final static String HTTP_ADDRESS = "https://api.pocketeos.top/api_oc_personal/v1.0.0/";
     public final static String HTTP_CHAIN_VOTE_ADDRESS = "https://api.pocketeos.top/voteoraclechain/";
    public final static String HTTP_CHAIN_ADDRESS = "https://api.pocketeos.top/api_oc_blockchain-v1.3.0/";
    public final static String HTTP_ANSWER_ADDRESS = "https://api.pocketeos.top/eosaskanswer30/";
    public final static String HTTP_CANDY_ADDRESS = "https://api.pocketeos.top/api_oc_pe_candy_system/";
    // 获取关注列表
    public final static String HTTP_Getfollow_list = HTTP_ADDRESS + "follow_list";
    // 获取新闻列表
    public final static String HTTP_Get_news_list = HTTP_ADDRESS + "news_list";
    // 获取所有币
    public final static String HTTP_getAssetCategoryAll = HTTP_ADDRESS + "getAssetCategoryAll";
    // 获取DAPP推荐企业
    public final static String HTTP_dapp_commpany_list = HTTP_ADDRESS + "enterprise/intro";
    // 获取DAPP推荐应用
    public final static String HTTP_dapp_list = HTTP_ADDRESS + "enterprise/intro_all_app";
    // 获取企业推荐应用
    public final static String HTTP_commpany_dapp_list = HTTP_ADDRESS + "enterprise/intro_app";
    // 获取验证码
    public final static String HTTP_Get_code = HTTP_ADDRESS + "message/send";
    // 绑定手机号
    public final static String HTTP_bind_phone_number = HTTP_ADDRESS + "user/bind_phone_number";
    // 验证获取验证码是否正确
    public final static String HTTP_Get_code_auth = HTTP_ADDRESS + "message/auth";
    // 解绑qq
    public final static String HTTP_unbindQQ = HTTP_ADDRESS + "user/unbindQQ";
    // 绑定qq
    public final static String HTTP_bindQQ = HTTP_ADDRESS + "user/bindqq";
    // 解绑微信
    public final static String HTTP_unbindWechat = HTTP_ADDRESS + "user/unbindWechat";
    // 绑定微信
    public final static String HTTP_bindwechat = HTTP_ADDRESS + "user/bindwechat";
    // 注册EOS账号
    public final static String HTTP_eos_register = HTTP_CHAIN_ADDRESS + "create_account";
    //备份EOS账号至服务器
    public final static String HTTP_add_new_eos = HTTP_ADDRESS + "user/add_new_eos";
    // 获取EOS账号信息
    public final static String HTTP_eos_get_account = HTTP_CHAIN_ADDRESS + "get_account_asset";
    // 获取链上信息
    public final static String HTTP_eos_get_table = HTTP_CHAIN_ADDRESS + "get_table_rows";
    // 获取资产汇率
    public final static String HTTP_eos_get_coin_rate = HTTP_CHAIN_ADDRESS + "get_rate";
    // 获取走势图
    public final static String HTTP_get_sparklines = HTTP_CHAIN_ADDRESS + "get_sparklines";
    // 上传用户头像
    public final static String HTTP_headImgUploada = HTTP_ADDRESS + "user/headImgUpload";
    // 更改钱包名字
    public final static String HTTP_updateName = HTTP_ADDRESS + "user/updateName";
    // 获取Pe富豪榜
    public final static String HTTP_pelist = HTTP_ADDRESS + "top/getPersonal";
    // 获取企业富豪榜
    public final static String HTTP_commpanylist = HTTP_ADDRESS + "top/getEnterprise";
    // 获取好友钱包详情信息
    public final static String HTTP_FriendsDetaislist = HTTP_ADDRESS + "user/getEosAccount";
    // 添加关注
    public final static String HTTP_add_follow = HTTP_ADDRESS + "add_follow";
    // 取消关注
    public final static String HTTP_cancel_follow = HTTP_ADDRESS + "cancel_follow";
    // 查询是否关注
    public final static String HTTP_is_follow = HTTP_ADDRESS + "isFollowRecord";
    // 获取消息中心
    public final static String HTTP_getMagList = HTTP_ADDRESS + "msg/getMagList";
    // 提交意见
    public final static String HTTP_post_suggestion = HTTP_ADDRESS + "user/add_infoFeedback";
    // 获取意见列表
    public final static String HTTP_get_suggestion_list = HTTP_ADDRESS + "user/get_infoFeedback";
    // 获取区块链状态
    public final static String HTTP_get_chain_info = HTTP_CHAIN_ADDRESS + "get_info";
    // 交易JSON序列化
    public final static String HTTP_get_abi_json_to_bin = HTTP_CHAIN_ADDRESS + "abi_json_to_bin";
    // 获取keys
    public final static String HTTP_get_required_keys = HTTP_CHAIN_ADDRESS + "get_required_keys";
    // 发起交易
    public final static String HTTP_push_transaction = HTTP_CHAIN_ADDRESS + "push_transaction";
    // 获取交易历史
    public final static String HTTP_get_transaction_history ="http://history.pocketeos.top/VX/GetActions";
    // 获取区块链账号信息
    public final static String HTTP_get_chain_account_info = HTTP_CHAIN_ADDRESS + "get_account";
    // 设置主账号
    public final static String HTTP_set_mian_account = HTTP_ADDRESS + "user/toggleEosMain";
    // 保护隐私
    public final static String HTTP_set_policy_account = HTTP_ADDRESS + "user/update_secret";
    // 系统设置
    public final static String HTTP_get_system_info = HTTP_ADDRESS + "system/getInfo";
    // app更新
    public final static String HTTP_get_app_info = HTTP_ADDRESS + "get_last_info";
    // 获取有问必答问题列表
    public final static String HTTP_GetAsks = HTTP_ANSWER_ADDRESS + "GetAsksJson";
    // 新建红包
    public final static String HTTP_send_red_packet = HTTP_PACKETADDRESS + "send_red_packet";
    // 获取用户的红包历史
    public final static String getHTTP_get_red_packet_history = HTTP_PACKETADDRESS + "select_user_red_packet";
    // 获取单个红包发送详情
    public final static String getHTTP_get_red_packet_details_history = HTTP_PACKETADDRESS + "selectRedPacketRecord";
    // 验证红包信息
    public final static String getHTTP_get_red_packet_auth_message = HTTP_PACKETADDRESS + "auth_message";
    // 获取糖果积分
    public final static String getHTTP_get_candy_score = HTTP_CANDY_ADDRESS + "get_candy_score";
    // 获取热门权益
    public final static String getHTTP_get_all_exchange = HTTP_CANDY_ADDRESS + "get_all_exchange";
    // 获取任务列表
    public final static String getHTTP_get_user_task = HTTP_CANDY_ADDRESS + "get_user_task";
    // 通过公钥获取账号
    public final static String getHTTP_GetAccounts = HTTP_CHAIN_VOTE_ADDRESS + "GetAccounts";
    // 获取BP节点列表
    public final static String getHTTP_GetBpJson = HTTP_CHAIN_VOTE_ADDRESS + "GetBpJson";
    // 获取账号投票信息
    public final static String getHTTP_GetMyVoteInfo = HTTP_CHAIN_VOTE_ADDRESS + "GetMyVoteInfo";
    // 获取权重
    public final static String getHTTP_GetNowVoteWeight = HTTP_CHAIN_VOTE_ADDRESS + "GetNowVoteWeight";
    // 通知完成投票
    public final static String getHTTP_complete_task = HTTP_CANDY_ADDRESS + "complete_task";
}
