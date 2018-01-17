<html>
<head>
    <meta charset="utf-8">
    <#include "../common/header.ftl">
    <script src="/jquery/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        var mobileReg;
        $(function(){
            $("#mobile").click(function(){
                var phoneReg = $("#phone").val();
                $("#phone").text(phoneReg);
                $.post("/sell/seller/user/getMobileText",{mobile:phoneReg},function(data){
                    mobileReg = data;
                });
            })


            $("#buttonR").click(function(){
                var phoneReg = $("#phoneReg").val();
                console.log(mobileReg);
                if(mobileReg == phoneReg){
                    $("#submitR").submit();
                }else{
                    alert("验证码错误");
                }
            });

        })
    </script>
</head>
<body>
<div id="wrapper" class="toggled">
    <!-- 边栏 -->
    <#include "../common/nav.ftl">
    <!-- 主体内容 -->
    <br/>
    <div class="col-md-5 column"><h5><strong>&nbsp当前位置:用户注册</strong></h5></div>
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/user/register" method="post" id="submitR">
                            <div class="form-group">
                                <label>用户账号</label>
                                <input type="text" class="form-control" name="userName"  placeholder="请输入账号" style="width:300px;"/>
                            </div>
                            <div class="form-group">
                                <label>登录密码</label>
                                <input type="password" class="form-control" name="password"  placeholder="请输入密码" style="width:300px;"/>
                            </div>
                            <div class="form-group">
                                <label>短信验证</label>
                                <input type="text" class="form-control" name="phone"  id="phone" placeholder="请输入手机号码" style="width:300px;"/>
                                <button id="mobile">获取短信验证码</button>
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" name="phoneReg"  id="phoneReg" placeholder="请输入验证码" style="width:300px;"/>
                            </div>
                            <button class="btn btn-default btn-primary" id="buttonR">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>