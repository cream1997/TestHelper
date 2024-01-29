import type UserVO from "@/interface/vo/user/UserVO";
import type ServerVO from "@/interface/ServerVO";

export default interface LoginUserVO {
    userVO: UserVO,
    serverVO: ServerVO
}