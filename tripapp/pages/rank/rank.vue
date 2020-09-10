<template>
	<view>
		
		<div class="left" style="float:left">
			<button  @tap="priceRank(productList)">价格排行榜</button>
		</div>
		<div class="right" style="float:right">
			<button type="default" @tap="favourRank(productList)">人气排行榜</button>
		</div>
		<view class="uni-product-list">
			<view class="uni-product" v-for="(product,index) in productList" :key="index" v-if="product.title.indexOf(selectValue)!=-1">

				<view class="image-view">
					<image v-if="renderImage" class="uni-product-image" :src="product.image" @tap="goToDetail(product.id)"></image>
				</view>
				<view class="uni-product-title">{{product.title}}</view>
				<view class="uni-product-price">
					<text class="uni-product-price-favour">￥{{product.originalPrice}}</text>
					<text class="uni-product-price-original">￥{{product.favourPrice}}</text>
					<text class="uni-product-tip">{{product.tip}}</text></br>
					<text class="uni-product-price-fav"> 被收藏{{product.favour}}次</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	// console.log(uni.getSystemInfoSync());
	import uniIcons from '@/components/uni-icons/uni-icons.vue'
	import uniNavBar from '@/components/uni-nav-bar/uni-nav-bar.vue'
	import uniSection from '@/components/uni-section/uni-section.vue'
	var dateUtils = require('../../common/util.js').dateUtils;


	export default {
		components: {
			uniIcons,
			uniNavBar,
			uniSection
		},
		data() {
			return {
				index: 0,
				title: 'product-list',
				productList: [],
				renderImage: false,
				banner: [{
						image: '/static/spots/gugong001.jpg'
					},
					{
						image: '/static/spots/tiantan001.jpg',
					},
					{
						image: '/static/spots/yiheyuan001.jpg',
					},
					{
						image: '/static/spots/changcheng001.jpg'
					},
					{
						image: '/static/spots/shaolin001.jpg'
					},
					{
						image: '/static/spots/huangshan001.jpg'
					}
				],
				listData: [],
				last_id: "",
				reload: false,
				selectValue: ''
			}
		},
		onLoad() {
			this.loadData();
			setTimeout(() => {
				this.renderImage = true;
			}, 300);
		},
		methods: {
			loadData(action = 'add') {
				const data = [{
						image: '/static/spots/gugong001.jpg',
						title: '故宫经典一日游',
						originalPrice: 120,
						favourPrice: 80,
						tip: '自营',
						favour: 99,
						id:0
					},
					{
						image: '/static/spots/tiantan001.jpg',
						title: '北京故宫-天坛一日游',
						originalPrice: 150,
						favourPrice: 120,
						tip: '特惠',
						favour: 22,
						id:1
					},
					{
						image: '/static/spots/yiheyuan001.jpg',
						title: '北京颐和园一日游',
						originalPrice: 100,
						favourPrice: 80,
						tip: '自营',
						favour: 44,
						id:2
					},
					{
						image: '/static/spots/changcheng001.jpg',
						title: '八达岭长城一日游',
						originalPrice: 80,
						favourPrice: 60,
						tip: '团购',
						favour: 33,
						id:3
					},
					{
						image: '/static/spots/shaolin001.jpg',
						title: '少林寺一日游',
						originalPrice: 200,
						favourPrice: 160,
						tip: '团购',
						favour: 88,
						id:4
					},
					{
						image: '/static/spots/huangshan001.jpg',
						title: '黄山经典两日游',
						originalPrice: 480,
						favourPrice: 400,
						tip: '自营',
						favour: 11,
						id:5
					}
				];

				if (action === 'refresh') {
					this.productList = [];
				}

				data.forEach(item => {
					this.productList.push(item);
				});
				//this.$options.methods.priceRank(this.productList);
			},

			bindPickerChange: function(e) {
				console.log('picker发送选择改变，携带值为', e.target.value)
				this.index = e.target.value
			},

			onPullDownRefresh() {
				this.loadData('refresh');
				// 实际开发中通常是网络请求，加载完数据后就停止。这里仅做演示，加延迟为了体现出效果。
				setTimeout(() => {
					uni.stopPullDownRefresh();
				}, 2000);
			},
			/*onReachBottom() {
				this.loadData();
			},*/

			clickLeft() {

				uni.showToast({
					title: '左侧按钮'
				})
			},
			/*search(productList,selectValue) {
				let newList = []
				productList.forEach(item=>{
					if(item.title.indexOf(selectValue)!=-1){
						newList.push(item)
					}
				});
				console.log(newList)
				return newList;
			},*/

			showUserInfo() {
				uni.showToast({
					title: '显示用户信息'
				})
			},
			confirmSearch() {
				console.log(this.selectValue),
					uni.showToast({
						title: '搜索成功',
					})
			},
			priceRank: function(productList) {
				return productList.sort(function(a, b) {
					let x = a['favourPrice']
					let y = b['favourPrice']
					return ((x < y) ? -1 : (x > y) ? 1 : 0)
				})

			},
			favourRank: function(productList) {
				return productList.sort(function(a, b) {
					let x = a['favour']
					let y = b['favour']
					return ((x > y) ? -1 : (x < y) ? 1 : 0)
				})

			},
			goToDetail: function(index) {
				/*
				let detail = {
					author_name: '西蒙牛',
					cover: '西部旅游网',
					id: '121375',
					post_id: '5310910',
					published_at: '2020-10-10',
					title: '少林寺一日游'
				}*/

				uni.navigateTo({
					//url: "/pages/detail/detail?detailDate=" + encodeURIComponent(JSON.stringify(detail))

					url: ("/pages/detail/detail?id=" + index)
				})
			}
		},
		onPullDownRefresh() {
			console.log('onPullDownRefresh')
			setTimeout(function() {
				uni.stopPullDownRefresh()
				console.log('stopPullDownRefresh')
			}, 1000)
		}
	}
