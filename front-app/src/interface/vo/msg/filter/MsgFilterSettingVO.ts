import type FilterMsgVO from "@/interface/vo/msg/filter/FilterMsgVO";

export default interface MsgFilterSettingVO {
  allReqFilterMsg: FilterMsgVO[];
  allResFilterMsg: FilterMsgVO[];
}
