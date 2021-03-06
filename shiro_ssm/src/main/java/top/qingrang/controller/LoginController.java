package top.qingrang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class LoginController {

	/**
	 * 登录，获取账号密码进行验证
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, String name, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
            System.out.println("UsernamePasswordToken:");
            System.out.println("hashCode:" + token.hashCode());
            System.out.println("Principal:" + token.getPrincipal());
            System.out.println("Credentials:" + String.valueOf((char[]) token.getCredentials()));
            System.out.println("host:" + token.getHost());
            System.out.println("Username:" + token.getUsername());
            System.out.println("Password:" + String.valueOf(token.getPassword()));
            // 执行登录.
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			return "redirect:index";
		} catch (AuthenticationException e) {
			model.addAttribute("error", "验证失败");
			return "login";
		}
	}
	
	@RequestMapping("ajax")
	@ResponseBody
	public String ajaxList(Model model) {
		String data = "shiboxue";		
		return data;
	}
}
