<template>
	<view class="content">
		<view class="nuter">
			<view :class="target==0?'active':''" @click="setIndex" data-index="0">
				全部
			</view>
			<view :class="target==1?'active':''" @click="setIndex" data-index="1">
				火车
			</view>
			<view :class="target==2?'active':''" @click="setIndex" data-index="2">
				飞机
			</view>
			<view :class="target==3?'active':''" @click="setIndex" data-index="3">
				轮船
			</view>
		</view>
		<swiper :duration="500" :current="thisindex" :data-index='thisindex' @change="toggle" circular>
			<swiper-item>

				<!-- 按顺序对应第一个的内容 -->
				<view v-for="item in transports" class="showTrans">
					<!-- <label class="transItem">{{item.tid}}</label> -->
					<label class="transItem">{{item.transType}}&nbsp;</label>
					<label class="transItem">列次&nbsp;{{item.transDesc}}</label>
					<br/>
					<label class="transItem">票价&nbsp;{{item.transPrice}}</label>
				</view>
			</swiper-item>
			<swiper-item>
				<!-- 按顺序对应第二个的内容-->
				<view v-for="item in transports" class="showTrans">
					<!-- {{item.tid}}
					{{item.transType}} -->
					<label class="transItem">列次&nbsp;{{item.transDesc}}</label>
					<br/>
					<label class="transItem">票价&nbsp;{{item.transPrice}}</label>
				</view>
			</swiper-item>
			<swiper-item>
				<!-- 按顺序对应第三个的内容 -->
				<view v-for="item in transports" class="showTrans">
					<label class="transItem">列次&nbsp;{{item.transDesc}}</label>
					<br/>
					<label class="transItem">票价&nbsp;{{item.transPrice}}</label>
				</view>
			</swiper-item>
			<swiper-item>
				<!-- 按顺序对应第四个的内容 -->
				<view v-for="item in transports" class="showTrans">
					<label class="transItem">列次&nbsp;{{item.transDesc}}</label>
					<br/>
					<label class="transItem">票价&nbsp;{{item.transPrice}}</label>
				</view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	export default {
		// 只要上面的选项跟下面的swiper-item个数相同，直接加上面的个数，下面的会自动对应显示，很简便，拿来就能用
		data() {
			return {
				// 当前样式
				target: 0,
				// 当前item位置
				thisindex: 0,
				// 数据数组
				transports: [],
				coloneHeight: 0
			}
		},
		methods: {
			// 切换触发的事件
			toggle(e) {
				let index = e.detail.current
				this.target = index
				this.getTransportsList();
			},
			// 点击nav控制下面的展示
			setIndex(e) {
				let index = e.currentTarget.dataset.index
				this.thisindex = index
			},
			getTransportsList: function() {
				if (this.thisindex == 0) {
					uni.request({
						url: 'http://wr3ck97y.xiaomy.net:30244/tripdemo/transports/',
						data: '',
						method: 'GET',
						success: (res) => {
							// this.transports=res.data.data;
							this.transports = res.data.data;
							console.log(res)
							//console.log(this.transports)
							//console.log(this.transports[0])
							// console.log(this.transports[0].length)
							// console.log(res.data.data)
							// console.log(res)
							// console.log(this.transports);
							//console.log(res.data);

							if (res.data.code === 200) {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询成功'
								})
							} else {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询失败！'
								})
							}
						}
					})
				} else if (this.thisindex == 1) {
					uni.request({
						url: 'http://wr3ck97y.xiaomy.net:30244/tripdemo/transports/train',
						data: '',
						method: 'GET',
						success: (res) => {
							this.transports = res.data.data;
							console.log(this.transports[1]);
							if (res.data.code === 200) {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询成功'
								})
							} else {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询失败！'
								})
							}
						}
					})
				} else if (this.thisindex == 2) {
					uni.request({
						url: 'http://wr3ck97y.xiaomy.net:30244/tripdemo/transports/plane',
						data: '',
						method: 'GET',
						success: (res) => {
							this.transports = res.data.data;
							console.log(this.transports[2]);
							if (res.data.code === 200) {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询成功'
								})
							} else {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询失败！'
								})
							}
						}
					})
				} else {
					uni.request({
						url: 'http://wr3ck97y.xiaomy.net:30244/tripdemo/transports/boat',
						data: {},
						method: 'GET',
						success: (res) => {
							this.transports = res.data.data;
							console.log(this.transports[3]);
							if (res.data.code === 200) {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询成功'
								})
							} else {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '查询失败！'
								})
							}
						}
					})
				}

			},
		}
	}
</script>

<style>
	.nuter {
		width: 100%;
		height: 80rpx;
		line-height: 80rpx;
		display: flex;
		justify-content: space-around;
		font-size: 35rpx;
	}

	.nuter view {
		flex: 1;
		font-size: 30rpx;
		text-align: center;
		transition: all 0.5s ease .1s;
		background-color: #f0f0f0;
	}

	.active {
		box-sizing: border-box;
		color: #007AFF;
		border-bottom: 5rpx solid #00aaff;
		background-color: #f3ffff;
		border-radius: 10rpx;
		box-shadow: 3px 3px 5px #888888;
	}

	swiper {
		width: 100%;
		height: 37.5rem;
	}

	swiper-item {
		width: 100%;
		word-break: break-all;
		/* overflow: hidden; */
		text-align: justify;
		position: absolute;
		/* line-height: 300rpx; */
		/* background-color: red; */
	}

	.swiper-item {
		overflow-y: scroll;
		width: 99.5%;
		height: 99%;
		/* background-color: white; */
		/* height: 99%; */
		box-sizing: border-box;
		padding: 1rpx;
	}

	.showTrans {
		width: 100%;
		height: 150rpx;
		border-radius: 10rpx;
		border: #4CD964 solid;
		display: block;
		margin: 20rpx;
	}
	
	.transItem {
		display: inline-block;
		color: #50B7EA;
	}
</style>
