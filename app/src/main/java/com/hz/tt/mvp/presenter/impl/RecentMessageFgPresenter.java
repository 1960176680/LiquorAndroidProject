package com.hz.tt.mvp.presenter.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hz.tt.R;
import com.hz.tt.api.okHttpUtils.NetConstant;
import com.hz.tt.api.okHttpUtils.OkHttpUtils;
import com.hz.tt.app.MyApp;
import com.hz.tt.greendao.gen.InBeanDao;
import com.hz.tt.mvp.model.entity.CategoryBean;
import com.hz.tt.mvp.model.entity.CountryBean;
import com.hz.tt.mvp.model.entity.InBean;
import com.hz.tt.mvp.model.entity.OriginBean;
import com.hz.tt.mvp.model.entity.QueryBean;
import com.hz.tt.mvp.model.entity.VolumeBean;
import com.hz.tt.mvp.model.entity.request.QueryRequest;
import com.hz.tt.mvp.model.entity.request.UpInRecordRequest;
import com.hz.tt.mvp.model.entity.response.ImageUpResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponse;
import com.hz.tt.mvp.model.entity.response.QueryResponseSingle;
import com.hz.tt.mvp.model.entity.response.UpInRecordResponse;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.util.UIUtils;
import com.hz.tt.widget.CustomDialog;
import com.lqr.adapter.LQRAdapterForRecyclerView;
import com.lqr.adapter.LQRViewHolderForRecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;

import static com.hz.tt.app.MyApp.getInstances;


public class RecentMessageFgPresenter extends BasePresenter<IRecentMessageFgView> {

    private Context context;
    private List<InBean> datas= new ArrayList<>();
    private List<InBean> removeDatas= new ArrayList<>();
    private int listsize;
    private LQRAdapterForRecyclerView<InBean> mAdapter;
    private int mUnreadCountTotal = 0;
    private CustomDialog mConversationMenuDialog;

    public RecentMessageFgPresenter(BaseActivity context) {
        super(context);
        this.context = context;
    }

    public void getConversations() {
//        loadData();
//        this.datas=datas;
        setAdapter(datas);
    }

