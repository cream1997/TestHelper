import type RoleItemVO from "@/interface/vo/role/RoleItemVO";

export default interface LoginUserInfoVO {
  uid: number;
  roleItems: Array<RoleItemVO>;
}
