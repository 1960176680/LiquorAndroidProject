package com.hz.tt.api;

import com.hz.tt.mvp.model.entity.response.CheckPhoneResponse;
import com.hz.tt.mvp.model.entity.response.RegisterResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @描述 server端api
 */

public interface MyApi {

    public static final String BASE_URL = "http://10.38.1.40:8080/SpringSpringmvcMybatis/";


    /**
     msg_type：WINECELLAR_MANAGER_LOGIN
    logistics_interface：{\"userName\":\"123456\",\"password\":\"123456\"}
     logistics_interface：请求报文json
        msg_type：请求接口
        data_digest：加密字符串（base64（md5（logistics_interface+appsecret）））
        appkey：密钥
                */
    //登录

    @GET("api?msg_type=WINECELLAR_MANAGER_LOGIN&data_digest=NzE5ZDQwNmU1NTk4NzM3Zjk2YjliMjJmNzAxMjkwMmQ=&logistics_interface=%7B%22userName%22%3A%22123456%22%2C%22password%22%3A%22123456%22%7D&appkey=123456")
    Observable<String> login();




    //检查手机是否被注册
    @POST("user/check_phone_available")
    Observable<CheckPhoneResponse> checkPhoneAvailable(@Body RequestBody body);

//    //发送验证码
//    @POST("user/send_code")
//    Observable<SendCodeResponse> sendCode(@Body RequestBody body);
//
//    //验证验证码是否正确(必选先用手机号码调sendcode)
//    @POST("user/verify_code")
//    Observable<VerifyCodeResponse> verifyCode(@Body RequestBody body);
//
    //注册
    @POST("user/register")
    Observable<RegisterResponse> register(@Body RequestBody body);

//    //登录
//    @POST("user/login")
//    Observable<LoginResponse> login(@Body RequestBody body);
//
//    //获取 token 前置条件需要登录   502 坏的网关 测试环境用户已达上限
//    @GET("user/get_token")
//    Observable<GetTokenResponse> getToken();
//
//    //设置自己的昵称
//    @POST("user/set_nickname")
//    Observable<SetNameResponse> setName(@Body RequestBody body);
//
//    //设置用户头像
//    @POST("user/set_portrait_uri")
//    Observable<SetPortraitResponse> setPortrait(@Body RequestBody body);
//
//    //当前登录用户通过旧密码设置新密码  前置条件需要登录才能访问
//    @POST("user/change_password")
//    Observable<ChangePasswordResponse> changePassword(@Body RequestBody body);
//
//    //通过手机验证码重置密码
//    @POST("user/reset_password")
//    Observable<RestPasswordResponse> restPassword(@Body RequestBody body);
//
//    //根据 id 去服务端查询用户信息
//    @GET("user/{userid}")
//    Observable<GetUserInfoByIdResponse> getUserInfoById(@Path("userid") String userid);
//
//    //通过国家码和手机号查询用户信息
//    @GET("user/find/{region}/{phone}")
//    Observable<GetUserInfoByPhoneResponse> getUserInfoFromPhone(@Path("region") String region, @Path("phone") String phone);
//
//    //发送好友邀请
//    @POST("friendship/invite")
//    Observable<FriendInvitationResponse> sendFriendInvitation(@Body RequestBody body);
//
//    //获取发生过用户关系的列表
//    @GET("friendship/all")
//    Observable<UserRelationshipResponse> getAllUserRelationship();
//
//    //根据userId去服务器查询好友信息
//    @GET("friendship/{userid}/profile")
//    Observable<GetFriendInfoByIDResponse> getFriendInfoByID(@Path("userid") String userid);
//
//    //同意对方好友邀请
//    @POST("friendship/agree")
//    Observable<AgreeFriendsResponse> agreeFriends(@Body RequestBody body);
//
//    //删除好友
//    @POST("friendship/delete")
//    Observable<DeleteFriendResponse> deleteFriend(@Body RequestBody body);
//
//    //设置好友的备注名称
//    @POST("friendship/set_display_name")
//    Observable<SetFriendDisplayNameResponse> setFriendDisplayName(@Body RequestBody body);
//
//    //获取黑名单
//    @GET("user/blacklist")
//    Observable<GetBlackListResponse> getBlackList();
//
//    //加入黑名单
//    @POST("user/add_to_blacklist")
//    Observable<AddToBlackListResponse> addToBlackList(@Body RequestBody body);
//
//    //移除黑名单
//    @POST("user/remove_from_blacklist")
//    Observable<RemoveFromBlackListResponse> removeFromBlackList(@Body RequestBody body);
//
//    //创建群组
//    @POST("group/create")
//    Observable<CreateGroupResponse> createGroup(@Body RequestBody body);
//
//    //创建者设置群组头像
//    @POST("group/set_portrait_uri")
//    Observable<SetGroupPortraitResponse> setGroupPortrait(@Body RequestBody body);
//
//    //获取当前用户所属群组列表
//    @GET("user/groups")
//    Observable<GetGroupResponse> getGroups();
//
//    //根据 群组id 查询该群组信息   403 群组成员才能看
//    @GET("group/{groupId}")
//    Observable<GetGroupInfoResponse> getGroupInfo(@Path("groupId") String groupId);
//
//    //根据群id获取群组成员
//    @GET("group/{groupId}/members")
//    Observable<GetGroupMemberResponse> getGroupMember(@Path("groupId") String groupId);
//
//    //当前用户添加群组成员
//    @POST("group/add")
//    Observable<AddGroupMemberResponse> addGroupMember(@Body RequestBody body);
//
//    //创建者将群组成员提出群组
//    @POST("group/kick")
//    Observable<DeleteGroupMemberResponse> deleGroupMember(@Body RequestBody body);
//
//    //创建者更改群组昵称
//    @POST("group/rename")
//    Observable<SetGroupNameResponse> setGroupName(@Body RequestBody body);
//
//    //用户自行退出群组
//    @POST("group/quit")
//    Observable<QuitGroupResponse> quitGroup(@Body RequestBody body);
//
//    //创建者解散群组
//    @POST("group/dismiss")
//    Observable<QuitGroupResponse> dissmissGroup(@Body RequestBody body);
////    Observable<DismissGroupResponse> dissmissGroup(@Body RequestBody body);
//
//    //修改自己的当前的群昵称
//    @POST("group/set_display_name")
//    Observable<SetGroupDisplayNameResponse> setGroupDisplayName(@Body RequestBody body);
//
//    //当前用户加入某群组
//    @POST("group/join")
//    Observable<JoinGroupResponse> JoinGroup(@Body RequestBody body);
//
//    //获取默认群组 和 聊天室
//    @GET("misc/demo_square")
//    Observable<DefaultConversationResponse> getDefaultConversation();
//
//    //根据一组ids 获取 一组用户信息
//    @GET("user/batch?{params}")
//    Observable<GetUserInfosResponse> getUserInfos(@Path("params") String params);
//
//    //得到七牛的token
//    @GET("user/get_image_token")
//    Observable<QiNiuTokenResponse> getQiNiuToken();
//
//    //获取版本信息
//    @GET("misc/client_version")
//    Observable<VersionResponse> getClientVersion();
//
//    //
//    @GET("user/sync/{version}")
//    Observable<SyncTotalDataResponse> syncTotalData(@Path("version") String version);
//
//
//    //下载图片
//    @GET
//    Observable<ResponseBody> downloadPic(@Url String url);

}
