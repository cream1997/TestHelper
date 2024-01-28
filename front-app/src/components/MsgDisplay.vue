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
watch(accountInfo, (newVal, oldValue) => {
  if (!oldValue.role && newVal.role) {
    heartInterval = setInterval(() => {
      heartBeat()
    }, 1000);
  }
})

function heartBeat() {
  post("/heartBeat", {uid: accountInfo.uid})
      .then((msgVOS: Array<MsgVO>) => {
        msgList.push(...msgVOS)
      })
}

onMounted(() => {
  heartInterval && clearInterval(heartInterval);
})
</script>

<template>
  <p class="title">
    <span class="title-text">消息控制台</span>
    <span class="num-display">999+</span>
    <button class="setup-btn">设置</button>
    <button class="clear-btn">清空</button>
  </p>
  <div class="middle">
    <ol class="msg-list-class">
      <li v-for="msg in msgList" :key="msg.no" @click="lookData(msg)">
        <span>{{ msg.type == 1 ? "请求" : "响应" }}</span>
        <span>{{ msg.msgName }}</span>
        <span>{{ msg.msgId }}</span>
        <span>{{ msg.type == 1 ? msg.sendTime : msg.receiveTime }}</span>
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
  padding: 0;
  margin: 0;
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
</style>