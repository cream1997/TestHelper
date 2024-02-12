import {defineStore} from "pinia";
import type AccountInfo from "@/interface/AccountInfo";
import type RoleItemVO from "@/interface/vo/role/RoleItemVO";
import UserState from "@/interface/UserState";

export const useAccountStore = defineStore("account", {
  state(): AccountInfo {
    return {
      accountId: 0 as number,
      uid: 0 as number,
      userState: UserState.UnLoginUser,
      role: null as RoleItemVO | null,
      roleItems: [] as RoleItemVO[],
      serverTime: "" as string,
      position: null as any
    };
  }
});
