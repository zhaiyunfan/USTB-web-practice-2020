<template>
	<view>
		这里是首页
		<view>
			{{city}}
		</view>
	</view>
</template>

<script>
	/*这个页面怎么接收参数呢*/

	export default {
		data() {
			return {
				city: '上海'
			}
		},
		/*onLoad: function(option) { //option为object类型，会序列化上个页面传递的参数
			console.log(option.city); //打印出上个页面传递的参数。
			this.city = option.city;
		},*/
		methods: {

		}
	}
</script>

<style>
</style>
