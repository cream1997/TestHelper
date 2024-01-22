import type RoleItemVO from "@/interface/RoleItemVO";

export default interface LoginUserInfoVO {
    uid: number,
    roleItems: Array<RoleItemVO>
}