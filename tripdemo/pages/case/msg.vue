<template>
	<view>
		<view class="title">请属于要发送验证码的手机号码</view>
		<input class="uni-input" focus placeholder="请输入正确的手机号码" v-model='mobile'/>
		<button type="default" @tap="sendMsg">发送验证码</button>
	</view>
</template>

<script>
	'use strict';
	export default {
		data() {
            return{
				mobile: ''
			}
		},
		methods: {
			sendMsg() {
				uniCloud.callFunction({
						name: 'sendMsg',
						data: {
							//mobile: '18730667008'
							mobile: this.mobile
						}
					})
					.then(res => {
						console.log(res);
					});
			}
		}
	}
</script>

<style>
</style>
