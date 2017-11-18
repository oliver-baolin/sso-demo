package com.maven.sso.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maven.sso.model.UserInfo;
import com.maven.sso.service.SessionProvider;
import com.maven.sso.service.UserService;
import com.maven.sso.util.RequestUtils;

/**
 * 登录入口
 * 
 * @author Zhang
 *
 */
@Controller
public class LoginController {

	@Autowired
	private SessionProvider sessionProvider;
	@Autowired
	private UserService userService;

	/**
	 * 去登录界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.aspx", method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}
	
	//判断用户是否登陆
		@RequestMapping(value = "/isLogin.aspx")
		public @ResponseBody
		MappingJacksonValue isLogin(String callback,HttpServletRequest request,HttpServletResponse response){
			Integer result  = 0;
			//判断用户是否已经登陆
			String username = sessionProvider.getAttributeForUsername(RequestUtils.getCSESSIONID(request, response));
			if(null != username){
				result = 1;
			}
			MappingJacksonValue  mjv = new MappingJacksonValue(result);
			mjv.setJsonpFunction(callback);
			return mjv;
			
		}

	/**
	 * 提交登录
	 * 
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login.aspx", method = RequestMethod.POST)
	public String login(String username, String password, HttpServletRequest request, 
			HttpServletResponse response,Model model) {

		// 1:用户名不能为空
		if (null != username) {
			// 2:密码不能为空
			if (null != password) {
				// 3:用户名必须正确
				UserInfo buyer = userService.queryUserInfo(username, password);
				if (null != buyer) {
					// 4:密码必须正确
					if (buyer.getPassword().equals(encodePassword(password))) {
						// 5:保存用户名到Session中(Redis中）
						sessionProvider.setAttribuerForUsername(RequestUtils.getCSESSIONID(request, response),buyer.getUserName());
						// 6:跳转到之前访问页面
						// return "redirect:" + returnUrl;
						return "index";
					} else {
						model.addAttribute("error", "密码必须正确");
					}

				} else {
					model.addAttribute("error", "用户名必须正确");
				}

			} else {
				model.addAttribute("error", "密码不能为空");
			}

		} else {
			model.addAttribute("error", "用户名不能为空");
		}
		return "login";
	}

	// 加密 （密码过于简单）有规则密码
	public String encodePassword(String password) {
		//
		// password = "gxzcwefxcbergfd123456errttyyytytrnfzeczxcvertwqqcxvxb";
		// 1:MD5 算法
		String algorithm = "MD5";
		char[] encodeHex = null;
		try {
			// MD5加密
			MessageDigest instance = MessageDigest.getInstance(algorithm);
			// 加密后
			byte[] digest = instance.digest(password.getBytes());
			//
			// 2:十六进制
			encodeHex = Hex.encodeHex(digest);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(encodeHex);
	}
	
	public static void main(String[] args) {
		LoginController l = new LoginController();
		String w = l.encodePassword("123456");
		System.out.println(w);
	}

}
