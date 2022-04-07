package com.stupidzhang.valid.mapper;

import com.stupidzhang.valid.model.CpsInvalidItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author stupidzhang
 */
public interface CpsInvalidItemMapper {

    void batchInsert(@Param("itemList") List<CpsInvalidItem> invalidItemList);

    Integer count(@Param("goodsId") String goodsId);
}
