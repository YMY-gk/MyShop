package com.me.goods.dto;

import com.me.goods.pojo.TbSpu;

import java.io.Serializable;
import java.util.List;

/**
 * 描述
 *
 * @author www.itheima.com
 * @version 1.0
 * @package com.changgou.goods.pojo *
 * @since 1.0
 */
public class Goods implements Serializable {
    private TbSpu spu;
    private List<TbSpu> skuList;

    public TbSpu getSpu() {
        return spu;
    }

    public void setSpu(TbSpu spu) {
        this.spu = spu;
    }

    public List<TbSpu> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<TbSpu> skuList) {
        this.skuList = skuList;
    }
}
