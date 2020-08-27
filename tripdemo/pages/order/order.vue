<!--本页面由uniapp切片工具生成，uni-app切片-可视化设计工具(一套代码编译到7个平台iOS、Android、H5、小程序)，软件下载地址：http://www.ymznkf.com/new_view_669.htm -->
<template>
	<view class="YmContent">
			<view class="all_orders_1">
				<text decode="true" class="all_orders_2">全部订单</text>
				<view class="orders">
					<view v-for="(item_orders, index) in list_orders" :key="index" class="item">
						<view class="all_orders_5">
							<view class="all_orders_7">
								<image v-on:click="all_orders_8_8_click(item_orders)"  src="/static/all_orders/images/all_orders_8_8.jpg" mode="scaleToFill" border="0"    class="all_orders_8"></image>
								<text decode="true" class="address_from">{{item_orders.address_from}}</text>
							</view>
							<image v-on:click="all_orders_10_10_click(item_orders)"  src="/static/all_orders/images/all_orders_10_10.jpg" mode="scaleToFill" border="0"    class="all_orders_10"></image>
							<view class="all_orders_11">
								<image v-on:click="all_orders_12_12_click(item_orders)"  src="/static/all_orders/images/all_orders_12_12.jpg" mode="scaleToFill" border="0"    class="all_orders_12"></image>
								<text decode="true" class="address_to">{{item_orders.address_to}}</text>
							</view>
							<view class="all_orders_14">
								<text decode="true" class="all_orders_15">订单号</text>
								<text decode="true" class="orderNo">{{item_orders.orderNo}}</text>
							</view>
						</view>
						<image v-on:click="all_orders_6_6_click(item_orders)"  src="/static/all_orders/images/all_orders_6_7.jpg" mode="scaleToFill" border="0"    class="all_orders_6"></image>
					</view>
				</view>
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
   @import './all_orders.scss'
</style>