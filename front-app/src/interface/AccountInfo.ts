import type RoleItemVO from "@/interface/vo/role/RoleItemVO";
import type UserState from "@/interface/UserState";
import type PositionVO from "@/interface/vo/PositionVO";

export default interface AccountInfo {
    accountId: number,
    userState: UserState,
    uid: number,
    roleItems: Array<RoleItemVO>,
    role: RoleItemVO | null,
    serverTime: string,
    position: PositionVO
}