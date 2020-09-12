<template>
	<view class="mosowe-swiper" :style="'height:'+height+'rpx'">
		<swiper
			class="uni-swiper"
			:indicator-dots="indicator === 'dots'"
			:indicator-color="indicatorColor"
			:indicator-active-color="indicatorActiveColor"
			:autoplay="autoplay"
			:current="active"
			:interval="interval"
			:duration="duration"
			:vertical="vertical"
			:disable-touch="touchable"
			:previous-margin="canPyramid ? pyramidMargin : ''"
			:next-margin="canPyramid ? pyramidMargin : ''"
			:display-multiple-items="canPyramid ? 1 : itemNums"
			circular
			@change="bannerChange"
			>
		<!-- 非微信小程序 -->
		<!-- #ifndef MP-WEIXIN -->
				<swiper-item
					class="item" 
					v-for="(item,index) of lists" 
					:key="index"
					:class="canPyramid && active !== index ? 'swiper-pyramid' : canPyramid && active === index ? 'swiper-active' : ''"
					@click="bannerClick(index)"
					>
					<!-- 仅图片展示 -->
					<image :src="imageKey ? item[imageKey] : item" class="image" v-if="swiperType === 'image'"></image>
					<!-- 仅文字展示 -->
					<text  class="text" v-if="swiperType === 'text'">{{textKey ? item[textKey] : item}}</text>
					<!-- 图文左右轮播 -->
					<view class="imageTextLine"  v-if="swiperType === 'imageTextLine'">
						<image class="avatar" :src="item[imageKey]" />
						<text class="content">{{item[textKey]}}</text>
					</view>
				</swiper-item>
		<!-- #endif -->
		
		
		<!-- 微信小程序内 -->
		<!-- #ifdef MP-WEIXIN -->
			<!-- 可手动滑动 -->
			<block v-if="!touchable">
				<swiper-item 
					class="item" 
					v-for="(item,index) of lists" 
					:key="index"
					:class="canPyramid && active !== index ? 'swiper-pyramid' : canPyramid && active === index ? 'swiper-active' : ''"
					@click="bannerClick({item,index})"
					>
					<!-- 仅图片展示 -->
					<image :src="imageKey ? item[imageKey] : item" class="image" v-if="swiperType === 'image'"></image>
					<!-- 仅文字展示 -->
					<text  class="text" v-if="swiperType === 'text'">{{textKey ? item[textKey] : item}}</text>
					<!-- 图文左右轮播 -->
					<view class="imageTextLine"  v-if="swiperType === 'imageTextLine'">
						<image class="avatar" :src="item[imageKey]" />
						<text class="content">{{item[textKey]}}</text>
					</view>
				</swiper-item>				
			</block>
			<!-- 禁用手动滑动 -->
			<block v-if="touchable">
				<swiper-item
					class="item" 
					v-for="(item,index) of lists" 
					:key="index"
					:class="canPyramid && active !== index ? 'swiper-pyramid' : canPyramid && active === index ? 'swiper-active' : ''"
					@touchstart.stop="WXAPP_bannerTouch"
					@touchend="WXAPP_bannerTouchEnd({item,index})"
					>
					<!-- 仅图片展示 -->
					<image :src="imageKey ? item[imageKey] : item" class="image" v-if="swiperType === 'image'" />
					<!-- 仅文字展示 -->
					<view  class="text" v-if="swiperType === 'text'"><text>{{textKey ? item[textKey] : item}}</text></view>
					<!-- 图文左右轮播 -->
					<view class="imageTextLine"  v-if="swiperType === 'imageTextLine'">
						<image class="avatar" :src="item[imageKey]" />
						<text class="content">{{item[textKey]}}</text>
					</view>
				</swiper-item>				
			</block>
		<!-- #endif -->
		</swiper>
		
		
		<!-- 数字指示 -->
		<view class="custom-indicator" v-if="indicator === 'number'">
			{{ active + 1 }}/{{lists.length}}
		</view>
	</view>
</template>

