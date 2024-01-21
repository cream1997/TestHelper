import type {Ref} from "vue";

export const ONE_MINUTE = 60 * 1000;
export const ONE_HOUR = 60 * ONE_MINUTE;
export const ONE_DAY = 24 * ONE_HOUR;

export const shortcuts = function (serverTime: Ref<number>) {
    return [
        {
            text: '一分钟后',
            value: () => {
                return new Date(serverTime.value + ONE_MINUTE)
            },
        },
        {
            text: '一小时后',
            value: () => {
                return new Date(serverTime.value + ONE_HOUR)
            },
        },
        {
            text: '一天后',
            value: () => {
                return new Date(serverTime.value + ONE_DAY);
            },
        },
    ]
}