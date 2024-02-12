<script lang="ts">
export default {
  name: "MsgDisplay"
};
</script>
<script lang="ts" setup>
import {useAccountStore} from "@/stores/account";
import type AccountInfo from "@/interface/AccountInfo";
import {nextTick, onMounted, reactive, ref, watch} from "vue";
import {post} from "@/axios/axios";
import type MsgVO from "@/interface/vo/MsgVO";

const accountInfo: AccountInfo = useAccountStore();
const msgList = reactive<Array<MsgVO>>([]);
const stopReceive = ref(false);
const searchMsgName = ref("");
const searchMsgNameSet = reactive(new Set());
const currentShowMsg = ref();

let heartInterval: number;
watch(
  () => accountInfo.role,
  (newVal, oldValue) => {
    if (!oldValue && newVal) {
      heartInterval = setInterval(() => {
        heartBeat();
      }, 1000);
    } else if (newVal && !oldValue) {
      heartInterval && clearInterval(heartInterval);
    }
  }
);

const msgListDomRef = ref(null);

const responseClearThreshold = 200;
const clearToNum = 50;

function heartBeat() {
  post("/heartBeat", accountInfo.uid).then((msgVOS: Array<MsgVO>) => {
    if (stopReceive.value) {
      return;
    }
    if (msgVOS.length == 0) {
      return;
    }
    if (!msgListDomRef.value) {
      return;
    }
    const msgListDom = msgListDomRef.value as HTMLElement;
    const originScrollTop = msgListDom.scrollTop;
    const clientHeight = msgListDom.clientHeight;
    const scrollIsInBottom = originScrollTop + clientHeight + 3 > msgListDom.scrollHeight;
    msgList.push(...msgVOS);
    msgVOS.forEach((item) => {
      searchMsgNameSet.add(item.msgName);
    });
    nextTick(() => {
      if (scrollIsInBottom) {
        msgListDom.scrollTop = msgListDom.scrollHeight - clientHeight;
      } else {
        msgListDom.scrollTop = originScrollTop;
      }
      // 消息太多就清理 fixme 下面的逻辑不确定是否正确
      const resNum = msgList.length;
      if (msgList.length >= responseClearThreshold) {
        // 清理保持滚动条位置
        const scrollBottom = msgListDom.scrollHeight - msgListDom.scrollTop - clientHeight;
        msgList.splice(0, resNum - clearToNum);
        nextTick(() => {
          msgListDom.scrollTop = msgListDom.scrollHeight - clientHeight - scrollBottom;
        });
      }
    });
  });
}

onMounted(() => {
  heartInterval && clearInterval(heartInterval);
});

function clearConsole() {
  msgList.splice(0, msgList.length);
}

const resTypeColor = {
  color: "rgba(173,173,100,0.5)"
};

const computeMsgTypeColor = function (msgType: number) {
  if (msgType == 1) {
    return resTypeColor;
  } else {
    return resTypeColor;
  }
};

function getMsgShowTime(msgVo: MsgVO) {
  let timeStr = msgVo.type == 1 ? msgVo.sendTime : msgVo.receiveTime;
  return new Date(parseInt(timeStr));
}

function showMsg(msg: MsgVO): boolean {
  return (
    !searchMsgName.value || msg.msgName.toLowerCase().includes(searchMsgName.value.toLowerCase())
  );
}

function changeMsgDataShow(msg: MsgVO) {
  if (currentShowMsg.value !== msg) {
    currentShowMsg.value = msg;
  } else {
    currentShowMsg.value = null;
  }
}

function selectMsgBackColor(msg: MsgVO) {
  if (msg == currentShowMsg.value) {
    return {
      backgroundColor: "rgb(225,124,124)"
    };
  }
}
</script>

<template>
  <p class="title">
    <span class="title-text">控制台</span>
    <span class="search-input">
      <el-input v-model="searchMsgName" class="input-with-select" placeholder="Please input">
        <template #prepend>
          <el-select v-model="searchMsgName" placeholder="选择" style="width: 60px">
            <el-option
              v-for="(name, index) in searchMsgNameSet"
              :key="index"
              :label="name"
              :value="name"
            />
          </el-select>
        </template>
      </el-input>
    </span>
    <span class="num-display">{{ msgList.length }}</span>
    <button class="setup-btn">设置</button>
    <button class="stop-btn" @click="stopReceive = !stopReceive">
      {{ stopReceive ? "恢复" : "暂停" }}
    </button>
    <button class="clear-btn" @click="clearConsole">清空</button>
  </p>
  <div class="middle">
    <ol class="msg-list-class" ref="msgListDomRef">
      <el-popover
        v-for="msg in msgList"
        :key="msg.no"
        :visible="currentShowMsg == msg"
        placement="right"
        trigger="click"
        width="360px"
      >
        <template #reference>
          <li
            v-show="showMsg(msg)"
            class="msg-class"
            :style="selectMsgBackColor(msg)"
            @click="changeMsgDataShow(msg)"
          >
            <span :style="computeMsgTypeColor(msg.type)" class="msgType-span">
              {{ msg.type == 1 ? "请求" : "响应" }}
            </span>
            <span class="msgName-span">
              {{ msg.msgName }}
              <span class="msgId-span">{{ msg.msgId }}</span>
            </span>
            <span class="msgTime-span">{{ getMsgShowTime(msg).toLocaleTimeString() }}</span>
          </li>
        </template>
        <pre style="max-height: 360px; overflow: auto">{{ msg.data }}</pre>
      </el-popover>
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
  height: 20px;
  padding: 5px 0;
  border-bottom: 1px solid rgba(173, 161, 161, 0.24);
  cursor: text;
}

.msg-class:hover {
  background-color: rgba(173, 161, 161, 0.24);
}

.msg-class:active {
  background-color: rgba(152, 140, 140, 0.47);
}

.title-text {
  width: 42px;
  font-size: small;
  font-weight: bold;
  display: inline-block;
}

.search-input {
  display: inline-block;
  width: 236px;
}

.input-with-select {
  width: 235px;
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

.clear-btn,
.stop-btn,
.setup-btn {
  text-align: right;
  cursor: pointer;
}

.msgType-span,
.msgName-span,
.msgId-span,
.msgTime-span {
  display: inline-block;
}

.msgType-span {
  width: 27px;
  font-size: small;
  font-weight: bold;
  padding: 0 2px;
}

.msgName-span {
  width: 336px;
  padding: 0 4px;
}

.msgId-span {
  margin-left: 4px;
  font-size: small;
}

.msgId-span:before {
  content: "( ";
  font-size: smaller;
}

.msgId-span:after {
  content: " )";
  font-size: smaller;
}

.msgTime-span {
  text-align: right;
  font-size: small;
}
</style>
