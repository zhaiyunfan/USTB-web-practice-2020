<template>
	<view class="container">
		这里是启动页面<br>
		学校名称是：{{msg}}
		<view>
			<!--放一个按钮-->
			<button type="default" @tap="goToOther">一个普通按钮</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				msg:'北京科技大学'
			}
		},
		methods: {
           goToOther:function(){
			   //跳转到另一个页面
			   //console.log('准备跳转到另一个页面....');
			   //window.location.href='xxx.html'
			   uni.navigateTo({
				   //虽然这里跳转地址没有写错，但是home.vue并没有配置到page.json里面，系统认为这个页面不存在。
			       url: '../home/home?city=北京' //url怎么写呢？
			   });
		   }
		}
	}
</script>

<style>
	.container {
		padding: 20px;
		font-size: 14px;
		line-height: 24px;
	}
</style>
