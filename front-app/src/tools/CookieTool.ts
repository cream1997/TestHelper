import Cookies from "js-cookie";

export const setCookie = (key: string, data: any, day?: number) => {
  if (!data) {
    data = "";
  } else {
    data = JSON.stringify(data);
  }
  if (day) {
    Cookies.set(key, data, {
      expires: day
    });
  } else {
    Cookies.set(key, data);
  }
};

export const clearCookie = (key: string) => {
  setCookie(key, "");
};

export const getCookie = (key: string) => {
  return Cookies.get(key);
};
