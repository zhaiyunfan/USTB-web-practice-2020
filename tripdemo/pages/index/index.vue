<template>
	<view class="main">
		<!--引入了启动组件-->
		<code-elf-guide v-if="guidePages"></code-elf-guide>
	</view>
</template>

<script>
	//注意这个引导页面已经封装成了一个组件,引入就可以使用了。
	import codeElfGuide from '@/components/code-elf-guide/code-elf-guide.vue'
	export default {
        components: {
            codeElfGuide
        },
		data() {
			return {
				guidePages:false
			}
		},
		onLoad(){
			this.loadExecution()
		},
		methods: {
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

<style>
	page,.main{
		width: 100%;
		height: 100%;
	}
</style>
