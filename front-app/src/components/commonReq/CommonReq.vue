<script setup lang="ts">
import GMTool from "@/components/commonReq/sub/GMTool.vue";
import RequestEditor from "@/components/commonReq/sub/RequestEditor.vue";
</script>

<template>
  <div class="gm-tool">
    <GMTool/>
  </div>
  <div class="request-editor">
    <RequestEditor/>
  </div>
</template>

<style scoped>
.gm-tool {
  width: 400px;
  height: 30%;
  border-bottom: 1px solid black;
}

.request-editor {
  width: 400px;
  height: 70%;
}
</style>