'use strict';
exports.main = async (event, context) => {
  try {
	console.log(event.mobile)
	console.log(event.randomCode)
    const res = await uniCloud.sendSms({
      smsKey: '037086937469a92d136602c721b81b5a',
      smsSecret: 'b4f42e0e00221098187318337e894b4a',
      phone: event.mobile,  //从mobile参数中获取手机号码
      templateId: 'uniID_code',
      data: {
        name: '去哪儿网',
        code: event.randomCode, //从参数中获取要发送的验证码
        action: '用户注册',
        expMinute: '3',
      }
    })
    // 调用成功，请注意这时不代表发送成功
    return res
  } catch(err) {
    // 调用失败
    console.log(err.errCode)
    console.log(err.errMsg)
    return {
      code: err.errCode ,
      msg: err.errMsg
    }
  }
};
