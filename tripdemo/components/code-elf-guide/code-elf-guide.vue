<template>
	<view class="content">
		<swiper class="swiper" :autoplay="autoplay" :duration="duration" @change="guideAction">
			<swiper-item>
				<view class="swiper-item">
					<view class="swiper-item-img">
						<image src="../../static/guide/title_01.png" mode="aspectFit"></image>
					</view>
					<view class="swiper-item-img">
						<image src="../../static/guide/icon_01.png" mode="aspectFit"></image>
					</view>
				</view>
				<view class="jump-over" @tap="launchFlag()">{{jumpover}}</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<view class="swiper-item-img">
						<image src="../../static/guide/title_02.png" mode="aspectFit"></image>
					</view>
					<view class="swiper-item-img">
						<image src="../../static/guide/icon_02.png" mode="aspectFit"></image>
					</view>
				</view>
				<view class="jump-over" @tap="launchFlag()">{{jumpover}}</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<view class="swiper-item-img">
						<image src="../../static/guide/title_03.png" mode="aspectFit"></image>
					</view>
					<view class="swiper-item-img">
						<image src="../../static/guide/icon_03.png" mode="aspectFit"></image>
					</view>
				</view>
				<view class="jump-over" @tap="launchFlag()">{{jumpover}}</view>
			</swiper-item>
			<swiper-item>
				<view class="swiper-item">
					<view class="swiper-item-img">
						<image src="../../static/guide/title_04.png" mode="aspectFit"></image>
					</view>
					<view class="swiper-item-img">
						<image src="../../static/guide/icon_04.png" mode="aspectFit"></image>
					</view>
				</view>
				<view class="experience" @tap="launchFlag()">{{experience}}</view>
			</swiper-item>
		</swiper>
		<!-- 按钮样式切换 -->
		<template v-if="cur != 3">
			<view class=" flex-column dots">
				<block v-for="(item,index) in background" :key="index">
					<view class="dot" :class="{'active':  index == cur}"></view>
				</block>
			</view>
		</template>
		<view class="uniapp-img">
			<image src="../../static/guide/uniapp4@2x.png" mode="aspectFit"></image>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				background: ['color1', 'color2', 'color3','color4'],
				cur: 0,
				autoplay: true,
				duration: 500,
				jumpover: '跳过',
				experience: '立即体验'
			}
		},
		methods: {
			guideAction(event) {
				let that = this,
					index = event.detail.current;
				that.cur = index;
			},
			
			launchFlag: function() {
				/**
				 * 向本地存储中设置launchFlag的值，即启动标识；
				 */
				uni.setStorage({
					key: 'launchFlag',
					data: true,
				});
				uni.switchTab({
					url: '/pages/home/home'
				});

			}
		}
	}
</script>
<style>
	page,
	.content {
		width: 100%;
		height: 100%;
		background-size: 100% auto;
		padding: 0;
	}

	.swiper {
		width: 100%;
		height: 80%;
		background: #FFFFFF;
	}

	.swiper-item {
		width: 100%;
		height: 100%;
		text-align: center;
		position: relative;
		display: flex;
		/* justify-content: center; */
		align-items: flex-end;
		flex-direction: column-reverse
	}

	.swiper-item-img {
		width: 100%;
		height: auto;
		margin: 0 auto;
	}

	.swiper-item-img image {
		width: 80%;
	}

	.uniapp-img {
		height: 20%;
		background: #FFFFFF;
		display: flex;
		justify-content: center;
		align-items: center;
		overflow: hidden;
	}

	.uniapp-img image {
		width: 40%;
	}

	.jump-over,
	.experience {
		position: absolute;
		height: 60upx;
		line-height: 60upx;
		padding: 0 40upx;
		border-radius: 30upx;
		font-size: 32upx;
		color: #454343;
		border: 1px solid #454343;
		z-index: 999;
	}

	.jump-over {
		right: 45upx;
		top: 125upx;
	}

	.experience {
		right: 50%;
		margin-right: -105upx;
		bottom: 0;
	}
	
	.dots {
		display: flex;
		justify-content: center;
		position: absolute;
		z-index: 999;
		height: 50rpx;
		left: 0;
		right: 0;
		bottom: 10rpx;
	}
	
	.dot {
		margin: 0 4rpx;
		width: 15rpx;
		height: 15rpx;
		background: #CDD2DD;
		border-radius: 8rpx;
		transition: all .6s;
	}
	
	.dot.active {
		width: 40rpx;
		background: #838892 !important;
	}
</style>