    public List<String> loadCategoryData(){
        List<CategoryBean> beanList=MyApp.getInstances().getDaoSession().getCategoryBeanDao().queryBuilder().build().list();
        List<String> list=new ArrayList<>();
        for (CategoryBean bean:beanList){
            list.add(bean.getTypeName());
        }
        return list;
    }
    public List<String> loadCountryData(){
        List<CountryBean> beanList=MyApp.getInstances().getDaoSession().getCountryBeanDao().queryBuilder().build().list();
        List<String> list=new ArrayList<>();
        for (CountryBean bean:beanList){
            list.add(bean.getTypeName());
        }
        return list;
    }
    public List<String> loadOriginData(){
        List<OriginBean> beanList=MyApp.getInstances().getDaoSession().getOriginBeanDao().queryBuilder().build().list();
        List<String> list=new ArrayList<>();
        for (OriginBean bean:beanList){
            list.add(bean.getTypeName());
        }
        return list;
    }
    public List<String> loadVolumeData(){
        List<VolumeBean> beanList=MyApp.getInstances().getDaoSession().getVolumeBeanDao().queryBuilder().build().list();
        List<String> list=new ArrayList<>();
        for (VolumeBean bean:beanList){
            list.add(bean.getTypeName());
        }
        return list;
    }
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
                        states.setTextColor(Color.RED);
                        delete_img.setVisibility(View.VISIBLE);
                    }else {
                        states.setTextColor(Color.GREEN);
                        delete_img.setVisibility(View.INVISIBLE);
                    }
                    states.setText(status);
                    delete_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle("注意:删除操作");
                            builder.setMessage("确定要删除吗？");
                            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    InBean inBean=datas.get(position);
                                    InBeanDao inBeanDao=MyApp.getInstances().getDaoSession().getInBeanDao();
                                    inBeanDao.delete(inBean);
                                    datas.remove(position);
                                    mAdapter.notifyDataSetChanged();
                                }
                            });
                            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            builder.show();
                        }
                    });


                    int size=datas.size();
                    num.setText(size-position+"");
                }
            };
            getView().getRvRecentMessage().setAdapter(mAdapter);
        }

    }

    public boolean addRecord(String bitmapPath){

        if (datas!=null){
            InBean bean=new InBean();
            IRecentMessageFgView view=getView();
            String Code=view.getCode().getText().toString().trim();
            List<InBean> inBeens=MyApp.getInstances().getDaoSession().getInBeanDao().queryBuilder().where(InBeanDao.Properties.Code.eq(Code),InBeanDao.Properties.Status.eq("未上传")).build().list();
            if (inBeens!=null&&inBeens.size()!=0){
                inBeens.removeAll(inBeens);
//                UIUtils.showToast("该数据已存在");
//                return true;
            }
            String Person=view.getPerson().getText().toString().trim();
            String Time=view.getTime().getText().toString().trim();
            String Type=view.getType().getText().toString().trim();
            String Country=view.getCountry().getText().toString().trim();
            String Birthday=view.getBirthday().getText().toString().trim();
            String Capacity=view.getCapacity().getText().toString().trim();
            String Year=view.getYear().getText().toString().trim();
            String Num=view.getNum().getText().toString().trim();
            String Location=view.getLocation().getText().toString().trim();
//            String Code=view.getCode().getText().toString().trim();
            if (!Time.equals("")
                    &&!Person.equals("")
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
                return true;
            }else{
                UIUtils.showToast("请检查数据完整性！");
                return false;
            }
        }
        return false;
    }

    public void clearYesUp(){
        datas.removeAll(removeDatas);
    }

    /**
     * 清空界面列表
     */
    public void clearAllData(){
        datas.clear();
        removeDatas.clear();
        mAdapter.notifyDataSetChanged();
    }

    public void upRecordImg(){
        if (datas!=null&&datas.size()!=0){
            mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));

            OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
            /**
             * 下面上传图片监听
             * ============================================================================
             * ===========================================================================
             * ============================================================================
             */
            okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
                mContext.hideWaitingDialog();
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
            /**
             * 上面上传图片监听
             * ============================================================================
             * ===========================================================================
             * ============================================================================
             */

//            上传图片
            InBean bean=datas.get(0);
            String bitmapPath=bean.getImgstr();
            File file = new File(bitmapPath);
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            builder.addFormDataPart("file", file.getName(), okhttp3.RequestBody.create(okhttp3.MediaType.parse("image/png"), file));
//            "http://121.43.167.170:8001/Wine/upload"
            okHttpUtils.myEnqueue(NetConstant.BASE_URL_IMG,builder.build());
