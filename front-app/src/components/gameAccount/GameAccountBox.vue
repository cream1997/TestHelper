<script lang="ts">
export default {
  name: "AccountBox"
};
</script>
<script setup lang="ts">
import {useAccountStore} from "@/stores/useAccountStore";
import type AccountStore from "@/interface/store/AccountStore";
import UserAccount from "@/components/gameAccount/user/UserAccount.vue";
import RoleAccount from "@/components/gameAccount/role/RoleAccount.vue";
import SelectRole from "@/components/gameAccount/role/SelectRole.vue";
import UserState from "@/interface/UserState";
import CreateRole from "@/components/gameAccount/role/CreateRole.vue";

const accountInfo: AccountStore = useAccountStore();
</script>

<template>
  <UserAccount v-if="accountInfo.userState === UserState.UnLoginUser" />
  <SelectRole v-if="accountInfo.userState === UserState.SelectRole" />
  <CreateRole v-if="accountInfo.userState === UserState.CreateRole" />
  <RoleAccount v-if="accountInfo.userState === UserState.enterRole" />
</template>

<style scoped></style>
