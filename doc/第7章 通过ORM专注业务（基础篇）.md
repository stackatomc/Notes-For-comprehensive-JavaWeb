# 第7章 通过ORM专注业务（基础篇）

标签：后端开发

---

- ORM概念
	- ORM是对象关系映射的缩写，用这种技术可以把数据库里的数据映射成Java对象（比如链表Map或Set等），从而能让我们在业务代码里通过操作Java对象而不是数据库来实现业务，使得我们更专注于业务本身，而不是更多地关注业务背后所对应的数据库操作
- Hibernate实现ORM
	- 两种方式：配置文件和注解
	- 重要对象：
		- SessionFactory 根据设计模式里的工厂模式创建Session绘画
		- Session 表示会话，和Web应用不同的是，Hibernate中绘画的主要任务是操作数据库，包括对表的增删改查和事务操作等
	- 使用方法：	
		- 一个Model类，一个关联表和类的配置文件.hbm.xml，一个.cfg.xml，一个工厂操作类（要注意使用事务，且使用完记得关闭
		- 常用注解@Entity @Table @Id @Column @Transient表示不被映射
	- Hibernate生成主键的常用方式：
		- `<generator class="increment"/>`不适合并发更新数据库的场景
		- 使用hilo是最常用的生成方式，从0到max_lo循环，差值为1，根据公式计算出主键值hi*(max_lo+1)+lo
		```
			<id name="id" column="id">
			<generator class="hilo">
			<param name="table">hibernate_hilo</param>
			<param name="column">next_hi</param>
			<param name="max_lo">100</param>
			</generator>
			</id> 
		```
		- 表明主键由数据库自己生成，通过配置数据库实现
		<generator class="identity"/>
		- native表明Hibernate根据使用的数据库自行判断采用identity、hilo、sequence其中一种作为逐渐生成方式，了灵活性很强
		<generator class="native"/>
		- sequence由数据库提供的sequence机制生成主键，不过只有当数据库支持时才可以，Oracle支持，MySQL不支持
- Hibernate中Session的操作
	- save()
	- load和get方法的差别
		- load并不会马上执行，且Hibernate认为该数据一定存在，使用代理来延迟加载，但如果在使用时发现了问题则只能抛异常
		- get方法马上获取，若无否则返回null
		- 对比：get比load安全，但时安全是要牺牲性能作为代价的
	- Session缓存与三种对象状态
		- 调用Session对象的方法操作数据库，是为了提升访问数据库的效率，Session会开辟一块内存空间作为缓存，在这个缓存里存放着一些和数据表像关联的JavaModel对象。Session缓存内置，也叫Hibernate的第一级缓存，常规情况是是由Hibernate维护无需人工干预
		- 三种状态：临时、持久化、游离
			- 临时：在上文创建出来，但还没更新到数据表里，也不处于Session缓存
			- 持久化：已经通过save()被保存在数据库
			- 游离：已经通过save被持久化后，close掉Session，虽然该对象仍与数据库的一条记录有映射关系，但此时Session已经关闭，所以属于游离状态
	- saveOrUpdate()方法较为灵活
	- save和persist方法的区别，save会返回刚插入记录的id值，persist方法没有返回值，因为persist方法并不保证标识符被立即加入到持久化实例中，标识符的加入动作可能被推迟到用flush方法的时候
	- session.flush() 注意当没有使用事务无tx.commit代码时要显示强制把Session缓存里的修改同步到数据库里。一定注意这点很容易隐藏出错，因为由于不会报异常，不及时同步，数据极有可能在后来做更改才被插入造成数据不一致的问题
	- session.evict(eciataCC); evict方法是清除Session缓存中指定的对象，但是要放置于事务提交或flush同步代码后
	- session中FlushMode参数与清除缓存的时间点
		- session.setFlushMode(FlushMode.AUTO) 表示当调用查询方法、commit或flush是清理缓存
		- 清理缓存主要与性能调试有关，属于性能调优的能力。可用于回答如何提升系统性能，回答参考为通过合理设置FlushMode从而提升性能
- HQL复杂语句
	- where和group by等查询
	```
	//1. where
	List<Object[]> accountList=(List<Object[]>)session.createQuery("select name,bank,balance from Account where balance<:highBalance and balance>:lowBalance").setParameter("highBalance",800.0f).setParameter("lowBalance",50.0f).list();
	//2. group by 用法同SQL类似
	```
	- 表关联查询和子查询
	```
	//1. 关联查询
	List<Object[]> list=(List<Object[]>)session.createQuery("select acc.name,acc.bank,acc.balance,u.userType from Account acc,UserInfo u where acc.ID=u.userId").list();
	//2. 子查询
	List<Object[]> groupDemoList=(List<Object[]>)session.createQuery("select name,bank,balance from Account where ID in (select userID from UserInfo)").list();
	```
	- 通过SQLQuery对象执行SQL语句
		- query=session.createSQLQuery("select ... ");
		- List... = query.list();
	- 通过Criteria设置查询条件
		- where、group by和order by等功能的操作
		- criteria.add(Restrictions.ge("ID",new Integer(5)));
		- criteria.add(Restrictions.and(Restrictions.ge("balance",new Integer(5))));
		- List results=session.createCriteria(Account.class).setProjection(Projections.rowCount()).list(); //用于返回记录条数results.get(0)，Projections类主要用于数据分组查询和统计功能 
- 针对Hibernate基础知识部分的知识点考察
	- 问题一 项目里，你是怎么实现数据表和JavaModel类之间的映射的
	- 问题二 在Hibernate里，有哪些主键生成的生成方式？在项目里你用到的是哪些方式?
	- 问题三 在Hibernate里，有临时对象、持久化对象和游离对象这三类，你了解多少？通过项目里的例子举例说明这三种状态？
	- 问题四 通过HQL语言，如何执行where groupby的操作，关联、子查询
	- 问题五 如何在Hibernate里执行SQL语句
	- 问题六 通过Criteria对象，如何设置查询条件，如何设置分组？
		- ProjectionList proList=Projections.projectionList();
		- proList.add(Projections.rowCount());
		- proList.add(Projections.max("balance"));
		- proList.add(Projections.sum("balance"));
		- proList.add(Projections.group[rp[erty("name"));
		- crit.setProjection(proList);
		- results=crit.list();