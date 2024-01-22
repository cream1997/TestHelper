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
  <div> 角色名称：
    <span class="server-time">
      <TimePicker :server-time="serverTime" :set-time="setTime"/>
    </span>
  </div>
  <div>等级：</div>
  <div>职业：</div>
  <div>血量：</div>
  <div>经验：</div>
</template>

<style scoped>
.server-time {
  float: right;
}
</style>