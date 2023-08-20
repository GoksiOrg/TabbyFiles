import http from '../helpers/httpService';

export interface LoginData {
    username: string;
    password: string;
}

export default function login(data: LoginData) {
    return new Promise(reject => {
        http.post('/auth/login', data)
            .then(() => {
                console.log(data);
            })
            .catch(reject);
    });
}
