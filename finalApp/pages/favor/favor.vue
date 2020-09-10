<template>
	<view class="YmContent">
		<view class="all_orders_1">
			<text decode="true" class="all_orders_2">全部收藏</text>
			<view class="orders">
				<view v-if="loadData&&favorsLen">
					<view v-for="favor in listFavors" class="item">
						<view class="all_orders_5">
							<view class="all_orders_7">
								<image v-on:click="all_orders_8_8_click(favor)" src="/static/all_orders/images/all_orders_8_8.jpg" mode="scaleToFill"
								 border="0" class="all_orders_8"></image>
								<text decode="true">{{favor.productName}}</text>
								<text decode="true" class="addr">{{favor.addr}}</text>
							</view>
							<image v-on:click="all_orders_10_10_click(favor)" src="/static/all_orders/images/all_orders_10_10.jpg"
							 mode="scaleToFill" border="0" class="all_orders_10"></image>
							<view class="all_orders_11">
								<image v-on:click="all_orders_12_12_click(favor)" src="/static/all_orders/images/all_orders_12_12.jpg"
								 mode="scaleToFill" border="0" class="all_orders_12"></image>
								<text decode="true">价格：¥</text>
								<text decode="true" class="price">{{favor.price}}</text>
							</view>
							<view class="all_orders_14">
								<text decode="true" class="all_orders_15">该商品一共被收藏<text style="color: #007AFF; font-size: 14px;">{{favor.favorTime}}</text>次</text>
								<!-- <button class="payButton" @click="pay(item_orders.oid,item_orders.price)" v-if="!item_orders.isPaid">点击取消收藏</button> -->
							</view>

						</view>
						<!-- <image v-on:click="all_orders_6_6_click(item_orders)" src="/static/all_orders/images/all_orders_6_8.jpg" mode="scaleToFill"
						 border="0" class="all_orders_6" v-if="!item_orders.isPaid"></image>
						<image v-on:click="all_orders_6_6_click(item_orders)" src="/static/all_orders/images/all_orders_6_7.jpg" mode="scaleToFill"
						 border="0" class="all_orders_6" v-else></image> -->
						<button class="deleteButton" @click="deleteOrder(favor.sid)">删除</button>

					</view>
				</view>
				<view v-else class="hint">
					暂无收藏
				</view>
			</view>
		</view>


		<!-- <view class="loading">{{loadingText}}</view>
		<view class="ymBbottom"></view> -->
	</view>
</template>

<script>
	import allFavors from "./allFavors.js";
	export default allFavors;
</script>

<style lang="scss" scoped>
	@import './allFavors.scss';

	.deleteButton {
		display: inline-block;
		font-size: 24rpx;
		margin-top: 74rpx;
		margin-right: 10rpx;
		float: right;
	}

	.payButton {
		display: inline-block;
		float: right;
		font-size: 24rpx;
	}

	.orders {
		height: 80%;
	}
	
	.hint {
		font-size: 30px;
		text-align: center;
	}
</style>
