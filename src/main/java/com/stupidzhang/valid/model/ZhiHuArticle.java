package com.stupidzhang.valid.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * url_commit
 * @author stupidzhang
 */
@Data
public class ZhiHuArticle implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String url;
    private String articleId;
    private String answerId;
    private String type;
    private Date createTime;
    private Date updateTime;

}