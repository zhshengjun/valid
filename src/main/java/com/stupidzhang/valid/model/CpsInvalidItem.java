package com.stupidzhang.valid.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author stupidzhang
 * @date 2022-04-07 16:29:26
 */
@Data
@SuperBuilder
public class CpsInvalidItem implements Serializable {

    private static final long serialVersionUID = -88208476520647554L;

    private Integer id;

    /**
     * 商品名称
     */
    private String title;

    private String goodsId;
    private String goodsUrl;

    /**
     * 平台
     */
    private String platform;

    /**
     * 文章链接
     */
    private Integer articleId;

    private Date createTime;

    private Date updateTime;

}
