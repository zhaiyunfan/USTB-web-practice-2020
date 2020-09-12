<template>
	<view class="main">
		<code-elf-guide v-if="guidePages"></code-elf-guide>
	</view>
</template>

<script>
	import codeElfGuide from '@/components/code-elf-guide/code-elf-guide.vue'
	
	export default {
		components: {
		    codeElfGuide
		},
		onLoad() {
			this.loadExecution()
		},
		data(){
			return{
				guidePages:false
			}
		},
		methods:{
			loadExecution: function(){
				/**
				 * 获取本地存储中launchFlag的值
				 * 若存在，说明不是首次启动，直接进入首页；
				 * 若不存在，说明是首次启动，进入引导页；
				 */
				try {
					// 获取本地存储中launchFlag标识
					//uni.getStorageSync 方法的作用类似于window.localStorge ，实现了本地缓存。
				    const value = uni.getStorageSync('launchFlag');
				    if (value) {
						// launchFlag=true直接跳转到首页
						uni.switchTab({
							url: '/pages/home/home' //这个home是一个tab页面
						});
				    } else {
						// launchFlag!=true显示引导页
				        this.guidePages = true
				    }
				} catch(e) { 
					// error 
					uni.setStorage({ 
						key: 'launchFlag', 
						data: true, 
						success: function () {
							console.log('error时存储launchFlag');
						} 
					}); 
					this.guidePages = true
				}
				return;
				uni.switchTab({
				    url: '/pages/home/home'
				});
			}
		}
	}
		
</script>

<!--这里一定要配置一下，100%占满，否则会显示不出来页面-->
<style>
	page,.main{
		width: 100%;
		height: 100%;
	}
</style>
