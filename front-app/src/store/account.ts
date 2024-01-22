import {defineStore} from "pinia";
import type AccountInfo from "@/interface/AccountInfo";

export const useAccountStore = defineStore("account", {
    state(): AccountInfo {
        return {
            accountId: 0,
            uid: 0,
            rid: 0,
            roleItems: []
        }
    },
})