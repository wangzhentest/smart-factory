import Cookies from 'js-cookie'

export function setCookie(key, value, expireHours) {
    const expireTime = new Date().getTime() + expireHours * 60 * 60 * 1000
    Cookies.set(key, value, { expires: new Date(expireTime) })
}
export function getCookie(key, expireSeconds) {
    const value = Cookies.get(key)
    if (!value) {
        return null
    }
    const now = new Date().getTime()
    const expireTime = new Date().getTime() + expireSeconds * 1000
    if (now > expireTime) {
        removeCookie(key)
        return null
    }
    return value
}

export function removeCookie(key) {
    Cookies.remove(key)
}
