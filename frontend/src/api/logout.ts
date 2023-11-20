import http from '../helpers/httpService.ts';

export default function logout() {
    return new Promise((resolve, reject) => {
        http.post('/auth/logout').then(resolve).catch(reject);
    });
}
