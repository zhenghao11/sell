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
        <div class="col-md-5 column"><h5><strong>&nbsp当前位置:新增/修改商品</strong></h5></div>
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/product/addOrUpdate" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="productId" value="${(productInfo.productId)!''}">
                            <div class="form-group">
                                <label>商品名称</label>
                                <input type="text" class="form-control" name="productName" value="${(productInfo.productName)!''}" placeholder="请输入商品名称,不能存在空格"/>
                            </div>
                            <div class="form-group">
                                <label>商品单价</label>
                                <input type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}" placeholder="请输入价格,最多两位小数" />
                            </div>
                            <div class="form-group">
                                <label>商品库存</label>
                                <input type="number" class="form-control" name="productStock" value="${(productInfo.productStock?c)!''}" placeholder="请输入库存,只能是数字" />
                            </div>
                            <div class="form-group">
                                <label>商品描述</label>
                                <input type="text" class="form-control" name="productDescription" value="${(productInfo.productDescription)!''}" placeholder="请输入商品描述" />
                            </div>
                            <div class="form-group">
                                <label>商品图片</label><br/>
                                <#if (productInfo.productIcon)??>
                                    <img height="100" width="100" src="${productInfo.productIcon}"><br/><br/>
                                </#if>
                                <input type="file" name="file"/>
                            </div>
                            <div class="form-group">
                                <label>商品状态</label>
                                <select name="productStatus" class="form-control">
                                    <#if (productInfo.productStatus)?? && productInfo.productStatus == 0>
                                        <option value="0" selected>上架</option>
                                        <option value="1" >下架</option>
                                    <#elseif (productInfo.productStatus)?? && productInfo.productStatus == 1>
                                        <option value="0" >上架</option>
                                        <option value="1" selected>下架</option>
                                    <#else>
                                        <option value="0" selected>上架</option>
                                        <option value="1" >下架</option>
                                    </#if>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>类目名称</label>
                                <select name="categoryType" class="form-control">
                                    <#list productCategoryList as productCategory>
                                        <option value="${productCategory.categoryType}"
                                                <#if (productInfo.categoryType)?? && productInfo.categoryType == productCategory.categoryType>
                                                    selected
                                                </#if>
                                        >
                                            ${productCategory.categoryName}
                                        </option>
                                    </#list>
                                </select>
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