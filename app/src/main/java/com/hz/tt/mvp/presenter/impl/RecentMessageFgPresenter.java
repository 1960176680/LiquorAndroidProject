package com.hz.tt.mvp.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

import com.hz.tt.R;
import com.hz.tt.mvp.presenter.base.BasePresenter;
import com.hz.tt.mvp.ui.activity.MainActivity;
import com.hz.tt.mvp.ui.common.BaseActivity;
import com.hz.tt.mvp.ui.view.IRecentMessageFgView;
import com.hz.tt.util.DateUtil;
import com.hz.tt.util.UIUtils;
import com.hz.tt.util.voice.TextToSpeechUtil;
import com.hz.tt.widget.CustomDialog;

import java.io.File;
import java.util.Date;

public class RecentMessageFgPresenter extends BasePresenter<IRecentMessageFgView> {
    TextToSpeechUtil speechUtil;
    private String bitmapPath;
    private Context context;

//    private List<Conversation> mData = new ArrayList<>();
//    private LQRAdapterForRecyclerView<Conversation> mAdapter;
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
        this.context=context;
//        speechUtil = new TextToSpeechUtil(context);
    }

    public void getConversations() {
//        loadData();
        setAdapter();
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

    private void setAdapter() {
//        if (mAdapter == null) {
//            mAdapter = new LQRAdapterForRecyclerView<Conversation>(mContext, mData, R.layout.item_recent_message) {
//                @Override
//                public void convert(LQRViewHolderForRecyclerView helper, Conversation item, int position) {
//                    if (item.getConversationType() == Conversation.ConversationType.PRIVATE) {
//                        ImageView ivHeader = helper.getView(R.id.ivHeader);
//                        UserInfo userInfo = DBManager.getInstance().getUserInfo(item.getTargetId());
//                        if (userInfo != null) {
//                            Glide.with(mContext).load(userInfo.getPortraitUri()).centerCrop().into(ivHeader);
//                            helper.setText(R.id.tvDisplayName, userInfo.getName())
//                                    .setText(R.id.tvTime, TimeUtils.getMsgFormatTime(item.getReceivedTime()))
//                                    .setViewVisibility(R.id.ngiv, View.GONE)
//                                    .setViewVisibility(R.id.ivHeader, View.VISIBLE);
//                        }
//                    } else {
//                        Groups groups = DBManager.getInstance().getGroupsById(item.getTargetId());
//                        //九宫格头像
//                        LQRNineGridImageView ngiv = helper.getView(R.id.ngiv);
//                        ngiv.setAdapter(mNgivAdapter);
//                        ngiv.setImagesData(DBManager.getInstance().getGroupMembers(item.getTargetId()));
//                        //群昵称
//                        helper.setText(R.id.tvDisplayName, groups == null ? "" : groups.getName())
//                                .setText(R.id.tvTime, TimeUtils.getMsgFormatTime(item.getReceivedTime()))
//                                .setViewVisibility(R.id.ngiv, View.VISIBLE)
//                                .setViewVisibility(R.id.ivHeader, View.GONE);
//                    }
//
////                    helper.setBackgroundColor(R.id.flRoot, item.isTop() ? UIUtils.getColor(R.color.gray7) : UIUtils.getColor(android.R.color.white))
//                    helper.setBackgroundColor(R.id.flRoot, item.isTop() ? R.color.gray8 : android.R.color.white)
//                            .setText(R.id.tvCount, item.getUnreadMessageCount() + "")
//                            .setViewVisibility(R.id.tvCount, item.getUnreadMessageCount() > 0 ? View.VISIBLE : View.GONE);
//                    TextView tvContent = helper.getView(R.id.tvContent);
//                    if (!TextUtils.isEmpty(item.getDraft())) {
//                        MoonUtils.identifyFaceExpression(mContext, tvContent, item.getDraft(), ImageSpan.ALIGN_BOTTOM);
//                        helper.setViewVisibility(R.id.tvDraft, View.VISIBLE);
//                        return;
//                    } else {
//                        helper.setViewVisibility(R.id.tvDraft, View.GONE);
//                    }
//
//                    if (item.getLatestMessage() instanceof TextMessage) {
//                        MoonUtils.identifyFaceExpression(mContext, tvContent, ((TextMessage) item.getLatestMessage()).getContent(), ImageSpan.ALIGN_BOTTOM);
//                    } else if (item.getLatestMessage() instanceof ImageMessage) {
//                        tvContent.setText("[" + UIUtils.getString(R.string.picture) + "]");
//                    } else if (item.getLatestMessage() instanceof VoiceMessage) {
//                        tvContent.setText("[" + UIUtils.getString(R.string.voice) + "]");
//                    } else if (item.getLatestMessage() instanceof FileMessage) {
//                        FileMessage fileMessage = (FileMessage) item.getLatestMessage();
//                        if (MediaFileUtils.isImageFileType(fileMessage.getName())) {
//                            tvContent.setText("[" + UIUtils.getString(R.string.sticker) + "]");
//                        } else if (MediaFileUtils.isVideoFileType(fileMessage.getName())) {
//                            tvContent.setText("[" + UIUtils.getString(R.string.video) + "]");
//                        }
//                    } else if (item.getLatestMessage() instanceof LocationMessage) {
//                        tvContent.setText("[" + UIUtils.getString(R.string.location) + "]");
//                    } else if (item.getLatestMessage() instanceof GroupNotificationMessage) {
//                        GroupNotificationMessage groupNotificationMessage = (GroupNotificationMessage) item.getLatestMessage();
//                        try {
//                            UserInfo curUserInfo = DBManager.getInstance().getUserInfo(UserCache.getId());
//                            GroupNotificationMessageData data = JsonMananger.jsonToBean(groupNotificationMessage.getData(), GroupNotificationMessageData.class);
//                            String operation = groupNotificationMessage.getOperation();
//                            String notification = "";
//                            String operatorName = data.getOperatorNickname().equals(curUserInfo.getName()) ? UIUtils.getString(R.string.you) : data.getOperatorNickname();
//                            String targetUserDisplayNames = "";
//                            List<String> targetUserDisplayNameList = data.getTargetUserDisplayNames();
//                            for (String name : targetUserDisplayNameList) {
//                                targetUserDisplayNames += name.equals(curUserInfo.getName()) ? UIUtils.getString(R.string.you) : name;
//                            }
//                            if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_CREATE)) {
//                                notification = UIUtils.getString(R.string.created_group, operatorName);
//                            } else if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_DISMISS)) {
//                                notification = operatorName + UIUtils.getString(R.string.dismiss_groups);
//                            } else if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_KICKED)) {
//                                if (operatorName.contains(UIUtils.getString(R.string.you))) {
//                                    notification = UIUtils.getString(R.string.remove_group_member, operatorName, targetUserDisplayNames);
//                                } else {
//                                    notification = UIUtils.getString(R.string.remove_self, targetUserDisplayNames, operatorName);
//                                }
//                            } else if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_ADD)) {
//                                notification = UIUtils.getString(R.string.invitation, operatorName, targetUserDisplayNames);
//                            } else if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_QUIT)) {
//                                notification = operatorName + UIUtils.getString(R.string.quit_groups);
//                            } else if (operation.equalsIgnoreCase(GroupNotificationMessage.GROUP_OPERATION_RENAME)) {
//                                notification = UIUtils.getString(R.string.change_group_name, operatorName, data.getTargetGroupName());
//                            }
//                            tvContent.setText(notification);
//                        } catch (HttpException e) {
//                            e.printStackTrace();
//                        }
//                    } else if (item.getLatestMessage() instanceof RedPacketMessage) {
//                        RedPacketMessage redPacketMessage = (RedPacketMessage) item.getLatestMessage();
//                        tvContent.setText("[" + UIUtils.getString(R.string.wx_red_pack) + "]" + redPacketMessage.getContent());
//                    }
//                }
//            };
//            mAdapter.setOnItemClickListener((helper, parent, itemView, position) -> {
//                Intent intent = new Intent(mContext, SessionActivity.class);
//                Conversation item = mData.get(position);
//                intent.putExtra("sessionId", item.getTargetId());
//                if (item.getConversationType() == Conversation.ConversationType.PRIVATE) {
//                    intent.putExtra("sessionType", SessionActivity.SESSION_TYPE_PRIVATE);
//                } else {
//                    intent.putExtra("sessionType", SessionActivity.SESSION_TYPE_GROUP);
//                }
//                mContext.jumpToActivity(intent);
//            });
//            mAdapter.setOnItemLongClickListener((helper, parent, itemView, position) -> {
//                Conversation item = mData.get(position);
//                View conversationMenuView = View.inflate(mContext, R.layout.dialog_conversation_menu, null);
//                mConversationMenuDialog = new CustomDialog(mContext, conversationMenuView, R.style.MyDialog);
//                TextView tvSetConversationToTop = (TextView) conversationMenuView.findViewById(R.id.tvSetConversationToTop);
//                tvSetConversationToTop.setText(item.isTop() ? UIUtils.getString(R.string.cancel_conversation_to_top) : UIUtils.getString(R.string.set_conversation_to_top));
//                conversationMenuView.findViewById(R.id.tvSetConversationToTop).setOnClickListener(v ->
//                        RongIMClient.getInstance().setConversationToTop(item.getConversationType(), item.getTargetId(), !item.isTop(), new RongIMClient.ResultCallback<Boolean>() {
//                            @Override
//                            public void onSuccess(Boolean aBoolean) {
//                                loadData();
//                                mConversationMenuDialog.dismiss();
//                                mConversationMenuDialog = null;
//                            }
//
//                            @Override
//                            public void onError(RongIMClient.ErrorCode errorCode) {
//
//                            }
//                        }));
//                conversationMenuView.findViewById(R.id.tvDeleteConversation).setOnClickListener(v -> {
//                    RongIMClient.getInstance().removeConversation(item.getConversationType(), item.getTargetId(), new RongIMClient.ResultCallback<Boolean>() {
//                        @Override
//                        public void onSuccess(Boolean aBoolean) {
//                            loadData();
//                            mConversationMenuDialog.dismiss();
//                            mConversationMenuDialog = null;
//                        }
//
//                        @Override
//                        public void onError(RongIMClient.ErrorCode errorCode) {
//
//                        }
//                    });
//                });
//                mConversationMenuDialog.show();
//                return true;
//            });
//            getView().getRvRecentMessage().setAdapter(mAdapter);
//        }
    }


    /**
     * 执行拍照
     */
    public void takePhoto(String s) {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            File dir = new File(Environment.getExternalStorageDirectory() + "/StayWareHouseImage/");
            if (!dir.exists() && !dir.mkdirs())
                return;
            Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(dir, DateUtil.format(new Date(), "yyyyMMddhhmmss") + "stayHorse_0" + s + ".jpg");
            bitmapPath = file.getPath();

            openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            openCameraIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
            ((MainActivity) context).startActivityForResult(openCameraIntent, 0x000000);
        } else {
            speech("没有储存卡");
        }
    }


    public void speech(String toast) {
//        speechUtil.speakXunFei(toast);
    }
}
