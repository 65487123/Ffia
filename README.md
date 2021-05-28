# Ffia (Fund fixed investment assistance) 
基金定投辅助

## 项目产生背景  
由于我最近在做自己的理财规划。到网上搜了些理财知识，发现了一种叫做每月定时定额定投指数基金的方案，感觉挺靠谱并且方便的。      
不过，经过一番思考，觉得这种定时定额定投方案还不是很完美,可以对这种方案做一些优化,进一步提高平均年复合收益。  
大致方案如下： 

1、定一个每月的定投日期(比如说每月一号)  
2、定投当日查看要定投的指数基金的估值,根据估值判断这个月投入的资金(比如,如果估值为高,投4000,估值正常投8000,估值为低投12000。估值可以去天天基金或者支付宝查)  
3、知道了这个月要定投的金额以后,不要在当日一次性全投进去,只先投一大部分进去(比如这个月准备要投8000,定投当日就先投6000),剩下一小部分留着这个月其他时间投。  
4、记下定投当日确定份额后的基金净值。  
5、当月定投日后,这个月的后面几天,每天(交易日)14.55查看定投的指数基金净值,如果发现净值比定投当日的净值低(低多少可以自己看情况定)就再投一部分进去,直到把这个月预计要投入的资金全投完。  
6、当定投的基金达到自己制定的止盈点后止盈    
解释:指数基金定投份额确认日是当月的最低点的概率微乎其微(连涨一个月),所以后面那小部分的钱肯定是能投一部分进去的(加仓次数设置越少,每个月预计投入额完全投完概率越高)

由于这种方案需要经常踩点查询基金的净值并比较,执行起来比较繁琐。所以我写了这个项目,每天14.55自动查询要定投的基金并比较,当需要加仓时候,会发短信通知我。
而我只需要打开购买基金的app,买入预先计划好的加仓额就行。(这些app如果能提供接口出来,那就能搞成全自动化了...可惜..)

## 项目功能
每天(交易日)14.55查询要定投的指数基金净值,和当月定投日确认份额时的净值比较,如果比他低了自己的预设值(比如可以设置为低于定投当日净值的百分之99.8%),就给指定手机号发短信，提示给这个基金加仓。

## 如何使用
很遗憾。由于这个项目是专门为我自己设定的方案设计的,所以不能直接把代码拉到本地就跑起来。(跑是能跑起来,但这只针对我自己制定的方案,不适用于别人)  
也就是说,这个项目代码只能用作学习参考。如果对我这种方案有兴趣并且也有这样的需求,可以自己拉到本地然后改改。(发短信是要钱的，如果不想花钱也可以改成发邮箱)  

## 实现原理
这个项目本身,技术上的原理没什么可以说的,甚至可以说是毫无技术含量可言,无非就是时间控制、调api发http、字符串的解析等。主体代码实现花了我一个小时都不到,反而是代码背后的设计思想、业务知识(指数基金原理、基金定投原理、国家经济发展战略等等)花了我比较多的时间。  
也就是说,这个项目的业务知识就是实现原理。可能说“业务”比较笼统，每个人对“业务”的理解都不一样，在我看来，业务就是如何赚钱，懂项目的业务就是懂这个项目是如何赚钱的(说委婉点就是解决了什么问题，创造了什么价值),懂公司的业务就是懂公司是如何运作并且盈利的.....扯远了


