
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/home/home","pages/order/order","pages/user/user","pages/login/login","pages/login/forget","pages/login/register","pages/case/msg","pages/user/userManage","pages/user/userFavor","pages/user/userProfiles/basicInfo","pages/user/userProfiles/email","pages/user/userProfiles/uesrPhoneNum","pages/favor/favor"],"window":{"navigationBarTextStyle":"white","navigationBarTitleText":"uni-app","navigationBarBackgroundColor":"#4CD964","backgroundColor":"#FFFFFF"},"tabBar":{"color":"#7A7E83","selectedColor":"#4CD964","borderStyle":"black","backgroundColor":"#ffffff","list":[{"pagePath":"pages/home/home","iconPath":"static/image/dm_home.png","selectedIconPath":"static/image/dm_home_selected.png","text":"首页"},{"pagePath":"pages/rank/rank","iconPath":"static/image/dm_my.png","selectedIconPath":"static/image/dm_my_selected.png","text":"排行"},{"pagePath":"pages/order/order","iconPath":"static/image/dm_cart.png","selectedIconPath":"static/image/dm_cart_selected.png","text":"订单"},{"pagePath":"pages/transport/transport","iconPath":"static/image/dm_my.png","selectedIconPath":"static/image/dm_my_selected.png","text":"出行"},{"pagePath":"pages/user/user","iconPath":"static/image/dm_my.png","selectedIconPath":"static/image/dm_my_selected.png","text":"用户"}]},"nvueCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"tripapp","compilerVersion":"2.8.11","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true},"window":{"navigationBarTitleText":"引导页,","titleNView":false}},{"path":"/pages/home/home","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"首页"}},{"path":"/pages/order/order","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"订单详情"}},{"path":"/pages/user/user","meta":{"isQuit":true,"isTabBar":true},"window":{"navigationBarTitleText":"用户中心","enablePullDownRefresh":true}},{"path":"/pages/login/login","meta":{},"window":{"navigationBarTitleText":"用户登录"}},{"path":"/pages/login/forget","meta":{},"window":{"navigationBarTitleText":"忘记密码"}},{"path":"/pages/login/register","meta":{},"window":{"navigationBarTitleText":"用户注册"}},{"path":"/pages/case/msg","meta":{},"window":{"navigationBarTitleText":"发送验证码案例"}},{"path":"/pages/user/userManage","meta":{},"window":{"navigationBarTitleText":"账号信息管理"}},{"path":"/pages/user/userFavor","meta":{},"window":{"navigationBarTitleText":"我的收藏"}},{"path":"/pages/user/userProfiles/basicInfo","meta":{},"window":{"navigationBarTitleText":"基本信息"}},{"path":"/pages/user/userProfiles/email","meta":{},"window":{"navigationBarTitleText":"绑定邮箱"}},{"path":"/pages/user/userProfiles/uesrPhoneNum","meta":{},"window":{"navigationBarTitleText":"更换手机号"}},{"path":"/pages/favor/favor","meta":{},"window":{"navigationBarTitleText":"我的收藏"}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
