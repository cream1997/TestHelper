import type LoginVO from "@/interface/vo/account/LoginVO";
import {post} from "@/net/axios";
import type Account from "@/interface/Account";
import {checkAccountNotNull} from "@/tools/CheckFormUtil";

export const reqLogin = (loginVO: LoginVO) => {
  checkAccountNotNull(loginVO);
  return post<Account>("/login", loginVO);
};

export const reqRegister = (loginVO: LoginVO) => {
  checkAccountNotNull(loginVO);
  return post<any>("/register", loginVO);
};
