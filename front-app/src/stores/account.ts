import {defineStore} from "pinia";
import type AccountStore from "@/interface/store/AccountStore";
import type RoleItemVO from "@/interface/vo/role/RoleItemVO";
import UserState from "@/interface/UserState";

export const useAccountStore = defineStore("account", {
  state(): AccountStore {
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
