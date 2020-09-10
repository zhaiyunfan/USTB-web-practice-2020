<template>
	<view class="center">
		<view class="logo" @tap="goLogin" :hover-class="!login ? 'logo-hover' : ''">
			<image class="logo-img" :src="login ? userInfo.avatarUrl :avatarUrl"></image>
			<view class="logo-title">
				<text class="uer-name long-text">{{login ? userInfo.username : '您未登录'}}</text>
				<text class="go-login navigat-arrow" v-if="!login">&#xe65e;</text>
			</view>
		</view>

		<view class="center-list">
			<view class="center-list-item" @tap="goToOrders">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text">我的订单</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>

		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="userManage">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text">帐号信息管理</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>

		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="goToFavor">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text">我的收藏</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>

		<view class="center-list">
			<view class="center-list-item border-bottom">
				<text class="list-icon">&#xe60b;</text>
				<text class="list-text">帮助与反馈</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
			<view class="center-list-item" @tap="initApp">
				<text class="list-icon">&#xe65f;</text>
				<text class="list-text">系统初始化</text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item">
				<text class="list-icon">&#xe614;</text>
				<text class="list-text">关于应用</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				login: getApp().globalData.isLogin,
				avatarUrl: "../../static/uni-center/logo.png",
				userInfo: getApp().globalData.user
			}
		},
		created: function() {
			/* try {
				this.userInfo = getApp().globalData.user = uni.getStorageSync("user");
				console.log(this.userInfo);
				if (getApp().globalData.user.uid != 0 || getApp().globalData.user.uid != null) {
					getApp().globalData.isLogin = true;
				}
			} catch (e) {
				//TODO handle the exception
			} */
		},
		onPullDownRefresh: function() {
			this.userInfo = uni.getStorageSync("user");
			console.log(this.userInfo)
			setTimeout(function() {
				uni.stopPullDownRefresh();
			}, 1000);
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/user/auth2',
				data: {
					identify: this.userInfo.phoneNum,
					password: this.userInfo.password
				},
				method: 'POST',
				header: {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				success: (res) => {
					console.log(res);
					if (res.data.code === 200) {
						/* uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '登陆成功'
						}) */
						// 设置缓存
						{
							getApp().globalData.isLogin = true;
							getApp().globalData.user.uid = res.data.data.uid;
							getApp().globalData.user.username = res.data.data.username;
							getApp().globalData.user.password = res.data.data.password;
							getApp().globalData.user.email = res.data.data.email;
							getApp().globalData.user.gender = res.data.data.gender;
							if (res.data.data.birthday != null)
								getApp().globalData.user.birthday = res.data.data.birthday.split(" ")[0];
							else
								getApp().globalData.user.birthday
							getApp().globalData.user.phoneNum = res.data.data.phoneNum;
							getApp().globalData.user.regDate = res.data.data.regDate;
							getApp().globalData.user.status = res.data.data.status;
							uni.setStorageSync("user", getApp().globalData.user);
						}
						console.log(getApp().globalData.user);
						uni.reLaunch({
							url: '/pages/user/user'
						})
					} else {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '登录失败！\n\r请检查用户名或者密码是否正确。'
						})
					}
				}
			})
		},
		methods: {
			goLogin: function() {
				if (this.login == false) {
					uni.navigateTo({
						url: '../login/login'
					})
				} else {
					uni.navigateTo({
						url: 'userManage'
					})
				}
			},
			goToOrders: function() {
				if (this.login == false) {
					uni.navigateTo({
						url: '../login/login'
					})
				} else {
					/* uni.switchTab({
						url: '../order/order'
					}) */
					uni.reLaunch({
						url: '../order/order'
					})
				}
			},
			goToFavor: function() {
				if (this.login == false) {
					uni.navigateTo({
						url: '../login/login'
					})
				} else {
					uni.navigateTo({
						url: '../favor/favor'
					})
				}
			},
			userManage: function() {
				if (this.login == false) {
					uni.navigateTo({
						url: '../login/login'
					})
				} else {
					uni.navigateTo({
						url: 'userManage'
					})
				}
			},
			goToSendMsgCase: function() {
				//页面的跳转。
				uni.navigateTo({
					url: '../case/msg' //跳转到用户登录页面
				})
			},

			initApp: function() {
				uni.showModal({
					title: '确定要初始化系统吗?',
					content: '初始化系统会清除所有缓存数据并且重启？',
					success: function(res) {
						if (res.confirm) {
							console.log('用户点击确定');
							// 清除缓存
							uni.clearStorage();
							// 一秒后重启
							uni.showToast({
								icon: 'none',
								duration: 3000,
								title: '清除成功1秒后重启'
							});
							setTimeout(function() {
								uni.hideToast();
								plus.runtime.restart();
							}, 1000);
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
		}
	}
</script>

<style>
	@font-face {
		font-family: texticons;
		font-weight: normal;
		font-style: normal;
		src: url('https://at.alicdn.com/t/font_984210_5cs13ndgqsn.ttf') format('truetype');
	}

	page,
	view {
		display: flex;
	}

	page {
		background-color: #f8f8f8;
	}

	.center {
		flex-direction: column;
	}

	.logo {
		width: 750upx;
		height: 240upx;
		padding: 20upx;
		box-sizing: border-box;
		background-color: #4cd964;
		flex-direction: row;
		align-items: center;
	}

	.logo-hover {
		opacity: 0.8;
	}

	.logo-img {
		width: 150upx;
		height: 150upx;
		border-radius: 150upx;
	}

	.logo-title {
		height: 150upx;
		flex: 1;
		align-items: center;
		justify-content: space-between;
		flex-direction: row;
		margin-left: 20upx;
	}

	.uer-name {
		height: 60upx;
		line-height: 60upx;
		font-size: 38upx;
		color: #FFFFFF;
	}

	.long-text {
		padding: 30upx;
		max-height: 130upx;
		/* overflow: hidden; */
		word-break: break-all;
		/* break-all(允许在单词内换行。) */
		text-overflow: ellipsis;
		/* 超出部分省略号 */
		display: -webkit-box;
		/** 对象作为伸缩盒子模型显示 **/
		-webkit-box-orient: vertical;
		/** 显示的行数 **/
		-webkit-line-clamp: 3;
		/** 设置或检索伸缩盒对象的子元素的排列方式 **/
	}

	.go-login.navigat-arrow {
		font-size: 38upx;
		color: #FFFFFF;
	}

	.login-title {
		height: 150upx;
		align-items: self-start;
		justify-content: center;
		flex-direction: column;
		margin-left: 20upx;
	}

	.center-list {
		background-color: #FFFFFF;
		margin-top: 20upx;
		width: 750upx;
		flex-direction: column;
	}

	.center-list-item {
		height: 90upx;
		width: 750upx;
		box-sizing: border-box;
		flex-direction: row;
		padding: 0upx 20upx;
	}

	.border-bottom {
		border-bottom-width: 1upx;
		border-color: #c8c7cc;
		border-bottom-style: solid;
	}

	.list-icon {
		width: 40upx;
		height: 90upx;
		line-height: 90upx;
		font-size: 34upx;
		color: #4cd964;
		text-align: center;
		font-family: texticons;
		margin-right: 20upx;
	}

	.list-text {
		height: 90upx;
		line-height: 90upx;
		font-size: 34upx;
		color: #555;
		flex: 1;
		text-align: left;
	}

	.navigat-arrow {
		height: 90upx;
		width: 40upx;
		line-height: 90upx;
		font-size: 34upx;
		color: #555;
		text-align: right;
		font-family: texticons;
	}
</style>
