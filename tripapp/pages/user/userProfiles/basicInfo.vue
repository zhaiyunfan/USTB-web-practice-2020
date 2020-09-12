<template>
	<view class="center">
		<view class="center-list">
			<view class="center-list-item">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text" v-html="username"></text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text" v-html="gender"></text>
			</view>
		</view>
		<view class="center-list">
			<view class="center-list-item">
				<text class="list-icon">&#xe60f;</text>
				<text class="list-text" v-html="birthday"></text>
			</view>
		</view>
		<view class="center-list" @click="showInput">
			<text class="change-info">点击更改个人信息</text>
			<modalDiy :isShow="show" @cancel="hideInput" :inp=true okCor='red' :cancelBtn='cancelBtn' :okBtn='okBtn'></modalDiy>
		</view>
	</view>
</template>

<script>
	import modalDiy from '../../../components/pop-input-text/modal.vue'
	export default {
		components: {
			modalDiy
		},
		data() {
			return {
				show: false,
				isCancel: false,
				cancelBtn: "取消",
				okBtn: "保存"
			}
		},
		methods: {
			showInput: function() {
				console.log("showInput invoked...")
				if (!this.isCancel)
					this.show = true;
				else {
					this.show = false;
					this.isCancel = false;
				}
			},
			cancel: function(val) {
				console.log(val)
			},
			hideInput: function(val) {
				console.log(val)
				this.isCancel = val
			},
			/* ok: function(val) {
				console.log(val)
			},
			save: function(val) {
				console.log(val)
				this.isSave = val
			} */
		},
		computed: {
			username: function() {
				let username = getApp().globalData.user.username;
				if (username != null)
					return "用户名(" + (username.length >= 30 ? "当前为默认用户名" : username) + ")";
				else
					return "用户名(暂时无法显示)";
			},
			gender: function() {
				let gender = getApp().globalData.user.gender;
				return "性别(" + (gender == null ? "未设置" : gender) + ")";
			},
			birthday: function() {
				let birthday = getApp().globalData.user.birthday;
				return "出生日期(" + (birthday == null ? "未设置" : birthday) + ")";
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

	.change-info {
		height: 90upx;
		line-height: 90upx;
		font-size: 34upx;
		color: #555;
		flex: 1;
		text-align: center;
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
