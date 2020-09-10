import app from "../../App.vue"

// 定义全局参数,控制数据加载
var _self, page = 1,
	timer = null;

export default {
	data() {
		return {
			total: 0,
			loadingText: '',
			list_orders: [],
			page: 0, //当前分页页码
			apiUrl: '', //后端接口地址
			id: '', //传值使用,方便存在本地的locakStorage  
			del_id: '' //方便存在本地的locakStorage  
		}
	},
	components: {

	},
	onLoad(options) {
		_self = this;

		//检查是否登录参考代码,需要用的时候，可以把注释取掉
		//if(this.checkLogin()==false){
		//	return;
		//}

		//this.getLaction();//得到gps

		this.page = 0;

		//检测有没有传入id参数
		if (options.id != null && options.id != "") {
			this.id = options.id;
		}
		//执行初始化,需要用的时候，可以把注释取掉
		//this.Refresh("init");

	},
	onShow() {
		console.log("on show");
		console.log('开始刷新订单...');
		uni.request({
			url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/data',
			data: {

			},
			method: 'GET',
			success: (res) => {
				console.log(res);
				this.list_orders = res.data.data;
				console.log(this.list_orders);
			}
		})
		console.log("开始核算总金额...");
		uni.request({
			url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/total',
			data: {

			},
			method: 'GET',
			success: (res) => {
				console.log(res);
				this.total = res.data.data;
			}
		})
	},
	onPullDownRefresh: function() {
		//下拉刷新的时候请求一次数据
		this.Refresh();
	},
	methods: {
		allSettle: function() {
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/allSettle',
				data: {

				},
				method: 'PUT',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功支付¥' + this.total + '!'
					});
					this.Refresh();
				}
			})
		},
		pay: function(oid, price) {
			console.log(oid);
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/pay/' + oid,
				data: {

				},
				method: 'PUT',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功支付¥' + price + '!'
					});
					this.Refresh();
				}
			})
		},
		deleteOrder: function(oid) {
			console.log(oid);
			console.log('开始删除订单...');
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/' + oid,
				data: {

				},
				method: 'DELETE',
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功删除该订单'
					});
					this.Refresh()
				}
			})
		},
		/**
		 * all_orders_6_6处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_6_6_click: function(event) {

		},

		/**
		 * all_orders_8_8处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_8_8_click: function(event) {

		},

		/**
		 * all_orders_10_10处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_10_10_click: function(event) {

		},

		/**
		 * all_orders_12_12处理函数
		 * 数据绑定  data-index="{{index}}" 
		 * 数据取值  var index = e.currentTarget.dataset.index; var cata = this.list_cata_list[index];
		 */
		all_orders_12_12_click: function(event) {

		},

		//刷新数据
		Refresh: function() {

			uni.showLoading();
			console.log('开始刷新数据...');
			var url = 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/data'
			console.log(url);
			uni.request({
				url: url,
				data: {

				},
				method: 'GET',
				success: (res) => {
					console.log(res);
					this.list_orders = res.data.data;
					console.log(this.list_orders);

					// 隐藏导航栏加载框  
					uni.hideNavigationBarLoading();
					// 停止下拉动作  
					uni.stopPullDownRefresh();

					// 隐藏加载框  
					uni.hideLoading();


					this.loadData = true;
				},
				fail: function(res) {
					uni.showToast({
						title: "服务器访问失败",
						icon: 'none',
						duration: 2000
					})
					console.log(res.data);
					console.log('is failed')
				}
			});
			console.log("开始核算总金额...");
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/orders/total',
				data: {
			
				},
				method: 'GET',
				success: (res) => {
					console.log(res);
					this.total = res.data.data;
				}
			});
		},
	}
}
