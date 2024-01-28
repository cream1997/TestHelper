<script setup lang="ts" name="MsgDisplay">
import {useAccountStore} from "@/store/account";
import type AccountInfo from "@/interface/AccountInfo";
import {onMounted, reactive, watch} from "vue";
import {post} from "@/axios/axios";
import type MsgVO from "@/interface/vo/MsgVO";

const accountInfo: AccountInfo = useAccountStore();
const msgList = reactive<Array<MsgVO>>([])


function lookData(msg: MsgVO) {

}

let heartInterval: number;
watch(() => accountInfo.role, (newVal, oldValue) => {
  if (!oldValue && newVal) {
    console.log("变化1")
    heartInterval = setInterval(() => {
      heartBeat()
    }, 1000);
  } else if (newVal && !oldValue) {
    console.log("变化2")
    heartInterval && clearInterval(heartInterval);
  }
})

function heartBeat() {
  console.log(accountInfo.uid)
  post("/heartBeat", accountInfo.uid)
      .then((msgVOS: Array<MsgVO>) => {
        msgList.push(...msgVOS)
      })
}

onMounted(() => {
  heartInterval && clearInterval(heartInterval);
})

function clearConsole() {
  msgList.splice(0, msgList.length)
}

const reqTypeShowColor = {
  color: "green"
}
const resTypeColor = {
  color: "rgba(173,173,100,0.5)"
}

const computeMsgTypeColor = function (msgType: number) {
  if (msgType == 1) {
    return resTypeColor;
  } else {
    return resTypeColor;
  }
}

function getMsgShowTime(msgVo: MsgVO) {
  let timeStr = msgVo.type == 1 ? msgVo.sendTime : msgVo.receiveTime;
  return new Date(parseInt(timeStr));
}

</script>

<template>
  <p class="title">
    <span class="title-text">消息控制台</span>
    <span class="num-display">{{ msgList.length }}</span>
    <button class="setup-btn">设置</button>
    <button class="clear-btn" @click="clearConsole">清空</button>
  </p>
  <div class="middle">
    <ol class="msg-list-class">
      <li class="msg-class" v-for="msg in msgList" :key="msg.no" @click="lookData(msg)">
        <span class="msgType-span" :style="computeMsgTypeColor(msg.type)">{{ msg.type == 1 ? "请求" : "响应" }}</span>
        <span class="msgName-span">{{ msg.msgName }}</span>
        <span class="msgId-span">{{ msg.msgId }}</span>
        <span class="msgTime-span">{{ getMsgShowTime(msg).toLocaleTimeString() }}</span>
      </li>
    </ol>
  </div>
  <div class="footer">回到顶部</div>
</template>

<style scoped>
.title {
  margin: 1px auto;
  border-bottom: 1px black dotted;
  height: 25px;
}

.middle {
  height: calc(100% - 55px);
}

.footer {
  border-top: 1px black dotted;
  text-align: center;
  height: 30px;
}

.msg-list-class {
  height: 100%;
  padding: 0;
  margin: 0;
  overflow: auto;
}

.msg-class {
  height: 5%;
}

.title-text {
  width: 320px;
  font-size: small;
  font-weight: bold;
  display: inline-block;
}

.num-display {
  font-size: small;
  border: 1px solid black;
  border-radius: 6px;
  width: 34px;
  text-align: center;
  margin-right: 4px;
  display: inline-block;
}

.clear-btn, .setup-btn {
  text-align: right;
}

.msgType-span {
  font-size: small;
  font-weight: bold;
  padding: 0 2px;
}

.msgName-span {
  padding: 0 4px;
}

.msgId-span {
  font-size: small;
}

.msgId-span:before {
  content: '(';
}

.msgId-span:after {
  content: ')';
}
</style>