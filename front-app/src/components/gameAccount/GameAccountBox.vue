<script lang="ts">
export default {
  name: "AccountBox"
};
</script>
<script setup lang="ts">
import {useAccountStore} from "@/stores/account";
import type AccountInfo from "@/interface/AccountInfo";
import UserAccount from "@/components/gameAccount/user/UserAccount.vue";
import RoleAccount from "@/components/gameAccount/role/RoleAccount.vue";
import SelectRole from "@/components/gameAccount/role/SelectRole.vue";
import UserState from "@/interface/UserState";
import CreateRole from "@/components/gameAccount/role/CreateRole.vue";

const accountInfo: AccountInfo = useAccountStore();
</script>

<template>
  <UserAccount v-if="accountInfo.userState === UserState.UnLoginUser" />
  <SelectRole v-if="accountInfo.userState === UserState.SelectRole" />
  <CreateRole v-if="accountInfo.userState === UserState.CreateRole" />
  <RoleAccount v-if="accountInfo.userState === UserState.enterRole" />
</template>

<style scoped></style>
