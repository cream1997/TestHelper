<script lang="ts">
export default {
  name: "RequestEditor"
};
</script>
<script setup lang="ts">
import {ref} from "vue";
import {post} from "@/net/axios";
import type MsgVO from "@/interface/vo/msg/MsgVO";
import JsonEditor from "json-editor-vue";

let rawMsg = ref();
let msgTemplateOptions = ref<Array<MsgVO>>([]);
const copyMsg = ref();

function searchMsgTemplate(msgNameKey: string) {
  post("/searchMsgTemplate", msgNameKey).then((msgVOS: Array<MsgVO>) => {
    msgTemplateOptions.value = msgVOS;
  });
}

function selectMsg() {
  copyMsg.value = JSON.parse(JSON.stringify(rawMsg.value));
}
</script>

<template>
  <div class="fast-menu-div">快捷菜单</div>
  <div class="req-editor-div">
    <el-select
      v-model="rawMsg"
      filterable
      remote
      reserve-keyword
      placeholder="请输入请求消息名称"
      :remote-method="searchMsgTemplate"
      style="width: 280px; margin-left: 2px"
      size="default"
      @change="selectMsg"
    >
      <el-option
        v-for="item in msgTemplateOptions"
        :key="item.msgId"
        :label="item.msgName"
        :value="item"
      />
    </el-select>
    <button class="send-btn">发送</button>
    <button class="tag-btn">标记</button>
    <JsonEditor v-model="copyMsg" class="json-editor-cls" />
  </div>
</template>

<style scoped>
.fast-menu-div {
  height: 100px;
  background-color: #9488d3;
}

.send-btn,
.tag-btn {
  width: 56px;
  height: 28px;
}

.tag-btn {
  margin-left: 2px;
}

.json-editor-cls {
  height: 435px;
  width: calc(100% - 2px);
  overflow: scroll;
}
</style>
