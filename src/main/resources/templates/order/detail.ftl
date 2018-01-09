<html>
<head>
    <meta charset="utf-8">
    <#include "../common/header.ftl">
</head>
<body>

<div id="wrapper" class="toggled">
    <!-- 边栏 -->
    <#include "../common/nav.ftl">
    <!-- 主体内容 -->
    <br/>
    <div class="col-md-5 column"><h5><strong>当前位置:订单详情</strong></h5></div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-5 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>订单总金额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>${orderMaster.orderId}</td>
                            <td>${orderMaster.orderAmount}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>详情id</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>购买数量</th>
                        <th>金额</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list orderDetailList as orderDetail>
                    <tr>
                        <td>${orderDetail.detailId}</td>
                        <td>${orderDetail.productName}</td>
                        <td>${orderDetail.productPrice}</td>
                        <td>${orderDetail.productQuantity}</td>
                        <td>${orderDetail.productPrice * orderDetail.productQuantity}</td>
                        <td>${orderDetail.createTime}</td>
                    </tr>
                    </#list>
                    </tbody>
                    </table>
                </div>
                <div class="col-md-12 column">
                    <#if orderMaster.getOrderMasterEnum().getMsg() == '新订单'>
                    <a href="/sell/seller/order/finish?orderId=${orderMaster.orderId}" type="button" class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderMaster.orderId}" type="button" class="btn btn-default btn-danger">取消订单</a>
                    </#if>
                    <a href="/sell/seller/order/list?page=${page}" type="button" class="btn btn-default btn-primary">返回该订单</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>