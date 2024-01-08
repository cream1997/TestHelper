import {defineStore} from "pinia";

export const useAccountStore = defineStore("account", {
    state() {
        return {
            accountId: 0
        }
    },
})