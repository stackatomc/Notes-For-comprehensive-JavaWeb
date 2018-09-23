# 第4章 通过Struts进一步了解MVC

- 标签：后端开发

---

- 开发一个基本的Struts程序
	- Struts是一个实现MVC各部分之间跳转的模板，程序员只要通过编写一些代码和配置文件，就能很方便的实现“从前端数据数据请求->请求跳转->处理请求”等跳转动作
- StrutsMVC框架和JSP+Servlet+JavaBean框架的比较
	- Struts提供一套跳转机制，只需要在Action类里编写返回的字符串，同时在struts.xml里编写返回字符串和跳转页面的对应关系，就能根据业务执行结果方便地跳转回前端页面
- 验证器
	- 第一种：直接在action.java中增加validate方法进行配置
		```
		//加入validate方法
    public void validate(){
        try{
            numI1=Integer.valueOf(num1);
        }catch(Exception e){
            addFieldError("num1","num1 is not a number");
        }
        try{
            numI2=Integer.valueOf(num2);
        }catch(Exception e){
            addFieldError("num2","num2 is not a number");
        }
    }
		```
	- 第二种：使用配置文件验证validation.xml
		```
		<field name="email">
        <field-validator type="email">
            <message>电子邮件地址必须有效</message>
        </field-validator>
    </field>
    <field name="handphone">
        <field-validator type="requiredstring">
            <message>手机号不能为空</message>
        </field-validator>
        <field-validator type="regex">
            <param name="regexExpression"><![CDATA[^1[358]\d{9}$]]></param>
            <message>请填写正确的手机号</message>
        </field-validator>
    </field>
		```
- 过滤器
	- 结合责责任链模式进行实现
	- 完成了一个例子：验证是否来自用户登陆进行过滤+处理页面（但是同上面均无法运行，可能是maven地jar包导入问题）
		- 例子说明：如果是来自用户登陆，继续执行用arg0.invoke()表示不做任何处理；若是其他页面，则判断是否有username，若有则arg0.invoke()继续执行，否则返回login页面
- struts快速进阶
	- struts默认执行execute，也可以传入指定Action里的处理方法`<form method="post" action="loginAction!login">`
	或者
	```
	<action name="userLogin" class="xxx" method="login">
		<result name="success">/success.jsp</result>
		<result name="error">/error.jsp</result>
	</action>
	```
	- type的说明
		- `result name="login" type="dispatcher">/login.jsp</result>`
		- 参数dispatcher：默认值，用来转向页面，通常是跳转到JSP等页面
		- 其他参数如：chain、redirect、redirectAction都涉及跳转
		- 面试会考察相关问题：chain、redirect、redirectAction都涉及跳转，有什么差别？
			- redirect,Action处理完后跳转到另一个资源（如JSP页面），参数会全部丢失，而action处理结果也会全部丢失
			- redirectAction一般是跳转到另一个Action里，同样，跳转后请求参数和action处理的结果也会丢失
			- 如果type是chain，也是跳转到另一个Action，这种情况下，参数会丢失，但action处理结果不会丢失
- StrutsMVC和JSP+Servlet+JavaBean框架的比较
	- （Struts领先）
	- 1  如何再后端接受前端传来地参数，Struts参数组转成ActionForm，并自动发送到Action里相同字段接受，而Servlet需要在Servlet中编写接收参数的代码
	- 2 如何把后端的处理结果再回传到前端，StrutsMVC只需要再struts.xml同意定义即可，而JSP+Servlet+JavaBean需要再Servlet里手动地跳转
	- 3 项目的开发方式，程序员使用StrutsMVC工作量较少，只需要按照使用方法填写Action类和struts.xml文件即可，而Servlet需要考虑所有必要哦的细节如何接收、对应跳转等
- struts的局限性
	- 1 为每一个请求创建一个Action：struts2里回味每个http请求实例化一个Action对象，对处理高并发会有些难度
	- 2 对Action执行前和后的处理支持不大好：比如每次进到ACTION前我们需要打印内存使用量，执行后需要记录跳转目标URL，这个当然可以直接写道Action里，但比较于Spring的AOP处理方式，这种单传把前后处理写道Action里有明显的不足
	- 3 对跳转的支持：如果在一个Action里因为需求需要跳转10个页面，当一旦跳转目标页面进行了修改，需要进行多处修改，无法做到轻量级修改
	- 4 安全性上瑕疵：我们可以通过url的方式直接执行action中的方法，但一般应该先通过用户权限的判断才能执行，虽然可以通过调用定义拦截器弥补这个问题，但增加了工作量`http://url:7979/proName/userLogin!list`
	- 5 属于侵入式：在使用Struts时，需要继承struts的类，而且编写execute方法，这样Struts框架就侵入到项目里了，比如做的是银行业务，如果哪天要把这套业务一直到其他Spring等框架项目里就比较大了