</script>

<style>
	/* 头条小程序组件内不能引入字体 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		font-weight: normal;
		font-style: normal;
		src: url('~@/static/uni.ttf') format('truetype');
	}

	/* #endif */

	/* #ifndef APP-NVUE */
	page {
		display: flex;
		flex-direction: column;
		box-sizing: border-box;
		background-color: #efeff4;
		min-height: 100%;
		height: auto;
	}

	view {
		font-size: 14px;
		line-height: inherit;
	}

	.example {
		padding: 0 15px 15px;
	}

	.example-info {
		padding: 15px;
		color: #3b4144;
		background: #ffffff;
	}

	.example-body {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		padding: 0;
		font-size: 14px;
		background-color: #ffffff;
	}

	/* #endif */
	.example {
		padding: 0 15px;
	}

	.example-info {
		/* #ifndef APP-NVUE */
		display: block;
		/* #endif */
		padding: 15px;
		color: #3b4144;
		background-color: #ffffff;
		font-size: 14px;
		line-height: 20px;
	}

	.example-info-text {
		font-size: 14px;
		line-height: 20px;
		color: #3b4144;
	}


	.example-body {
		flex-direction: column;
		padding: 15px;
		background-color: #ffffff;
	}

	.word-btn-white {
		font-size: 18px;
		color: #FFFFFF;
	}

	.word-btn {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: center;
		border-radius: 6px;
		height: 48px;
		margin: 15px;
		background-color: #007AFF;
	}

	.word-btn--hover {
		background-color: #4ca2ff;
	}

	.uni-nav-bar-text {
		font-size: 28rpx;
	}

	.city {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		width: 120rpx;

		margin-left: 0px;
	}

	.input-view {
		/* #ifndef APP-PLUS-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		width: 480rpx;
		flex: 1;
		background-color: #f8f8f8;
		height: 30px;
		border-radius: 15px;
		padding: 0 15px;
		flex-wrap: nowrap;
		margin: 7px 0;
		line-height: 30px;
	}

	.input-uni-icon {
		line-height: 30px;
	}

	.nav-bar-input {
		height: 30px;
		line-height: 30px;
		/* #ifdef APP-PLUS-NVUE */
		width: 480rpx;
		/* #endif */
		padding: 0 5px;
		font-size: 28rpx;
		background-color: #f8f8f8;
	}

	.example-body {
		padding: 0;
	}


	view {
		font-size: 28upx;
	}

	.uni-product-list {
		display: flex;
		width: 100%;
		flex-wrap: wrap;
		flex-direction: row;
	}

	.uni-product {
		padding: 20upx;
		display: flex;
		flex-direction: column;
		margin-top: 10px;
	}

	.image-view {
		height: 330upx;
		width: 330upx;
		margin: 12upx 0;
	}

	.uni-product-image {
		height: 330upx;
		width: 330upx;
	}

	.uni-product-title {
		width: 300upx;
		word-break: break-all;
		display: -webkit-box;
		overflow: hidden;
		line-height: 1.5;
		text-overflow: ellipsis;
		-webkit-box-orient: vertical;
		-webkit-line-clamp: 2;
	}

	.uni-product-price {
		margin-top: 10upx;
		font-size: 28upx;
		line-height: 1.5;
		position: relative;
	}

	.uni-product-price-original {
		color: #e80080;
	}

	.uni-product-price-fav {
		font-size: 13px;
		color: #e8a4ad;
	}

	.uni-product-price-favour {
		color: #888888;
		text-decoration: line-through;
		margin-left: 10upx;
	}

	.uni-product-tip {
		position: absolute;
		right: 10upx;
		background-color: #ff3333;
		color: #ffffff;
		padding: 0 10upx;
		border-radius: 5upx;
	}
	.left
	{
		width: 230px;
		height: 20px;
		margin: 0 auto;
		margin-left: 10upx;
		margin-bottom: auto;
	}
	.right
	{
		width: 230px;
		height: 20px;
		margin: 0 auto;
		margin-right: 10upx;
		margin-bottom: auto;
	}
</style>
