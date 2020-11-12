package com.hust.yongpagani.miaosha.vo;

import com.hust.yongpagani.miaosha.domain.Goods;

import java.util.Date;

/**
 * @author Created by Divo
 * @date 2020/11/12
 * Description: 用来封装（整合）商品信息
 */
public class GoodsVo extends Goods {
    private Integer stockCount;
    private Date startDate;
    private Date endDate;

    public Integer getStockCount() {
        return stockCount;
    }

    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
