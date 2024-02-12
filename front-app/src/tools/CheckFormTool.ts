import type LoginVO from "@/interface/vo/account/LoginVO";

export const checkAccountNotNull = function (loginVO: LoginVO) {
  if (!loginVO || !loginVO.username || !loginVO.password) {
    throw new Error("账号密码不能为空");
  }
};
