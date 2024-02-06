<script setup lang="ts">
import GMTool from "@/components/commonReq/sub/GMTool.vue";
import RequestEditor from "@/components/commonReq/sub/RequestEditor.vue";
import {ref} from "vue";

const showGMTool = ref(false);
</script>

<template>
  <p class="title">
    {{ showGMTool ? "GM工具" : "请求编辑器" }}
    <button @click="showGMTool = !showGMTool" class="trigger-btn">切换</button>
  </p>
  <div class="gm-tool" v-show="showGMTool">
    <GMTool/>
  </div>
  <div class="request-editor" v-show="!showGMTool">
    <RequestEditor/>
  </div>
</template>

<style scoped>
.gm-tool, .request-editor {
  width: 400px;
  height: 100%;
}

.trigger-btn {
  text-align: right;
}

.title {
  margin: 0;
  border-bottom: 1px solid black;
  display: flex;
  justify-content: space-between;
}

</style>