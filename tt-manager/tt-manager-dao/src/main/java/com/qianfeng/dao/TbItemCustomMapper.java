package com.qianfeng.dao;

import com.qianfeng.pojo.dto.Order;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.po.TbItemExample;
import com.qianfeng.pojo.vo.TbItemCustom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbItemCustomMapper {

    List<TbItemCustom> listItemByPage(@Param("page") Page page, @Param("order") Order order);

    Long countItemsByCondition();
}