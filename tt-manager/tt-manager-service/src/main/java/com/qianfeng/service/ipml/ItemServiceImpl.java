package com.qianfeng.service.ipml;

import com.qianfeng.dao.TbItemCustomMapper;
import com.qianfeng.dao.TbItemMapper;
import com.qianfeng.pojo.dto.Order;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.po.TbItemExample;
import com.qianfeng.pojo.vo.TbItemCustom;
import com.qianfeng.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 商品业务的逻辑实现类
* */
@Service
public class ItemServiceImpl implements ItemService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbItemMapper itemDao;

    @Autowired
    private TbItemCustomMapper itemCustomDao;

    @Override
    public TbItem getItemById(Long itemId) {
        return itemDao.selectByPrimaryKey(itemId);
    }
/*不分页查询*/
    @Override
    public List<TbItem> listItems() {
        List<TbItem> list = null;

        try{

            list = itemDao.selectByExample(null);

        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }

        return list;
    }
/*分页查询*/
    @Override
    public Result<TbItemCustom> listItemByPage(Page page, Order order) {
        Result<TbItemCustom> result = new Result<TbItemCustom>();
        try{
            /*方法一*/
            /*@Param*/
            /*方法二*/
            /*Map<String,Object> map = new HashMap<String,Object>();
            map.put("page",page);
            map.put("order",order);
            List<TbItemCustom> list = itemCustomDao.listItemByPage(map);*/

            Long total = itemCustomDao.countItemsByCondition();
            List<TbItemCustom> list = itemCustomDao.listItemByPage(page,order);

            result.setTotal(total);
            result.setRows(list);
        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }

        return result;
    }

    /*批量删除*/
    @Override
    public int batchUpdate(List<Long> ids) {
        int i = 0;
        try{
            //创建一个对象，设置商品状态为删除“3”
            TbItem item = new TbItem();
            item.setStatus((byte)3);
            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行操作
            i = itemDao.updateByExampleSelective(item,example);
        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return  i;

    }
    /*批量上架*/
    @Override
    public int upUpdate(List<Long> ids) {
        int i = 0;
        try{
            //创建一个对象，设置商品状态为删除“3”
            TbItem item = new TbItem();
            item.setStatus((byte)1);
            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行操作
            i = itemDao.updateByExampleSelective(item,example);
        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return  i;
    }

    /*批量下架*/
    @Override
    public int downUpdate(List<Long> ids) {
        int i = 0;
        try{
            //创建一个对象，设置商品状态为删除“3”
            TbItem item = new TbItem();
            item.setStatus((byte)2);
            //创建更新模板
            TbItemExample example = new TbItemExample();
            TbItemExample.Criteria criteria = example.createCriteria();
            criteria.andIdIn(ids);
            //执行操作
            i = itemDao.updateByExampleSelective(item,example);
        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return  i;
    }
}
