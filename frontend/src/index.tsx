import 'vite/modulepreload-polyfill';
import { createRoot } from 'react-dom/client';
import App from './App.tsx';
import 'bootstrap';
import '../sass/app.scss';

createRoot(document.getElementById('root')!).render(<App />);
