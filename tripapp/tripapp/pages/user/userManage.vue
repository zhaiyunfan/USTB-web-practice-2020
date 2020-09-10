<template>
	<view class="center">
		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="setBasicInfo">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text">基本信息</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="resetPassword">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text">密码</text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="setEmail">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text" v-html="email"></text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item border-bottom" @tap="setPhoneNum">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text" v-html="phoneNum"></text>
				<text class="navigat-arrow">&#xe65e;</text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item" @tap="logOut">
				<text class="list-icon">&#xe614;</text>
				<text class="list-text">退出登录</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {
			setBasicInfo: function() {
				uni.navigateTo({
					url: 'userProfiles/basicInfo'
				})
			},
			resetPassword: function() {
				uni.navigateTo({
					url: '../login/forget'
				})
			},
			setEmail: function() {
				uni.navigateTo({
					url: 'userProfiles/email'
				})
			},
			setPhoneNum: function() {
				uni.navigateTo({
					url: 'userProfiles/uesrPhoneNum'
				})
			},
			logOut: function() {
				uni.showModal({
					title: "确定退出当前用户吗?",
					success: function(res) {
						if (res.confirm) {
							for (let var1 in getApp().globalData.user) {
								var1 = '';
							}
							getApp().globalData.isLogin = false;
							uni.reLaunch({
								url: 'user'
							})
						}
					}
				})
			}
		},
		computed: {
			email: function() {
				let email = getApp().globalData.user.email;
				return "邮箱(" + (email == null ? "未绑定邮箱" : email) + ")";
			},
			phoneNum: function() {
				let phoneNum = getApp().globalData.user.phoneNum;
				console.log(phoneNum)
				return "改绑手机号(" + (phoneNum == null ? "未绑定手机号" : phoneNum) + ")";
			}
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
