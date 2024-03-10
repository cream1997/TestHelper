import {defineStore} from "pinia";
import type FilterMsgSettingStore from "@/interface/store/FilterMsgSettingStore";

export const useFilterMsgSettingStore = defineStore("filterMsgSetting", {
  state(): FilterMsgSettingStore {
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
  }
});
