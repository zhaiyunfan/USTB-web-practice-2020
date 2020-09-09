<template>
	<view>
		<view class="banner">
			<image class="banner-img" :src="detailData[id].banner.cover"></image>
			<view class="banner-title">{{detailData[id].banner.title}}</view>
		</view>
		<view class="article-meta">
			<text class="article-author">{{detailData[id].banner.author_name}}</text>
			<text class="article-text">发表于</text>
			<text class="article-time">{{detailData[id].banner.published_at}}</text>
		</view>
		<view class="article-content">
			<rich-text :nodes="detailData[id].htmlString"></rich-text>
		</view>
		<!-- #ifdef MP-WEIXIN -->
		<ad v-if="detailData[id][id].htmlString" unit-id="adunit-01b7a010bf53d74e"></ad>
		<!-- #endif -->
		<view>
			<hr>
			<view class='line'>
				<view class='lineLeft'>姓名</view>
				<view class="lineRight">
					<!-- data-name为自定义参数名称,同时也会以此为参数名存入data的form中-->
					<input class="input" @input="change" :value="form.name" data-name="name" placeholder-class="plaClass" placeholder='请输入姓名'></input>
				</view>
			</view>

			<view class='line'>
				<view class='lineLeft'>手机号</view>
				<view class="lineRight">
					<input class="input" @input="change" :value="form.phone" data-name="phone" placeholder-class="plaClass"
					 placeholder='请输入手机号'></input>
				</view>
			</view>
			<view class='line'>
				<view class='lineLeft'>身份证</view>
				<view class="lineRight">
					<input class="input" @input="change" :value="form.id" data-name="id" placeholder-class="plaClass" placeholder='请输入身份证'></input>
				</view>
			</view>
			<!--
			<view class='line'>
				<view class='lineLeft'>地址</view>
				<view class="lineRight">
					<picker @change="change" mode="region" data-name="region" :value="form.region" :custom-item="customItem">
						<view class="picker">
							{{form.region[0]}}，{{form.region[1]}}，{{form.region[2]}}
						</view>
					</picker>
					<view class="tips tri"></view>
				</view>
			</view>
			-->
			<view class='line'>
				<view class='lineLeft'>备注</view>
				<view class="lineRight">
					<input class="input" @input="change" :value="form.remarks" data-name="remarks" placeholder-class="plaClass"
					 placeholder='备注'></input>
					<view class="tips">选填</view>
				</view>
			</view>
			<view class="buttonBox" @click="submit">
				提交订单
			</view>
		</view>
	</view>
</template>

