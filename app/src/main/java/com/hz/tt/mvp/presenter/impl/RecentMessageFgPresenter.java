package com.hz.tt.mvp.presenter.impl;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.InBeanDao;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.model.entity.request.UpInRecordRequest;
import com.hz.tt.mvp.model.entity.response.ImageUpResponse;
import com.hz.tt.mvp.model.entity.response.UpInRecordResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.util.UIUtils;
import com.hz.tt.util.voice.TextToSpeechUtil;
import com.hz.tt.widget.CustomDialog;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;


public class RecentMessageFgPresenter extends BasePresenter<IRecentMessageFgView> {
    TextToSpeechUtil speechUtil;
    private String bitmapPath;
    private Context context;
    private List<InBean> datas= new ArrayList<>();
    private List<InBean> removeDatas= new ArrayList<>();
    private int listsize;
    //    private List<Conversation> mData = new ArrayList<>();
    private LQRAdapterForRecyclerView<InBean> mAdapter;
    private int mUnreadCountTotal = 0;
    //    private LQRNineGridImageViewAdapter mNgivAdapter = new LQRNineGridImageViewAdapter<GroupMember>() {
//        @Override
//        protected void onDisplayImage(Context context, ImageView imageView, GroupMember groupMember) {
//            Glide.with(context).load(groupMember.getPortraitUri()).centerCrop().into(imageView);
//        }
//    };
    private CustomDialog mConversationMenuDialog;

    public RecentMessageFgPresenter(BaseActivity context) {
        super(context);
        this.context = context;
//        speechUtil = new TextToSpeechUtil(context);
    }

    public void getConversations() {
//        loadData();
//        this.datas=datas;
        setAdapter(datas);
    }

//    private void loadData() {
//        RongIMClient.getInstance().getConversationList(new RongIMClient.ResultCallback<List<Conversation>>() {
//            @Override
//            public void onSuccess(List<Conversation> conversations) {
//                if (conversations != null && conversations.size() > 0) {
//                    mData.clear();
//                    mData.addAll(conversations);
//                    filterData(mData);
//                }
//            }
//
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                LogUtils.e("加载最近会话失败：" + errorCode);
//            }
//        });
//    }

//    private void filterData(List<Conversation> conversations) {
//        for (int i = 0; i < conversations.size(); i++) {
//            Conversation item = conversations.get(i);
//            //其他消息会话不显示（比如：系统消息）
//            if (!(item.getConversationType() == Conversation.ConversationType.PRIVATE || item.getConversationType() == Conversation.ConversationType.GROUP)) {
//                conversations.remove(i);
//                i--;
//                continue;
//            }
//            if (item.getConversationType() == Conversation.ConversationType.GROUP) {
//                List<GroupMember> groupMembers = DBManager.getInstance().getGroupMembers(item.getTargetId());
//                if (groupMembers == null || groupMembers.size() == 0) {
//                    DBManager.getInstance().deleteGroupsById(item.getTargetId());//删除没有群成员的群
//                    conversations.remove(i);
//                    i--;
//                }
//            } else if (item.getConversationType() == Conversation.ConversationType.PRIVATE) {
//                if (!DBManager.getInstance().isMyFriend(item.getTargetId())) {
//                    conversations.remove(i);
//                    i--;
//                }
//            }
//        }
//        mUnreadCountTotal = 0;
//        for (Conversation conversation : conversations) {
//            mUnreadCountTotal += conversation.getUnreadMessageCount();
//        }
//        updateTotalUnreadView();
//        if (mAdapter != null)
//            mAdapter.notifyDataSetChangedWrapper();
//    }

    private void updateTotalUnreadView() {
        if (mUnreadCountTotal > 0) {
            ((MainActivity) mContext).getTvMessageCount().setText(mUnreadCountTotal + "");
            ((MainActivity) mContext).getTvMessageCount().setVisibility(View.VISIBLE);
            ((MainActivity) mContext).setToolbarTitle(UIUtils.getString(R.string.app_name) + "(" + mUnreadCountTotal + ")");
        } else {
            ((MainActivity) mContext).getTvMessageCount().setVisibility(View.GONE);
            ((MainActivity) mContext).setToolbarTitle(UIUtils.getString(R.string.app_name));
        }
    }

