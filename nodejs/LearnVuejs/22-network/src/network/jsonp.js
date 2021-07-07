// export default function originJSONP(option) {
// 	// 1.从传入的option中提取URL
// 	const url = option.url;
//
// 	// 2.在body中添加script标签
// 	const body = document.getElementsByTagName('body')[0]
// 	const script = document.createElement('script')
//
// 	// 3.监听window上的jsonp的调用
// 	try {
// 		window[option.callback] = function (result) {
// 			body.removeChild(script)
// 			option.success(result)
// 		}
// 		script.src = url + '?callback=' + option.callback;
// 		body.appendChild(script)
// 	} catch (e) {
// 		option.failure(e)
// 	}
// }

let count = 1

export default function originPJSONP(option) {
	// 1.从传入的option中提取URL
	const url = option.url;

	// 2.在body中添加script标签
	const body = document.getElementsByTagName('body')[0]
	const script = document.createElement('script');

	// 3.内部生产一个不重复的callback
	const callback = 'jsonp' + count++

	// 4.监听window上的jsonp的调用
	return new Promise((resolve, reject) => {
		try {
			window[callback] = function (result) {
				body.removeChild(script);
				resolve(result)
			}
			const params = handleParam(option.data);
			script.src = url + '?callback=' + callback + params;
			body.appendChild(script)
		} catch (e) {
			body.removeChild(script)
			reject(e)
		}
	})
}

function handleParam(data) {
	let url = ''
	for (let key in data) {
		let value = data[key] !== undefined ? data[key] : ''
		url += `&${key}=${encodeURIComponent(value)}`
	}
	return url
}