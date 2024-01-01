<script setup lang="ts" name="Home">
import Account from "@/components/Account.vue";
import QuickMenu from "@/components/QuickMenu.vue";
import MsgDisplay from "@/components/MsgDisplay.vue";
import GMTool from "@/components/GMTool.vue";
import RequestEditor from "@/components/RequestEditor.vue";
</script>

<template>
  <div class="homeBox">
    <div class="account">
      <Account/>
    </div>
    <div class="quickMenu">
      <QuickMenu/>
    </div>

    <div class="msgDisplay">
      <MsgDisplay/>
    </div>

    <div class="common-req-panel">
      <div class="gmTool">
        <GMTool/>
      </div>

      <div class="requestEditor">
        <RequestEditor/>
      </div>
    </div>
    <div class="test-script-editor">

    </div>

  </div>
</template>

<style scoped>
.homeBox {
  width: 100%;
  height: 100%;
  background-color: #888;
  display: flex;
  flex-wrap: wrap;
}

.homeBox > * {
  border: 1px solid black;
  box-sizing: border-box;
}

.account {
  background-color: #65CBF7;
  width: 500px;
  height: 150px;
}

.quickMenu {
  background: #9C3493;
  height: 150px;
  width: calc(100% - 500px);
}

.msgDisplay {
  width: 450px;
  height: 750px;
  background-color: #51458d;
}

.gmTool {
  background-color: #8B0000;
  width: 400px;
  height: 300px;
}

.requestEditor {
  background-color: #FFD700;
  width: 400px;
  height: 450px;
}

.test-script-editor {
  width: 400px;
  background-color: #B3A5FC;
}
</style>