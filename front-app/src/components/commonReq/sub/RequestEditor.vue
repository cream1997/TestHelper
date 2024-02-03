<script setup lang="ts" name="RequestEditor">
import {ref} from "vue";
import {post} from "@/axios/axios";
import type MsgVO from "@/interface/vo/MsgVO";

let value = ref()
let msgTemplateOptions = ref<Array<MsgVO>>([]);

function searchMsgTemplate(msgNameKey: string) {
  post("/searchMsgTemplate", msgNameKey)
      .then((msgVOS: Array<MsgVO>) => {
        msgTemplateOptions.value = msgVOS;
      })
}

</script>

<template>
  <div class="fast-menu-div">快捷菜单</div>
  <div class="req-editor-div">
    <el-select
        v-model="value"
        filterable
        remote
        reserve-keyword
        placeholder="请输入请求消息名称"
        :remote-method="searchMsgTemplate"
        style="width: 240px; margin-left: 2px;"
        size="default"
    >
      <el-option
          v-for="item in msgTemplateOptions"
          :key="item.msgId"
          :label="item.msgName"
          :value="item"
      />
    </el-select>
    <button>发送</button>
    <button>标记</button>
  </div>
</template>

<style scoped>

</style>