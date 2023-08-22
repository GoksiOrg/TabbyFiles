import http from '../helpers/httpService';

export interface LoginData {
    username: string;
    password: string;
}

export default function login(data: LoginData) {
    return new Promise((resolve, reject) => {
        http.post('/api/csrf')
            .then(() => {
                http.post('/auth/login', data)
                    .then(response => {
                        window.location = response.request.responseURL;
                        resolve(response);
                    })
                    .catch(reject);
            })
            .catch(reject);
    });
}
