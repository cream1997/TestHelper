<script setup lang="ts" name="RoleAccount">
import {ref} from "vue";
import {shortcuts} from "@/constant/TimeConst";


const serverTime = ref<number>(Date.now())
const fastUpdate = shortcuts(serverTime)
setInterval(() => {
  serverTime.value = new Date(serverTime.value + 1000).getTime()
}, 1000)

function dateDisable(date: Date): boolean {
  return date.getTime() <= serverTime.value;
}
</script>

<template>
  <div> 角色名称：
    <span class="server-time">
      <el-date-picker
          v-model="serverTime"
          type="datetime"
          placeholder="选择日期时间"
          :shortcuts="fastUpdate"
          :disabled-date="dateDisable"
      />
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