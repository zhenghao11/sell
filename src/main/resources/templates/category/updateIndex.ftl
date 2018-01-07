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
    <div class="col-md-5 column"><h5><strong>&nbsp当前位置:修改类目</strong></h5></div>
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" action="/seller/category/addOrUpdate" method="post">
                        <input type="hidden" name="categoryId" value="${(productCategory.categoryId)!''}">
                        <div class="form-group">
                            <label>类目名称</label>
                            <input type="text" class="form-control" name="categoryName" value="${(productCategory.categoryName)!''}" placeholder="请输入类目名称,不能存在空格"/>
                        </div>
                        <div class="form-group">
                            <label>类目编号</label>
                            <input type="text" class="form-control" name="categoryType" value="${(productCategory.categoryType)!''}" placeholder="请输入类目编号,只能为数字" />
                        </div>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </div>
</div>
</body>
</html>