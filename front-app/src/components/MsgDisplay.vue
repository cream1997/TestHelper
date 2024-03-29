<script lang="ts">
export default {
  name: "MsgDisplay"
};
</script>
<script lang="ts" setup>
import {useAccountStore} from "@/stores/useAccountStore";
import type AccountStore from "@/interface/store/AccountStore";
import {nextTick, onUnmounted, reactive, ref, shallowReactive, watch} from "vue";
import {post} from "@/net/axios";
import type MsgVO from "@/interface/vo/msg/MsgVO";
import FetchResWorker from "@/net/FetchResWorker.ts?worker";
import MsgFilterSetting from "@/components/msg/MsgFilterSetting.vue";
import {useFilterSettingStore} from "@/stores/useFilterSettingStore";
import {ReqNameColor, ReqTypeColor, ResNameColor, ResTypeColor} from "@/constant/ColorConst";

const filterSettingStore = useFilterSettingStore();

const accountInfo: AccountStore = useAccountStore();
const msgList = shallowReactive<Array<MsgVO>>([]);
const stopReceive = ref(false);
const searchMsgName = ref("");
const searchMsgNameSet = reactive(new Set());
const currentShowMsg = ref<MsgVO | null>();

const fetchResWorker: Worker = new FetchResWorker();
fetchResWorker.onmessage = () => {
  heartBeat();
};
watch(
  () => accountInfo.role,
  (newVal, oldValue) => {
    if (!oldValue && newVal) {
      startFetchResWorker();
    } else if (newVal && !oldValue) {
      stopFetchResWorker();
    }
  }
);

function startFetchResWorker() {
  fetchResWorker.postMessage("start");
}

function stopFetchResWorker() {
  fetchResWorker.postMessage("end");
}

function fetchResWorkerTerminal() {
  stopFetchResWorker();
  fetchResWorker.terminate();
}

onUnmounted(() => {
  fetchResWorkerTerminal();
});

const msgListDomRef = ref(null);

const responseClearThreshold = 500;
const clearToNum = 50;

function heartBeat() {
  post("/heartBeat", accountInfo.uid).then((msgVOS: Array<MsgVO>) => {
    if (stopReceive.value || msgVOS.length == 0 || !msgListDomRef.value) {
      return;
    }
    const msgListDom = msgListDomRef.value as HTMLElement;
    const originScrollTop = msgListDom.scrollTop;
    const clientHeight = msgListDom.clientHeight;
    const scrollIsInBottom = originScrollTop + clientHeight + 3 > msgListDom.scrollHeight;
    // todo 消息先处理

    // 后过滤
    msgVOS.forEach((msgVO) => {
      if (!filterSettingStore.needFilter(msgVO.msgId)) {
        msgList.push(msgVO);
      }
    });

    msgList.forEach((item) => {
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

const computeMsgTypeColor = function (msgType: number) {
  if (msgType == 1) {
    return ReqTypeColor;
  } else {
    return ResTypeColor;
  }
};

const computeMsgNameColor = function (msgType: number) {
  if (msgType == 1) {
    return ReqNameColor;
  } else {
    return ResNameColor;
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
  if (currentShowMsg.value?.no !== msg.no) {
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
      <el-input
        v-model="searchMsgName"
        class="input-with-select"
        placeholder="请输入要过滤的消息名称"
      >
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
    <MsgFilterSetting />
    <button class="stop-btn" @click="stopReceive = !stopReceive">
      {{ stopReceive ? "恢复" : "暂停" }}
    </button>
    <button class="clear-btn" @click="msgList.splice(0, msgList.length)">清空</button>
  </p>
  <div class="middle">
    <ol class="msg-list-class" ref="msgListDomRef">
      <el-popover
        v-for="msg in msgList"
        :key="msg.no"
        :visible="currentShowMsg?.no == msg.no"
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
            <span class="msgName-span" :style="computeMsgNameColor(msg.type)">
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
