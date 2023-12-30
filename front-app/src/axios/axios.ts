import axios from "axios";

const axiosInstance = axios.create({
    baseURL: "http://localhost:8080/",
    timeout: 1500,
    headers: {
        // fixme 网上都说默认就是json，但是实际测试发现不是，这里我手动设置一下，后续在看是什原因
        "Content-Type": "application/json;charset=UTF-8"
    },
    // headers: {'X-Custom-Header': 'foobar'} todo 似乎是用来解决跨越问题的，先注释
    // `transformRequest` 允许在向服务器发送前，修改请求数据
    // 它只能用于 'PUT', 'POST' 和 'PATCH' 这几个请求方法
    // 数组中最后一个函数必须返回一个字符串， 一个Buffer实例，ArrayBuffer，FormData，或 Stream
    // 你可以修改请求头。
    transformRequest: [function (data, headers) {
        // 对发送的 data 进行任意转换处理
        // fixme 不知道这样处理对不对；目前不加这个，发送对象都需要手动包裹
        return JSON.stringify(data);
    }],

    // `transformResponse` 在传递给 then/catch 前，允许修改响应数据
    transformResponse: [function (data) {
        // 对接收的 data 进行任意转换处理

        return data;
    }],
});

// 配置拦截器
// 添加请求拦截器
axiosInstance.interceptors.request.use(config => {
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
axiosInstance.interceptors.response.use(response => {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    // 配置直接返回data数据，这样在接收回调处就不用显示.data了
    return response.data;
}, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

export default axiosInstance;