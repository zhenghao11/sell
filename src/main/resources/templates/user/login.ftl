<html>
<head>
    <meta charset="utf-8">
    <#include "../common/header.ftl">
    <script type="text/javascript" src="/jquery/jquery-3.1.1.min.js" />
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"/>
    <script type="text/javascript">
        function register(){
            window.location.href = "/sell/seller/user/goToRegister"
        }
    </script>
</head>
<body>
<div id="wrapper" class="toggled">
    <!-- 边栏 -->
<#include "../common/nav.ftl">
    <!-- 主体内容 -->
    <br/>
    <div class="col-md-5 column"><h5><strong>&nbsp当前位置:用户登录</strong></h5></div>
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/user/login?returnUrl=${(returnUrl)!''}"  method="post">
                            <div class="form-group">
                                <label>用户账号</label>
                                <input type="text" class="form-control" name="userName"  placeholder="请输入账号" style="width:300px;"/>
                            </div>
                            <#if (userNameMsg)??>
                                <h5 style="color:red;">${userNameMsg}</h5>
                            </#if>
                            <div class="form-group">
                                <label>登录密码</label>
                                <input type="password" class="form-control" name="password"  placeholder="请输入密码" style="width:300px;"/>
                            </div>
                            <#if (passwordMsg)??>
                            <h5 style="color:red;">${passwordMsg}</h5>
                            </#if>
                            <input type="submit" class="btn btn-default btn-primary" value="登录" style="width:80px;">
                            &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
                            <input type="button" class="btn btn-default btn-primary" onclick="register();" value="注册" style="width:80px;">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>







</html>