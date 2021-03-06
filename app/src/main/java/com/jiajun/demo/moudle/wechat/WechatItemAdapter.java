package com.jiajun.demo.moudle.wechat;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jiajun.demo.R;
import com.jiajun.demo.model.entities.WechatItem;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * 微信首页recycleView适配器
 */


public class WechatItemAdapter extends BaseMultiItemQuickAdapter<WechatItem.ResultBean.ListBean, BaseViewHolder> {

    public boolean isNotLoad;
    public int mImgWidth;
    public int mImgHeight;

    public WechatItemAdapter(List<WechatItem.ResultBean.ListBean> data) {
        super(data);
        addItemType(WechatItem.ResultBean.ListBean.STYLE_BIG, R.layout.item_wechat_style1);
        addItemType(WechatItem.ResultBean.ListBean.STYLE_SMALL, R.layout.item_wechat_style2);
    }

    public WechatItemAdapter(List<WechatItem.ResultBean.ListBean> data, boolean isNotLoadImg, int imgWidth, int imgHeight) {
        super(data);
        addItemType(WechatItem.ResultBean.ListBean.STYLE_BIG, R.layout.item_wechat_style1);
        addItemType(WechatItem.ResultBean.ListBean.STYLE_SMALL, R.layout.item_wechat_style2);
        isNotLoad = isNotLoadImg;
        mImgWidth = imgWidth;
        mImgHeight = imgHeight;
        Logger.e(imgWidth+"gao:"+imgHeight);
    }

    @Override
    protected void convert(BaseViewHolder helper, WechatItem.ResultBean.ListBean item) {
        switch (helper.getItemViewType()) {
            case WechatItem.ResultBean.ListBean.STYLE_BIG:
                helper.setText(R.id.title_wechat_style1, TextUtils.isEmpty(item.getTitle()) ? mContext.getString(R.string.wechat_select) : item.getTitle());
                if (!isNotLoad) {
                    Glide.with(mContext.getApplicationContext())
                            .load(item.getFirstImg())
                            .override(mImgWidth, mImgHeight)
                            .placeholder(R.drawable.lodingview)
                            .error(R.drawable.errorview)
                            .crossFade(1000)
                            .into((ImageView) helper.getView(R.id.img_wechat_style));
                }
                break;
            case WechatItem.ResultBean.ListBean.STYLE_SMALL:
                helper.setText(R.id.title_wechat_style2, TextUtils.isEmpty(item.getTitle()) ? mContext.getString(R.string.wechat_select) : item.getTitle());
                if (!isNotLoad) {
                    Glide.with(mContext.getApplicationContext())
                            .load(item.getFirstImg())
                            .placeholder(R.drawable.lodingview)
                            .error(R.drawable.errorview)
                            .override(mImgWidth / 2, mImgWidth / 2)
                            .crossFade(1000)
                            .into((ImageView) helper.getView(R.id.img_wechat_style));
                }
                break;
            default:
                break;
        }
    }
}
