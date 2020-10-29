## 平台简介

### 源码
- [Github](https://github.com/zhangxqing/health-cloud)
- 前端地址[health-front](https://github.com/zhangxqing/health-front)

```
health-cloud
|
├──health-common --通用包
|  |
|  ├──health-common-core --核心工具包
|  |
|  ├──health-common-redis --redis工具包
|  |
|  ├──health-common-log --日志工具包(依赖 health-system-api)
|  |
|  ├──health-common-auth --权限工具包(依赖 health-system-api)
|  | 
|  ├──health-common-result --统一返回工具包 (依赖 health-common-auth)
|  |
|  ├──health-common-swagger --api文档包
|
|
|
├──health-gateway --网关(依赖 health-common-core)
|
├──health-service-api --服务api模块
|  |
|  ├──health-system-api --系统业务api(依赖 health-common-core,health-common-swagger)
|
├──health-service --微服务
|  |
|  ├──health-system --系统业务(依赖 health-system-api,health-common-log，health-common-core，health-common-swagger,health-common-result,health-common-auth,health-common-redis)
|  |
|  ├──health-auth --认证服务(依赖 health-system-api,health-common-redis,health-common-log)

```



启动顺序：
注意事项: 启动本服务先要将本机redis起来,否则登陆界面图片验证码无法刷新,然后依次启用以下服务
- eureka
- gateway
- system
- auth

Swagger文档地址：http://localhost:8081/doc.html
