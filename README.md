## 平台简介

### 源码
- [Github](https://github.com/zhangxqing/health-cloud)

本项目FORK自  [若依/RuoYi](https://gitee.com/y_project/RuoYi)

蓝本是[zhangmrit/Ruoyi](https://gitee.com/zhangmrit/RuoYi)

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
- eureka
- gateway
