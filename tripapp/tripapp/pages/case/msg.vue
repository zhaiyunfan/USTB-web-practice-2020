<template>
	<view class="container">
		<!--测试给手机发送验证码-->
		<view class="title">输入手机号码</view>
		<input class="uni-input" placeholder="请输入手机号码" v-model="mobile" />
		<view class="title">输入验证码</view>
		<input class="uni-input" placeholder="请输入验证码" v-model="randomCode"/>
		<button type="default" @tap="sendMsg">发送验证码</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
                mobile: '',
				randomCode: ''
			}
		},
		methods: {
			sendMsg() {
				// promise方式
				uniCloud.callFunction({
						name: 'sendValiCode',
						data: {
							mobile: this.mobile,
							randomCode: this.randomCode
						}
					})
					.then(res => {});
			}
		}
	}
</script>

<style>
	.uni-input {
		height: 80rpx;
		font-size: large;
	}
</style>
