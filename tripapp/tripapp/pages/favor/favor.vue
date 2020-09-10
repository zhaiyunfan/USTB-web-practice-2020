<template>
	<view>
		<view v-for="favor in favors">
			{{favor}}
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				favors: ''
			}
		},
		onShow: function() {
			uni.request({
				url: 'http://hkkiack8.xiaomy.net:32771/tripapp/favors/' + getApp().globalData.user.uid,
				data: '',
				method: 'POST',
				header: {
					"Content-Type": "application/x-www-form-urlencoded"
				},
				success: (res) => {
					console.log(res)
					this.favors = res.data;
					console.log(this.favors)
				}
			})
		}
	}
</script>

<style>
</style>
