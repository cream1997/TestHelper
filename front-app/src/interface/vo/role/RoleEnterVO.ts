import type RoleItemVO from "@/interface/vo/role/RoleItemVO";
import type PositionVO from "@/interface/vo/PositionVO";

export default interface RoleEnterVO {
  roleItem: RoleItemVO;
  serverTime: string;
  position: PositionVO;
}
