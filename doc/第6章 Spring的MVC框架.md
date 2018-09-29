# 第6章 Spring的MVC框架

标签：后端开发

---

- SpringMVC与StrutsMVC对比
	- SpringMVC耦合度较StrutsMVC低
- 相关重要概念
	- @RestMapping() 在控制器中出现，处理请求
	- SpringMVC的配置文件，配置文件名字可以随便起，一般放在和web.xml相同的目录里
		- 3种控制器类：SimpleUrlHandleMapping、BeanNameUrlHandlerMapping、AnnotationMethodHandlerAdapter
		- 注意参数prefix( value="/")和suffix( value=".jsp").一般起名是根据web.xml对SpringMVC的servlet-name配置，命名如spring-servlet.xml
- ModelAndView返回视图结果，常见用法
	- 1 用来重定向 return new ModelAndView("redirect:/4.4.htm")在客户端重定向，此时应该重新请求另一个URL，地址栏发生变化（若把redirect换成forward,则表示处理转发，是服务器直接访问目标地址的URL，把那个URL的返回内容读取过来然后放置到浏览器上，用这种方式地址栏的URL保持不变）
	- 2 在不同控制器之间跳转 类似Struts可以在Action种跳转到另一个Action，ModelAndView对象实现跳转到另一个控制器用return new ModelAndView("redirect:/displayAllUsers");
	- 3 在不同控制器之间带参数跳转(可能参数是方法中刚计算得) 第一种实现方法是在URL手动拼装参数，类似如new ModelAndView("redirect:/displayAllUsers?owner="+Tom+"&type="+1);第二种是通过RedirectAttributes来传递，如
	```
	public String displayCart(RedirectAttributes attr){
		attr.addAttribute("owner","Tom");
		return "redirect://displayAll";
	}
	```
		- 深入：第二种做法如果用户按F5刷新页面，会重复提交同样的数据。可以通过额外的代码规避这个问题，也可以通过RedirectAttribtues类的addFlashAttribtue方法来实现，该方法的做法是把参数放在Session中，跳转后再溢出。而再目标方法中通过@ModelAttribute注解来获取，如
		```
		public String displayCart(RedirectAttributes attr){
		attr.addFlashAttribute("owner","Tom");
		return "redirect://displayAll";
		}

		public String displayAll(@ModelAttribute("owner") String owner){
			System.out.println(owner)
			}
		```
- SpringMVC应用中的拦截器（Spring拦截器在web应用里的用法）
	- 拦截器使用场景：
		- 1 记录请求信息，以便进行信息监控和信息统计
		- 2 检查权限，比如检测请求进入之前是否登陆，如果没有可以返回到登陆页面
		- 3 监控性能，比如可以通过拦截器记录请求进入处理器的开始时间，在处理后再记录结束时间，由此可以统计出该请求的处理时间
	- 接口HandlerInterceptorAdapter拦截URL，接口MethodInterceptor接口针对方法调用进行拦截
	- 项目实践中注意将不希望让用户访问的页面放在/WEB-INF中,可以直接通过url直接访问的放在webroot根目录即可
- SpringWeb方面的常见问题
	- 问题一 你们的项目时如何搭建SpringWeb框架的，具体而言，如何定义控制器类，视图解析器有几个，一般是怎样定义的？
		- 回答 结合项目的具体需求说下整个Web的处理流程，比如银行项目，如一个具体的存钱请求，怎么发URL，URL会经过什么样的拦截器处理，然后怎么被控制器类接受处理，最后怎么经过视图解析器，从而把结果返回到前端页面
	- 问题二 在项目里，