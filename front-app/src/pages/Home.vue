<script setup lang="ts" name="Home">
import Account from "@/components/gameAccount/GameAccountBox.vue";
import QuickMenu from "@/components/QuickMenu.vue";
import CommonReq from "@/components/commonReq/CommonReq.vue";
import MsgDisplay from "@/components/MsgDisplay.vue";
import {useAccountStore} from "@/stores/account";
import UserState from "@/interface/UserState";
import {post} from "@/axios/axios";
import {onMounted} from "vue";

const accountInfo = useAccountStore();

onMounted(() => {
  window.onbeforeunload = () => {
    if (!accountInfo.uid) {
      return;
    }
    post("/userLogout", accountInfo.uid);
    accountInfo.$patch({
      accountId: 0,
      userState: UserState.UnLoginUser,
      uid: 0,
      role: null,
      roleItems: []
    });
  };
});
</script>

<template>
  <div class="homeBox">
    <div class="account module top-component">
      <Account />
    </div>
    <div class="quickMenu module top-component">
      <QuickMenu />
    </div>

    <div class="msgDisplay module bottom-component">
      <MsgDisplay />
    </div>

    <div class="common-req-panel module bottom-component">
      <CommonReq />
    </div>
    <div class="test-script-editor module bottom-component">组合式工具</div>
  </div>
</template>

<style scoped>
.homeBox {
  background-color: #ada8a8;
  width: 100%;
  height: 100vh;
  display: flex;
  flex-wrap: wrap;
}

.module {
  background-color: rgb(230, 232, 239);
  border: 1px solid black;
  box-sizing: border-box;
  padding: 1px;
}

.top-component {
  height: 130px;
}

.account {
  width: 450px;
  height: 130px;
}

.quickMenu {
  height: 130px;
  width: calc(100% - 450px);
}

.bottom-component {
  height: calc(100vh - 130px);
}

.msgDisplay {
  width: 450px;
}

.common-req-panel {
  width: 400px;
}

.test-script-editor {
  width: calc(100% - 850px);
}
</style>
