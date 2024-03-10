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
    updateSetting(
      filterMsgVO: FilterMsgVO | null,
      resetDefaultFilter: boolean,
      clearCustomFilter: boolean
    ) {
      const accountStore = useAccountStore();
      const accountId = accountStore.accountId;
      post("changeMsgFilterSetting", {
        accountId: accountStore.accountId,
        filterMsgVO: filterMsgVO,
        resetDefaultFilter: resetDefaultFilter,
        clearCustomFilter: clearCustomFilter
      }).then((res: MsgFilterSettingDTO) => {
        this.defaultFilterMsg = res.defaultSetting;
        this.customFilterMsg = res.customSetting;
      });
    }
  }
});
