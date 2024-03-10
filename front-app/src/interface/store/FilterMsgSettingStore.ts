import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";

export default interface FilterMsgSettingStore {
  defaultFilterMsg: MsgFilterSettingVO;
  customFilterMsg: MsgFilterSettingVO;
}
