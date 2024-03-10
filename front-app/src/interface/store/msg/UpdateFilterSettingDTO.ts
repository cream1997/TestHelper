import type FilterMsgVO from "@/interface/vo/msg/filter/FilterMsgVO";

export default interface UpdateFilterSettingDTO {
  accountId: string;
  resetDefaultFilter: boolean;
  clearCustomFilter: boolean;
  filterMsgVO: FilterMsgVO;
}
