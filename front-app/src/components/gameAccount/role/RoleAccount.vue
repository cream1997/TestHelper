<script setup lang="ts" name="RoleAccount">
import TimePicker from "@/components/time/TimePicker.vue";
import type AccountInfo from "@/interface/AccountInfo";
import {useAccountStore} from "@/store/account";
import {onUnmounted, ref, watch} from "vue";

const accountInfo: AccountInfo = useAccountStore();
const delayColor = ref({
  color: 'red',
})
const delay = ref(30)
watch(delay, (newVal) => {
  if (newVal <= 50) {
    delayColor.value.color = "green"
  } else if (newVal <= 100) {
    delayColor.value.color = "yellow"
  } else {
    delayColor.value.color = "red"
  }
}, {immediate: true})

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
    <span>
      <TimePicker :server-time="parseInt(accountInfo.serverTime)" :set-time="setTime"/>
    </span>
    <span class="position-span">
      <span class="mapName-span">{{ accountInfo.position.mapName }}</span>
      <span class="mapLine-span">{{ accountInfo.position.line }}线</span>
      <span class="xy-span">{{ accountInfo.position.xy.x }}:{{ accountInfo.position.xy.y }}</span>
    </span>
    <span class="delay-span" :style="delayColor">{{ delay }}ms</span>
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
  width: 145px;
}

.delay-span {
  padding: 0 1px;
  display: inline-block;
  width: 45px
}

.mapName-span {
  font-weight: bold;
  padding-left: 2px;
}

.mapLine-span {
  font-weight: bold;
  padding: 0 2px;
}

.xy-span {
  padding: 0 2px;
  font-size: 14px;
}

.delay-span {
  text-align: right;
  color: #5eda5e;
  padding-right: 10px
}
</style>