<script>
export default {
  name: 'mosowe-swiper',
  components: {},
  props: {
		current: { // 当前展示的索引
			type:Number,
			default: 0
		},
		autoplay: { // 是否自动切换
			type: Boolean,
			default: true
		},
		interval: { // 自动切换时间间隔
			type: Number,
			default: 3000
		},
		duration: { // 切换动画时长
			type: Number,
			default: 500
		},
		vertical: { // 滑动方向是否为纵向
			type: Boolean,
			default: false
		},
		indicator: { // 指示点样式：dots点，number数字右下角，空则不会显示
			type: String,
			default: ''
		},
		indicatorColor: { // dot点样式：默认颜色
			type: String,
			default: 'rgba(255, 255, 255, 0.5)'
		},
		indicatorActiveColor: { // dot点选中样式：高亮颜色
			type: String,
			default: '#ffffff'
		},
		scene: { // 场景值
			type: String,
			default: ''
		},
		touchable: { // 是否禁用手动滑动
			type: Boolean,
			default: false
		},
		lists: { // 轮播列表
			type: Array,
			default: () => {
				return [];
			} 
		},
		swiperType: { // 轮播类型:image图片轮播，imageTextLine图文一行轮播，text文本轮播
			type: String,
			default: 'image'
		},
		previewImage: { // 开启图片预览
			type: Boolean,
			default: false
		},
		imageKey: { // 图片的key值，重复使用的组件可能遇到不同的key，此处传图片的key
			type: String,
			default: ''
		},
		textKey: { // 文本的key值，重复使用的组件可能遇到不同的key，此处传文本的key
			type: String,
			default: ''
		},
		height: { // 轮播区的高度，单位rpx
			type: Number,
			default: 300
		},
		pyramid: { // 金字塔式，横向且纯图模式有效，开启金字塔模式时，active初始化最少为1，最大为this.lists.length -2
			type: Boolean,
			default: false
		},
		pyramidMargin: { // 金字塔式，前后露出的距离，单位rpx，px
			type: String,
			default: '30rpx'
		},
		itemNums: { // 同时展示个数,开启金字塔模式时, itemNums = 1
			type: String,
			default: '1'
		}
	},
  data () {
    return {
			active: 0,
			activePrev: -1,
			activeNext: -1,
			canPyramid: false,
			touchStartTime:0, // 微信小程序端：触摸事件判断点击或滑动
    };
  },
	created () {
		this.active = this.current
		if (this.pyramid && this.swiperType === 'image' && !this.vertical) {
			this.canPyramid = true
			if (this.active === 0 || this.active < 0 || this.active > this.lists.length - 1) {
				this.active = 1
			} else if (this.active === this.lists.length - 1) {
				this.active = this.lists.length -2
			}
		}
	},
  methods: {
		// 微信小程序：banner触摸时，禁止手动滑动的时候触发
		WXAPP_bannerTouch () {
			if(this.previewImage) {
				this.touchStartTime = new Date().getTime()
			}
		},
		// 微信小程序：触摸完
		WXAPP_bannerTouchEnd (item) {
			let t = new Date().getTime()
			if (t-this.touchStartTime <= 200) { // 点击
				this.bannerClick(item)
			} else {
				if (this.touchable) {
					return false
				}
			}
		},
		// banner轮播时
		bannerChange (e) {
			this.active = e.detail.current;
			this.$emit('change', e.detail.current);
		},
		// banner点击时
		bannerClick:function (index) {
			/*console.log(item);
			if (this.swiperType === 'image' && this.previewImage) { // 纯图片模式下，开启预览模式
				let urls = [];
				if (this.imageKey) {
					for (let image of this.lists) {
						urls.push(image[this.imageKey]);
					}					
				} else {
					urls = this.lists;
				}
				console.log(urls)
				uni.previewImage({
					current: item.index,
					urls: urls
				});				
			}
			this.$emit('bclick', item);*/
			uni.navigateTo({
				//url: "/pages/detail/detail?detailDate=" + encodeURIComponent(JSON.stringify(detail))
			
				url: ("/pages/detail/detail?id="+index)
			})
		},
		
  }
};
</script>

<style lang="scss">
.mosowe-swiper{
	width: 100%;
	position: relative;
	.uni-swiper {
		height: inherit;
		.uni-swiper-dot{
			width: 20px !important;
			height: 8px !important;
			border-radius: 4px;
		}
		.item {
			box-sizing: border-box;
			.image{
				width: 100%;
				height: 100%;
			}
			// 支付宝
			/* #ifdef MP-ALIPAY */
			&.swiper-pyramid{
				padding: 0 30rpx;
			}
			/* #endif */
			
			// 非支付宝
			/* #ifndef MP-ALIPAY */
			&.swiper-pyramid{
				padding: 30rpx;
			}
			&.swiper-prev {
				animation: prev .5s forwards;
			}
			&.swiper-next {
				animation: next .5s forwards;
			}
			&.swiper-active {
				animation: actives .5s forwards;
			}
			/* #endif */
		}
	}
	// 纯图
	.image{
		width: 100%;
		height: 100%;
	}
	// 纯文
	.text{
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		width: 100%;
		display: block;
	}
	// 图文一行
	.imageTextLine {
		overflow: hidden;
		width: 100%;
		display: flex;
		align-items: center;
		.avatar{
			flex: 0 0 50rpx;
			height: 50rpx;
			border-radius: 50%;
			margin-right: 10rpx;
		}
		.content{
			flex: 1;
			white-space: nowrap;
			text-overflow: ellipsis;
			overflow: hidden;
		}
	}
	.custom-indicator{
		position: absolute;
		right: 30rpx;
		bottom: 30rpx;
		background: rgba(0, 0, 0, 0.2);
		width: 80rpx;
		height: 40rpx;
		line-height: 40rpx;
		font-size: 24rpx;
		color: #fff;
		border-radius: 30rpx;
		text-align: center;
	}
}
@keyframes prev {
	0%{
		padding: 0;
	}
	100% {
		padding: 30rpx;
	}
}
@keyframes next {
	0%{
		padding: 0;
	}
	100% {
		padding: 30rpx;
	}
}
@keyframes actives {
	0%{
		padding: 30rpx;
	}
	100% {
		padding: 0;
	}
}
</style>