- 关于Struts框架的常见面试点
	- 现在Struts不经常被使用，因为小型的项目多采用JPS+Servlet+JavaBean+DB的开发模式，一些大型基于企业级的项目往往采用Spring+MyBatis的开发模式
	- Struts由于不经常被使用，优势并不是能导致多被使用。而如果候选人会使用Struts可以加分，当问到为什么仍在最近的商业项目上使用Struts，容易答巧成拙，建议回答由于历史原因或者客户要求等，这个还想还是得用Struts项目
	- 涉及面试试题，建议结合项目回答（应该是推荐举出实现的例子）
		- 第一，说出在项目里Struts作为拦截器和验证器是怎么使用的
			- 回答：如项目中，验证器有在Action中定义validate方法或者使用xml文件validation.xml，提到email regex等...
			- 拦截器则是提到责任链模式，arg0.invoke()，还可以说上Struts的缺点
		- 第二，结合项目，说明自己做了哪些工作，比如在Action里，你怎么和业务以及数据库代码耦合？
			- 回答：Action充当的是控制器C层，所以通过调用数据访问层的dao接口即可
		- 第三，结合项目，告诉考官一些技术细节，比如拦截器是怎么使用的，在该项目里结合一个需求，告诉考官拦截器的工作流程和开发方式。或者结合项目说明下验证器的用法
			- 回答：举出验证是否来自登陆界面的例子，用Struts+责任链涉及模式进行实现
		- 第四，说明使用Struts乱国家给这个项目带来那些痛点
			- 1 为每个请求创建一个Action，从请求Action的方式
			- 2 对Action执行前的后的处理支持不大好，前要记录内存量后要记录跳转目标URL，相比较于Spring的AOP处理
			- 3 对跳转的支持导致无法做到轻量级修改
			- 4 安全问题，不需要经过需求判断便直接在url中执行Action，若要进行定义拦截器处理可以，但会增加工作量
			- 5 属于侵入式。
>来自 Swen码农 的CSDN 博客 [https://blog.csdn.net/a3025056/article/details/52790054?utm_source=copy](https://blog.csdn.net/a3025056/article/details/52790054?utm_source=copy)
>使用struts的时候，我需要继承一些struts的类，这时struts侵入到了我的代码里。 
使用spring，编写一些业务类的时候不需要继承spring特定的类，通过配置完成依赖注入后就可以使用，此时，spring就没有侵入到我业务类的代码里。 
侵入式让用户代码产生对框架的依赖，这些代码不能在框架外使用，不利于代码的复用。但侵入式可以使用户跟框架更好的结合，更容易更充分的利用框架提供的功能。 
非侵入式的代码则没有过多的依赖，可以很方便的迁移到其他地方。但是与用户代码互动的方式可能就比较复杂。 
这两种方式都有各自的优缺点吧，主要看实际开发时怎么权衡了。
		- 第五，和Spring框架相比，你感觉各自有什么优缺点，请结合项目说明，不要空虚
		- 第六，你的项目会经常扩展，业务实现方式也经常变更，结合Struts框架说明一旦出现变更了你需要做哪些事？
			- 回答：比如需要更改业务，你该更改那些文件，一旦更改了代码，如何部署到服务器上？通常需要重启tomcat才可以修改生效，但可以在项目中
			>解决方法：把struts.xml的struts标签内加入以下配置< constant name="struts.devMode" value="true" />
		- 第七，这个项目的访问量是多少？最高的并发访问量能达到多少？Struts框架能否很好的处理高并发的情况?
			- 回答：不能，因为Struts为每一个请求都实例化一个Action对象。（每个request会new ACTION instance这还算小case）
关键是每次request都要反射、都要数道拦截、都要new ACTION、都要new proxy
- 本章技术总结
	- 1 在Struts2里，如何实现一个Action？
	- 2 怎么指定进入Action后该调用哪个方法？
	- 3 定义验证器的步骤是什么？
	- 4 定义拦截器的步骤是什么？
	- 5 Struts2中的type类型有那些？如果不写type，默认是什么？
	- 6 如何通过配置type类型，实现一个Action往另一个Action的跳转？
	- 7 描述下StrutsMVC的工作流程和开发模式
	- 8 和JSP+Servlet+JavaBean的开发模式相比，StrutsMVC有哪些好处，同时，说明下Struts框架有哪些不足？