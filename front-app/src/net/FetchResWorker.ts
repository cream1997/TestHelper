/*
 * 单开一个工作线的主要目的是避免setInterval的休眠(MsgDisplay的主动抓取不能自动休眠)
 * 解释:如果在页面中直接使用setInterval,离开页面一段时间，浏览器的优化策略会自动停止setInterval，无法控制
 * 后续可以利用这个线程进行性能优化，让这个线程去发送抓取请求，然后将抓取响应发送给主页面；因为这一块计算密集
 * */
let heart: number;
self.onmessage = (e) => {
  if (e.data === "start") {
    heart = setInterval(() => {
      self.postMessage(null);
    }, 1000);
  } else {
    heart && clearInterval(heart);
  }
};
