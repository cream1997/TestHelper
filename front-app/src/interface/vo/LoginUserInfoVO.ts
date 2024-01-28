import type RoleItemVO from "@/interface/vo/RoleItemVO";

export default interface LoginUserInfoVO {
    uid: number,
    roleItems: Array<RoleItemVO>
}