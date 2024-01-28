import type RoleItemVO from "@/interface/vo/RoleItemVO";
import type PositionVO from "@/interface/vo/PositionVO";

export default interface RoleEnterVO {
    roleItem: RoleItemVO,
    serverTime: number,
    position: PositionVO
}