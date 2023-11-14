import http from '../helpers/httpService';
import { AxiosResponse } from 'axios';

export interface LoginData {
    username: string;
    password: string;
}

interface LoginResponse {
    continue: string;
}

export default function login(data: LoginData) {
    const queryParam = 'continue';

    const searchParams = new URLSearchParams(window.location.search);
    const continueParam = searchParams.get(queryParam);
    let toAppend = '';
    if (continueParam) {
        toAppend = `?${queryParam}=${continueParam}`;
    }
    return new Promise<AxiosResponse<LoginResponse, any>>((resolve, reject) => {
        http.post('/api/csrf')
            .then(() => {
                http.post<LoginResponse>(`/auth/login${toAppend}`, data)
                    .then(response => {
                        resolve(response);
                    })
                    .catch(reject);
            })
            .catch(reject);
    });
}
