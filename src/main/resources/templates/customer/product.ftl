
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <title>茶部落</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <script src="/jquery/jquery-3.1.1.min.js"></script>
    <script src="/jquery/amazeui.min.js" type="text/javascript"></script>
    <link href="/css/amazeui.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
<header data-am-widget="header" class="am-header am-header-default sq-head ">
    <div class="am-header-right am-header-nav">
        <button type="button" class="am-btn am-btn-warning" id="doc-confirm-toggle" style="background: none; border: 0; font-size: 24px;">
            <i class="am-header-icon am-icon-trash"></i>
        </button>
    </div>
</header>
<div class="content-list" id="outer">
    <div class="list-left" id="tab">
        <li><a style="position: relative;">新品推荐<i class="num">8</i></a></li>
        <li><a>芝士奶盖系列</a></li>
        <li class="current"><a>精选奶茶</a></li>
        <li><a>部落奶盐</a></li>
        <li><a>正宗可可奶盖</a></li>
        <li><a>清新果茶</a></li>
        <li><a>招牌榴莲奶盖</a></li>
        <li><a>部落奶盖系列</a></li>
        <li><a>鲜榨果汁</a></li>
        <li><a>创意特饮</a></li>
        <li><a>纯吃茶</a></li>
        <li><a>沙冰系列</a></li>
        <li><a>热饮系列</a></li>
        <li><a>小吃系列</a></li>
    </div>
    <div class="list-right" id="content">
        <ul class="list-pro">
            <li>
                <a href="detail.html"><img src="images/1.png" class="list-pic" /></a>
                <div class="shop-list-mid">
                    <div class="tit"><a href="detail.html">法国加力果12个装 进口新鲜水果 嘎啦苹果 包邮</a></div>
                    <div class="am-gallery-desc">￥52</div>
                </div>
                <div class="list-cart">
                    <div class="d-stock ">
                        <a class="decrease">-</a>
                        <input id="num" readonly="" class="text_box" name="" type="text" value="0">
                        <a class="increase">+</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="detail.html"><img src="images/2.png" class="list-pic" /></a>
                <div class="shop-list-mid">
                    <div class="tit"><a href="detail.html">法国加力果12个装 进口新鲜水果 嘎啦苹果 包邮</a></div>
                    <div class="am-gallery-desc">￥52</div>
                </div>
                <div class="list-cart">
                    <div class="d-stock ">
                        <a class="decrease">-</a>
                        <input id="num" readonly="" class="text_box" name="" type="text" value="0">
                        <a class="increase">+</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="detail.html"><img src="images/1.png" class="list-pic" /></a>
                <div class="shop-list-mid">
                    <div class="tit"><a href="detail.html">法国加力果12个装 进口新鲜水果 嘎啦苹果 包邮</a></div>
                    <div class="am-gallery-desc">￥52</div>
                </div>
                <div class="list-cart">
                    <div class="d-stock ">
                        <a class="decrease">-</a>
                        <input id="num" readonly="" class="text_box" name="" type="text" value="0">
                        <a class="increase">+</a>
                    </div>
                </div>
            </li>
            <li>
                <a href="detail.html"><img src="images/2.png" class="list-pic" /></a>
                <div class="shop-list-mid">
                    <div class="tit"><a href="detail.html">法国加力果12个装 进口新鲜水果 嘎啦苹果 包邮</a></div>
                    <div class="am-gallery-desc">￥52</div>
                </div>
                <div class="list-cart">
                    <div class="d-stock ">
                        <a class="decrease">-</a>
                        <input id="num" readonly="" class="text_box" name="" type="text" value="0">
                        <a class="increase">+</a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
<!--底部-->
<div style="height: 100px;"></div>
<div class="fix-bot">
    <a href="" class="list-js">合计：<i>0元</i><em>(0份)</em></a>
    <a href="" class="list-jsk">选好了</a>
</div>

<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
    <div class="am-modal-dialog">
        <div class="am-modal-bd" style="height: 80px; line-height: 80px;">  您确定要清空饮品吗？</div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>

<script>
    //购物数量加减
    $(function(){
        $('.increase').click(function(){
            var self = $(this);
            var current_num = parseInt(self.siblings('input').val());
            current_num += 1;
            if(current_num > 0){
                self.siblings(".decrease").fadeIn();
                self.siblings(".text_box").fadeIn();
            }
            self.siblings('input').val(current_num);
            update_item(self.siblings('input').data('item-id'));
        })
        $('.decrease').click(function(){
            var self = $(this);
            var current_num = parseInt(self.siblings('input').val());
            if(current_num > 0){
                current_num -= 1;
                if(current_num < 1){
                    self.fadeOut();
                    self.siblings(".text_box").fadeOut();
                }
                self.siblings('input').val(current_num);
                update_item(self.siblings('input').data('item-id'));
            }
        })
    })

    //删除提示信息
    $(function() {
        $('#doc-modal-list').find('.am-icon-close').add('#doc-confirm-toggle').
        on('click', function() {
            $('#my-confirm').modal({
                relatedTarget: this,
                onConfirm: function(options) {
                    var $link = $(this.relatedTarget).prev('a');
                    var msg = $link.length ? '你要删除的饮品 为 ' + $link.data('id') :
                            '确定了';
//        alert(msg);
                },
                onCancel: function() {
                    alert('不删除');
                }
            });
        });
    });

    //tab切换
    $(function(){
        window.onload = function()
        {
            var $li = $('#tab li');
            var $ul = $('#content ul');
            $li.click(function(){
                var $this = $(this);
                var $t = $this.index();
                $li.removeClass();
                $this.addClass('current');
                $ul.css('display','none');
                $ul.eq($t).css('display','block');
            })
        }
    });
</script>
</body>
</html>
