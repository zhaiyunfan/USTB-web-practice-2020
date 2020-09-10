import app from "../../App.vue"

// 定义全局参数,控制数据加载
var _self, page = 1,
	timer = null;

export default {
	data() {
		return {
			loadingText: '',
			listFavors: [],
			page: 0, //当前分页页码
			apiUrl: '', //后端接口地址
			id: '', //传值使用,方便存在本地的locakStorage  
			del_id: '', //方便存在本地的locakStorage
			loadData: false,
			cityList: [{
					oid: 1,
					name: "北京",
					desc: "故宫一日游",
					price: 120,
					tip: "自营"
				},
				{
					oid: 2,
					name: "上海",
					desc: "黄浦江一日游",
					price: 720,
					tip: "自营"
				},
				{
					oid: 3,
					name: "广州",
					desc: "城市自传体验",
					price: 320,
					tip: "自营"
				},
				{
					oid: 4,
					name: "西安",
					desc: "故宫一日游",
					price: 120,
					tip: "自营"
				},
				{
					oid: 5,
					name: "成都",
					desc: "青城山一日游",
					price: 240,
					tip: "团购"
				},
				{
					oid: 6,
					name: "浙江",
					desc: "钱塘江一日游",
					price: 220,
					tip: "自营"
				},
				{
					oid: 7,
					name: "重庆",
					desc: "轻轨一日游",
					price: 220,
					tip: "自营"
				},
				{
					oid: 8,
					name: "厦门",
					desc: "海边一日游",
					price: 20,
					tip: "自营"
				},
				{
					oid: 9,
					name: "安徽",
					desc: "黄山经典两日游",
					price: 420,
					tip: "自营"
				},
				{
					oid: 10,
					name: "河南",
					desc: "少林寺一日游",
					price: 160,
					tip: "团购"
				}
			]
		}
	},
	components: {

	},
	computed: {
		favorsLen: function() {
			return this.listFavors.length > 0;
		}
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
	onShow: function() {
		uni.request({
			url: 'http://hkkiack8.xiaomy.net:32771/tripapp/favors/' + getApp().globalData.user.uid,
			data: '',
			method: 'POST',
			header: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			success: (res) => {
				console.log(res)
				console.log(res.data.length)
				this.listFavors = res.data;
				console.log(this.listFavors)
				this.loadData = true;
			}
		})
	},
	onPullDownRefresh: function() {
		//下拉刷新的时候请求一次数据
		this.Refresh();
	},
	methods: {
		pay: function(oid, price) {
			console.log(oid);
			uni.request({
				url: 'http://l7g02ivr.xiaomy.net:48361/moon/orders/pay/' + oid,
				data: '',
				method: 'POST',
				header: {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功支付¥' + price + '!'
					});
					setTimeout(function() {
						location.reload();
					}, 1000)
				}
			})
		},
		deleteOrder: function(oid) {
			console.log(oid);
			console.log('开始删除订单...');
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/favors/delete',
				data: {
					uid: getApp().globalData.user.uid,
					sid: oid
				},
				method: 'POST',
				header: {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				success: (res) => {
					console.log(res);
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您已成功取消收藏'
					});
					this.Refresh();
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
			var url = 'http://hkkiack8.xiaomy.net:32771/tripapp/favors/' + getApp().globalData.user.uid;
			console.log(url);
			uni.request({
				url: url, //后端接口地址，需要改成自己的接口地址
				data: '',
				method: 'POST',
				header: {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				success: (res) => {
					this.loadData = false;
					console.log(res.data)
					console.log(res.data.length)
					// 置空list
					this.listFavors = [];
					for (let i = 0; i < res.data.length; i++) {
						this.listFavors[i] = this.cityList[res.data[i] - 1];
					}
					console.log(this.listFavors)
					console.log(this.cityList)

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
			})
		},
	},
}
