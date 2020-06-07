## 平台简介

### 源码
- [Github](https://github.com/zhangxqing/health-cloud)
- 后端蓝本自[ruoyi](https://gitee.com/y_project/RuoYi)
- 前端蓝本自[ruoyi-ant](https://gitee.com/zhangmrit/ruoyi-ant)

```
health-cloud
|
├──health-common --通用包
|  |
|  ├──health-common-core --核心工具包
|  |
|  ├──health-common-redis --redis工具包
|  |
|  ├──health-common-log --日志工具包
|  |
|  ├──health-common-auth --权限工具包
|
|
├──health-eureka --注册中心
|
├──health-gateway --网关
|
├──health-service-api --服务api模块
|  |
|  ├──health-system-api --系统业务api
|
├──health-service --微服务
|  |
|  ├──health-system --系统业务

```



启动顺序：
注意事项: 启动本服务先要将本机redis起来,否则登陆界面图片验证码无法刷新,然后依次启用以下服务
- eureka
- gateway
- system
- auth

Swagger文档地址：http://localhost:8081/doc.html
