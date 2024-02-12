export const getDayTime = () => {
  const hours = new Date().getHours();
  if (hours >= 6 && hours <= 10) {
    return "早上";
  } else if (hours > 10 && hours <= 12) {
    return "上午";
  } else if (hours > 12 && hours <= 18) {
    return "下午";
  } else if (hours > 18 && hours < 24) {
    return "晚上";
  } else if (hours >= 0 && hours < 6) {
    return "晚上";
  }
};
