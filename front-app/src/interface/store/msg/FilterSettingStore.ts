import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";

export default interface FilterSettingStore {
  defaultFilterMsg: MsgFilterSettingVO;
  customFilterMsg: MsgFilterSettingVO;
}
