<template>
	<view class="forget">

		<view class="content">
			<!-- 主体 -->
			<view class="main">
				<view class="tips">绑定或改绑您的邮箱</view>
				<wInput v-model="emailData" type="text" maxlength="50" placeholder="请输入新的邮箱号"></wInput>
				<wInput v-model="verCode" type="number" maxlength="4" placeholder="验证码" isShowCode codeText="获取重置码" setTime="30"
				 ref="runCode" @setCode="getVerCode()"></wInput>
			</view>

			<wButton class="wbutton" text="更换邮箱" :rotate="isRotate" @click.native="setEmail"></wButton>

		</view>
	</view>
</template>

<script>
	var _this;
	import wInput from '../../../components/watch-login/watch-input.vue' //input
	import wButton from '../../../components/watch-login/watch-button.vue' //button
	export default {
		data() {
			return {
				emailData: "", //电话
				verCode: "", //验证码
				isRotate: false, //是否加载旋转
			}
		},
		components: {
			wInput,
			wButton
		},
		mounted() {
			_this = this;
		},
		methods: {
			getVerCode() {
				//获取验证码
				if (_this.phoneData.length != 11) {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '手机号不正确'
					});
					return false;
				}
				console.log("获取验证码")
				this.$refs.runCode.$emit('runCode'); //触发倒计时（一般用于请求成功验证码后调用）
				uni.showToast({
					icon: 'none',
					position: 'bottom',
					title: '模拟倒计时触发'
				});

				setTimeout(function() {
					_this.$refs.runCode.$emit('runCode', 0); //假装模拟下需要 终止倒计时
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '模拟倒计时终止'
					});
				}, 3000)
			},
			setEmail: function() {
				//重置密码
				if (this.isRotate) {
					//判断是否加载中，避免重复点击请求
					return false;
				}
				let standard = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
				if (!standard.test(this.emailData)) {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '邮箱格式不正确'
					})
					return false;
				}
				/* if (this.verCode.length != 4) {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '验证码不正确'
					});
					return false;
				} */
				_this.isRotate = true
				setTimeout(function() {
					_this.isRotate = false
				}, 3000)

				console.log(this.emailData)
				uni.request({
					url: 'http://hkkiack8.xiaomy.net:32771/tripapp/user/changeEmail',
					data: {
						uid: getApp().globalData.user.uid,
						email: this.emailData
					},
					method: 'POST',
					header: {
						"Content-Type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						if (res.data.code == 200) {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '绑定成功'
							})
							getApp().globalData.user.email = this.emailData;
							uni.setStorageSync("user", getApp().globalData.user);
							uni.reLaunch({
								url: '../userManage'
							})
						} else {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: res.data.msg
							})
						}
					}
				})

			}
		}
	}
</script>

<style>
	@import url("../../../components/watch-login/css/icon.css");
	@import url("../../login/css/main.css");
</style>
