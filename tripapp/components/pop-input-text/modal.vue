<template name="modal">
	<view class="">
		<view class="div" :style={height:windowHeight,width:windowWidth} :class="isShow==true?show:unShow">
			<view class="title">
				{{title}}
			</view>
			<view class="inp" v-show="inp">
				<view class="hint">新用户名</view>
				<input type="text" value="" :placeholder="placeholder" v-model="newUsername" />
			</view>
			<view class="inp" v-show="inp">
				<view class="hint">性别</view>
				<radio-group class="genderRadio" @change="getSelectedGender($event)">
					<radio value="男">男</radio>
					<radio value="女">女</radio>
				</radio-group>
			</view>


			<view class="inp" v-show="inp">
				<view class="birthdayHint">出生日期:</view>
				<input type="text" value="" v-model="year" class="birthdayItem" />
				-
				<input type="text" value="" v-model="moth" class="birthdayItem" />
				-
				<input type="text" value="" v-model="day" class="birthdayItem" />
			</view>


			<!-- <view class="content" v-show='!inp'>
				{{content}}
			</view> -->
			<view class="btn">
				<view class="left" :style={color:cancelCor} hover-class="hover" @click="cancel()">
					{{cancelBtn}}
				</view>
				<view class="right" :style={color:okCor} hover-class="hover" @click="ok()">
					{{okBtn}}
				</view>
			</view>
		</view>
		<view class="mask" v-show="isShow"></view>
	</view>
</template>

<script>
	export default {
		name: "modal",
		props: {
			isShow: {
				type: Boolean,
				default: false
			},
			title: {
				type: String,
				default: '更改您的个人信息'
			},
			placeholder: {
				type: String,
				default: '请输入'
			},
			inpValue: String,
			cancelBtn: {
				type: String,
				default: '取消'
			},
			okBtn: {
				type: String,
				default: '确定'
			},
			cancelCor: {
				type: String,
				default: 'black'
			},
			okCor: {
				type: String,
				default: '#007AFF'
			},
			inp: {
				type: Boolean,
				default: true
			},
			content: {
				type: String,
				default: '请输入内容...'
			}
		},
		data() {
			return {
				windowHeight: '',
				windowWidth: '',
				animation: {},
				show: 'show',
				unShow: 'unShow2',
				newUsername: '',
				newGender: '',
				year: '',
				moth: '',
				day: '',
				hasErrorInfo: false
			}
		},
		created() {
			console.log("modal created...")
			let that = this
			uni.getSystemInfo({
				success: function(res) {
					that.windowHeight = res.windowHeight * 0.45 + 'px'
					that.windowWidth = res.windowWidth * 0.8 + 'px'
				}
			})
		},
		methods: {
			cancel() {
				this.$emit('cancel', true)
			},
			ok() {
				console.log(this.newUsername)
				console.log(this.newGender)
				console.log(this.newBirthday)
				if ((this.newUsername == null || this.newUsername == "") && (this.newGender == null || this.newGender == "") &&
					(this.newBirthday == null || this.newBirthday.length < 6)) {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '您未做出任何更改或有一项数据未填写，请点击取消'
					})
					return false;
				}

				let date = new Date();
				if (this.newBirthday != null && this.newBirthday.length >= 6) {
					if (this.newBirthday.length > 8) {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '您输入的日期不合法'
						})
						this.hasErrorInfo = true;
					} else {
						date.setYear(this.year);
						date.setMonth(this.moth);
						date.setDate(this.day);
						console.log(date.getTime())
						this.hasErrorInfo = false;
					}
				}

				if (this.hasErrorInfo)
					return false;

				console.log(getApp().globalData.user)
				uni.request({
					url: 'http://hkkiack8.xiaomy.net:32771/tripapp/user/changeBasic',
					data: {
						uid: getApp().globalData.user.uid,
						username: this.newUsername,
						gender: this.newGender,
						birthday: date.getTime()
					},
					method: 'POST',
					header: {
						"Content-Type": "application/x-www-form-urlencoded" //自定义请求头信息
					},
					success: (res) => {
						console.log(res)
						if (res.data.code == 200) {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '更新成功'
							})
							console.log(res.data.data.birthday.split(" ")[0])
							getApp().globalData.user.birthday = res.data.data.birthday.split(" ")[0];
							getApp().globalData.user.username = res.data.data.username;
							getApp().globalData.user.gender = res.data.data.gender;
							uni.setStorageSync("user", getApp().globalData.user);
							uni.navigateBack({
								delta: 1
							})
							console.log("relauch")
							uni.reLaunch({
								url: '../../pages/user/user'
							})
						} else {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: res.data.msg
							})
						}
					}
				})

			},
			getSelectedGender: function(e) {
				console.log(e.target.value.trim())
				this.newGender = e.target.value.trim();
			}
		},
		computed: {
			newBirthday: function() {
				return this.year + this.moth + this.day;
			}
		}
	}
</script>

<style>
	.mask {
		position: fixed;
		left: 0;
		top: 0;
		width: 100%;
		height: 100%;
		background-color: rgba(0, 0, 0, 0.3);
		z-index: 50;
	}

	.div {
		text-align: center;
		color: white;
		background: white;
		z-index: 99;
		position: absolute;
		top: 50%;
		left: 50%;
		transform: translate(-50%, -85%);
		border-radius: 5%;
	}

	.title {
		display: inline-block;
		text-align: center;
		font-weight: 500;
		font-size: 20px;
		color: #007AFF;
		margin-top: 10%;
	}

	.inp {
		padding: 5px 0px;
		font-size: 13px;
		margin: auto;
		margin-top: 10%;
		text-align: center;
		color: #868686;
		width: 90%;
		border: 1.2px solid #DEDEDE;
		border-radius: 5px;
	}

	.hint {
		display: inline-block;
		text-align: center;
		margin: 10rpx auto;
	}

	.birthdayHint {
		display: inline-block;
		text-align: center;
		/* font-weight: 500; */
		/* font-size: 20px; */
		color: #007AFF;
		width: 150px;
		margin-left: 5%;
	}

	.btn {
		position: absolute;
		bottom: 0;
		left: 0;
		width: 100%;
		height: 40px;
		display: flex;
		font-size: 13px;
	}

	.left {
		border-top: 1px solid #EAEAEA;
		border-right: 1px solid #EAEAEA;
		width: 50%;
		color: black;
		display: inline-block;
		/* text-align: center; */
		margin: 0 auto;
		font-size: 18px;
		line-height: 40px;
	}

	.right {
		font-size: 18px;
		border-top: 1px solid #EAEAEA;
		width: 50%;
		color: #007AFF;
		display: inline-block;
		margin: 0 auto;
		/* text-align: center; */
		line-height: 40px;
	}

	.hover {
		background: rgba(0, 0, 0, 0.05);
	}

	.unShow1 {
		animation: go_out .3s;
	}

	@keyframes go_out {
		0% {
			opacity: 1;
		}

		100% {
			opacity: 0;
		}
	}

	.unShow2 {
		display: none;
	}

	.show {
		animation: go_in 1s;
		display: block;
	}

	@keyframes go_in {
		0% {
			opacity: 0;
		}

		100% {
			opacity: 1;
		}
	}

	.content {
		font-size: 13px;
		margin-top: 12%;
		color: #000000;
	}

	.genderRadio {
		display: inline-block;
		margin-right: 60rpx;
	}

	.birthdayItem {
		display: inline-block;
		border-radius: 2rpx;
		border-color: #007AFF solid;
	}
</style>
