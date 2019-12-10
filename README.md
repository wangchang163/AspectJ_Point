# AspectJ_Point

AspectJ实现Android埋点，包括所有的点击事件（弹窗，列表），页面切换流程（fragment+activity），兼容fragment切换带来的展示隐藏问题


### 部分语法总结

@Aspect ：定义一个AspectJ文件，会在编译器编译的时候自动解析，不需要主动调用

Join Points：表示连接点对象

Pointcuts：切片，表示代码切入点

@Before：表示切入点方法执行前调用

@Around：表示切入点前后均可调用

@After：表示切入点方法执行后调用

execution：在被切入的方法内执行（前后），也就是执行时

call：在被切入的方法调用时执行（前后），被调用时

withincode：切入点过滤，来进行精确定位

within：切入点过滤，在某某类及其子类里

### 匹配规则

例如："execution(* onClick(..))"

第一个*所在的位置表示的是返回值，*表示的是任意的返回值,.. 所在位置是方法参数的位置，.. 表示的是任意类型、任意个数的参数，空括号表示没有任何参数

