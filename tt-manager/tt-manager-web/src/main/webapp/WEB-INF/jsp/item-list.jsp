<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%--当前页面的html页面--%>
<table id="itemListDg"></table>
<%--当前页的js--%>
<script>
    /*tb= [{//新增},{//删除},{//上架}]*/
    var tb = [{
        iconCls:'icon-add',
        text:'新增',
        handler:function () {
            console.log('新增');
        }
    },{
        iconCls:'icon-remove',
        text:'删除',
        handler:function () {
            var selections = $('#itemListDg').datagrid('getSelections');
            if(selections.length == 0){
                $.messager.alert("警告","请选择记录再删除！");
                return;
            }
            $.messager.confirm("确认","确认删除嘛？",function (r) {
                if(r){
                    var ids = [];
                    for(var i = 0;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }

                    $.post(
                        //1.url
                        'item/batch',
                        //2.data
                        {"ids":ids},
                        //3.function
                        function (data) {
                            if(data>0){
                                $('#itemListDg').datagrid('reload');
                            }
                        }
                        //dataType
                        //,'json'

                    );
                }
            });
        }
    },{
        iconCls:'icon-up',
        text:'上架',
        handler:function () {
           var selections = $('#itemListDg').datagrid('getSelections');
           if(selections.length == 0){
               $.messager.alert('提示','您未选择商品！');
               return;
           }
           $.messager.confirm("确认","您确认上架嘛？",function (r) {
               if(r){
                   var ids = [];
                   for(var i = 0;i<selections.length;i++){
                       ids.push(selections[i].id);
                   }

                   $.post(
                       'item/up',
                       {"ids":ids},
                       function (data) {
                           if(data>0){
                               $('#itemListDg').datagrid('reload');
                           }
                       }
                   );
               }
           });
        }
    },{
        iconCls:'icon-down',
        text:'下架',
        handler:function () {
            var selections = $('#itemListDg').datagrid('getSelections');
            if(selections.length == 0){
                $.messager.alert('提示','您未选择商品！');
                return;
            }
            $.messager.confirm('警告','确认下架嘛？',function (r) {
                if(r){
                    var ids = [];
                    for(var i = 0 ;i<selections.length;i++){
                        ids.push(selections[i].id);
                    }
                    $.post(
                        'item/down',
                        {"ids":ids},
                        function (data) {
                            if(data>0){
                                $('#itemListDg').datagrid('reload');
                            }
                        }
                    );

                }
            });
        }
    }];
    /*方法*/
    $(function () {
        $('#itemListDg').datagrid({
            //允许多列排序
            multiSort:true,
            //表格属性
            url:'items',
            toolbar:tb,
            fitColumns:true,
            pageSize:20,
            pageList:[20,50,100],
            striped:true,
            pagination:true,
            fit:true,
            columns:[[
                {field:'ck',checkbox:true},
                {field:'id',title:'商品编号',sortable:true},
                {field:'title',title:'商品标题',sortable:true},
                {field:'status',title:'商品状态',sortable:true,
                    formatter:function (v,r,i) {
                        switch (v){
                            case 1:
                                return '正常';
                                break;
                            case 2:
                                return '下架';
                                break;
                            case 3:
                                return '删除';
                                break;
                            default:
                                return '未知';
                                break;
                        }
                    }},
                {field:'catName',title:'分类名称'},
                {field:'sellPoint',title:'商品卖点'},
                {field:'created',title:'创建时间',formatter:function (v,r,i) {
                    return moment(v).format('YYYY-MM-DD h:mm:ss a');
                }},
                {field:'priceView',title:'价格'}
            ]]
        });

    });
</script>