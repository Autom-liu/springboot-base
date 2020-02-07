# 一个基于spring boot 2.1^ 的基础API接口服务环境搭建


相对于上一个版本，新版springboot-base进行如下升级改造：

## 项目结构

基础项目部分主要是两大核心公共模块：

- common : 零依赖可迁移的公共模块，提供一些基础的接口以及工具类
- common-service ： 业务模块公共服务，针对项目各模块的公共依赖。

## 规范枚举

项目中的所有字典枚举均需实现公共接口`BaseStatusEnum`。
主要拆分为错误码枚举`IErrorEnum`、业务枚举`BaseBizEnum`以及排序字段枚举`BaseOrderByEnum`。


## 统一异常规范

项目所有异常均分为两类：业务异常：`BizException`和框架级处理异常：`SystemException`
其中业务异常是受检异常`Exception`，需要手动抛出。
框架级处理异常是运行时异常`RuntimeException`，不需要捕获。
这些异常均需要使用统一的错误码枚举构造抛出。

所有未捕获的异常，将由统一异常处理器`GobalExceptionHandler`完成处理。

## 规范公共返回接口

所有的接口返回均是`IResult`的实现类，也就是说，必须返回`code`、`msg`字段。根据数据类型不同，抽象成返回单条数据的`Result`、返回多条数据的`ResultList`以及返回分页数据的`ResultPage`。

## 基础配置

项目提供了基础的跨域配置、请求枚举转义配置以及其他web相关的配置。

## 规范实体领域模型

项目中规范划分实体Bean、DTO、VO以及Query。
所有数据库实体保持BO形式存在，请求实体以及服务相互调用的实体以DTO形式存在，所有返回实体均以VO形式返回。
有关查询服务的实体传递使用Query。可以通过继承PageQuery来完成分页列表的查询，即使不需要分页的业务，只需设置分页标识为`false`即可。

## 单表零SQL的快速开发

每个模块根据表名会生成对应的接口Service和实现类，通过继承`BaseService`，可以完成所有在`IService`接口上定义的单表操作实现。同时提供了分页查询的抽象。即使是最复杂的带条件分页查询，只需要通过每个实现类下的代理`Example`完成自定义的条件构造即可。

## 请求参数格式兼容

请求参数有可能是表单，也有可能是`json`？没关系，只需要在接口参数上标记`@CommonRequestBody`就可以直接兼容这两种格式。（默认是没有的哦~~）

## 请求拦截

项目提供了两大请求拦截，一个是请求日志拦截`RequestAop`，使用AOP技术实现。另外一个是权限校验拦截`RequestPemissionInteceptor`，采用拦截器实现，用户可根据自己实际需求实现统一的权限校验流程，在接口方法上使用`@PermissionRequire`注解标记的接口都将会进行权限校验。

## 配置文件分离和加密

模块化划分配置文件，并使用`jasypt`对配置文件中敏感信息加密。

# 快速开始 quick start

推荐使用种子项目的代码生成器生成项目：

只需要配置数据源、项目名称等配置，就可以快速为你完整搭建属于本项目独有的基础框架。

生成后，你只需要关注api层的开发，即可快速完成单表增删改查业务。

## 创建API：

以本`springboot-base`为例，在生成的模块下创建包`com.edu.scnu.baseinfo.web.api`。

> 注意一定要是`web.api`，否则会导致请求日志记录失效。

包名确定后，你只需要按照正常的spring mvc的开发模式开发web层，添加`@RestController`和`RequestMapping`请求注解，在web层，你几乎可以享受和原始spring mvc模式开发，并不任何影响。

注入需要的service，放心，所有的service都已经生成好的了，单表操作不需要再创建Service。


方法返回值不知道？没关系，统一使用`IResult`接口即可，那具体怎么返回呢，将拿到的数据直接作为`Result.success`参数，或者使用`IResult.error`返回错误信息即可。

最后有异常怎么处理，简单粗暴，直接抛出即可。

最后你可以很快完成基于单表的增删改和主键查询：

```java
@RestController
public class AccountApi {
	
	@Autowired
	private AccountService accountService;

	@PostMapping("/account/add")
	public IResult addAccount(AccountDTO dto) throws BizException {
		// TODO 必传字段校验
		accountService.insert(dto);
		return Result.success("");
	}
	
	@DeleteMapping("/account/{id}")
	public IResult deleteAccount(@PathVariable("id") Integer id) throws BizException {
		accountService.deleteById(id);
		return Result.success("");
	}
	
	@GetMapping("/account/{id}")
	public IResult findOne(@PathVariable("id") Integer id) throws BizException {
		AccountVO accountVO = accountService.findOne(id);
		return Result.success(accountVO);
	}
	
	@GetMapping("/account/list")
	public IResult findList(AccountQuery query) {
		List<AccountVO> result = accountService.queryList(query);
		return ResultList.success(result);
	}
	
}
```

想要做带条件的分页查询，很简单，无需写SQL，只需要修改Service实现类中的`queryPage`方法。
里面有一个`TODO`标记`Here build the condition you want`

你可以在这里构造你想要列表查询的条件。怎么构造？输入`example.createCriteria()`，再看看后面的方法，相信你一看就懂，不仅能满足日常开发大部分条件构造，还能享受函数式编程的快感。

```java
public PageVO<AccountVO> queryPage(AccountQuery query) {
		
	AccountExample example = new ExampleProxy();

	super.handlePageOrder(query, false, example);
		
	// TODO Here build the condition you want
		
	List<Account> accounts = accountMapper.selectByExample(example);
		
	return super.handlePageResult(accounts);
}
```

需要排序怎么办？看到`super.handlePageOrder(query, false, example);`了吗？只需要将第二个参数改为`true`就可以了。但前提是你必须在Query定义多一个属于自己的排序字段枚举，这个枚举变量名一定要是`orderBy`哦。因为这样才能重写掉父类的`orderBy`字段。
