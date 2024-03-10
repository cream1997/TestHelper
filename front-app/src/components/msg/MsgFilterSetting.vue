<script lang="ts">
export default {
  name: "MsgFilterSetting"
};
</script>
<script setup lang="ts">
import {onMounted, ref} from "vue";
import MsgFilterSelector from "@/components/msg/MsgFilterSelector.vue";
import FilterMsgType from "@/components/msg/constant/FilterMsgType";
import {post} from "@/net/axios";
import {useAccountStore} from "@/stores/account";
import type AccountStore from "@/interface/store/AccountStore";
import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";
import {useFilterMsgSettingStore} from "@/stores/FilterMsgSetting";
import type FilterMsgSettingStore from "@/interface/store/FilterMsgSettingStore";

const accountInfo: AccountStore = useAccountStore();
const filterMsgSetting: FilterMsgSettingStore = useFilterMsgSettingStore();
const settingShow = ref<boolean>(false);

// 加载消息过滤设置
onMounted(() => {
  post("getDefaultFilterMsg", accountInfo.accountId).then((res: MsgFilterSettingVO) => {
    filterMsgSetting.defaultFilterMsg = res;
  });

  post("/getCustomerFilterMsg", accountInfo.accountId).then((res: MsgFilterSettingVO) => {
    filterMsgSetting.customFilterMsg = res;
  });
});
</script>

<template>
  <el-popover :visible="settingShow" placement="bottom" trigger="click" width="500px">
    <template #reference>
      <button @click="settingShow = !settingShow" class="setup-btn">设置</button>
    </template>
    <div style="height: 500px; overflow: auto">
      <p class="filter-setting-title">
        默认过滤
        <button>重置</button>
      </p>

      <MsgFilterSelector class="default-filter-msg-div" :filterType="FilterMsgType.Default" />
      <hr class="filter-setting-hr" />
      <p class="filter-setting-title">
        自定义过滤
        <button>清空</button>
      </p>
      <MsgFilterSelector class="custom-filter-msg-div" :filterType="FilterMsgType.Custom" />
      <hr class="filter-setting-hr" />
      <div>添加自定义过滤消息</div>
    </div>
  </el-popover>
</template>

<style scoped>
.setup-btn {
  text-align: right;
  cursor: pointer;
}

.filter-setting-title {
  margin-top: 1px;
  margin-bottom: 1px;
  display: flex;
  justify-content: space-between;
}

.filter-setting-hr {
  margin-top: 1px;
  margin-bottom: 1px;
}

.default-filter-msg-div {
  height: 78px;
  width: 100%;
  overflow: auto;
}

.custom-filter-msg-div {
  height: 250px;
  width: 100%;
  overflow: auto;
}
</style>
