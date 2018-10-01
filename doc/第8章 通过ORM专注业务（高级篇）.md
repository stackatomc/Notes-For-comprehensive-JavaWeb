# 第8章 通过ORM专注业务（高级篇）

标签：后端开发

---

- 一对一关联
	- 在父对象Person的hbm.xml配置文件中加入`<one-to-one name="Card" class="com.model.Card" cascade="all"/>`，cascade="all"表示父对象Person创建将自动创建子对象Card，父对象删除将自动删除子对象Card
	- 或使用注解在Model中加入
	```
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="CardID")
	private Card card;
- 双向一对一关联
- 一对多关联
	- 配置文件，返回List
	```
	<list name="cardList" inverse="true" table="Person" cascade="all">
	<key column="PersonID"/>
	<list-index column="CardIndex"/>
	<one-to-many class="Model.Card"/>
	</list>
	```
		- 注意inverse=true时要显式写出，表示父对象维护关联子对象，即父删子自动删；否则默认为false，则对应子对象仅仅去掉与父对象有关系的联系如此处将CardIndex置为空。CardIndex是描述多方索引次序的字段
	- 注解，返回Set
	```
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PersonID")
	private Person person;

	操作类中获取返回结果为Set
	Set<Card> cards=new HashSet<Card>();
	card.setPerson(person);
	cards.add(card);
	person.setCards(cards);
	session.save(person);
	session.flush();
	```
	- 配置文件，返回Map实现也可以
- 多对多关联
	- 配置文件`<many-to-many class="Model.Course" column="course_id"/>`
	- 注解
	```@ManyToMany 
	@Cascade(value={org.hibernate.annotations.CascadeType.SAVE_UPDATE}) 
	@JoinTable{name="Students_Courses",joinColumns=(@JoinColumn(name="StudentID")},inverseJoinColumns={@JoinColumn(name="CourseID")})
	private Set<Course> courses;	
	```
- 关联性操作里的cascade取值
	- 在配置文件中，常用到的cascade选项分别是all、delete、none、save-update和delete-orphan
- inverse参数主要用于一对多、多对多的关系
	- one-many这种情况 默认的情况是inverse=false 所以应在多的一方维护
- Hibernate里的事件机制
	- 由两部分组成：第一是拦截器机制，它能拦截特定的动作拦截，并能在这些动作发生时调用我们自己定义的方法；第二是事件系统，通过它我们能重写Hibernate的事件监听器
	- 实现方法：
		- 1 首先自定义一个拦截器类，该类需要继承EmptyInterceptor类实现
		- 2 在自定义拦截器里，通过重写方法来定义针对特定事件（如重写onSave(...)方法，afterTransactionBegin(...)事务开始时执行的回调方法等）
		- 3 在使用时，可对seesionFactory指定拦截器或者对单个session指定
		```
		//1. sessionFactory=cfg.setInterceptor(interceptor).buildSessionFactory();
		//2. sessionFactory=cfg.buildFactory();
		session=sessionFactory.withOptions().interceptor(interceptor).openSession();
		```
	- 可重写的常用方法如：onLoad(...),preFlush(),postFlush(),onFlushDirty(...)当session对象的flush()进行脏数据检查若持久化对象发生改变时会调用
- 此处事件机制的面试点
	- 第一 事件机制你是用哪种方式？拦截器还是监听器
	- 第二 你是怎么用的？比如在仙姑美丽时怎么实现拦截器的？比如怎么继承什么类?重写了哪些回调函数等？落实代码细节
	- 第三 为什么要用？用了以后给项目带来哪些好处？或者你项目里有哪些需求最好用拦截器实现？
	- 第四 拦截器有基于Session和sessionFactory的，你的项目用的是哪一种？为什么要用这种？
- 事件系统和监听器
	- 通过监听器机制来监听Hibernate的各动作并执行对应的回调函数，监听器的实现原理是为每一个事件（比如更新、添加、删除等）注册一个或多个监听器，一旦事件发生，则通知所有相关的监听器执行对应的回调方法
	- 与拦截器对比，监听器可以获得被监听对象修改前和修改后的值，而且，监听器可以通过Event对象得到Session对象
	- 实现方法：
		- 1 自定义监听器实现接口，常用如MergeEventListener、DeleteEventListener、PersistEventListener、EvictEventListener、SaveUpdateEventListener...等，实现该方法
		- 2 在hibernate.cfg.xml中配置监听器
- Hibernate的优化
	- 策略一 调整批处理的条数，一般认为，Oracle数据库的JDBC驱动的默认值是15，当设置为30或50时，性能会有明显提升，如果继续增大，超出100，则性能提升就不明显了`<prop key="hibernate.jdbc.batch_size">50</prop>`
		- 回答可参考通过新建3个表，插入几万条数据，然后写测试程序，对3个表进行关联查询，随后不断调整这个数值，通过打印运行时间和内存使用量等参数，查看哪个值最合适。通过对删除、更新、插入等操作的测试，最终发现duiOracle来说比较适合的值是100
	- 策略二 数据库本身的优化不可少
		- 数据库优化策略常见如适当地建立索引，如果数据量很大就少用关联，或者适当地建立分区
	- 策略三 如果操作很复杂，可以使用SQL，必要时可以绕过Hibernate直接操作JDBC
		- 回答参考虽然HQL也可以做where group by等操作，但如果HQL过于复杂，不仅影响代码可读性，还可能导致性能无法衡量。所以遇到这种情况，建议使用SLQ，因为可以事件在数据库层面，通过执行计划等工具，查看并优化SQL，随后把优化过的语句放入Hibernate
		- 当项目里有大量的insert/update/delete操作时，则可以绕过Hibernate直接操作JDBC，性能会更好，如
		```
		Connection conn=session.connection();
		PreparedStatementstmt=conn.preparedStatement("update...")
		```
- Hibernate中的事务管理
	- 编程式事务管理方法：如果修改，由于编程式事务管理代码紧紧与业务代码联系，不容易维护修改
	- 通常更多的使用声明式事务管理方法
- 使用SessionFactory二级缓存
	- 刚才的一级缓存：可调用evict(Object obj)清除指定或clear()清空缓存中所哟u的持久化对象
	- 二级缓存是SessionFactory级别的，一般不启用。并不是所有的对象都适合放在二级缓存中
		- 适合：1 很少被修改的数据，比如历史数据；2参考数据，比如项目里的常熟对应表；3 不会被并发访问的数据
		- 不适合：1 经常被修改的数据；2 绝对不允许出现并发操作的数据
	- 实现方法
		- 1 导入必要的jar包，Hibernate默认使用EhCache作为二级缓存组建，还有其他如Hashtable（类型不同，EhCache类型是可写入内存和硬盘）
		- 2 在hibernate.cfg.xml配置文件中加入session-factory元素，用于配置二级缓存
		- 3 需要额外在src目录下编写ehcache.xml用于配置EhCache二级缓存的具体属性值
		- 4 在类与数据库的配置文件中通过cache元素指定针对该类数据的二级缓存策略，如<cache usage="read-only">
		- 5 写HibernateMain，可看当先后用两个不同session对象执行get方法，Hibernate只想数据库发起了依次select请求，第二次实际上是从二级缓存里得到数的
- 项目中常用的优化策略
	- 第一 在核实的时间点清楚缓存
		- 回答主要答一级缓存Session，如果缓存太大时会拖累性能，所以必要时使用evict或clear清除缓存
	- 第二 在不知道返回结果时多少时，谨慎使用list，因为如果一旦返回数目过大，就会超出内存，导致OOM异常；在数据返回量大的情况下，可以通过分页的形式，，用多次请求分批返回结果
	- 第三 在一对一和多对多的关系中，在加载时，可以使用延迟加载机制，这样能使不少对象只有在使用时才会被初始化，从而可以节省内存空间
	`@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="person")`
	- 第四 在编写读数据的代码之前，对SQL或HQL语句要操作的表做个评估，比如要关联多个大表时，建议不要使用HQL的关联代码，而是使用SQL
- 回答性能调优方面的问题时
	- 1 在遇到性能瓶颈时，可以在一些疑似性能问题的代码前后输出当前时间和当前内存使用量，由此判断出这段代码的运行市场和内存占用量
	- 2 在找到问题症结后，可以通过直接使用JDBC API，或则和通过观察执行计划来优化SQL语句
- 在Hibernate的面试点
	- 第一 需要候选人初级时会用Hibernate，能在项目经理带领下做项目；高级时不仅要熟悉用法，还要根据项目里数据表的需求，适当地选用技术种类，而且一旦出现性能问题，能直到怎么排查和调试
	- 第二 当候选人在Hibernate经验不足一年或近期无使用时，会着重问技术上地细节，由此来确认他在ORM方面地能力
	- 第三 根据项目提问，比如1 在项目里用的是哪个版本?对应的数据库是什么？
	- （续上）2 你们使用注解还是用排至文件的方式编写映射文件？
	- 3 在项目里，你们大多使用HQL还是SQL的方式获取数据
	- 4 你们项目的数据规模有多大？一张表里最多有多少数据？你们项目里通过Hibernate装在的数据量一般是多少？
	- 第四 深入提问如1 在项目里是否用过一对一、一堆多或多对多关联？相关的配置文件该怎么写？
		- 如果没有用过，可说出合理理由，如项目数据量比较少，业务比较简单，项目经理认为没有必要
	- 2 在配置一对一、一对多、多对多关联时，cascade和inverse该怎么配？结合项目需求阐述inverse该配在一方还是多方？
	- 3 在一对多或者多对多关联的情况下，在一方这端，你们使用set、list还是map或者时其他什么类型来装在多方的数据？说明选用得的理由
	- 4 阐述Session缓存里的三种对象状态？session的一些重要方法，比如flush、save、persist、clear、evict的作用？save、persist和saveOrUpdate这三个方法的不同之处？
	- 5 是否掌握三种状态之间转换的对应session里的方法
	- 6 session的load和get方法有什么差别？比如在一对多情况下， 如果在加载一方的时候，不想加载多方，那么该怎么办？通过这个问题，来确认候选人是否具备基本的调优技能
	- 7 在项目里，是否用到了hibernate的拦截器或者监听器？为什么要用？在拦截器和监听器里，你们实现了什么功能？如果都没用过，不会成为扣分项。如果用过，而且在拦截器和监听器里加入的功能确实有必要，那么这个会成为加分项
	- 第五 其他基础问题，比如1 在hibernate里你们怎么实现事务？你们一般用到了哪些注解？主键生成策略是?
	- 第六 考察是否为高级程序员的性能调优问题，如1 你们项目里用到的时一级缓存还是二级缓存？如果时二级缓存，那么用到的时什么组件？一般你们是把项目里的什么数据放入二级缓存？
	- 2 在hibernate里，你们在性能优化的方面，做了哪些方面的事情，胡总额和你们在写代码的时候，如何保证hibernate操作数据库的性能？
	- 3 在项目里，你们一般怎么监控hibernate操作数据库的性能？
		- 具体措施可以时输出各SQL的运行时间；也可以监控数据库本身， 比如一旦连接数过多，或者出现死锁情况，就发报警邮件；也可以监控项目内存和数据库锁在服务器的内存使用情况，如果使用量过高，发报警邮件
	- 4 在你们项目里，一旦出现性能问题，你们怎么排查定位？
		- 一般是会在各方法运行前后打印时间戳和内存使用情况，出现问题后查看日志可以定位到究竟是哪个方法、哪个SQL/HQL导致的问题