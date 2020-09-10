<template>
	<view class="forget">

		<view class="content">
			<!-- 主体 -->
			<view class="main">
				<view class="tips">绑定或改绑您的手机号</view>
				<wInput v-model="phoneData" type="text" maxlength="50" placeholder="请输入新的手机号"></wInput>
				<wInput v-model="verCode" type="number" maxlength="4" placeholder="验证码" isShowCode codeText="获取重置码" setTime="30"
				 ref="runCode" @setCode="getVerCode()"></wInput>
			</view>

			<wButton class="wbutton" text="更换手机号" :rotate="isRotate" @click.native="setPhoneNum"></wButton>

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
				phoneData: "", //电话
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
			setPhoneNum: function() {
				//重置密码
				if (this.isRotate) {
					//判断是否加载中，避免重复点击请求
					return false;
				}
				let standard = /^[1][3,4,5,7,8][0-9]{9}$/;
				if (!standard.test(this.phoneData)) {
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


				uni.request({
					url: 'http://hkkiack8.xiaomy.net:32771/tripapp/user/changePhoneNum',
					data: {
						uid: getApp().globalData.user.uid,
						phoneNum: this.phoneData
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
							getApp().globalData.user.phoneNum = this.phoneData;
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
