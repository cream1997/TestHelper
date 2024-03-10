<script lang="ts">
export default {
  name: "MsgFilterSelector"
};
</script>
<script setup lang="ts">
import FilterMsgType from "@/components/msg/constant/FilterMsgType";
import {post} from "@/net/axios";
import {useAccountStore} from "@/stores/account";
import type AccountStore from "@/interface/store/AccountStore";
import {useFilterMsgSettingStore} from "@/stores/FilterMsgSetting";
import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";
import {computed, type ComputedRef, reactive} from "vue";

const accountInfo: AccountStore = useAccountStore();
const filterMsgSetting = useFilterMsgSettingStore();

const props = defineProps(["filterType"]);
const filterType: FilterMsgType = props.filterType;

const filterMsg: ComputedRef<MsgFilterSettingVO | null> = computed(() => {
  if (filterType == FilterMsgType.Default) {
    return filterMsgSetting.defaultFilterMsg;
  } else if (filterType == FilterMsgType.Custom) {
    return filterMsgSetting.customFilterMsg;
  }
  return null;
});
</script>

<template>
  <div>
    <div class="default-filter-msg-div">
      <ul class="msg-panel1">
        <li v-for="msg in filterMsg?.allReqFilterMsg" :key="msg.msgId">
          <input type="checkbox" :checked="msg.filter" />
          {{ msg.msgName }}
        </li>
      </ul>
    </div>
    <div class="default-filter-msg-div">
      <ul class="msg-panel1">
        <li v-for="msg in filterMsg?.allResFilterMsg" :key="msg.msgId">
          <input type="checkbox" :checked="msg.filter" />
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
