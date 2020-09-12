<template>
	<view class="YmContent">
		<view class="all_orders_1">
			<text decode="true" class="all_orders_2">全部订单</text>
			<view class="orders">
				<view v-for="(item_orders, index) in list_orders" :key="index" class="item">
					<view class="all_orders_5">
						<view class="all_orders_7">
							<image v-on:click="all_orders_8_8_click(item_orders)" src="/static/all_orders/images/all_orders_8_8.jpg" mode="scaleToFill"
							 border="0" class="all_orders_8"></image>
							<text decode="true">旅游线路：</text>
							<text decode="true" class="addr">{{item_orders.addr}}</text>
						</view>
						<image v-on:click="all_orders_10_10_click(item_orders)" src="/static/all_orders/images/all_orders_10_10.jpg" mode="scaleToFill"
						 border="0" class="all_orders_10"></image>
						<view class="all_orders_11">
							<image v-on:click="all_orders_12_12_click(item_orders)" src="/static/all_orders/images/all_orders_12_12.jpg"
							 mode="scaleToFill" border="0" class="all_orders_12"></image>
							<text decode="true">价格：¥</text>
							<text decode="true" class="price">{{item_orders.price}}</text>
						</view>
						<view class="all_orders_14">
							<text decode="true" class="all_orders_15">订单时间：</text>
							<text decode="true" class="orderDate">{{item_orders.orderDate}}</text>
							<button class="payButton" @click="pay(item_orders.oid,item_orders.price)" v-if="!item_orders.isPaid">点击支付</button>
						</view>

					</view>

					<image v-on:click="all_orders_6_6_click(item_orders)" src="/static/all_orders/images/all_orders_6_8.jpg" mode="scaleToFill"
					 border="0" class="all_orders_6" v-if="!item_orders.isPaid"></image>
					<image v-on:click="all_orders_6_6_click(item_orders)" src="/static/all_orders/images/all_orders_6_7.jpg" mode="scaleToFill"
					 border="0" class="all_orders_6" v-else></image>
					<button class="deleteButton" @click="deleteOrder(item_orders.oid)">删除</button>
				</view>
				<view class="item"></view>
			</view>
		</view>
		<view class="totalPrice">
			<text decode="true" class="total">合计待支付：¥{{total}}</text>
			<button class="totalButton" @click="allSettle">一键结算</button>
		</view>
		<view class="loading">{{loadingText}}</view>
		<view class="ymBbottom"></view>
	</view>
</template>

<script>
	import all_orders from "./all_orders.js";
	export default all_orders;
</script>

<style lang="scss" scoped>
	@import './all_orders.scss';

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

	.totalPrice {
		height: 100px;
		width: 100%;
		background-color: #ffffff;
		position: fixed;
		bottom: 0;
		border:0.05px solid #e7e7e7;
	}
	.total{
		font-size: 28rpx;
		margin-left: 50rpx;
		margin-top: 10rpx;
		line-height:46px;
	}
	.totalButton{
		display: inline-block;
		float: right;
		font-size: 28rpx;
		margin-right: 60rpx;
		margin-top: 10rpx;
	}
</style>