<script>
	const valid = require('../../util/valid.js'); //校验规则文件
	const util = require('../../util/util.js'); //防重点击函数
	export default {
		data() {
			return {
				id: 0,
				detailData: [{
						title: 'list-triplex-row',
						banner: {
							cover: '/static/spots/gugong001.jpg',
							title: '故宫经典一日游',
							author_name: '西蒙牛',
							published_at: '2020-07-14'
						},
						htmlString: "故宫",

						form: {
							region: ['广东省', '广州市', '海珠区'] //默认参数
						}
					},
					{
						title: 'list-triplex-row',
						banner: {
							cover: '/static/spots/tiantan001.jpg',
							title: '北京故宫-天坛一日游',
							author_name: 'csj',
							published_at: '2020-07-14'
						},
						htmlString: "天坛介绍",

						form: {
							region: ['广东省', '广州市', '海珠区'] //默认参数
						}
					},
					{
						title: 'list-triplex-row',
						banner: {
							cover: '/static/spots/yiheyuan001.jpg',
							title: '北京颐和园一日游',
							author_name: 'csj',
							published_at: '2020-07-14'
						},
						htmlString: "颐和园的介绍",
						
						form: {
							region: ['广东省', '广州市', '海珠区'] //默认参数
						}
					},
					{
						title: 'list-triplex-row',
						banner: {
							cover: '/static/spots/changcheng001.jpg',
							title: '八达岭长城一日游',
							author_name: 'csj',
							published_at: '2020-07-14'
						},
						htmlString: "长城的介绍",
						
						form: {
							region: ['广东省', '广州市', '海珠区'] //默认参数
						}
					},
					{
							title: 'list-triplex-row',
							banner: {
								cover: '/static/spots/shaolin.jpg',
								title: '少林寺一日游',
								author_name: '西蒙牛',
								published_at: '2020-07-14'
							},
							htmlString: "少林寺，是中国佛教禅宗祖庭和中国功夫的发源地，现为世界文化遗产、全国重点文物保护单位、国家AAAAA级旅游景区，位于河南省郑州市登封市嵩山五乳峰下，因坐落于嵩山腹地少室山茂密丛林之中，故名“少林寺”。始建于北魏太和十九年（495年），是孝文帝为了安置他所敬仰的印度高僧跋陀尊者，在与都城洛阳相望的嵩山少室山北麓敕建而成。少林寺常住院占地面积约57600平方米，现任方丈是曹洞正宗第47世、第33代嗣祖沙门释永信。 [1-2]  少林寺是世界著名的佛教寺院，是汉传佛教的禅宗祖庭，在中国佛教史上占有重要地位，被誉为“天下第一名刹”。因其历代少林武僧潜心研创和不断发展的少林功夫而名扬天下，素有“天下功夫出少林，少林功夫甲天下”之说。",
					
							form: {
								region: ['广东省', '广州市', '海珠区'] //默认参数
							}
					},
					{
							title: 'list-triplex-row',
							banner: {
								cover: '/static/spots/huangshan001.jpg',
								title: '黄山经典两日游',
								author_name: 'csj',
								published_at: '2020-07-14'
							},
							htmlString: "黄山的介绍",
					
							form: {
								region: ['广东省', '广州市', '海珠区'] //默认参数
							}
					}
				],
				form:[]
			}
		},
		onShareAppMessage() {
			return {
				title: this.detailData[id].banner.title,
				path: '/pages/detail/detail?detailDate=' + JSON.stringify(this.detailData[id].banner)
			}
		},
		onLoad: function (option) { //option为object类型，会序列化上个页面传递的参数
				this.id = option.id;
		        console.log(option.id); //打印出上个页面传递的参数。
		        uni.setNavigationBarTitle({
		        	title: this.detailData[this.id].banner.title
		        });
		 },
		methods: {
			// 输入框或者picker事件方法
			change(e) {
				let name = e.currentTarget.dataset.name;
				let tempVal = e.target.value || e.detail.value;
				if (this.form[name] === undefined) {
					console.log('首次添加属性名')
					this.$set(this.form, name, tempVal)
				} else {
					// 若存在则直接赋值
					this.form[name] = tempVal
				}
			},

			// 验证方法
			toVaild(tempList) {
				// valid(value,type)为引入的校验方法,49行可见
				return tempList.every((item, index) => {
					let isVal = this.form[item.paramName]
					if (!isVal || !valid(isVal, item.rules)) {
						uni.showModal({
							title: '错误',
							content: `${item.failPass}`,
							showCancel: false
						})
						return false;
					}
					return true;
				})
			},

			// 提交
			submit: util.throttle(function(e) {
				console.log('提交')
				let tempList = [{
					paramName: 'name', //data-name和form中的参数名
					failPass: '请输入正确的姓名', //失败的提示
					rules: 'name', //校验的规则名称
				}, {
					paramName: 'phone', //data-name和form中的参数名
					failPass: '请输入正确的手机号', //失败的提示
					rules: 'phone', //校验的规则名称
				}, {
					paramName: 'id', //data-name和form中的参数名
					failPass: '请输入正确的身份证', //失败的提示
					rules: 'cardid', //valid中的规则名称
				}]
				if (this.toVaild(tempList)) {
					uni.showToast({
						title: '通过'
					})
				}
			}, 1000), //防重点击,1s内只可点击一次
		},

		getDetail() {
			uni.request({
				url: 'https://unidemo.dcloud.net.cn/api/news/36kr/' + this.detailData[id].banner.post_id,
				success: (data) => {
					if (data.statusCode == 200) {
						this.detailData[id].htmlString = data.data.content.replace(/\\/g, "").replace(/<img/g,
							"<img style=\"display:none;\"");
					}
				},
				fail: () => {
					console.log('fail');
				}
			})
		}
	}
</script>

<style lang="scss">
	view {
		font-size: 28upx;
		line-height: 1.8;
	}


	.line {
		margin-top: 30rpx;
		height: 100rpx;
		width: 92%;
		margin: 0 auto;
		border-bottom: 1px solid #f5f5f5;
		display: flex;

		.lineRight {
			.tips {
				color: #9a9a9c;
				font-weight: bold;
			}

			flex: 1;
			height: 100%;
			display: flex;
			align-items: center;
			justify-content: space-between;
			position: relative;
		}

		.lineLeft {
			display: flex;
			width: 20%;
			align-items: center;
			height: 100%;
			font-weight: bold;
			color: #000000;
		}

		.input {
			padding-right: 20rpx;
			height: 100%;
			width: 70%;
			text-align: left;
			font-size: 28rpx;
			color: #545151;
		}

		.picker {
			height: 100%;
			justify-content: flex-start;
			display: flex;
			align-content: center;
			width: 500rpx;
		}

		picker {
			height: 84rpx;
			line-height: 84rpx;
		}
	}


	.buttonBox {
		width: 91%;
		margin: 0 auto;
		height: 84rpx;
		border-radius: 84rpx;
		color: white;
		display: flex;
		align-items: center;
		justify-content: center;
		/*position: fixed;*/
		top: 10rpx;
		bottom: 100rpx;
		left: 0;
		right: 0;
		background-color: #1da31b;
	}

	.banner {
		height: 360upx;
		overflow: hidden;
		position: relative;
		background-color: #ccc;
	}

	.banner-img {
		width: 100%;
	}

	.banner-title {
		max-height: 84upx;
		overflow: hidden;
		position: absolute;
		left: 30upx;
		bottom: 30upx;
		width: 90%;
		font-size: 32upx;
		font-weight: 400;
		line-height: 42upx;
		color: white;
		z-index: 11;
	}

	.article-meta {
		padding: 20upx 40upx;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		color: gray;
	}

	.article-text {
		font-size: 26upx;
		line-height: 50upx;
		margin: 0 20upx;
	}

	.article-author,
	.article-time {
		font-size: 30upx;
	}

	.article-content {
		padding: 0 30upx;
		overflow: hidden;
		font-size: 30upx;
		margin-bottom: 30upx;
	}
</style>
