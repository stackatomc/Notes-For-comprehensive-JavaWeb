# 第三章 JSP+Servlet+JavaBean框架

标签：后端开发

---

`本章代码见codes/chapter03`

- 纯JSP实现
	- 注意jsp指令[jsp页面中<%@ %>、<%! %>、<% %>、<%= %>、<%-- --%>的意思](https://blog.csdn.net/jzw230726/article/details/56669838)
	- 与JDBC数据库连接，但直接连接数据库十分大而全，无架构思想非常不好
	- 缺点：代码容易重复，切换JSP和HTML逻辑，导致阅读和开发上的困扰，使别人维护起来较难；当增加某个限制，如加入验证码时，需要对页面的多处重复代码进行修改，增加代码维护的难度；要改成Oracle数据库，所有涉及数据库的代码都要进行修改；等
- JSP+Servlet实现
	- 注意设置交互，表单form的action设置和web中servlet如何配置；通过pageEncoding="GB18030"设置编码方式支持中文输入；doGet、doPost区别；hidden使用表单隐藏type交互；
	- Servlet的基本运行过程：客户端通过HTTP协议向Web服务器发送请求，Web服务器接受该请求并将其发给Servlet；如果Servlet尚未被加载，则Web服务器将把它加载到Java虚拟机并执行它；随后Servlet接受该HTTP请求并执行相应的处理；Servlet向Web服务器返回应答，Web服务器把从Servlet收到的应答发给客户端
	- Servlet的生命周期：有三个，init，service，destory。当被服务器实例化时，容器运行它的init方法；当request到达时，运行service方法，执行对应的doPost和doGet方法；当服务器决定将实例销毁时，调用其destory方法
	- 关于Servlet多线程是否安全
		- 不安全,并发处理时，Servlet用“单实例多线程”
		- 第一、可以有多个Servlet处理业务请求，对不同业务请求可定义多个Servlet处理
		- 第二、Servlet时单实力的，对于同一种业务请求只有一个Servlet是实例
		- 第三、同一个Servlet可以同时处理多个客户端的请求，比如有两个客户端同时登陆，会启动两个负责登陆的Servlet线程，并通过触发Service方法来处理请求。不同线程中接收到的信息是不同的
		- 关于线程是否安全具体：再Servlet里，负责保存上下文的ServletContext和负责处理Session兑现过的HttpSession是线程不安全的，而处理请求的ServletRequest似乎线程安全的
		- 一些保证线程安全的做法
			- 有一个SingleThreadMedel接口，由于效率低下已经被Servlet贵伐抛弃，当Servlet实现这个接口，可保证再一个时刻仅有一个线程可以再给定的Servlet实例的service方法中执行
			- 由于ServletRequset是线程安全的，，每个Servlet线程胡i创建自己的ServletRequest，所以尽量再Servlet中使用局部变量，即有单属于本Servlet的对象
			- 在多个Servlet中，如果要同时对外部对象（比如文件）进行i需改，则一定要加锁，做到互斥的访问。但由于Servlet一般只负责跳转，不大负责业务处理，所以这点在实际开发国臣各种很少用到
			- 对于需要保护的实例，用synchronized加以保护，但需要尽量缩小保护的范围
	- 苦其心志，劳其体肤，饿其肥肉
- JSP+Servlet+JavaBean+JDBC实现
	- 注意useBean的使用
	- 思考：表单设计要结合之前所学Integer和int的差异，对输入做限定，避免出现为null的情况（书上这点没说），当数据库查找时抛出异常


- 本章知识点常见面试问题
	- 问题1 Servlet中forward和redirect有何区别？
		- 回答：forward是转发，URL不变，是服务器请求资源,服务器直接访问目标地址的URL,把那个URL的响应内容读取过来,然后把这些内容再发给浏览器；redirect是重定向，显示新的URL，是服务端根据逻辑,发送一个状态码,告诉浏览器重新去请求那个地址
		- request.getRequestDispatcher("new.jsp").forward(request, response);   //转发到new.jsp
		- response.sendRedirect("new.jsp");   //重定向到new.jsp
	- 问题2 Servlet里doPost和doGet的区别
		- 回答：get传递参数简单，但不适合出啊你的敏感数据；post是把整个form表单传送给服务器端，post适合发送大量的数据
	- 问题3 有哪些方式可以实现页面的跳转？forward和sendRedirect有什么差异？参考问题1
	- 问题4 说一下Servlet的生命周期
		- 回答：3个，简要说明执行过程（见上笔记）