//            mAdapter.notifyDataSetChanged();
        }else{
            datas.addAll(removeDatas);
            mAdapter.notifyDataSetChanged();
        }
    }
    public void upRecord(){
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils=OkHttpUtils.initClient();
        /**
         * ============================================================
         * 下面上传入库记录监听
         * ============================================================
         */
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
                InBeanDao inBeanDao = getInstances().getDaoSession().getInBeanDao();
                inBeanDao.update(inBean);
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
                removeDatas.add(0,datas.get(0));
                datas.remove(0);
                upRecordImg();
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));
/**
 * ============================================================
 * 上面上传入库记录监听
 * ============================================================
 */
        okHttpUtils.myEnqueue(new UpInRecordRequest(datas).getUrl(),null);
    }

    public void speech(String toast) {
//        speechUtil.speakXunFei(toast);
    }

    public void query() {
        mContext.showWaitingDialog(UIUtils.getString(R.string.please_wait));
        OkHttpUtils okHttpUtils = OkHttpUtils.initClient();
        okHttpUtils.setOnResultListener(newstr1 -> mContext.runOnUiThread(() -> {
            mContext.hideWaitingDialog();
            if (newstr1.equals("数据请求失败")) {
                mContext.speechUtil.speakXunFei("数据请求失败");
                return;
            }
            Gson gson = new Gson();
            QueryResponse response = null;
            try {
                response = gson.fromJson(newstr1, QueryResponse.class);
            } catch (JsonSyntaxException e) {
                mContext.hideWaitingDialog();
                mContext.speechUtil.speakXunFei("服务器异常");
//                    mContext.loginError(new ServerException(UIUtils.getString(R.string.login_error)));
                e.printStackTrace();
                return;
            }
            String code = response.getErrorCode();
            if (code.equals("1000")) {
//                QueryResponseSingle queryResponseSingle=gson.fromJson(response.getData(),QueryResponseSingle.class);

                Type type = new TypeToken<ArrayList<QueryResponseSingle>>()
                {}.getType();
                ArrayList<QueryResponseSingle> jsonObjects = new Gson().fromJson(response.getData(), type);
                if (jsonObjects.size()!=0){


                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("注意:重要提示");
                    builder.setMessage("该商品库中已存在，对已有商品入库请切换到出/入库界面;若在本界面继续操作将会对库中的该商品基本信息进行修改，其中数量修改无效！");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();






                    QueryResponseSingle queryResponseSingle=jsonObjects.get(0);
                    getView().getType().setText(queryResponseSingle.getCategory());
                    getView().getCountry().setText(queryResponseSingle.getCountry());
                    getView().getBirthday().setText(queryResponseSingle.getOrigin());
                    getView().getCapacity().setText(queryResponseSingle.getVolume());
                    getView().getYear().setText(queryResponseSingle.getProductiveYear());
                    getView().getNum().setText(queryResponseSingle.getCountNum());

                    getView().getLocation().setText(queryResponseSingle.getPosition());

//                    Bitmap bitmap = ((BitmapDrawable) getView().getImgV().getDrawable()).getBitmap();
//                    getView().getImgV().setImageResource(R.mipmap.ic_launcher);
//                    if (bitmap != null && !bitmap.isRecycled()){
//                        bitmap.recycle();
//                        bitmap = null;
//                    }
//                    Glide.with(mContext).load("http://121.43.167.170:8001/Wine/"+queryResponseSingle.getPhoto()).centerCrop().into(getView().getImgV());
                }else {
//                    UIUtils.showToast("无此商品信息，请新增！");
//                    mContext.speechUtil.speakXunFei("无此商品信息");
                }
//                datas.addAll(jsonObjects);
//                mAdapter.notifyDataSetChanged();

                mContext.hideWaitingDialog();
//                    UserCache.save(loginResponse.getResult().getId(), phone, loginResponse.getResult().getToken());
            } else {
                mContext.speechUtil.speakXunFei(response.getErrorMsg());
                mContext.hideWaitingDialog();
                return;
//                    loginError(new ServerException(UIUtils.getString(R.string.login_error) + code));
            }
        }));


//        查询的json数据
//        String country=getView().getEtCountryV().getText().toString().trim();
//        String birthplace=getView().getEtBirthplaceV().getText().toString().trim();
//        String type=getView().getEtTypeV().getText().toString().trim();
//        String capacity=getView().getEtCapacityV().getText().toString().trim();
//        String year=getView().getEtYearV().getText().toString().trim();
        QueryBean queryBean=new QueryBean();
//        queryBean.setCountry(country);
//        queryBean.setOrigin(birthplace);
//        queryBean.setCategory(type);
//        queryBean.setVolume(capacity);
//        queryBean.setProductiveYear(year);
        if (!getView().getCode().getText().toString().trim().equals("")){
            queryBean.setRecordCode(getView().getCode().getText().toString().trim());
            okHttpUtils.myEnqueue(new QueryRequest(queryBean).getUrl(), null);
        }else {
            mContext.hideWaitingDialog();
            UIUtils.showToast("查询条码为空！");
        }


//            mAdapter.notifyDataSetChanged();
    }
//    上面为查询

}
