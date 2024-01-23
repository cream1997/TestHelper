<script setup lang="ts" name="TimePicker">
import {onMounted, ref, watch} from "vue";
import {ONE_DAY, ONE_HOUR, ONE_MINUTE} from "@/constant/TimeConst";


let props = defineProps(["serverTime", "setTime"]);
let time = ref<number>(0)


let stopWatchServerTime: any;


function startWatchServerTime() {
  stopWatchServerTime && stopWatchServerTime();
  stopWatchServerTime = watch(props, () => {
    time.value = props.serverTime;
  }, {immediate: true})
}

onMounted(() => {
  startWatchServerTime();
})

function triggerPanel(open: boolean) {
  if (open) {
    stopWatchServerTime && stopWatchServerTime();
  } else {
    startWatchServerTime();
  }
}

function dateDisable(date: Date): boolean {
  return date.getTime() <= props.serverTime;
}

const shortcuts = [
  {
    text: '一分钟后',
    value: () => {
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
  <el-date-picker style="width: 155px"
                  v-model="time"
                  type="datetime"
                  placeholder="选择日期时间"
                  :editable="false"
                  :clearable="false"
                  :shortcuts="shortcuts"
                  :disabled-date="dateDisable"
                  @change="setTime"
                  @visible-change="triggerPanel"
  />
</template>
