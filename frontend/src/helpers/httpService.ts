import axios from 'axios';

const http = axios.create({
    timeout: 5000,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
    },
});

const csrfMetaHolder = document.head.querySelector("meta[name='_csrf']");
if (csrfMetaHolder !== null && csrfMetaHolder instanceof HTMLMetaElement) {
    http.defaults.headers.common['X-CSRF-TOKEN'] = csrfMetaHolder.content;
}

export default http;
