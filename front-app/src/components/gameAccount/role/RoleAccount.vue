<script setup lang="ts" name="RoleAccount">
import TimePicker from "@/components/time/TimePicker.vue";
import type AccountInfo from "@/interface/AccountInfo";
import {useAccountStore} from "@/store/account";
import {onUnmounted} from "vue";

const accountInfo: AccountInfo = useAccountStore();


const interval = setInterval(() => {
  // 服务器传回来的time是string（fastjson为了避免long值精度丢失做了处理）
  accountInfo.serverTime = new Date(parseInt(accountInfo.serverTime) + 1000).getTime() + ""
}, 1000);

function setTime(time: number) {
  console.log(time)
}

onUnmounted(() => {
  clearInterval(interval)
})
</script>

<template>
  <div class="title">
    <span class="server-time">
      <TimePicker :server-time="parseInt(accountInfo.serverTime)" :set-time="setTime"/>
    </span>
    <span class="position-span">
      {{ accountInfo.position.mapName }}
      {{ accountInfo.position.line }}线
      {{ accountInfo.position.xy.x }}:{{ accountInfo.position.xy.y }}</span>
    <span class="delay-span">100ms</span>
    <button class="btn">切换</button>
    <button class="btn">退出</button>
  </div>
  <div>
    角色名称：{{ accountInfo.role?.roleName }}
    职业：{{ accountInfo.role?.career }}
    等级:{{ accountInfo.role?.level }}
  </div>
  <div>血量：</div>
  <div>经验：</div>
  <div>状态:</div>
</template>

<style scoped>
.title {
  box-sizing: border-box;
  border-bottom: 1px solid black;
}

.position-span {
  padding: 0 1px;
  display: inline-block;
  width: 150px;
}

.delay-span {
  padding: 0 1px;
  display: inline-block;
  width: 50px
}

.server-time {

}
</style>