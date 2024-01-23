import type RoleItemVO from "@/interface/RoleItemVO";
import type UserState from "@/interface/UserState";

export default interface AccountInfo {
    accountId: number,
    userState: UserState,
    uid: number,
    roleItems: Array<RoleItemVO>,
    role: RoleItemVO | null
}