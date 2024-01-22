<script setup lang="ts" name="TimePicker">
import {ref} from "vue";
import {ONE_DAY, ONE_HOUR, ONE_MINUTE} from "@/constant/TimeConst";

let outerTime = Date.now();

const time = ref<number>(outerTime)
setInterval(() => {
  time.value = new Date(time.value + 1000).getTime()
}, 1000)

function dateDisable(date: Date): boolean {
  return date.getTime() <= outerTime;
}

const shortcuts = [
  {
    text: '一分钟后',
    value: () => {
      console.log(time)
      return new Date(time.value + ONE_MINUTE)
    },
  },
  {
    text: '一小时后',
    value: () => {
      return new Date(time.value + ONE_HOUR)
    },
  },
  {
    text: '一天后',
    value: () => {
      return new Date(time.value + ONE_DAY);
    },
  },
];

</script>

<template>
  <el-date-picker
      v-model="time"
      type="datetime"
      placeholder="选择日期时间"
      :shortcuts="shortcuts"
      :disabled-date="dateDisable"
  />
</template>

<style scoped>

</style>