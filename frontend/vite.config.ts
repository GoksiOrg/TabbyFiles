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
        manifest: true,
        outDir: "../src/main/resources/static",
        assetsDir: "",
        rollupOptions: {
            input: "src/index.tsx",
        },
    },
    publicDir: "../assets"
})
