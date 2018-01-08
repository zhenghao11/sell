<html>
    <head>
        <meta charset="utf-8">
        <#include "../common/header.ftl">
<#--        <style type="text/css"> .s1 {text-align:center;  vertical-align:middle  }</style>-->
    </head>
<body>
<div id="wrapper" class="toggled">
    <!-- 边栏 -->
    <#include "../common/nav.ftl">
    <!-- 主体内容 -->
    <br/>
    <div class="col-md-5 column"><h5><strong>&nbsp当前位置:商品列表</strong></h5></div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered table-condensed">
                            <thead>
                            <tr>
                                <th style="text-align:center;vertical-align:middle;">商品ID</th>
                                <th style="text-align:center;vertical-align:middle;">商品名称</th>
                                <th style="text-align:center;vertical-align:middle;">商品价格</th>
                                <th style="text-align:center;vertical-align:middle;">商品库存</th>
                                <th style="text-align:center;vertical-align:middle;">商品描述</th>
                                <th style="text-align:center;vertical-align:middle;">商品小图</th>
                                <th style="text-align:center;vertical-align:middle;">商品状态</th>
                                <th style="text-align:center;vertical-align:middle;">类目</th>
                                <th style="text-align:center;vertical-align:middle;">创建时间</th>
                                <th style="text-align:center;vertical-align:middle;">修改时间</th>
                                <th style="text-align:center;vertical-align:middle;"colspan="3">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list productInfoList as productInfo>
                            <tr>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.productId}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.productName}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.productPrice}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.productStock?c}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.productDescription}</td>
                                <td><img height="100" width="100" src="${(productInfo.productIcon)! ''}"></td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.getSaleEnum().getMsg()}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.categoryName}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.createTime}</td>
                                <td style="text-align:center;vertical-align:middle;">${productInfo.updateTime}</td>
                                <td style="text-align:center;vertical-align:middle;"><a href="/seller/product/addIndex?productId=${productInfo.productId}&page=${currentPage}">修改</a></td>
                                <#if productInfo.getSaleEnum().getCode() == 1>
                                    <td style="text-align:center;vertical-align:middle;"><a href="/seller/product/onSale?productId=${productInfo.productId}&page=${currentPage}">上架</a></td>
                                    <#else>
                                    <td style="text-align:center;vertical-align:middle;"><a href="/seller/product/offSale?productId=${productInfo.productId}&page=${currentPage}">下架</a></td>
                                </#if>
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
                                <li><a href="/seller/product/list?page=${currentPage-1}">上一页</a></li>
                            </#if>
                            <#list 1..totalPages as page>
                            <#if currentPage == page>
                                <li class="disabled"><a href="#">${page}</a></li>
                            <#else>
                                <li><a href="/seller/product/list?page=${page}">${page}</a></li>
                            </#if>
                            </#list>

                            <#if currentPage gte totalPages>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/seller/product/list?page=${currentPage+1}">下一页</a></li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>