    private void setAdapter(List<InBean> datas) {
        if (mAdapter == null) {
            mAdapter = new LQRAdapterForRecyclerView<InBean>(mContext, datas, R.layout.iten_in) {
                @Override
                public void convert(LQRViewHolderForRecyclerView helper, InBean item, int position) {
                    TextView danHao = helper.getView(R.id.danHao);
                    TextView states = helper.getView(R.id.states);
                    TextView num = helper.getView(R.id.num);
                    ImageView delete_img = helper.getView(R.id.delete_img);

                    danHao.setText(item.getCode());
                    String status=item.getStatus();
                    if (status.equals("未上传")){
                        delete_img.setVisibility(View.VISIBLE);
                    }
                    states.setText(status);
                    int size=datas.size();
                    num.setText(size-position+"");
                }
            };
            getView().getRvRecentMessage().setAdapter(mAdapter);
        }

    }

    public void addRecord(String bitmapPath){
        this.bitmapPath=bitmapPath;
        if (datas!=null){
            InBean bean=new InBean();
            IRecentMessageFgView view=getView();
            String Person=view.getPerson().getText().toString().trim();
            String Time=view.getTime().getText().toString().trim();
            String Type=view.getType().getText().toString().trim();
            String Country=view.getCountry().getText().toString().trim();
            String Birthday=view.getBirthday().getText().toString().trim();
            String Capacity=view.getCapacity().getText().toString().trim();
            String Year=view.getYear().getText().toString().trim();
            String Num=view.getNum().getText().toString().trim();
            String Location=view.getLocation().getText().toString().trim();
            String Code=view.getCode().getText().toString().trim();
            if (!Time.equals("")
                    &&!Type.equals("")
                    &&!Country.equals("")
                    &&!Birthday.equals("")
                    &&!Capacity.equals("")
                    &&!Year.equals("")
                    &&!Num.equals("")
                    &&!Location.equals("")
                    &&!Code.equals("")
                    &&bitmapPath!=null&&!bitmapPath.equals("")){
                bean.setPerson(Person);
                bean.setTime(Time);
                bean.setType(Type);
                bean.setCountry(Country);
                bean.setBirthday(Birthday);
                bean.setCapacity(Capacity);
                bean.setYear(Year);
                bean.setNumber(Num);
                bean.setLocation(Location);
                bean.setCode(Code);
                bean.setImgstr(bitmapPath);
                bean.setStatus("未上传");
                datas.add(0,bean);
                mAdapter.notifyDataSetChanged();

                InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
                inBeanDao.insert(bean);
            }else{
                UIUtils.showToast("请检查数据完整性！");
            }
        }
    }

    public void clearYesUp(){
        datas.removeAll(removeDatas);
    }
    public void upRecordImg(){
        if (datas!=null&&datas.size()!=0){
            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
            InBean bean=datas.get(0);
            OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
            okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
//                mContext.hideWaitingDialog();
                if (newstr1.equals("数据请求失败")) {
                    mContext.speechUtil.speakXunFei("数据请求失败");
                    return;
                }
                Gson gson = new Gson();
                ImageUpResponse response = null;
                try {
                    response = gson.fromJson(newstr1,ImageUpResponse.class);
                } catch (JsonSyntaxException e) {
                    mContext.hideWaitingDialog();
                    mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                    e.printStackTrace();
                    return;
                }
                String code = response.getCode();
                if (code.equals("1000")) {
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                    String imgUri=response.getData();
                    try {
                        JSONObject jsonObject = new JSONObject(imgUri);
                        String imgtrue=jsonObject.getString("url");
                        datas.get(0).setImgstr(imgtrue);
                        mContext.hideWaitingDialog();
                        upRecord();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    mContext.speechUtil.speakXunFei(code);
                    mContext.hideWaitingDialog();
                    return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
                }
            }));

//            上传图片
            File file = new File(bitmapPath);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("file", file.getName(), okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/png"), file));
            okHttpUtils.myEnqueue("http://10.38.1.40:8080/Wine/upload",builder.build());
//            mAdapter.notifyDataSetChanged();
        }else{
            datas.addAll(removeDatas);
            mAdapter.notifyDataSetChanged();
        }
    }
    public void upRecord(){
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            if (newstr1.equals("数据请求失败")) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            UpInRecordResponse response = null;
            try {
                response = gson.fromJson(newstr1,UpInRecordResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.speechUtil.speakXunFei("服务器异常");
                mContext.hideWaitingDialog();
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
                mContext.hideWaitingDialog();
                InBean inBean=datas.get(0);
                inBean.setStatus("已上传");
                InBeanDao inBeanDao = MyApp.getInstances().getDaoSession().getInBeanDao();
                inBeanDao.update(inBean);
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                removeDatas.add(datas.get(0));
                datas.remove(0);
                upRecordImg();
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));

        okHttpUtils.myEnqueue(new UpInRecordRequest(datas).getUrl(),null);
    }

    public void speech(String toast) {
//        speechUtil.speakXunFei(toast);
    }
}
