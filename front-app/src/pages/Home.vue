<script setup lang="ts" name="Home">
import Account from "@/components/gameAccount/AccountBox.vue";
import QuickMenu from "@/components/QuickMenu.vue";
import CommonReq from "@/components/commonReq/CommonReq.vue";
import MsgDisplay from "@/components/MsgDisplay.vue";
</script>

<template>
  <div class="homeBox">
    <div class="account module">
      <Account/>
    </div>
    <div class="quickMenu module">
      <QuickMenu/>
    </div>

    <div class="msgDisplay module">
      <MsgDisplay/>
    </div>

    <div class="common-req-panel module">
      <CommonReq/>
    </div>
    <div class="test-script-editor module">

    </div>
  </div>
</template>

<style scoped>
.homeBox {
  background-color: #ada8a8;
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
}

.module {
  background-color: gray;
  border: 1px solid black;
  box-sizing: border-box;
  padding: 1px;
}

.account {
  width: 450px;
  height: 130px;
}

.quickMenu {
  height: 130px;
  width: calc(100% - 450px);
}

.msgDisplay {
  width: 450px;
  height: 750px;
}

.common-req-panel {
  width: 400px;
  height: 750px;
}


.test-script-editor {
  width: calc(100% - 850px);
}
</style>