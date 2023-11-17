import { TabbyUser } from '../state/user';

export function getMaxUpload(user: TabbyUser) {
    let currentMax = -1;
    user.roles.forEach(role => {
        if (role.maxUpload > currentMax) currentMax = role.maxUpload;
    });
    return currentMax;
}
