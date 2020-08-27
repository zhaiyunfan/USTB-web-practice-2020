'use strict';
exports.main = async (event, context) => {
	try {
		const res = await uniCloud.sendSms({
			smsKey: 'f924ede09a1e52112f100826d713e616',
			smsSecret: 'cd9f8b62f8497e032c8eb9fd745d9298',
			phone: event.mobile,
			templateId: 'uniID_code',
			data: {
				name: '去哪儿网',
				code: '123456',
				action: '用户注册',
				expMinute: '3',
			}
		})
		// 调用成功，请注意这时不代表发送成功
		return res
	} catch (err) {
		// 调用失败
		console.log(err.errCode)
		console.log(err.errMsg)
		return {
			code: err.errCode,
			msg: err.errMsg
		}
	}
};
