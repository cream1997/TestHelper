import {defineStore} from "pinia";
import type FilterSettingStore from "@/interface/store/msg/FilterSettingStore";
import {useAccountStore} from "@/stores/useAccountStore";
import type FilterMsgVO from "@/interface/vo/msg/filter/FilterMsgVO";
import {post} from "@/net/axios";
import type MsgFilterSettingDTO from "@/interface/store/msg/MsgFilterSettingDTO";

export const useFilterSettingStore = defineStore("filterMsgSetting", {
  state(): FilterSettingStore {
    return {
      defaultFilterMsg: {
        allReqFilterMsg: [],
        allResFilterMsg: []
      },
      customFilterMsg: {
        allReqFilterMsg: [],
        allResFilterMsg: []
      }
    };
  },
  actions: {
    updateSetting,
    needFilter
  }
});

function needFilter(this: FilterSettingStore, msgId: number): boolean {
  // 定义一个内部函数来避免重复代码，并使用===进行比较
  const isMsgMatching = (msgList: Array<{filter: boolean; msgId: number}>) => {
    return msgList.some((msg) => msg.filter && msg.msgId === msgId);
  };

  // 确保defaultFilterMsg和customFilterMsg已定义
  if (!this.defaultFilterMsg || !this.customFilterMsg) {
    console.error("defaultFilterMsg or customFilterMsg is undefined");
    return false; // 或者根据实际情况考虑抛出异常
  }

  // 使用isMsgMatching函数来简化代码和提高代码复用性
  // 合并allReqFilterMsg和allResFilterMsg的处理逻辑
  return (
    isMsgMatching(this.defaultFilterMsg.allReqFilterMsg) ||
    isMsgMatching(this.defaultFilterMsg.allResFilterMsg) ||
    isMsgMatching(this.customFilterMsg.allReqFilterMsg) ||
    isMsgMatching(this.customFilterMsg.allResFilterMsg)
  );
}

function updateSetting(
  this: FilterSettingStore,
  filterMsgVO: FilterMsgVO | null,
  resetDefaultFilter: boolean,
  clearCustomFilter: boolean
) {
  const accountStore = useAccountStore();
  const accountId = accountStore.accountId;
  post("changeMsgFilterSetting", {
    accountId,
    filterMsgVO,
    resetDefaultFilter,
    clearCustomFilter
  }).then((res: MsgFilterSettingDTO) => {
    this.defaultFilterMsg = res.defaultSetting;
    this.customFilterMsg = res.customSetting;
  });
}
