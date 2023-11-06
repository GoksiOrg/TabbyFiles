import { TabbyUser } from '../index.ts';

export function isAdmin(user: TabbyUser) {
    return user.roles.some(role => role.admin);
}

export function getMaxUpload(user: TabbyUser) {
    let currentMax = -1;
    user.roles.forEach(role => {
        if (role.maxUpload > currentMax) currentMax = role.maxUpload;
    });
    return currentMax;
}
