import type MsgFilterSettingVO from "@/interface/vo/msg/filter/MsgFilterSettingVO";

export default interface MsgFilterSettingDTO {
  defaultSetting: MsgFilterSettingVO;
  customSetting: MsgFilterSettingVO;
}
