import type RoleItemVO from "@/interface/RoleItemVO";

export default interface AccountInfo {
    accountId: number,
    uid: number,
    rid: number
    roleItems: Array<RoleItemVO>
}