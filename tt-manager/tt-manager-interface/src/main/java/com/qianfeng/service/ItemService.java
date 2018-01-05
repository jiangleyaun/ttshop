package com.qianfeng.service;

import com.qianfeng.pojo.dto.Order;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.vo.TbItemCustom;

import java.util.List;

public interface ItemService {

    /*根据id查询*/
    TbItem getItemById(Long itemId);

    /*查询不分页的所有数据*/
    List<TbItem> listItems();

    /*查询分页*/
    Result<TbItemCustom> listItemByPage(Page page, Order order);
    /*批量删除*/
    int batchUpdate(List<Long> ids);
    /*批量上架*/
    int upUpdate(List<Long> ids);
    /*批量下架*/
    int downUpdate(List<Long> ids);
}
