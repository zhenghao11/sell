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
    <div class="col-md-5 column"><h5><strong>&nbsp当前位置:类目列表</strong></h5></div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                            <tr>
                                <th style="text-align:center">类目id</th>
                                <th style="text-align:center">类目名称</th>
                                <th style="text-align:center">类目编号</th>
                                <th style="text-align:center">创建时间</th>
                                <th style="text-align:center">修改时间</th>
                                <th style="text-align:center">操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        <#list productCategoryList as category>
                            <tr>
                                <td align="center">${category.categoryId}</td>
                                <td align="center">${category.categoryName}</td>
                                <td align="center">${category.categoryType}</td>
                                <td align="center">${category.createTime}</td>
                                <td align="center">${category.updateTime}</td>
                                <td align="center"><a href="/seller/category/updateIndex?categoryId=${category.categoryId}">修改</a></td>
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
                                <li><a href="/seller/category/list?page=${currentPage-1}">上一页</a></li>
                            </#if>
                            <#list 1..totalPages as page>
                                <#if currentPage == page>
                                    <li class="disabled"><a href="#">${page}</a></li>
                                <#else>
                                    <li><a href="/seller/category/list?page=${page}">${page}</a></li>
                                </#if>
                            </#list>
                            <#if currentPage gte totalPages>
                                <li class="disabled"><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/seller/category/list?page=${currentPage+1}">下一页</a></li>
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