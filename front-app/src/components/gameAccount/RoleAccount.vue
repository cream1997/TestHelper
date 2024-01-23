<script setup lang="ts" name="RoleAccount">
import {ref} from "vue";
import TimePicker from "@/components/time/TimePicker.vue";

const serverTime = ref<number>(Date.now());

setInterval(() => {
  serverTime.value = new Date(serverTime.value + 1000).getTime()
}, 1000)

function setTime(time: number) {
}
</script>

<template>
  <div class="title">
    <span class="server-time">
      <TimePicker :server-time="serverTime" :set-time="setTime"/>
    </span>
    <span class="position-span">勇者大陆 1线 128:83</span>
    <span class="delay-span">100ms</span>
    <button class="btn">切换</button>
    <button class="btn">退出</button>
  </div>
  <div>角色名称： 职业：等级:</div>
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