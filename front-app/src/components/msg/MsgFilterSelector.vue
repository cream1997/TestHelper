<script lang="ts">
export default {
  name: "MsgFilterSelector"
};
</script>
<script setup lang="ts">
import FilterMsgType from "@/components/msg/constant/FilterMsgType";
import {post} from "@/net/axios";
import {useAccountStore} from "@/stores/useAccountStore";
import type AccountStore from "@/interface/store/AccountStore";
import {useFilterSettingStore} from "@/stores/useFilterSettingStore";
import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";
import {computed, type ComputedRef, reactive} from "vue";
import type FilterMsgVO from "@/interface/vo/msg/filter/FilterMsgVO";

const filterMsgSettingStore = useFilterSettingStore();

const props = defineProps(["filterType"]);
const filterType: FilterMsgType = props.filterType;

const filterMsg: ComputedRef<MsgFilterSettingVO | null> = computed(() => {
  if (filterType == FilterMsgType.Default) {
    return filterMsgSettingStore.defaultFilterMsg;
  } else if (filterType == FilterMsgType.Custom) {
    return filterMsgSettingStore.customFilterMsg;
  }
  return null;
});

function changeFilterSetting(msg: FilterMsgVO) {
  msg.filter = !msg.filter;
  filterMsgSettingStore.updateSetting(msg, false, false);
}
</script>

<template>
  <div>
    <div class="default-filter-msg-div">
      <ul class="msg-panel1">
        <li v-for="msg in filterMsg?.allReqFilterMsg" :key="msg.msgId">
          <input
            type="checkbox"
            v-if="filterType == FilterMsgType.Default"
            :checked="msg.filter"
            @click="changeFilterSetting(msg)"
          />
          {{ msg.msgName }}
        </li>
      </ul>
    </div>
    <div class="default-filter-msg-div">
      <ul class="msg-panel1">
        <li v-for="msg in filterMsg?.allResFilterMsg" :key="msg.msgId">
          <input
            type="checkbox"
            v-if="filterType == FilterMsgType.Default"
            :checked="msg.filter"
            @click="changeFilterSetting(msg)"
          />
          {{ msg.msgName }}
        </li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
.default-filter-msg-div {
  display: inline-block;
  vertical-align: top;
}

.msg-panel1 {
  list-style: none;
}
</style>
