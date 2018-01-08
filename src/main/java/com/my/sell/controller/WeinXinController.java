package com.my.sell.controller;

import com.my.sell.enums.ExceptionEnum;
import com.my.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;



/**
 * created by hzheng on 2017/7/24.
 */
@Controller
@RequestMapping(value = "/wechat")
@Slf4j
public class WeinXinController {

    @Autowired
    WxMpService wxMpService;

    /**
     * 根据access_token获取openid
     * @param code
     */
    @GetMapping(value = "/auth")
    public void auth(@RequestParam("code") String code){
        log.info("code:{}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx5f39f46506c5635f&secret=f4f349786d99ba632895f2990f7ecff5&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url, String.class);
        log.info("forObject:{}",forObject);
    }

    /**
     * 构造网页授权url
     * @param returnUrl
     * @return
     */
    @GetMapping(value = "/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) throws Exception{
        log.info("returnUrl:{}",returnUrl);
        String url = "http://selltngh.natapp4.cc/wechat/userInfo";
        String redirectUrl =wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_BASE, returnUrl);
        log.info("redirectUrl:{}",redirectUrl);
        return "redirect:"+redirectUrl;
    }

    /**
     * 获得openid
     * @param code
     * @param returnUrl
     * @return
     */
    @GetMapping(value = "/userInfo")
    public String userInfo(@RequestParam("code") String code,@RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch(WxErrorException e){
            log.error("微信网页授权,获取assessToken失败:{}",e);
            throw new SellException(ExceptionEnum.WECHAT_TOKEN_ERROR,e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("openid:{}",openId);
        return "redirect:"+returnUrl+"?openid="+openId;
    }
}
