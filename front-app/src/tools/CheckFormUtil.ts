export const checkAccountNotNull = function (username: string, password: string) {
  if (!username || !password) {
    throw new Error("账号密码不能为空");
  }
};
