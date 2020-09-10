module.exports = function(value, type) {
	if (value == undefined) value = ''
	switch (type) {
		case 'account':
			// 账号
			var re = /^[a-zA-z]\w{3,15}$/;
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'cardid':
			// 身份证
			return IdentityCodeValid(value)
			break;
		case 'name':
			// 姓名
			var re = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'phone':
			//手机 
			var re = /^1\d{10}$/
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'tel':
			// 电话
			var re = /^0\d{2,3}-?\d{7,8}$/;
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'mail':
			// 邮件 
			var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'special':
			// 特殊符号
			var regEn = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im,
				regCn = /[·！#￥（——）：；“”‘、，|《。》？、【】[\]]/im;
			if (regEn.test(value) || regCn.test(value)) {
				console.log("名称不能包含特殊字符.");
				return false;
			} else {
				return true;
			}
			break;
		case 'currency':
			// 金额
			var re = /^\d+.?\d{0,2}$/;
			if (re.test(value)) {
				return true;
			} else {
				return false;
			}
			break;
		case 'password':
			// 密码
			if (value.length < 6) {
				return false;
			} else {
				return true;
			}
			break;
		default:
			break;
	}
	return false;
}

/**
 * 身份证校验算法
 */
function IdentityCodeValid(code) {
	var city = {
		11: "北京",
		12: "天津",
		13: "河北",
		14: "山西",
		15: "内蒙古",
		21: "辽宁",
		22: "吉林",
		23: "黑龙江 ",
		31: "上海",
		32: "江苏",
		33: "浙江",
		34: "安徽",
		35: "福建",
		36: "江西",
		37: "山东",
		41: "河南",
		42: "湖北 ",
		43: "湖南",
		44: "广东",
		45: "广西",
		46: "海南",
		50: "重庆",
		51: "四川",
		52: "贵州",
		53: "云南",
		54: "西藏 ",
		61: "陕西",
		62: "甘肃",
		63: "青海",
		64: "宁夏",
		65: "新疆",
		71: "台湾",
		81: "香港",
		82: "澳门",
		91: "国外 "
	};
	var tip = "";
	var pass = true;

	if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
		tip = "身份证号格式错误";
		pass = false;
	} else if (!city[code.substr(0, 2)]) {
		tip = "地址编码错误";
		pass = false;
	} else {
		//18位身份证需要验证最后一位校验位
		if (code.length == 18) {
			code = code.split('');
			//∑(ai×Wi)(mod 11)
			//加权因子
			var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
			//校验位
			var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
			var sum = 0;
			var ai = 0;
			var wi = 0;
			for (var i = 0; i < 17; i++) {
				ai = code[i];
				wi = factor[i];
				sum += ai * wi;
			}
			var last = parity[sum % 11];
			if (parity[sum % 11] != code[17]) {
				tip = "校验位错误";
				pass = false;
			}
		}
	}
	// if (!pass) alert(tip);
	return pass;
}
