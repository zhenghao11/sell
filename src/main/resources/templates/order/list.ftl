<html>
<head>
    <meta charset="utf-8">
    <#include "../common/header.ftl">
    <script src="/jquery/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<#--    <script type="text/javascript">
        $(function(){
            $("#search").click(function(){
                var tbody = document.getElementById("forBody");
                $.post("/seller/order/search",{keyword:$("#keyword").val()},function(data){
                    var str = '';
    /*                for(i in data){
                        str += "<tr>" +
                                "<td>" + data[i].orderId + "<td>" +
                                "<td>" + data[i].buyerName + "<td>" +
                                "<td>" + data[i].buyerPhone + "<td>" +
                                "<td>" + data[i].buyerAddress + "<td>" +
                                "<td>" + data[i].orderAmount + "<td>" +
                                "<td>" + data[i].getPayStatusEnum().msg + "<td>" +
                                "<td>" + data[i].createTime + "<td>" +
                                "<td>" + data[i].updateTime + "<td>" +
                               "</tr>";
                    }*/
                    tbody.innerHTML = str;
                })
            });
        })
    </script>-->
</head>
<body>
<div id="wrapper" class="toggled">
    <!-- 边栏 -->
    <#include "../common/nav.ftl">
    <!-- 主体内容 -->
    <br/>
    <div class="col-md-5 column"><h5><strong>当前位置:订单列表</strong></h5></div>
    <div class="col-md-5 column" style="float: right;width:300px; height:100px"><input type="text" id="keyword" placeholder="请输入要搜索的订单号"/>
        <button id="search">搜索</button>
    </div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>买家名字</th>
                            <th>买家电话</th>
                            <th>买家地址</th>
                            <th>订单总金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="4">操作</th>
                        </tr>
                        </thead>
                        <tbody id="forBody">
                        <#list orderMasterList as orderMaster>
                        <tr>
                            <td>${orderMaster.orderId}</td>
                            <td>${orderMaster.buyerName}</td>
                            <td>${orderMaster.buyerPhone}</td>
                            <td>${orderMaster.buyerAddress}</td>
                            <td>${orderMaster.orderAmount}</td>
                            <td>${orderMaster.getOrderMasterEnum().getMsg()}</td>
                            <td>${orderMaster.getPayStatusEnum().getMsg()}</td>
                            <td>${orderMaster.createTime}</td>
                            <td>${orderMaster.updateTime}</td>
                            <td><a href="/seller/order/detail?orderId=${orderMaster.orderId}&page=${currentPage}">详情</a></td>
                                <#if orderMaster.getOrderMasterEnum().getMsg() == '新订单'>
                                    <td><a href="/seller/order/cancel?orderId=${orderMaster.orderId}">取消</a></td>
                                </#if>
                            <td><a href="/seller/order/delete?orderId=${orderMaster.orderId}&page=${currentPage}" class="alert-link">删除</a></td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled"><a href="#">上一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage-1}">上一页</a></li>
                    </#if>
                    <#list 1..totalPages as page>
                    <#if currentPage == page>
                        <li class="disabled"><a href="#">${page}</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${page}">${page}</a></li>
                    </#if>
                    </li>
                    </#list>
                    <#if currentPage gte totalPages>
                        <li class="disabled"><a href="#">下一页</a></li>
                    <#else>
                        <li><a href="/seller/order/list?page=${currentPage+1}">下一页</a></li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body" id="show" style="text-align:center">

            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary" >查看所有新订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<audio id="notice" loop="loop">
    <source src="/mp3/Justin Bieber - Love Yourself.mp3" type="audio/mpeg" />
</audio>

<script>
    var res = null;
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://127.0.0.1:8080/webSocket');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    }

    websocket.onclose = function (event) {
        console.log('连接关闭');
    }

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
        res++;
        $("#show").text("您有"+res+"条新订单");
        //弹窗提醒, 播放音乐
        $('#myModal').modal('show');
        document.getElementById('notice').play();
    }

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>
</body>
</html>