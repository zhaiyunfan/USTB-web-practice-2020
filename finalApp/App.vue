<script>
	export default {
		onLaunch: function() {
			console.log('App Launch');
			if (uni.getStorageSync("user") != null && uni.getStorageSync("user") != "") {
				console.log(uni.getStorageSync("user"));
				try {
					uni.request({
						url: 'http://hkkiack8.xiaomy.net:32771/tripapp/user/auth2',
						data: {
							identify: uni.getStorageSync("user").phoneNum,
							password: uni.getStorageSync("user").password
						},
						method: 'POST',
						header: {
							"Content-Type": "application/x-www-form-urlencoded"
						},
						success: (res) => {
							console.log(res);
							if (res.data.code === 200) {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '登陆成功'
								})
								// 设置缓存
								{
									getApp().globalData.isLogin = true;
									getApp().globalData.user.uid = res.data.data.uid;
									getApp().globalData.user.username = res.data.data.username;
									getApp().globalData.user.password = res.data.data.password;
									getApp().globalData.user.email = res.data.data.email;
									getApp().globalData.user.gender = res.data.data.gender;
									if (res.data.data.birthday != null)
										getApp().globalData.user.birthday = res.data.data.birthday.split(" ")[0];
									else
										getApp().globalData.user.birthday
									getApp().globalData.user.phoneNum = res.data.data.phoneNum;
									getApp().globalData.user.regDate = res.data.data.regDate;
									getApp().globalData.user.status = res.data.data.status;
									uni.setStorageSync("user", getApp().globalData.user);
								}
								console.log(getApp().globalData.user);
								uni.reLaunch({
									url: '../user/user'
								})
							}
						}
					})
				} catch (e) {
					//TODO handle the exception
				}
			}
		},
		onShow: function() {
			console.log('App Show');
		},
		onHide: function() {
			console.log('App Hide');
		},
		globalData: {
			user: {
				uid: '',
				username: '',
				password: '',
				email: '',
				gender: '',
				birthday: '',
				phoneNum: '',
				regDate: '',
				status: '',
				avatarUrl: '/static/image/userAvatar.png'
			},
			isLogin: false
		}
	};
</script>

<style>
	/* 解决头条小程序组件内引入字体不生效的问题 */
	/* #ifdef MP-TOUTIAO */
	@font-face {
		font-family: uniicons;
		src: url('/static/uni.ttf');
	}

	/* #endif */
</style>
