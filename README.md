# monitor
系统是模拟环境质量监控系统的页面显示，用到的数据是模拟数据，监测的城市是自定义了4个城市的监测点，环境质量指数的计算是由后台将模拟数据按照质量公式进行了计算得出，
然后将得到的质量数据展示到页面上。
前端使用的技术有jquery,ajax,layer,html,Echarts
后端使用的技术有springboot,mybatis
数据库是8.0.23版本的Mysql数据库
系统拥有的功能有用户登录注册、邮箱找回用户密码、污染物数据的显示、环境质量API的显示、污染物数据的下载、污染物数据的修改、污染物数据的自动录入（由定时任务完成）、污染物数据的
计算（定时任务完成）、异常数据的报警（邮箱形式发到管理员上面）、异常数据的显示、用户下载的信息页面显示。
