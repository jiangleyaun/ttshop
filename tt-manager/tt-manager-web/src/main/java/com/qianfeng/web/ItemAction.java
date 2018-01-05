package com.qianfeng.web;


import com.qianfeng.pojo.dto.Order;
import com.qianfeng.pojo.dto.Page;
import com.qianfeng.pojo.dto.Result;
import com.qianfeng.pojo.po.TbItem;
import com.qianfeng.pojo.vo.TbItemCustom;
import com.qianfeng.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ItemAction {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @ResponseBody
    @RequestMapping(value = "/item/{itemId}",method = RequestMethod.GET)
    public TbItem getItemById(@PathVariable("itemId") Long itemId){
        return itemService.getItemById(itemId);
    }

/*    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public List<TbItem> listItem(){
        List<TbItem> list = null;

        try{

            list = itemService.listItems();
            System.out.println(list);

        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }

        return list;
    }*/
    /*单表分页查询*/
/*    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public Result<TbItem> listItem(Page page){
        Result<TbItem> result = null;

        try{

            result = itemService.listItemByPage(page);

        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }

        return result;
    }*/

    /*两表连查分页*/
    @ResponseBody
    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public Result<TbItemCustom> listItem(Page page, Order order){
        Result<TbItemCustom> result = null;

        try{

            result = itemService.listItemByPage(page,order);

        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }

        return result;
    }

    /*批量删除*/
    @ResponseBody
    @RequestMapping(value = "/item/batch",method = RequestMethod.POST)
    public int batchUpdate(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try{

            i = itemService.batchUpdate(ids);

        }catch (Exception e){

            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return  i;
    }
    /*批量上架*/
    @ResponseBody
    @RequestMapping(value = "/item/up",method = RequestMethod.POST)
    public int upUpdate(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try{
            i = itemService.upUpdate(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return i;
    }
    /*批量下架*/
    @ResponseBody
    @RequestMapping(value = "/item/down",method = RequestMethod.POST)
    public int downUpdate(@RequestParam("ids[]") List<Long> ids){
        int i = 0;
        try{
            i = itemService.downUpdate(ids);
        }catch (Exception e){
            logger.error(e.getMessage(),e);

            e.printStackTrace();
        }
        return i;
    }

}
