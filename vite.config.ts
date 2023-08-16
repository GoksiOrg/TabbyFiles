import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [react()],
    server: {
        watch: {
            usePolling: true
        }
    },
    build: {
        outDir: "./src/main/resources/static/",
        rollupOptions: {
            input: "./src/main/ts/index.tsx",
            output: {
                entryFileNames: "app.js"
            }
        }
    },
    publicDir: "./assets"
})
